package by.ustsinovich.fcadhack.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    @Serial
    private static final long serialVersionUID = 4926097364170454514L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private boolean isLoggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Token token = (Token) o;

        return isLoggedOut == token.isLoggedOut &&
                Objects.equals(id, token.id) &&
                Objects.equals(accessToken, token.accessToken) &&
                Objects.equals(refreshToken, token.refreshToken) &&
                Objects.equals(user, token.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accessToken, refreshToken, isLoggedOut, user);
    }

}
