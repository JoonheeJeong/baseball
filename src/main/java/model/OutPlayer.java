package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OutPlayer {
    private Long id;
    private Long playerId;
    private Reason reason;
    private LocalDateTime createdAt;
}
