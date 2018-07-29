package codesquad.domain.user;


public class AdminUser extends User {

    @Override
    public boolean isAdmin() {
        return true;
    }
}
