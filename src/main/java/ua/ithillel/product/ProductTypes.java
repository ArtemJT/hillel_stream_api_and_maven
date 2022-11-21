package ua.ithillel.product;

public enum ProductTypes {
    BOOK("Book"), TOY("Toy"), OTHER("Other");

    private final String name;

    ProductTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
