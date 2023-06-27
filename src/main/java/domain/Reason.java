package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum Reason {
    RETIRE("은퇴"),
    INJURED("부상"),
    CRIMINAL("범죄");

    private final String description;
}
