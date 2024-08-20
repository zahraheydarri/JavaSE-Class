package tamrin4.test;


import tamrin4.model.da.PersonDa;

import tamrin4.model.da.ProductDa;
import tamrin4.model.entity.Brand;
import tamrin4.model.entity.Person;
import tamrin4.model.entity.Product;

public class ProductTest {
    public static void main(String[] args) throws Exception{
        Person person = null;
        try (PersonDa personDa = new PersonDa()) {
            person = personDa.findById(1);
        }

        if (person != null) {
            Product product=
                    Product
                            .builder()
                            .productId(1)
                            .productName("Sunscreen")
                            .brand(Brand.Mac)
                            .price(12000)
                            .count(1)
                            .owner(person)
                            .build();

            try (ProductDa productDa = new ProductDa()) {
                productDa.save(product);
            }
        }
    }
}
