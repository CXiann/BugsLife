
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


public class LoginPageController {

    @FXML
    private Label wrongLabel;
    @FXML
    private Button button;
    @FXML
    private TextField usernameF;
    @FXML
    private TextField passwordF;
    
    private BugsLife b = new BugsLife();
    public static String usernameC, passwordC;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {

        usernameC = usernameF.getText();
        passwordC = passwordF.getText();
        if (BugsLife.canLogin(usernameC, passwordC)) {
            b.changeScene("bugslife/FXML/ProjectUI.fxml");
        }
        else if (usernameC.isEmpty() || passwordC.isEmpty()) {
            wrongLabel.setText("Please fill up your data");
        }else{
            wrongLabel.setText("You have entered wrong username or password!!");
        }
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

}
