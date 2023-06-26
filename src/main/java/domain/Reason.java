package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Reason {
    RETIRE("은퇴"),
    INJURED("부상"),
    CRIMINAL("범죄");

    private final String description;
}
