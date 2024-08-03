package products;

public class Main {
    public static void main(String[] args) {
        Dell dell = new Dell();

        dell.setName("Dell");
        dell.setPrice(1200);
        dell.setVolt(12);
        dell.setCpu("intel i5");
        dell.setRam(20);
        dell.setTouch(true);

        Asus asus =new Asus();
        asus.setName("Asus");
        asus.setPrice(3000);
        asus.setVolt(12);
        asus.setCpu("intel i5");
        asus.setRam(20);
        asus.setBattery(22);

        Furniture furniture = new Furniture();
        furniture.setName("Furniture");
        furniture.setPrice(1000);
        furniture.setWeight(44);
        furniture.setPerson(12);


        System.out.println(asus);
        System.out.println(dell);
        System.out.println(furniture);


    }
}
