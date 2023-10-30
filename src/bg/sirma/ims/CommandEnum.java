package bg.sirma.ims;

public enum CommandEnum {
    EXIT(""),
    REGISTER("(username) (password)"),
    LOGIN("(username) (password)"),
    LOGOUT(""),
    ITEM_ADD_KILOGRAM("(name)|(manufacturer)|(description)|(category)|(quantity)|(price)"
            + System.lineSeparator() +
            "{optional...[|(expiration-date)]}"),
    ITEM_ADD_PIECE("(name)|(manufacturer)|(description)|(category)|(quantity)|(price)"
            + System.lineSeparator() +
            "{optional...[|(`grocery`)|(expiration-date)] optional...[|(`fragile`)|(weight)] optional...[|(`electronic`)|(warranty)]}");

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
