package tamrin3.model.da;

// CREATE TABLE PERSON(
// ID NUMBER PRIMARY KEY,
// NAME NVARCHAR2(30),
// FAMILY NVARCHAR2(30),
// GENDER NVARCHAR2(6),
// BIRTH_DATE DATE,
// CITY NVARCHAR2(20),
// SE_SKILL NUMBER(1),
// EE_SKILL NUMBER(1)
// );

// CREATE SEQUENCE PERSON_SEQ START WITH 1 INCREMENT BY 1;


import tamrin3.model.entity.City;
import tamrin3.model.entity.Gender;
import tamrin3.model.entity.Person;
import tamrin3.model.utils.JdbcProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDa implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    JdbcProvider jdbcProvider = new JdbcProvider();


    public PersonDa() throws SQLException {
        connection = jdbcProvider.getConnection();
    }

    public void save(Person person) throws SQLException {
        person.setId(jdbcProvider.getNextId("PERSON_SEQ"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PERSON VALUES(?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setString(4, person.getGender().name());
        preparedStatement.setDate(5, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(6, person.getCity().name());
        preparedStatement.setBoolean(7, person.isSeSkill());
        preparedStatement.setBoolean(8, person.isEeSkill());
        preparedStatement.execute();
    }

    public void edit(Person person) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "UPDATE PERSON SET NAME=?, FAMILY=?, GENDER=?, BIRTH_DATE=?, CITY=?, SE_SKILL=?, EE_SKILL=? WHERE ID=?"
        );
        preparedStatement.setString(1, person.getName());
        preparedStatement.setString(2, person.getFamily());
        preparedStatement.setString(3, person.getGender().name());
        preparedStatement.setDate(4, Date.valueOf(person.getBirthDate()));
        preparedStatement.setString(5, person.getCity().name());
        preparedStatement.setBoolean(6, person.isSeSkill());
        preparedStatement.setBoolean(7, person.isEeSkill());
        preparedStatement.setInt(8, person.getId());
        preparedStatement.execute();
    }

    public void remove(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PERSON WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Person> findAll() throws SQLException {
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM PERSON ORDER BY ID"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Person> personList = new ArrayList<>();

        while (resultSet.next()) {
            Person person =
                    Person
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .name(resultSet.getString("NAME"))
                            .family(resultSet.getString("FAMILY"))
                            .gender(Gender.valueOf(resultSet.getString("GENDER")))
                            .birthDate(resultSet.getDate("BIRTH_DATE").toLocalDate())
                            .city(City.valueOf(resultSet.getString("CITY")))
                            .seSkill(resultSet.getBoolean("SE_SKILL"))
                            .eeSkill(resultSet.getBoolean("EE_SKILL"))
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
