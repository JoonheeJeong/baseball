package domain;

import lombok.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.IntStream;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PositionTeamPlayer {

    private static final int NUM_TEAMS = 3;

    private Position position;
    private String 한화_이글스;
    private String 두산_베어스;
    private String 롯데_자이언츠;

    @Override
    public String toString() {
        String[] args = new String[NUM_TEAMS + 1];
        args[0] = position.getDescription();
        args[1] = 한화_이글스;
        args[2] = 두산_베어스;
        args[3] = 롯데_자이언츠;
        return String.format("%4s" + "%10s".repeat(NUM_TEAMS), (Object []) args);
    }
}
