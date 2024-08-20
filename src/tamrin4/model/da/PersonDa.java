package tamrin4.model.da;

import tamrin4.model.entity.Person;
import tamrin4.model.utils.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonDa implements DataAccess<Person , Integer>{
    private Connection connection;
    private PreparedStatement preparedStatement;




    @Override
    public void save(Person person) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT PERSON1_SEQ.nextval AS NEXT_ID FROM dual"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person.setPersonId(resultSet.getInt("NEXT_ID"));

        preparedStatement=connection.prepareStatement(
                "INSERT INTO PERSON_TBL VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, person.getPersonId());
        preparedStatement.setString(2, person.getPersonName());
        preparedStatement.setString(3,person.getFamily());
        preparedStatement.execute();
    }

    @Override
    public void edit(Person person) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON_TBL SET NAME=? , FAMILY=? WHERE PERSON_ID=?"
        );
        preparedStatement.setString(1, person.getPersonName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setInt(3, person.getPersonId());
        preparedStatement.execute();

    }

    @Override
    public void remove(Integer id) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON_TBL WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Person> findAll() throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .personId(resultSet.getInt("PERSON_ID"))
                            .personName(resultSet.getString("PERSON_NAME"))
                            .family(resultSet.getString("FAMILY"))
                            //.productList()
                            .build();
            personList.add(person);
        }
        return personList;
    }

    @Override
    public Person findById(Integer id) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON_TBL WHERE PERSON_ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Person person = null;
        if (resultSet.next()) {
            person =
                    Person
                            .builder()
                            .personId(resultSet.getInt("PERSON_ID"))
                            .personName(resultSet.getString("PERSON_NAME"))
                            .family(resultSet.getString("FAMILY"))
                            //.productList()
                            .build();
        }
        return person;
    }

    public List<Person> findByFamily(String family) throws Exception {
        connection = JdbcProvider.getJdbc().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON_TBL WHERE FAMILY=?"
        );
        preparedStatement.setString(1, family);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Person> personList = new ArrayList<>();
        while (resultSet.next()) {
            Person person=
                    Person
                            .builder()
                            .personId(resultSet.getInt("PERSON_ID"))
                            .personName(resultSet.getString("PERSON_NAME"))
                            .family(resultSet.getString("FAMILY"))
                            //.productList()
                            .build();
            personList.add(person);
        }
        return personList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
