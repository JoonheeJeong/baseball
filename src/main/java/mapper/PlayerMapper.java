package mapper;

import domain.Player;

import java.util.List;

public interface PlayerMapper {
    void insert(Player player);
    List<Player> selectListByTeamId(Long teamId);
    void updateRetiredById(Long id);
}
