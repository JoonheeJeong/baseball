package mapper;

import domain.OutPlayer;
import dto.OutPlayerResponseDto;

import java.util.List;

public interface OutPlayerMapper {
    void insert(OutPlayer outPlayer);

    List<OutPlayerResponseDto> selectAll();

}
