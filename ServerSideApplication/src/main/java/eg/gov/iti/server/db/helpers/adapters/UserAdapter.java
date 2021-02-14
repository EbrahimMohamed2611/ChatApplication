package eg.gov.iti.server.db.helpers.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserDto;
import eg.gov.iti.server.db.entities.User;

public class UserAdapter {
    private static UserDto userDto;
    private static User userEntity;
    public static UserDto getUserDtoFromEntityAdapter(User userEntity){

        userDto =new UserDto();
        userDto.setUserId(userEntity.getUserId());
        userDto.setPhoneNumber(userEntity.getPhoneNumber());
        userDto.setFullName(userEntity.getUserName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setUserGender(userEntity.getUserGender());
        userDto.setCountry(userEntity.getCountry());
        userDto.setDateOfBirth(userEntity.getDateOfBirth());
        userDto.setBio(userEntity.getBio());
        userDto.setImageEncoded(userEntity.getImageEncoded());
        userDto.setStatus(userEntity.getStatus());

        return userDto;
    }
    public static User getUserEntityFromUserDtoAdapter(UserDto userDto){

        userEntity =new User();
        userEntity.setUserId(userDto.getUserId());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setUserName(userDto.getFullName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setUserGender(userDto.getUserGender());
        userEntity.setCountry(userDto.getCountry());
        userEntity.setDateOfBirth(userDto.getDateOfBirth());
        userEntity.setBio(userDto.getBio());
        userEntity.setImageEncoded(userDto.getImageEncoded());
        userEntity.setStatus(userDto.getStatus());

        System.out.println(userEntity);
        return userEntity;
    }
}
