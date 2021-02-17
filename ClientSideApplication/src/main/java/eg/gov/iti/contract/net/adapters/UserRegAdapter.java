package eg.gov.iti.contract.net.adapters;

import eg.gov.iti.contract.clientServerDTO.dto.UserRegDto;
import eg.gov.iti.contract.clientServerDTO.enums.Gender;
import eg.gov.iti.contract.clientServerDTO.enums.Status;
import eg.gov.iti.contract.ui.models.UserRegisterModel;

import java.sql.Date;

public class UserRegAdapter {

    private static UserRegDto userRegDto;

    public static UserRegDto getUserDtoFromModelAdapter(UserRegisterModel userRegisterModel){

        userRegDto =new UserRegDto();

        userRegDto.setPhoneNumber(userRegisterModel.getPhoneNumber());
        userRegDto.setEmail(userRegisterModel.getEmail());
        userRegDto.setDateOfBirth(Date.valueOf(userRegisterModel.getDateOfBirth()));
        userRegDto.setPassword(userRegisterModel.getPassword());

        switch(userRegisterModel.getUserGender()){
            case "MALE":
                userRegDto.setUserGender(Gender.MALE);
                break;
            case "FEMALE":
                userRegDto.setUserGender(Gender.FEMALE);
                break;
        }
        userRegDto.setFullName(userRegisterModel.getFullName());
        switch (userRegisterModel.getStatus()){
            case "AWAY":
                userRegDto.setStatus(Status.AWAY);
                break;
            case "AVAILABLE":
                userRegDto.setStatus(Status.AVAILABLE);
                break;
            case "BUSY":
                userRegDto.setStatus(Status.BUSY);
                break;
        }

        return userRegDto;
    }

}