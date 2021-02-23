package eg.gov.iti.contract.ui.helpers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final static Pattern IP_PATTERN = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");

    private static Matcher match;


    public void nameValidator(JFXTextField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("^[a-zA-Z_-][ a-zA-Z0-9_-]{6,14}$");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Invalid Name");
        textField.getValidators().addAll(requiredFieldValidator,regexValidator);

    }
    public void phoneValidator(JFXTextField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Invalid Phone Number");
        textField.getValidators().addAll(requiredFieldValidator,regexValidator);

    }
    public void emailValidator(JFXTextField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Invalid Email");
        textField.getValidators().addAll(requiredFieldValidator,regexValidator);
    }
    public void passwordValidator(JFXPasswordField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W\\_])[a-zA-Z0-9\\W\\_]{8,15}$");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Weak Password");
        textField.getValidators().addAll(requiredFieldValidator,regexValidator);
    }
    public void confirmPasswordValidator(JFXPasswordField confirmPasswordField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Unmatched Password");
        confirmPasswordField.getValidators().addAll(requiredFieldValidator,regexValidator);
    }

    public static boolean checkIP(String ip){
        match = IP_PATTERN.matcher(ip);
        return match.matches();
    }

}
