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
public class OutPlayer {
    private BigInteger id;
    private BigInteger playerId;
    private Reason reason;
    private LocalDateTime createdAt;
}
