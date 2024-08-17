package tamrin3.test;

import tamrin3.model.da.PersonDa;

import java.sql.SQLException;

public class PersonTest {
    public static void main(String[] args) throws Exception {
//        try with resource
        try(PersonDa personDa = new PersonDa()){
            personDa.save(null);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }


    }
}

