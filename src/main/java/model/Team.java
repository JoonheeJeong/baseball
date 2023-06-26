package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Team {
    private Long id;
    private Long stadiumId;
    private String name;
    private LocalDateTime CreatedAt;
}
