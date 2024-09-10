package tamrin4.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tamrin4.model.bl.ProfileBl;
import tamrin4.model.entity.Product;
import tamrin4.model.entity.Profile;
import tamrin4.view.dto.FormState;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Button loginBtn;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBtn.setOnAction(event -> {
            try {
                Profile profile = ProfileBl.findByUsernameAndPassword(usernameTxt.getText(), passwordTxt.getText());
                FormState.profile = profile;
                Stage stage =new Stage();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/tamrin4/view/Product.fxml")));

                stage.setScene(scene);
                stage.setTitle("Product");
                stage.show();

                loginBtn.getScene().getWindow().hide();

            }catch (Exception ex){
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR , ex.getMessage());
                alert.show();
            }
        });
    }
}
