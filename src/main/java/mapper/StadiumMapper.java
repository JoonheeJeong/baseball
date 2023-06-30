package mapper;

import domain.Stadium;

import java.util.List;

public interface StadiumMapper {
    void insert(Stadium stadium);
    List<Stadium> selectAll();
}
