package tamrin4.model.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString

public class Profile {
    private String name;
    private String family;
    private String username;
    private String password;
    private boolean active;
    private String accessLevel;
}
