package tamrin1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> productNames = new ArrayList<>();
        ArrayList<Integer> productPrices = new ArrayList<>();
        int option;
        int sum = 0;
        int x=0;

        do {
            System.out.println("1)Add Product");
            System.out.println("2)Sum Price");
            System.out.println("3)Print Product");
            System.out.println("0)Exit");

            System.out.print("Enter Option : ");
            option = Integer.parseInt(sc.nextLine());
            System.out.println("-----------------------------------------------");

            switch (option) {
                case 1: //Add product
                    System.out.print("Enter Product Name : ");
                    String n=sc.nextLine();
                    productNames.add(n);
                    System.out.print("Enter Product Price : ");
                    int p=Integer.parseInt(sc.nextLine());
                    productPrices.add(p);
                    sum+=productPrices.get(x);
                    System.out.println("Product Added Successfully");
                    x+=1;
                    break;

                case 2: //Sum Price
                    System.out.println("Sum Price : " + sum);
                    break;
                case 3: //Print Product
                    System.out.println("Product Name :" + productNames);
                    break;
                case 0: //Exit
                    break;
            }
        }while (option != 0);
    }
}
