package ua.ithillel.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@EqualsAndHashCode
public class Product {

    private final ProductTypes type;
    private final Integer id;
    private Double price;
    private Integer discount;
    private final LocalDateTime createDate = LocalDateTime.now();

    public Product(ProductTypes type, Integer id, Double price) {
        this.type = type;
        this.id = id;
        this.price = price;
        ProductList.addProduct(this);
    }

    public Product(ProductTypes type, Integer id, Double price, Integer discount) {
        this(type, id, price);
        this.discount = discount;
        setDiscount(discount);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDiscount(Integer discount) {
        Double newPrice = price - (price * discount / 100);
        Product prod = ProductList.getProduct(this);
        if (prod != null) prod.setPrice(newPrice);
    }

    @Override
    public String toString() {
        String datePattern = ", date='%5$td-%5$tm-%5$tY'}";
        String withDiscount = "{type=%s, id=%d, price=%.2f$, discount=%d%%" + datePattern;
        String withoutDiscount = "{type=%s, id=%d, price=%.2f$" + datePattern;
        String formatPattern = discount != null ? withDiscount : withoutDiscount;
        return String.format(Locale.US, formatPattern, type, id, price, discount, createDate);
    }
}
