package codesquad.domain;


public class AdminUser extends User {

    @Override
    public boolean isAdmin() {
        return true;
    }
}
