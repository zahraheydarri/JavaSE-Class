package tamrin4.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

public class Person {
    private int personId;
    private String personName;
    private String family;
    private List<Product> productList;
}
