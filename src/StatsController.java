import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.WeekFields;
import java.util.*;

/**
 * Created by Jakub Petrík on 25/09/2017.
 */
public class StatsController {

    @FXML
    public DatePicker datePicker;
    @FXML
    public ChoiceBox choiceBox;
    @FXML
    public TableView tableView;

    @FXML
    public void initialize(){
        choiceBox.setItems(FXCollections.observableArrayList("Day", "Week", "Month"));
    }
    @FXML
    public void showStats(ActionEvent e){
        statsDate();
    }

    public LocalDate statsDate(){
        LocalDate date = datePicker.getValue();

        if(choiceBox.getSelectionModel().isEmpty() || choiceBox.getValue().equals(null)){
            JOptionPane.showMessageDialog(null,"Please choose a date or period first!");
            return null;
        } else if(choiceBox.getValue().equals("Day")){
            return date;
        } else if(choiceBox.getValue().equals("Week")){
            int wN = weekNum(date);
            //Monday of week;
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.WEEK_OF_YEAR, wN);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            Date input1 = cal.getTime();
            LocalDate weekStart = input1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //Sunday of week
            cal.set(Calendar.WEEK_OF_YEAR, wN);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            Date input2 = cal.getTime();
            LocalDate weekEnd = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            weekStartEnd(weekStart,weekEnd);

        } else if(choiceBox.getValue().equals("Month")){
            
            return date;
        }

        return date;
    }

    public int weekNum(LocalDate date){

        Locale locale = Locale.ENGLISH;
        int weekOfYear = date.get(WeekFields.of(locale).weekOfWeekBasedYear());

        return weekOfYear;
    }

    public static List<LocalDate> weekStartEnd(LocalDate startDate, LocalDate endDate){

        List<LocalDate> result = new ArrayList<LocalDate>();
        result.add(startDate);
        result.add(endDate);

        return result;
    }


}
