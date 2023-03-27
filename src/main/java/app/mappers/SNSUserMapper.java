package app.mappers;



import app.domain.model.SNSUser;
import app.mappers.dto.SNSUserDTO;
import pt.isep.lei.esoft.auth.domain.model.User;
import pt.isep.lei.esoft.auth.mappers.dto.UserDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SNSUserMapper {

    public SNSUserDTO toDTO(SNSUser u) {
        return new SNSUserDTO(u.getSNSNumber());
    }

    public List<SNSUserDTO> toDTO(List<SNSUser> SNSusers) {
        List<SNSUserDTO> SNSusersDTO = new ArrayList();
        Iterator var3 = SNSusers.iterator();

        while(var3.hasNext()) {
            SNSUser user = (SNSUser) var3.next();
            SNSusersDTO.add(this.toDTO(user));
        }
        return SNSusersDTO;
    }
}
