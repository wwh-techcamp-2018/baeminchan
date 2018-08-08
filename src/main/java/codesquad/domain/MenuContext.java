package codesquad.domain;

public enum MenuContext {

    MAIN_CATEGORY(1),
    BEST_CATEGORY(46);

    private long id;

    MenuContext(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }
}