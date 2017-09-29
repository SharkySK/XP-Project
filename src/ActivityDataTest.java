import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rasmus on 28-09-2017.
 */
public class ActivityDataTest {
    @Test
    public void getActivityList() throws Exception {

        int expectedAmount = 4;

        ActivityData activityData = new ActivityData();
        activityData.loadList();
        assertEquals(expectedAmount, activityData.getActivityList().size());
    }

    @Test
    public void loadList() throws Exception {

        int expectedAmount = 4;

        ActivityData activityData = new ActivityData();
        activityData.loadList();
        assertEquals(expectedAmount, activityData.getActivityList().size());
    }

    @Test
    public void searchActivity() throws Exception {

        String expectedName = "activityjakub";

        ActivityData activityData = new ActivityData();
        activityData.loadList();
        assertEquals(expectedName, activityData.searchActivity(4).getName());
    }

}