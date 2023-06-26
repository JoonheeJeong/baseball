package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Player {
    private Long id;
    private Long teamId;
    private String name;
    private String position;
    private LocalDateTime createdAt;
}
