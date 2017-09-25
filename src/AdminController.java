import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminController {

    @FXML
    private AnchorPane anchor;

    @FXML
    private void bookingScreen(ActionEvent e) {
        changeAnchor("GUI/BookingSubScreen.fxml");
    }

    @FXML
    private void activityScreen(ActionEvent e) {
        changeAnchor("GUI/ActivitySubScreen.fxml");
    }

    @FXML
    private void statisticScreen(ActionEvent e) {
        changeAnchor("GUI/StatsSubScreen.fxml");
    }

    private void changeAnchor(String path) {
        try {
            AnchorPane tempAnchor = FXMLLoader.load(getClass().getResource(path));
            anchor.getChildren().setAll(tempAnchor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

