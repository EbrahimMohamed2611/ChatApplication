package eg.gov.iti.contract.db.entities;

import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int userId;
    private String phoneNumber;
    private String fullName;
    private String password;
    private String encryptionPassword;
    private String country;
    private Date dateOfBirth;
    private Gender userGender;
    private String bio;
    private transient ImageView imageView;
    private String imageEncoded;


}
