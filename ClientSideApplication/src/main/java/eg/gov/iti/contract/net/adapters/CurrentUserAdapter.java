package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.contract.ui.models.CurrentUserModel;

import java.sql.Date;
import java.time.LocalDate;

public class CurrentUserAdapter {
    private static UserDto userDto;
    private static CurrentUserModel userModel;

    public static UserDto getUserDtoFromModelAdapter(CurrentUserModel userModel) {

        userDto = new UserDto();
        userDto.setPhoneNumber(userModel.getPhoneNumber());
        userDto.setFullName(userModel.getFullName());
        userDto.setEmail(userModel.getEmail());
        userDto.setPassword(userModel.getPassword());
        userDto.setCountry(userModel.getCountry());
        userDto.setDateOfBirth(Date.valueOf(userModel.getDateOfBirth().toString()));
        userDto.setBio(userModel.getBio());
        userDto.setImageEncoded(userModel.getImageEncoded());

        return userDto;
    }

    public static CurrentUserModel getUserModelFromUserDtoAdapter(UserDto userDto) {

        userModel = CurrentUserModel.getInstance();
        userModel.setPhoneNumber(userDto.getPhoneNumber());
        userModel.setFullName(userDto.getFullName());
        userModel.setEmail(userDto.getEmail());
        userModel.setPassword(userDto.getPassword());
        userModel.setCountry(userDto.getCountry());
//        userModel.setDateOfBirth(userDto.getDateOfBirth().toLocalDate());
        userModel.setDateOfBirth(LocalDate.of(1997,11,20));
        userModel.setBio(userDto.getBio());
        userModel.setImageEncoded(userDto.getImageEncoded());

        System.out.println(userModel);
        return userModel;
    }

}
