package eg.gov.iti.server.db.dto;

import eg.gov.iti.server.db.entities.Gender;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable {

    private int userId;
    private String phoneNumber;
    private String fullName;
    transient private String password;
    private String country;
    private Date dateOfBirth;
    private Gender userGender;
    private String bio;
    private transient ImageView imageView;
    private String imageEncoded;


}
