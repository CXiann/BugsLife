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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;


public class IssueFreqTimeStampLineController implements Initializable {

    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private Button button;
    //private TreeMap<String,Integer> tmap = new TreeMap<String, Integer>();
    private String[] month = {"Jan","Feb","Mac","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
    
    private BugsLife bug = new BugsLife();
    private MainPage mainP = bug.getM();
    @FXML
    private void handleButtonAction(ActionEvent event) {
        lineChart.getData().clear();
        
        int[]issueFreq = freqIssuePerMonth("2019");
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        for (int i = 0; i < 12; i++) {
            series.getData().add(new XYChart.Data<>(month[i], issueFreq[i]));
        }
        lineChart.getData().add(series);
    }

    
    public int[] freqIssuePerMonth(String year){
        int firstDashIndex;
        String month;
        int[] issueFreq = new int[12];
        List<Project> p = mainP.getProjectsList();
        for (int j = 0; j < p.size(); j++) {
            List<Issue> issue = p.get(j).getIssuesList();
            for (int i = 0; i < issue.size(); i++) {
                String fullTimeStamp = issue.get(i).getTimestamp();
                firstDashIndex = fullTimeStamp.indexOf("-");
                if (fullTimeStamp.substring(0,firstDashIndex).equals(year)) {
                    month = fullTimeStamp.substring(firstDashIndex+1,firstDashIndex + 3);
                    if (month.equals("01")) {
                        issueFreq[0]+= 1;
                    }else if (month.equals("02")) {
                        issueFreq[1]+= 1;
                    }else if (month.equals("03")) {
                        issueFreq[2]+= 1;
                    }else if (month.equals("04")) {
                        issueFreq[3]+= 1;
                    }else if (month.equals("05")) {
                        issueFreq[4]+= 1;
                    }else if (month.equals("06")) {
                        issueFreq[5]+= 1;
                    }else if (month.equals("07")) {
                        issueFreq[6]+= 1;
                    }else if (month.equals("08")) {
                        issueFreq[7]+= 1;
                    }else if (month.equals("09")) {
                        issueFreq[8]+= 1;
                    }else if (month.equals("10")) {
                        issueFreq[9]+= 1;
                    }else if (month.equals("11")) {
                        issueFreq[10]+= 1;
                    }else if (month.equals("12")) {
                        issueFreq[11]+= 1;
                    }
                }
            }
        }return issueFreq;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
