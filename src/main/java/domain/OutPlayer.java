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
public class OutPlayer {
    private Long id;
    private Long playerId;
    private Reason reason;
    private LocalDateTime createdAt;
}
