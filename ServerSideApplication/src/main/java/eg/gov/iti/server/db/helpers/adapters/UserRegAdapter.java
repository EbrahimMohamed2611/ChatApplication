package eg.gov.iti.server.db.helpers.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserRegDto;
import eg.gov.iti.server.db.entities.User;

public class UserRegAdapter {
    private static User userEntity;


    public static User getUserEntityFromUserRegDtoAdapter(UserRegDto userRegDto) {
        userEntity =new User();
        userEntity.setPhoneNumber(userRegDto.getPhoneNumber());
        userEntity.setUserName(userRegDto.getFullName());
        userEntity.setEmail(userRegDto.getEmail());
        userEntity.setPassword(userRegDto.getPassword());
        userEntity.setUserGender(userRegDto.getUserGender());
        userEntity.setDateOfBirth(userRegDto.getDateOfBirth());
        userEntity.setStatus(userRegDto.getStatus());

        System.out.println(userEntity);
        return userEntity;
    }
}
