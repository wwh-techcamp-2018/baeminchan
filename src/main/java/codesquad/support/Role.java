package codesquad.support;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"), DEFAULT("DEFAULT");
    private String roleName;
    Role(String roleName) {
        this.roleName = roleName;
    }
}
