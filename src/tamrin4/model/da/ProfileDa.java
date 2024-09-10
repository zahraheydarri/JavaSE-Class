package tamrin4.model.da;

import tamrin4.model.entity.Profile;
import tamrin4.model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProfileDa implements AutoCloseable{
    private Connection connection;
    private PreparedStatement preparedStatement;


    public void remove(String username) throws SQLException {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement("UPDATE PROFILE_TBL SET ACTIVE=0 WHERE USERNAME=?");
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
    }

    public Optional<Profile> findByUsernameAndPassword(String username, String password) throws SQLException {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM PROFILE_TBL WHERE USERNAME=? AND PASSWORD=?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        Optional<Profile> optionalProfile = Optional.empty();
        if (resultSet.next()) {
            Profile profile =
                    Profile
                            .builder()
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .accessLevel(resultSet.getString("ACCESS_LEVEL"))
                            .active(resultSet.getBoolean("ACTIVE"))
                            .build();
            optionalProfile = Optional.of(profile);
        }
        return optionalProfile;
    }





    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
