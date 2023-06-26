package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Stadium {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
