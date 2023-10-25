package bg.sirma.ims.item.interfaces;

import java.math.BigDecimal;

public interface Sellable {
    BigDecimal getPrice();
    void setPrice(BigDecimal price);
}
