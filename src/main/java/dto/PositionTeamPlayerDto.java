package dto;

import domain.Position;
import lombok.*;

import java.util.LinkedHashMap;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PositionTeamPlayerDto {
    private List<String> teamNames;
    private LinkedHashMap<Position, List<String>> positionToPlayers;

    @Override
    public String toString() {
        final String format = "%8s\t".repeat(1 + teamNames.size());
        teamNames.add(0, "포지션");
        StringBuilder sb = new StringBuilder("'\n");
        sb.append(String.format(format, teamNames.toArray()));
        positionToPlayers.forEach((position, players) -> {
            players.add(0, position.getDescription());
            sb.append('\n')
              .append(String.format(format, players.toArray()));
        });
        return sb.toString();
    }
}
