package ua.ithillel.product;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

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

    public ProductTypes getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDiscount() {
        return discount;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (type != product.type) return false;
        return Objects.equals(id, product.id) && Objects.equals(price, product.price)
                && Objects.equals(discount, product.discount) && Objects.equals(createDate, product.createDate);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + createDate.hashCode();
        return result;
    }
}
