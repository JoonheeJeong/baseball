package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Long id;
    private Long stadiumId;
    private String name;
    private LocalDateTime CreatedAt;
}
