package model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Stadium {
    @Id
    @Generated
    private Long id;
    private String name;
    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
