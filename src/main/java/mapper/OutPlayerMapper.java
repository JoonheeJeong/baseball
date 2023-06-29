package mapper;

import domain.OutPlayer;
import dto.OutPlayerResponseDTO;

import java.util.List;

public interface OutPlayerMapper {
    void insert(OutPlayer outPlayer);
    List<OutPlayerResponseDTO> selectAll();
}
