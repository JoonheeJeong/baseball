package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
@ToString
public enum Position {
    P("투수"),
    C("포수"),
    FB("1루수"),
    SB("2루수"),
    TB("3루수"),
    SS("유격수"),
    LF("좌익수"),
    CF("중견수"),
    RF("우익수");

    private final String description;

    public static Optional<Position> getByDescription(String description) {
        return Arrays.stream(values())
                .filter(position -> position.getDescription().equals(description))
                .findAny();
    }
}
