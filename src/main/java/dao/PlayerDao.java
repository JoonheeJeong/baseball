package dao;

import domain.Player;
import domain.Position;
import dto.PositionTeamPlayerDto;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import mapper.PlayerMapper;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Log4j2
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

    public PositionTeamPlayerDto selectListForEachTeamByPosition() throws SQLException {
        List<String> teamNames = new LinkedList<>();
        LinkedHashMap<Position, List<String>> positionToPlayers = new LinkedHashMap<>();

        @Cleanup Connection conn = session.getConnection();
        @Cleanup CallableStatement callStmt = conn.prepareCall("{call select_position_team_player}");
        callStmt.execute();
        for (int i = 0; i < 3; ++i) {
            callStmt.getResultSet();
            callStmt.getMoreResults();
        }
        @Cleanup ResultSet resultSet = callStmt.getResultSet();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnSize = metaData.getColumnCount();
        for (int i = 2; i <= columnSize; ++i)
            teamNames.add(metaData.getColumnName(i));
        while (resultSet.next()) {
            String positionName = resultSet.getString(1);
            Position position = Position.getByDescription(positionName);
            List<String> playerNames = new LinkedList<>();
            for (int i = 2; i <= columnSize; ++i) {
                String playerName = resultSet.getString(i);
                playerNames.add(playerName);
            }
            positionToPlayers.put(position, playerNames);
        }
        return PositionTeamPlayerDto.builder()
                .teamNames(teamNames)
                .positionToPlayers(positionToPlayers)
                .build();
    }
}
