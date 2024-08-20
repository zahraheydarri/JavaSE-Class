package tamrin4.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

public class Product {
    private int productId;
    private String productName;
    private Brand brand;
    private double price;
    private int count;
    private Person owner;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
