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
            "{optional...[|(`grocery`)|(expiration-date)] optional...[|(`fragile`)|(weight)] optional...[|(`electronic`)|(warranty)]}"),
    ITEM_REMOVE("(id)"),
    ITEM_UPDATE("(id) (quantity)"),
    PAYMENT_ADD_PAYPAL("(username from PayPal) (password from PayPal)"),
    PAYMENT_ADD_CARD("(card number)"),
    ORDER_ADD_TO_CART("(item id) (quantity)"),
    ORDER_TOTAL_COST("Total cost of cart"),
    ORDER_DO_ORDER("...[(pin)]");

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
