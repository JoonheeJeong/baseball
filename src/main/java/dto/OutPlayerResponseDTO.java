package dto;

import domain.Position;
import domain.Reason;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OutPlayerResponseDTO {
    private Long id;
    private String name;
    private Position position;
    private Reason reason;
    private LocalDateTime createdAt;
}
