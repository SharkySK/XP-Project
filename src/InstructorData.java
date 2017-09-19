import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InstructorData {

    private ObservableList<Instructor> instructorList = FXCollections.observableArrayList();

    public ObservableList<Instructor> getInstructorList() {
        return instructorList;
    }

    public void loadList() {
        DBConn dbConn = new DBConn();
        instructorList = FXCollections.observableArrayList(dbConn.getAllInstructors());
    }

}
