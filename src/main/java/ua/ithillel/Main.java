package ua.ithillel;

import ua.ithillel.product.Product;
import ua.ithillel.product.ProductList;
import ua.ithillel.product.ProductNotFoundException;

import static ua.ithillel.product.ProductTypes.*;

public class Main {

    public static void main(String[] args) {
        Product book1 = new Product(BOOK, 456987, 753.25, 15);
        Product book2 = new Product(BOOK, 569874, 157.52);
        Product book3 = new Product(BOOK, 698745, 537.50, 10);
        Product book4 = new Product(BOOK, 987456, 175.20);
        Product book5 = new Product(BOOK, 874569, 253.25);

        Product toy1 = new Product(TOY, 789321, 159.60);
        Product toy2 = new Product(TOY, 893217, 591.55, 10);
        Product toy3 = new Product(TOY, 932178, 59.20);
        Product toy4 = new Product(TOY, 321789, 915.06, 20);
        Product toy5 = new Product(TOY, 217893, 95.60);

        ProductList.getProductList().forEach(System.out::println);

        System.out.println(ProductList.getFilteredList(BOOK, 250.0));

        System.out.println(ProductList.getProductsWithDiscount(BOOK));

        try {
            System.out.println(ProductList.getCheapestProduct(BOOK));
            System.out.println(ProductList.getCheapestProduct(OTHER));
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(ProductList.getThreeLastAddedProducts());

        ProductList.calcTotalCostProducts(2022, BOOK, 550.0);

        System.out.println(ProductList.getMapOfProductLists());
    }
}