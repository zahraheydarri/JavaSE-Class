package tamrin3.model.entity;


import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

public class Person {
    private int id;
    private String name;
    private String family;
    private Gender gender;
    private LocalDate birthDate;
    private City city;
    private boolean seSkill;
    private boolean eeSkill;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

