import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminController {

    @FXML
    AnchorPane anchor;

    @FXML
    private void bookingScreen(ActionEvent e) {
        try {
            AnchorPane tempAnchor = FXMLLoader.load(getClass().getResource("GUI/BookingSubScreen.fxml"));
            anchor.getChildren().setAll(tempAnchor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void activityScreen(ActionEvent e) {
        try {
            AnchorPane tempAnchor = FXMLLoader.load(getClass().getResource("GUI/ActivitySubScreen.fxml"));
            anchor.getChildren().setAll(tempAnchor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
