package ua.ithillel.product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductList {

    private static final List<Product> productList = new ArrayList<>();

    private ProductList() {
    }

    public static List<Product> getFilteredList(ProductTypes productType, Double maxPrice) {
        System.out.println("---------\n1.2. Filtered product list:");
        return productList.stream()
                .filter(e -> e.getType().equals(productType) && e.getPrice() >= maxPrice)
                .collect(Collectors.toList());
    }

    public static List<Product> getProductsWithDiscount(ProductTypes productType) {
        System.out.println("---------\n2.2. Products with discount:");
        return productList.stream()
                .filter(e -> e.getType().equals(productType) && e.getDiscount() != null)
                .collect(Collectors.toList());
    }

    public static Product getCheapestProduct(ProductTypes productType) {
        System.out.println("---------\n3.1. Cheapest product:");
        return productList.stream()
                .filter(e -> e.getType().equals(productType))
                .min(Comparator.comparingDouble(Product::getPrice))
                .orElseThrow(() -> new ProductNotFoundException("Product [category: " + productType + "] not found"));
    }

    public static List<Product> getThreeLastAddedProducts() {
        System.out.println("---------\n4.2. Three Last Added Products:");
        return productList.stream()
                .filter(i -> {
                    int index = productList.indexOf(i);
                    int listSize = productList.size();
                    return index < listSize && index >= listSize - 3;
                })
                .collect(Collectors.toList());
    }

    public static void calcTotalCostProducts(int year, ProductTypes productType, Double maxPrice) {
        System.out.println("---------\n5.2. Calculate total cost of products:");
        double sum = productList.stream()
                .filter(e -> e.getCreateDate().getYear() == year)
                .filter(e -> e.getType().equals(productType) && e.getPrice() <= maxPrice)
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.printf(Locale.US, "%,.2f$%n", sum);
    }

    public static Map<String, List<Product>> getMapOfProductLists() {
        System.out.println("---------\n6.2. Map of product lists:");
        return new ProductMap();
    }

    public static List<Product> getProductList() {
        return productList;
    }

    static void addProduct(Product product) {
        productList.add(product);
    }

    static Product getProduct(Product product) {
        int index = productList.indexOf(product);
        return index >= 0 ? productList.get(index) : null;
    }

    public static List<Product> filterProductListByType(ProductTypes productType) {
        return productList.stream()
                .filter(e -> e.getType().equals(productType))
                .collect(Collectors.toList());
    }
}
