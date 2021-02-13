package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserAuthDto;
import eg.gov.iti.contract.ui.models.UserAuthModel;

public class UserAuthAdapter {
    UserAuthDto userAuthDto;
//    UserAuthModel userAuthEntity;
    private UserAuthDto getUserAuthDtoFromModelAdapter(UserAuthModel userAuthModel){

        userAuthDto =new UserAuthDto();

        userAuthDto.setPhoneNumber(userAuthModel.getPhoneNumber());
        userAuthDto.setPassword(userAuthModel.getPassword());

        System.out.println(userAuthDto);
        return userAuthDto;
    }
//    private User getUserEntityFromUserDtoAdapter(UserAuthDto userDto){
//
//        userAuthEntity =new User();
//        userAuthEntity.setPhoneNumber(userDto.getPhoneNumber());
//        userAuthEntity.setPassword(userDto.getPassword());
//
//        System.out.println(userAuthEntity);
//        return userAuthEntity;
//    }

}
