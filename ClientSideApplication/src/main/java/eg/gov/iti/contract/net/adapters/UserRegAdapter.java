package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserAuthDto;
import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserRegisterModel;

public class UserRegAdapter {

    private static UserDto userDto;

    public static UserDto getUserDtoFromModelAdapter(UserRegisterModel userRegisterModel){

        userDto =new UserDto();

        userDto.setPhoneNumber(userRegisterModel.getPhoneNumber());
        userDto.setEmail(userRegisterModel.getEmail());
        userDto.setDateOfBirth(userRegisterModel.getDateOfBirth());
        userDto.setPassword(userRegisterModel.getPassword());
        userDto.setUserGender(userRegisterModel.getUserGender());
        userDto.setFullName(userRegisterModel.getFullName());
        userDto.setStatus(userRegisterModel.getStatus());

        return userDto;
    }

}
