package bg.sirma.ims.model.item.interfaces;

import java.math.BigDecimal;

public interface Sellable {
    BigDecimal getPrice();
    void setPrice(BigDecimal price);
}
