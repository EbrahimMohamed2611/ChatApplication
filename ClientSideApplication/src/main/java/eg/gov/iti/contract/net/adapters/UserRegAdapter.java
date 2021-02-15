package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserRegDto;
import eg.gov.iti.contract.ui.models.UserRegisterModel;

public class UserRegAdapter {

    private static UserRegDto userRegDto;

    public static UserRegDto getUserDtoFromModelAdapter(UserRegisterModel userRegisterModel){

        userRegDto =new UserRegDto();

        userRegDto.setPhoneNumber(userRegisterModel.getPhoneNumber());
        userRegDto.setEmail(userRegisterModel.getEmail());
        userRegDto.setDateOfBirth(userRegisterModel.getDateOfBirth());
        userRegDto.setPassword(userRegisterModel.getPassword());
        userRegDto.setUserGender(userRegisterModel.getUserGender());
        userRegDto.setFullName(userRegisterModel.getFullName());
        userRegDto.setStatus(userRegisterModel.getStatus());

        return userRegDto;
    }

}