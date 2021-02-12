package eg.gov.iti.server.db.helpers.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserAuthDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.server.db.entities.User;

public class UserAuthAdapter {
//    UserAuthDto userAuthDto;
    User userAuthEntity;
//    private UserAuthDto getUserAuthDtoFromEntityAdapter(User userEntity){
//
//        userAuthDto =new UserAuthDto();
//        userAuthDto.setPhoneNumber(userEntity.getPhoneNumber());
//        userAuthDto.setPassword(userEntity.getPassword());
//
//        System.out.println(userAuthDto);
//        return userAuthDto;
//    }
    private User getUserEntityFromUserDtoAdapter(UserAuthDto userDto){

        userAuthEntity =new User();
        userAuthEntity.setPhoneNumber(userDto.getPhoneNumber());
        userAuthEntity.setPassword(userDto.getPassword());

        System.out.println(userAuthEntity);
        return userAuthEntity;
    }

}
