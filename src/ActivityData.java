import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ActivityData {

    private ObservableList<Activity> activityList = FXCollections.observableArrayList();

    public ObservableList<Activity> getActivityList() {
        return activityList;
    }

    public void loadList() {
        DBConn dbConn = new DBConn();
        activityList = FXCollections.observableArrayList(dbConn.getActivities());
    }

}
