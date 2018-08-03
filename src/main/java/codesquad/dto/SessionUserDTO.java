package codesquad.dto;

import codesquad.domain.User;
import lombok.Data;

@Data
public class SessionUserDTO {
    private Long uid;
    private boolean isAdmin;

    private SessionUserDTO(Long uid, boolean isAdmin) {
        this.uid = uid;
        this.isAdmin = isAdmin;
    }

    public SessionUserDTO() {
    }

    public static SessionUserDTO fromUser(User user) {
        return new SessionUserDTO(user.getUid(), user.isAdmin());
    }
}
