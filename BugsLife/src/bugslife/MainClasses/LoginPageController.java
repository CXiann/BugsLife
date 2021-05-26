/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugslife.MainClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Wen
 */
public class LoginPageController {

    @FXML
    private Label wrongLabel;
    @FXML
    private Button button;
    @FXML
    private TextField usernameF;
    @FXML
    private TextField passwordF;

    private String username, password;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        username = usernameF.getText();
        password = passwordF.getText();
        if (BugsLife.canLogin(username, password)) {
            BugsLife main = new BugsLife();
            main.closeWindow();
            //main.changeScene("nextfxml.fxml");
        }
        else if (username.isEmpty() || password.isEmpty()) {
            wrongLabel.setText("Please fill up your data");
        }else{
            wrongLabel.setText("You have entered wrong username or password!!");
        }
        System.out.println("Username : " + username);
        System.out.println("Password : " + password);
        
    }

}
