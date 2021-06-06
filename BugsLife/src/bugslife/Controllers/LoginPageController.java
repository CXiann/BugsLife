/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    public static String usernameC, passwordC;
    @FXML
    private ImageView cross;
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        usernameC = usernameF.getText();
        passwordC = passwordF.getText();
        if (BugsLife.canLogin(usernameC, passwordC)) {
            BugsLife.stg.close();
            //BugsLife main = new BugsLife();
            //main.closeWindow();
        }
        else if (usernameC.isEmpty() || passwordC.isEmpty()) {
            wrongLabel.setText("Please fill up your data");
        }else{
            wrongLabel.setText("You have entered wrong username or password!!");
        }
        System.out.println("Username : " + usernameC);
        System.out.println("Password : " + passwordC);
        
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

}
