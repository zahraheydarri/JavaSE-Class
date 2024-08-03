package products;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Furniture extends NonElectric{
    private int person;


    @Override
    public String toString() {
        return "Furniture{" +
                "Name =" + getName() + "   " +
                "Price =" + getPrice() + "   " +
                "Weight =" + getWeight() + "   " +
                "Person =" + person +
                '}';
    }
}
