/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import bugslife.MainClasses.MainPage;
import bugslife.MainClasses.Project;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProjectUIController implements Initializable {

    @FXML
    private TableColumn<Project, Integer> projectIDF;
    @FXML
    private TableColumn<Project, String> projectNameF;
    @FXML
    private TableColumn<Project, Integer> issueCountF;
    @FXML
    private TableColumn<Project, Integer> projectDateF;
    @FXML
    private TableView<Project> table;
    @FXML
    private AnchorPane createProjectPane;
    @FXML
    private TextField textProjectName;
    @FXML
    private Label projectPromptLabel;

    private BugsLife b = new BugsLife();
    private MainPage m = new BugsLife().getM();
    private ObservableList<Project> list = FXCollections.observableArrayList();
    public static Project selectedProject;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Project p : m.getProjectsList()) {
            list.add(p);
        }
        projectIDF.setCellValueFactory(new PropertyValueFactory<Project, Integer>("id"));
        projectNameF.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        issueCountF.setCellValueFactory(new PropertyValueFactory<Project, Integer>("issueCount"));
        projectDateF.setCellValueFactory(new PropertyValueFactory<Project, Integer>("projectTime"));
        table.setItems(list);
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 1) {
                try {
                    selectingProject();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void selectingProject() throws Exception {
        if (table.getSelectionModel().getSelectedItem() != null) {
            selectedProject = table.getSelectionModel().getSelectedItem();
            b.changeScene("bugslife/FXML/IssueUI.fxml");
        }
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void createNewProject(ActionEvent event) {
        if (!createProjectPane.isVisible()) {
            createProjectPane.setVisible(true);
        }
    }

    @FXML
    private void okBackToProjectUI(ActionEvent event) {
        projectPromptLabel.setVisible(false);
        if (createProjectPane.isVisible()) {
            if (!textProjectName.getText().isEmpty()) {
                m.addProjects(textProjectName.getText());
                textProjectName.setText("");
                list.clear();
                for (Project p : m.getProjectsList()) {
                    list.add(p);
                }
                createProjectPane.setVisible(false);
            } else {
                projectPromptLabel.setVisible(true);
            }
        }
    }

    @FXML
    private void closeProjectPrompt(MouseEvent event) {
        if (createProjectPane.isVisible()) {
            textProjectName.setText("");
            projectPromptLabel.setVisible(false);
            createProjectPane.setVisible(false);
        }
    }
}
