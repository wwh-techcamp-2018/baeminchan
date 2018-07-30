package codesquad.domain;


public enum Regrex{
    PASSWORD("^[a-zA-Z0-9]*$"),
    NAME("^[가-힣]*$"),
    PHONE_NUMBER("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");

    private String regrexStr;

    Regrex(String regrexStr){
        this.regrexStr = regrexStr;
    }
    public String getRegrexStr() {
        return regrexStr;
    }
}