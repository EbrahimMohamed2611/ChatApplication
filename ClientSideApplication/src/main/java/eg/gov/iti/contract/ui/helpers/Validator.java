package eg.gov.iti.contract.ui.helpers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

public class Validator {
    public void nameValidator(JFXTextField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("^[a-zA-Z_-][ a-zA-Z0-9_-]{2,14}$");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Invalid Name. Must be 3:15 character");
        textField.getValidators().addAll(requiredFieldValidator,regexValidator);

    }
    public void phoneValidator(JFXTextField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("^01[0125]{1}(\\-)?[^0\\D]{1}\\d{7}$");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Invalid Phone Number. eg: 01(0,1,2,5)********");
        RegexValidator checkExistenceValidator=new RegexValidator();
        checkExistenceValidator.setRegexPattern("");
        checkExistenceValidator.setMessage("Phone Number is already exist");
        checkExistenceValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        textField.getValidators().addAll(requiredFieldValidator,regexValidator,checkExistenceValidator);

    }
    public void emailValidator(JFXTextField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Invalid Email. eg: user@mail.com");
        textField.getValidators().addAll(requiredFieldValidator,regexValidator);
    }
    public void passwordValidator(JFXPasswordField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W\\_])[a-zA-Z0-9\\W\\_]{8,15}$");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Weak Password. eg: 1997@Ahmed");
        textField.getValidators().addAll(requiredFieldValidator,regexValidator);
    }
    public void confirmPasswordValidator(JFXPasswordField confirmPasswordField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Unmatched Password.");
        confirmPasswordField.getValidators().addAll(requiredFieldValidator,regexValidator);
    }

    public void countryValidator(JFXTextField textField){
        RegexValidator regexValidator =new RegexValidator();
        RequiredFieldValidator requiredFieldValidator =new RequiredFieldValidator();
        regexValidator.setRegexPattern("");
        regexValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setIcon(new FontIcon(FontAwesomeSolid.EXCLAMATION_TRIANGLE));
        requiredFieldValidator.setMessage("Required");
        regexValidator.setMessage("Invalid Country Name.");
        textField.getValidators().addAll(requiredFieldValidator,regexValidator);

    }

}
