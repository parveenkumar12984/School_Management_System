package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import sample.conn;

public class SignUpController {

    @FXML
    private JFXButton signUpButton;

    @FXML
    private JFXTextField userNameSignUp;

    @FXML
    private JFXPasswordField passwordSignUp;

    @FXML
    private JFXButton cancelSignUp;

    @FXML
    private JFXTextField firstNameSignUp;

    @FXML
    private JFXTextField lastNameSignUp;

    @FXML
    private JFXComboBox<String> genderSignUp;

    @FXML
    private JFXTextField locationSignUp;

    @FXML
    private JFXTextField contactSignUp;

    @FXML
    void initialize() {


        genderSignUp.getItems().addAll("Male","Female");


        signUpButton.setOnAction(event -> {

            String first = firstNameSignUp.getText();
            String last = lastNameSignUp.getText();
            String gender = genderSignUp.getValue();
            String username = userNameSignUp.getText();
            String password = passwordSignUp.getText();
            String location = locationSignUp.getText();
            String contact = contactSignUp.getText();

            try{
                conn c1 = new conn();
                String q1 = "insert into signup(firstname,lastname,gender,username,password,location,contact) " +
                        "values ('"+first+"','"+last+"','"+gender+"','"+username+"','"+password+"','"+location+"','"+contact+"')";
                c1.s.executeUpdate(q1);
                firstNameSignUp.setText("");lastNameSignUp.setText("");genderSignUp.setValue("Select Gender");userNameSignUp.setText("");passwordSignUp.setText("");
                locationSignUp.setText("");contactSignUp.setText("");
            }
            catch (Exception e){
                e.printStackTrace();
            }


        });

        cancelSignUp.setOnAction(event -> cancelSignUp.getScene().getWindow().hide() );


    }

}
