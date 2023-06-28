package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
@Setter
public class PositionTeamPlayer {
    private Position position;
    private TeamName 한화_이글스;
    private TeamName 두산_베어스;
    private TeamName 롯데_자이언츠;

    @Override
    public String toString() {
        StringBuilder formatStringBuilder = new StringBuilder("%4s");
        Field[] fields = PositionTeamPlayer.class.getDeclaredFields();
        int numOfTeams = fields.length - 1;
        formatStringBuilder.append(" %10s".repeat(numOfTeams));
        String[] args = new String[numOfTeams + 1];
        args[0] = position.getDescription();
        IntStream.range(1, numOfTeams + 1)
                .forEach(i -> args[i] = TeamName.valueOf(fields[i].getName()).getDescription());
        return String.format(formatStringBuilder.toString(), (Object []) args);
    }
}
