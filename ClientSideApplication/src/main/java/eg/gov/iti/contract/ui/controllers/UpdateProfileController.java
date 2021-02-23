package eg.gov.iti.contract.ui.controllers;

import com.jfoenix.controls.*;
import com.jfoenix.validation.RegexValidator;
import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.CurrentUserAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.UpdateProfileServiceInterface;
import eg.gov.iti.contract.ui.helpers.ImageConverter;
import eg.gov.iti.contract.ui.helpers.ModelsFactory;
import eg.gov.iti.contract.ui.helpers.StageCoordinator;
import eg.gov.iti.contract.ui.helpers.Validator;
import eg.gov.iti.contract.ui.models.CurrentUserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

public class UpdateProfileController implements Initializable {

    @FXML
    private Circle profilePic;

    @FXML
    private JFXTextField phoneTxtField;

    @FXML
    private JFXTextField nameTxtField;

    @FXML
    private JFXTextField emailTxtField;

    @FXML
    private JFXPasswordField pwdTxtField;

    @FXML
    private JFXPasswordField confirmPwdTxtField;

    @FXML
    private JFXButton choosePhotoBtn;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTextArea bioTxtArea;

    @FXML
    private JFXButton updateUserBtn;

    @FXML
    private JFXButton backCancelBtn;
    @FXML
    private JFXTextField countryTxtField;

    private StageCoordinator coordinator;
    private CurrentUserModel currentUserModel;
    private ModelsFactory modelsFactory;
    private UpdateProfileServiceInterface updateProfileService;
    private List<String> countries;
    private Validator validator;
    boolean isValidUserName = false;
    boolean isValidCountry = false;
    boolean isValidEmail = false;
    boolean isValidPassword = false;
    boolean isValidConfirmPassword = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelsFactory = ModelsFactory.getInstance();
        currentUserModel = modelsFactory.getCurrentUserModel();
        updateProfileService = ServicesLocator.getUpdateProfileService();
        profilePic.fillProperty().bind(currentUserModel.getProfilePic().fillProperty());

        validator = new Validator();

        validator.nameValidator(nameTxtField);
        validator.emailValidator(emailTxtField);
        validator.passwordValidator(pwdTxtField);
        validator.confirmPasswordValidator(confirmPwdTxtField);
        validator.countryValidator(countryTxtField);
        setCountryTxtFieldToBeSearchable();

        nameTxtField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                isValidUserName = nameTxtField.validate();

            }
        });
        emailTxtField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                isValidEmail = emailTxtField.validate();

            }
        });
        pwdTxtField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                isValidPassword = pwdTxtField.validate();

            }
        });
        confirmPwdTxtField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (!t1) {
                var regexValidator = confirmPwdTxtField.getValidators().get(1);
                if (regexValidator instanceof RegexValidator) {
                    ((RegexValidator) regexValidator).setRegexPattern(pwdTxtField.getText());
                }
                isValidConfirmPassword = confirmPwdTxtField.validate();

            }
        });
        countryTxtField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if ((countries.stream().map(item -> item.toLowerCase()).collect(Collectors.toList())).contains(countryTxtField.getText().toLowerCase())) {
                var regexValidator = countryTxtField.getValidators().get(1);
                if (regexValidator instanceof RegexValidator) {
                    ((RegexValidator) regexValidator).setRegexPattern(".*");
                }
            } else {
                var regexValidator = countryTxtField.getValidators().get(1);
                if (regexValidator instanceof RegexValidator) {
                    ((RegexValidator) regexValidator).setRegexPattern("");
                }
            }
            isValidCountry = countryTxtField.validate();
        });
        updateViewFromModel();
        coordinator = StageCoordinator.getInstance();
    }

    private void updateViewFromModel() {
        phoneTxtField.setText(currentUserModel.getPhoneNumber());
        pwdTxtField.setText(currentUserModel.getPassword());
        nameTxtField.setText(currentUserModel.getFullName());
        confirmPwdTxtField.setText(currentUserModel.getPassword());
        countryTxtField.setText(currentUserModel.getCountry());
        emailTxtField.setText(currentUserModel.getEmail());
        datePicker.setValue(currentUserModel.getDateOfBirth());
        bioTxtArea.setText(currentUserModel.getBio());
    }

    @FXML
    private void selectPic() {
        FileChooser fileChooser = new FileChooser();
        File file;
        file = fileChooser.showOpenDialog(null);
        try {
            currentUserModel.setImageEncoded(ImageConverter.getEncodedImage(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentUserModel.setProfileImage(new Image(file.toURI().toString()));
    }


    @FXML
    private void allowUpdate(ActionEvent event) {
        if (updateUserBtn.getText().equals("Update")) {
            enableFields(true);

        } else if (updateUserBtn.getText().equals("Apply")) {
            enableFields(false);
            try {
                updateModelFromView();
                System.out.println("in Apply");
                if (updateProfileService.updateProfile(CurrentUserAdapter.getUserDtoFromModelAdapter(currentUserModel))) {
                    System.out.println("update ran successfully");
                } else {
                    System.out.println("update failed");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
        updateViewFromModel();
    }

    private void updateModelFromView() {
        currentUserModel.setPassword(pwdTxtField.getText());
        currentUserModel.setCountry(countryTxtField.getText());
        currentUserModel.setDateOfBirth(datePicker.getValue());
        currentUserModel.setFullName(nameTxtField.getText());
        currentUserModel.setEmail(emailTxtField.getText());
        currentUserModel.setBio(bioTxtArea.getText());
    }

    private void enableFields(boolean state) {
        nameTxtField.setEditable(state);
        emailTxtField.setEditable(state);
        pwdTxtField.setEditable(state);
        confirmPwdTxtField.setEditable(state);
        choosePhotoBtn.setDisable(!state);
        bioTxtArea.setEditable(state);
        countryTxtField.setEditable(state);
        if (state) {
            updateUserBtn.setText("Apply");
            backCancelBtn.setText("Cancel");
        } else {
            updateUserBtn.setText("Update");
            backCancelBtn.setText("Back");
        }
    }

    @FXML
    private void backToHomeScene(ActionEvent event) {
        if (backCancelBtn.getText().equals("Back")) {
            enableFields(false);
            coordinator.switchToHomeScene();
        } else if (backCancelBtn.getText().equals("Cancel")) {
            enableFields(false);
        }
        updateViewFromModel();
    }

    void setCountryTxtFieldToBeSearchable() {
        String[] locales = Locale.getISOCountries();
        countries = Arrays.stream(locales).map(s -> {
            return new Locale("", s).getDisplayCountry();
        }).collect(Collectors.toList());
        JFXAutoCompletePopup<String> countryPopUp = new JFXAutoCompletePopup<>();
        countryPopUp.getSuggestions().addAll(countries);
        countryPopUp.hide();
        countryPopUp.setSelectionHandler(event -> {
            countryTxtField.setText(event.getObject());
        });
        countryTxtField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            countryTxtField.textProperty().addListener((observableValue1, s, t11) -> {
                countryPopUp.filter(item -> item.toLowerCase().startsWith(countryTxtField.getText().toLowerCase()));
                if (countryPopUp.getFilteredSuggestions().isEmpty()) {
                    countryPopUp.hide();
                } else {
                    countryPopUp.show(countryTxtField);
                }
            });

        });
    }
    private boolean isDataValid() {
        return (isValidUserName && isValidEmail && isValidCountry && isValidPassword && isValidConfirmPassword);
    }
    public static void makeComboSearchable(JFXComboBox<String> comboBox) {
        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        autoCompletePopup.getSuggestions().addAll(comboBox.getItems());
        autoCompletePopup.hide();

        autoCompletePopup.setSelectionHandler(event -> {
            comboBox.setValue(event.getObject());
        });

        TextField editor = comboBox.getEditor();
        editor.focusedProperty().addListener((observable, oldValue, focus) ->

                editor.textProperty().addListener(o -> {
                    //The filter method uses the Predicate to filter the Suggestions defined above
                    //I choose to use the contains method while ignoring cases
                    autoCompletePopup.filter(item -> item.toLowerCase().startsWith(editor.getText().toLowerCase()));
                    //Hide the autocomplete popup if the filtered suggestions is empty or when the box's original popup is open
                    if (autoCompletePopup.getFilteredSuggestions().isEmpty() || comboBox.showingProperty().get()) {
                        autoCompletePopup.hide();
                    } else {
                        autoCompletePopup.show(editor);
                    }
                })
        );
    }
}
