package tamrin3;

import tamrin3.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "javase",
                "java123"
        );

        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from product_tbl order by id"
        );

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Product> productList = new ArrayList<Product>();

        while (resultSet.next()) {
            Product product =
                    Product
                            .builder()
                            .id(resultSet.getInt("id"))
                            .productName(resultSet.getString("productName"))
                            .price(resultSet.getInt("price"))
                            .count(resultSet.getInt("count"))
                            .build();
            productList.add(product);
        }
        System.out.println(productList);
        preparedStatement.close();
        connection.close();
    }
}
