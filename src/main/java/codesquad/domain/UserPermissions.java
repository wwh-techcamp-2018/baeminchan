package codesquad.domain;

import java.util.Arrays;

public enum UserPermissions {
    ADMIN(0),
    NORMAL(1);

    private long permissions;

    UserPermissions(long permissions) {
        this.permissions = permissions;
    }

    public UserPermissions valueOf(long permissions) {
        return Arrays.stream(UserPermissions.values())
                .filter(p -> p.permissions == permissions)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public long getPermissions() {
        return permissions;
    }
}
