package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Long id;
    private Long teamId;
    private String name;
    private String position;
    private LocalDateTime createdAt;
}
