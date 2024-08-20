package tamrin4.test;

import tamrin4.model.da.PersonDa;
import tamrin4.model.entity.Person;

public class PersonTest {
    public static void main(String[] args) throws Exception{
        Person person = Person
                .builder()
                .personId(1)
                .personName("Nazanin")
                .family("hosseini")
                .build();

        try (PersonDa personDa = new PersonDa()){
//           personDa.save(person);
//          personDa.edit(person);
//           personDa.remove(2);
//           System.out.println(personDa.findAll());
//           System.out.println(personDa.findById(2));
//           System.out.println(personDa.findByFamily("Heydari"));
        }

    }
}
