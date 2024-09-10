package tamrin4.model.bl;


import tamrin4.controller.exception.UserNotFoundException;
import tamrin4.model.da.ProfileDa;
import tamrin4.model.entity.Profile;

import java.util.Optional;

public class ProfileBl {
    public static void remove(String username) throws Exception{
        try (ProfileDa profileDa = new ProfileDa()){
            profileDa.remove(username);
        }
    }

    public static Profile findByUsernameAndPassword(String username, String password) throws Exception{
        try (ProfileDa profileDa = new ProfileDa()){
            Optional<Profile> profile = profileDa.findByUsernameAndPassword(username, password);
            if(profile.isPresent()){
                return profile.get();
            }else {
                throw new UserNotFoundException();
            }
        }
    }
}
