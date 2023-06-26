package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private BigInteger id;
    private BigInteger stadiumId;
    private String name;
    private LocalDateTime CreatedAt;
}
