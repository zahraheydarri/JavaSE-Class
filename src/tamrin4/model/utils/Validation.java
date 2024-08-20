package tamrin4.model.utils;

import java.util.regex.Pattern;

public class Validation {
    public String nameValidator(String name) throws Exception{
        if (Pattern.matches("^[a-zA-Z\\s]{3,20}+", name)) {
            return name;
        }else {
            throw new Exception("Invalid name");
        }
    }

    public String familyValidator(String family) throws Exception{
        if (Pattern.matches("^[a-zA-Z\\s]{3,20}+", family)) {
            return family;
        }else {
            throw new Exception("Invalid name");
        }
    }
}
