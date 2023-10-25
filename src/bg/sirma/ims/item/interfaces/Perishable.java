package bg.sirma.ims.item.interfaces;

public interface Perishable {
    boolean isPerishable();
    String handleExpiration();
}
