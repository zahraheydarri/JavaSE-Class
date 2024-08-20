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
    private int id;
    private String name;
    private Brand brand;
    private double price;
    private int count;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
