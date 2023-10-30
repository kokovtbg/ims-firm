package bg.sirma.ims;

public enum CommandEnum {
    EXIT(""),
    REGISTER("(username) (password)"),
    LOGIN("(username) (password)"),
    LOGOUT("");

    private String value;

    CommandEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
