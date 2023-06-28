package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum TeamName {
    한화_이글스("한화 이글스"),
    두산_베어스("두산 베어스"),
    롯데_자이언츠("롯데 자이언츠");

    private final String description;
}
