package bugslife.MainClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BugsLife extends Application {

    private static Stage stg;
    static Scanner in = new Scanner(System.in);
    static MainPage m = deserialize();

    @Override
    public void start(Stage primaryStage) throws IOException {
        /*
        //Code to use when rerun
        //Only use when creating another project in exsiting mainpage
        Project.projectCount =  m.getLastProjectNum();
        
        //Only use when creating another issue in exsiting project
        Issue.issueCount = m.getProjectsList().get(0).getLastIssueNum();
        
        //Only use when creating another comment in exsiting issue
        Comment.commentCount = m.getProjectsList().get(0).getIssue(1).getLastCommentNum();
         */
        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bugslife/FXML/LoginPage.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/images/bugsIcon.png")));
        primaryStage.setTitle("Bugs Everywhere");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    
    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
    
    public void closeWindow(){
        stg.close();
    }

    public static void printSearchResult(ArrayList<Issue> matched, String keyword) {
        System.out.println("\nSearch Results for \"" + keyword + "\" :");
        if (!matched.isEmpty()) {
            for (Issue m : matched) {
                m.printSingleIssue();
            }
        } else {
            System.out.println("No result found!");
        }
    }

    public static void printSortedResult(Project p, String sortType) {
        CommandLineTable lt = new CommandLineTable();
        System.out.println("\nSorted Result: ");
        lt.setShowVerticalLines(true);
        lt.setHeaders("ID", "Title", "Status", "Tag", "Priority", "Time", "Assignee", "CreatedBy");
        //lt.print();
        p.sortIssue(sortType);
    }

    public static void printProjectDashboard(Project p) {
        CommandLineTable lt = new CommandLineTable();
        System.out.println("\nProject Dashboard: ");
        lt.setShowVerticalLines(true);
        lt.setHeaders("ID", "Project Name", "Issues");
        lt.addRow(p.printAllProjects());
        lt.print();
    }

    public static void printIssuesDashboard(Project p) {
        CommandLineTable lt = new CommandLineTable();
        System.out.println("\nIssue Dashboard: ");
        lt.setShowVerticalLines(true);
        lt.setHeaders("ID", "Title", "Status", "Tag", "Priority", "Time", "Assignee", "CreatedBy");
        for (Issue i : p.issues) {
            lt.addRow(i.printIssue());
        }
        lt.print();
    }

    public static void printIssuesSection(Issue i) {
        System.out.println("\nIssue Page: ");
        i.combinedIssueSection();
    }

    private static void serialize(MainPage m) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(m);
        String path = "data.json";

        try {
            FileWriter writer = new FileWriter(path);
            writer.write(json);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("JSON file successful created.");
    }

    private static MainPage deserialize() {
        Gson gson = new Gson();
        try {
            return gson.fromJson(new FileReader("data.json"), MainPage.class);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static void setICStaticCountZero() {
        Issue.issueCount = 0;
        Comment.commentCount = 0;
    }

    private static void setCStaticCountZero() { //add before addComment(in new created issue)
        Comment.commentCount = 0;              //add before addComment(in new created issue of new project)
    }

    public static TreeMap countFrequencies(List<String> list) {
        TreeMap<String, Integer> tmap = new TreeMap<String, Integer>();
        for (String t : list) {
            Integer c = tmap.get(t);
            tmap.put(t, (c == null) ? 1 : c + 1);
        }
        return tmap;
    }

    public static boolean canLogin(String user, String password) {
        for (User u : m.getUsers()) {
            if (u.getUsername().equals(user) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
