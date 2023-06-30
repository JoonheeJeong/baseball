package dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TeamResponseDto {
    private String teamName;
    private LocalDateTime teamCreatedAt;
    private String stadiumName;
    private LocalDateTime stadiumCreatedAt;
}
