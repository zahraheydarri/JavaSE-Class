package tamrin4.model.utils;

import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcProvider {
    @Getter
    private final static JdbcProvider jdbcProvider = new JdbcProvider();
    private final static BasicDataSource basicDataSource = new BasicDataSource();

    private JdbcProvider() {

    }
//    public static JdbcProvider getJdbc() {return jdbcProvider;}

    public Connection getConnection() throws SQLException {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("javase");
        basicDataSource.setPassword("java123");
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        return basicDataSource.getConnection();
    }
}
