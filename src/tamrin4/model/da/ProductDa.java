package tamrin4.model.da;

import tamrin4.model.entity.Brand;
import tamrin4.model.entity.Product;
import tamrin4.model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDa implements DataAccess<Product , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;




    @Override
    public void save(Product product) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT PRODUCT_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        product.setProductId(resultSet.getInt("NEXT_ID"));

        preparedStatement=connection.prepareStatement(
                "INSERT INTO PRODUCT_TBL VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, product.getProductId());
        preparedStatement.setString(2, product.getProductName());
        preparedStatement.setString(3,product.getBrand().name());
        preparedStatement.setDouble(4, product.getPrice());
        preparedStatement.setInt(5,product.getCount());
        preparedStatement.execute();
    }

    @Override
    public void edit(Product product) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "update PRODUCT_TBL set NAME=? , FAMILY=?, PRICE=?, COUNT=? where PRODUCT_ID=?"
        );
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setString(2, product.getBrand().name());
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setInt(4, product.getCount());
        preparedStatement.setInt(5, product.getProductId());
        preparedStatement.execute();

    }

    @Override
    public void remove(Integer id) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PRODUCT_TBL WHERE PRODUCT_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Product> findAll() throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> productList = new ArrayList<>();

        while (resultSet.next()) {
            Product product =
                    Product

                            .builder()
                            .productId(resultSet.getInt("ID"))
                            .productName(resultSet.getString("NAME"))
                            .brand(Brand.valueOf(resultSet.getString("BRAND")))
                            .price(resultSet.getDouble("PRICE"))
                            .count(resultSet.getInt("COUNT"))
                            //.owner()
                            .build();
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product findById(Integer id) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE PRODUCT_ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = null;
        if (resultSet.next()) {
            product=
                    Product
                            .builder()
                            .productId(resultSet.getInt("ID"))
                            .productName(resultSet.getString("NAME"))
                            .brand(Brand.valueOf(resultSet.getString("BRAND")))
                            .price(resultSet.getDouble("PRICE"))
                            .count(resultSet.getInt("COUNT"))
                            //.owner()
                            .build();
        }
        return product;
    }



    public List<Product> findByBrand(Brand brand) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PRODUCT_TBL WHERE BRAND=?"
        );
        preparedStatement.setString(1, brand.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> productList = new ArrayList<>();
        while (resultSet.next()) {
            Product product=
                    Product
                            .builder()
                            .productId(resultSet.getInt("ID"))
                            .productName(resultSet.getString("NAME"))
                            .brand(Brand.valueOf(resultSet.getString("BRAND")))
                            .price(resultSet.getDouble("PRICE"))
                            .count(resultSet.getInt("COUNT"))
                            //.owner()
                            .build();
            productList.add(product);
        }
        return productList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
