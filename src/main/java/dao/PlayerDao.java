package dao;

import domain.Player;
import domain.Position;
import domain.TeamName;
import lombok.Cleanup;
import mapper.PlayerMapper;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerDao extends AbstractMybatisDao {

    private static final PlayerDao INSTANCE = new PlayerDao();

    private PlayerDao() {
    }

    public static PlayerDao getInstance() {
        return INSTANCE;
    }

    public void insert(Player player, boolean newSession) {
        if (newSession)
            manageNewSession();

        PlayerMapper mapper = session.getMapper(PlayerMapper.class);
        mapper.insert(player);
    }

    public List<Player> selectListByTeamId(Long teamId) {
        PlayerMapper mapper = session.getMapper(PlayerMapper.class);
        return mapper.selectListByTeamId(teamId);
    }

    public Map<Position, Map<TeamName, String>> selectListForEachTeamByPosition() throws SQLException {
        Map<Position, Map<TeamName, String>> ret = new HashMap<>();
        Arrays.stream(Position.values()).forEach(position -> ret.put(position, new HashMap<>()));

        @Cleanup Connection conn = session.getConnection();
        @Cleanup CallableStatement callStmt = conn.prepareCall("{call select_position_team_player()}");
        @Cleanup ResultSet resultSet = callStmt.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int size = metaData.getColumnCount();
        while (resultSet.next()) {
            String positionName = resultSet.getString(1);
            Position position = Position.getByDescription(positionName);
            Map<TeamName, String> teamToPlayerMap = new HashMap<>();
            for (int i = 2; i <= size; ++i) {
                TeamName teamName = TeamName.valueOf(metaData.getColumnName(i));
                String playerName = resultSet.getString(i);
                teamToPlayerMap.put(teamName, playerName);
            }
            ret.put(position, teamToPlayerMap);
        }
        return ret;
    }
}
