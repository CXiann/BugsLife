/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import bugslife.MainClasses.MainPage;
import bugslife.MainClasses.Project;
import bugslife.MainClasses.Issue;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class IssueFreqPerStatusPieController implements Initializable {

    @FXML
    private PieChart Issue_Status;
    @FXML
    private Button button;
    @FXML
    private Label label;

    private BugsLife bug = new BugsLife();
    private MainPage mainP = bug.getM();

    //static LocalDate startDate = LocalDate.of(2021, Month.MAY, 25);
    //static LocalDate endDate = LocalDate.of(2021, Month.MAY, 27);
    @FXML
    private void handleButtonAction(ActionEvent event) {
        List<Project> P = mainP.getProjectsList();
        List<String> list2 = new ArrayList<>();
        for (int j = 0; j < P.size(); j++) {
            List<Issue> issue = P.get(j).getIssuesList();
            for (int i = 0; i < issue.size(); i++) {
                list2.add(issue.get(i).getStatus());
            }
        }
        TreeMap<String, Integer> tmap = countFrequencies(list2);

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        for (Map.Entry m : tmap.entrySet()) {
            list.add(new PieChart.Data((String) m.getKey(), (Integer) m.getValue()));
        }

        Issue_Status.setData(list);

        for (final PieChart.Data data : Issue_Status.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    label.setText((Double.toString(data.getPieValue())));
                }
            });
        }

    }

    public static TreeMap countFrequencies(List<String> list) {
        TreeMap<String, Integer> tmap = new TreeMap<String, Integer>();
        for (String t : list) {
            Integer c = tmap.get(t);
            tmap.put(t, (c == null) ? 1 : c + 1);
        }
        return tmap;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /*
    private static boolean isWithinRange(LocalDate testDate) {
        return (testDate.isAfter(startDate) && testDate.isBefore(endDate));
    }
*/
}
