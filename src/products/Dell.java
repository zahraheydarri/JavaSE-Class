package products;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dell extends LapTop{
    private boolean touch;


    @Override
    public String toString() {
        return "Dell{" +
                "Name =" + getName() + "   " +
                "Price =" + getPrice() + "   " +
                "Volt =" + getVolt() + "   " +
                "Cpu =" + getCpu() + "   " +
                "Ram =" + getRam() + "   " +
                "Touch =" + touch +
                '}';
    }
}
