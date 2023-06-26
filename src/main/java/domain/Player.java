package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private BigInteger id;
    private BigInteger teamId;
    private String name;
    private String position;
    private LocalDateTime createdAt;
}
