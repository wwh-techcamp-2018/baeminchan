package support.domain;

import javax.persistence.Entity;

public enum Role {
    ADMIN("관리자"),
    DEFAULT("고객");

    private String level;

    Role(String level) {
        this.level = level;
    }
}
