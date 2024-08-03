package products;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Asus extends LapTop{
    private int battery;


    @Override
    public String toString() {
        return "Asus{" +
                "Name =" + getName() + "   " +
                "Price =" + getPrice() + "   " +
                "Volt =" + getVolt() + "   " +
                "Cpu =" + getCpu() +  "   "  +
                "Ram =" + getRam() + "   " +
                "battery =" + battery +
                '}';
    }
}
