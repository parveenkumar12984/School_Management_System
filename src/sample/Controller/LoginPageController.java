package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.conn;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;

public class LoginPageController {

    public int flag;
    private static String name;

    @FXML
    private JFXButton signIn;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signUp;

    @FXML
    private JFXButton cancel;

    private FXMLLoader loader;

    @FXML
    void initialize() {

        signIn.setOnAction(event -> {

            String username = userName.getText();
            String pasword = password.getText();

            Stage stage=new Stage();
            stage.setTitle("Home Page");
            stage.setMaximized(true);
            try{
                if(!username.equals("")&& !pasword.equals("")){

                    conn c1 = new conn();
                    String query = "select * from signup where username='"+username+"' and password = '"+pasword+"'";
                    ResultSet resultSet = c1.s.executeQuery(query);

                    while (resultSet.next()){
                        if(resultSet.getString("username").equals(username) && resultSet.getString("password").equals(pasword)){

                            flag=1;

                            String firstname = resultSet.getString("firstname");
                            String lastname = resultSet.getString("lastname");
                            name = (firstname + " " +lastname);

                            FXMLLoader loader = new FXMLLoader();

                            loader.setLocation(getClass().getResource("/sample/view/homePage.fxml"));
                            try{
                                loader.load();
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }

                            Parent root = loader.getRoot();

                            Scene scene=new Scene(root);
                            stage.setScene(scene);

                            stage.show();
                            signIn.getScene().getWindow().hide();
                        }
                    }

                    if(flag==0){
                        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(50),userName);
                        translateTransition.setFromX(0f);
                        translateTransition.setByX(10f);
                        translateTransition.setCycleCount(6);
                        translateTransition.setAutoReverse(true);
                        translateTransition.playFromStart();

                        TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(50),password);
                        translateTransition1.setFromX(0f);
                        translateTransition1.setByX(10f);
                        translateTransition1.setCycleCount(6);
                        translateTransition1.setAutoReverse(true);
                        translateTransition1.playFromStart();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Plzz Enter details!!!!");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

        signUp.setOnAction(event -> {

            loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signUp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();

            Stage stage = new Stage();
            stage.setTitle("Sign Up Window");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();

        });

        cancel.setOnAction(event ->  System.exit(0));
    }

    public String getName() {
        return name;
    }
}
