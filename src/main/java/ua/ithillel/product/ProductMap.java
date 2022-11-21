package ua.ithillel.product;

import java.util.*;
import java.util.stream.Collectors;

class ProductMap extends AbstractMap<String, List<Product>> {

    private final Map<String, List<Product>> productMap;

    public ProductMap() {
        productMap = Arrays.stream(ProductTypes.values())
                .filter(e -> !ProductList.filterProductListByType(e).isEmpty())
                .collect(Collectors.toMap(ProductTypes::getName, ProductList::filterProductListByType));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        productMap.forEach((key, value) -> {
            StringJoiner sj = new StringJoiner(",\n", "{\n\t", "\n}\n");
            sj.add('"' + key + "\",\n\t" + value.toString());
            sb.append(sj);
        });
        return sb.toString();
    }

    @Override
    public Set<Entry<String, List<Product>>> entrySet() {
        return productMap.entrySet();
    }
}
