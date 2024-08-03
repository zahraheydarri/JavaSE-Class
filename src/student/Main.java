package student;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<StudentInfo> list = new ArrayList<>();
        StudentInfo std1 = new StudentInfo();
        int Option =0;
        float sum = 0;

        do {
            System.out.println("1)Add Student");
            System.out.println("2)Age Average");
            System.out.println("3)Count Student");
            System.out.println("4)Print All Students");
            System.out.println("0)Exit");

            System.out.print("Enter your choice:  ");
            Option = Integer.parseInt(sc.nextLine());
            System.out.println("-----------------------------------------------");

            switch (Option) {
                case 1: //Add Student
                    System.out.print("Enter Student Name:  ");
                    std1.Name=sc.nextLine();
                    System.out.print("Enter Student Age:  ");
                    std1.Age= Integer.parseInt(sc.nextLine());
                    list.add(std1);
                    sum+= std1.Age;

                    System.out.println("Student added successfully");
                    break;
                case 2: //Age Average
                    System.out.println("Age Average: " +sum/list.size());
                    break;
                case 3: //Count Student
                    System.out.println(list.size());
                    break;
                case 4: //Print All
                    System.out.println(std1.Name+" "+std1.Age);
                    break;
                case 0: //Exit
                    break;
                default:
                    System.out.println("Invalid Option !!!");
            }


        }while(Option != 0);
    }
}
