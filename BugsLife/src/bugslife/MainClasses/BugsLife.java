package bugslife.MainClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import bugslife.Controllers.LoginPageController;
import javafx.animation.AnimationTimer;
import javafx.stage.StageStyle;

public class BugsLife extends Application {

    public static Stage stg;
    static Scanner in = new Scanner(System.in);
    static MainPage m = deserialize();

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        //Code to use when rerun
        //Only use when creating another project in exsiting mainpage
        Project.projectCount =  m.getLastProjectNum();
        
        //Only use when creating another issue in exsiting project
        Issue.issueCount = m.getProjectsList().get(0).getLastIssueNum();
        
        //Only use when creating another comment in exsiting issue
        Comment.commentCount = m.getProjectsList().get(0).getIssue(1).getLastCommentNum();
         */

        //serialize(m);
        /*Calling login page*/
        stg = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bugslife/FXML/LoginPage.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        //Try the commented line if cannot run again
//        File file = new File("C:\\Users\\User\\Documents\\NetBeansProjects\\BugsLifeClone\\BugsLife\\BugsLife\\src\\resources\\images\\bugsIcon.png");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/images/bugsIcon.png")));
//        primaryStage.getIcons().add(new Image(file.toURI().toString()));
        primaryStage.setTitle("Bugs Everywhere");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //new Thread(BugsLife.this::logic).start();

        /*test console
        printIssueDashboard(m.getProject(1));
        printIssueDashboard(m.getProject(2));
        printIssueDashboard(m.getProject(3));
         */ /*test chart
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bugslife/FXML/IssueFreqTimeStampPie.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bugslife/FXML/IssueFreqPerTagPie.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bugslife/FXML/IssueFreqPerStatusPie.fxml"));
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bugslife/FXML/IssueFreqPerTagPie.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
         */

    }
/*
    public void logic() {
        String input_Project;
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } while (stg.isShowing());
        System.out.println("====================================\n"
                + "Welcome back, " + LoginPageController.usernameC + "!\n"
                + "====================================\n");
        printProjectDashboard(m);
        loop:
        while (true) {
            System.out.println("Enter project ID to check project");
            System.out.println("or 'G' to show graph");
            input_Project = in.nextLine();
            for (int i = 0; i < m.getProjectsList().size(); i++) {
                if (input_Project == m.getProjectsList().get(i).getProjectId()) {
                    break loop;
                }
            }
            System.out.println("Please enter a valid project ID\n");
        }
        printIssueDashboard(m.getProject(input_Project));
        
    }
  */  
    public boolean validInput(String input, String[]toCheck){
        boolean flag = false;
        for (int i = 0; i < toCheck.length; i++) {
            if (input.equalsIgnoreCase(toCheck[i])) {
                flag = true;
                break;
            }
        }return flag;
    }

    public static int projectPromptForID(){
        printProjectDashboard(m);
        while (true) {
            System.out.println("Enter project ID to check project");
            int input = in.nextInt();
            for (int i = 0; i < m.getProjectsList().size(); i++) {
                if (input == m.getProjectsList().get(i).getProjectId()) {
                    return input;
                }
            }
            System.out.println("Please enter a valid project ID\n");
        }
    }
    /*
    public void stop() throws Exception {
        System.out.println("hello");

        Stage stg2 = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("bugslife/FXML/IssueFreqPerTagPie.fxml"));
        Scene scene = new Scene(root);
        stg2.setScene(scene);
        stg2.show();

    }
     */
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public void closeWindow() {
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

    public static void printProjectDashboard(MainPage m) {
        CommandLineTable lt = new CommandLineTable();
        System.out.println("\nProject Dashboard: ");
        lt.setShowVerticalLines(true);
        lt.setHeaders("ID", "Project Name", "Issues");
        for (Project p : m.getProjectsList()) {
            lt.addRow(p.printProject());
        }
        lt.print();
    }

    public static void printIssueDashboard(Project p) {
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

    public static boolean canLogin(String user, String password) {
        for (User u : m.getUsers()) {
            if (u.getUsername().equals(user) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public MainPage getM() {
        return m;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
