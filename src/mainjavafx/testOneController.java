package mainjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class testOneController {

    @FXML
    private Button btnOK;

    @FXML
    void btnOKClicked(ActionEvent event) {
        Stage mainWindow = (Stage) btnOK.getScene().getWindow();
        mainWindow.setTitle("Clicked!");
    }

}
