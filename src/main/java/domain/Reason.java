package domain;

import exception.IllegalPositionDescriptionException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@AllArgsConstructor
@Getter
@ToString
public enum Reason {
    RETIRE("은퇴"),
    INJURED("부상"),
    CRIMINAL("범죄");

    private final String description;

    public static Reason getByDescription(String description) throws IllegalPositionDescriptionException {
        return Arrays.stream(values())
                .filter(position -> position.getDescription().equals(description))
                .findAny()
                .orElseThrow(() -> new IllegalPositionDescriptionException("존재하지 않는 사유: " + description));
    }
}
