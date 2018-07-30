package codesquad.domain.user;


public class AdminUser extends User {
    public AdminUser() {
        super();
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
