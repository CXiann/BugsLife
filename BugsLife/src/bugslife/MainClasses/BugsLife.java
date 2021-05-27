package bugslife.MainClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        /* in case u ned to create again the json file
                User[] u = new User[4];
        u[0] = new User("jhoe", "password");
        u[1] = new User("btan", "password");
        u[2] = new User("liew", "password");
        u[3] = new User("ang", "password");
        Project p = new Project("Nuxtjs");                                     //create new project @param : title
        MainPage m = new MainPage(new ArrayList<>(Arrays.asList(p)), new ArrayList<>(Arrays.asList(u)));
        m.getProjectsList().get(0).createIssue("Can't display the table",
                "Hi, \n\n            I'm receiving the \"Can't display the data because Power BI can't determine the relationship between two or more fields.\" error message and I do not understand why. I have searched the net for help but can't find a post that answers my question.\n\n           It may well be a straight modelling restriction but best to check.\n\n\n   I have two datasources (Sheet 1 and Sheet 2). Both are Excel files.",
                "Frontend", 4, "In Progress", "jhoe", "btan");    //create new issues @param : issueTitle,description,tag,priority,creator,assignee

        Issue i1 = m.getProject(1).getIssue(1);                               //create issues object to modify easily
        i1.addComment("liew",
                "Please find below links to the two .pbix files I've created in an attempt toconquer this error I'm receiving.");                         //add comment into selected issues @param : creator,commentText

        i1.addReaction(1, "happy");  //add reaction to selected comment Id @param : commentId,reactionType

        i1.addComment("ang",
                "Try this https://www.dropbox.com/home?preview=Relationships.pbix\n\nJust I removed the relationship between sheet1 and sheet1BridgingTable, sheet2 and sheet2BrdgingTable");
        m.getProject(1).createIssue("Can't open file",
                "Windows 10 Photo will not open a jpg file. I get \"We can't open this file.\" error message.\n\nThey're family photo that I sure would like to salvage.  Thanks",
                "Backend", 3, "Open", "btan", "jhoe");

        Issue i2 = m.getProject(1).getIssue(2);                               //create issues object to modify easily
        setCStaticCountZero();
        i2.addComment("liew",
                "I am having the same issue with a disc of photos from Christmas given to me by my sister. I have tried opening all of the photos with all of my available opening options and still get the same message as above poster. ");                         //add comment into selected issues @param : creator,commentText
        i2.addReaction(1, "angry");
        i2.addReaction(1, "happy");
        i2.addReaction(1, "happy");

        i2.addComment("btan",
                "lad photos of windows 8 & 10 are worst possible application ever developed!\n\n                    just change default view to \"windows photo viewer\" if you dont have any other application");
        i2.addReaction(2, "angry");
        i2.addReaction(2, "angry");
        i2.addReaction(2, "angry");
        i2.addReaction(2, "angry");
        i2.addReaction(2, "angry");
        i2.addReaction(2, "happy");
        i2.addReaction(2, "happy");

        i2.addComment("ang",
                "Window 10 can but window vista does not work");
        i2.addReaction(3, "angry");
        i2.addReaction(3, "angry");
        i2.addReaction(3, "angry");
        i2.addReaction(3, "angry");
        i2.addReaction(3, "angry");
        i2.addReaction(3, "angry");
        i2.addReaction(3, "angry");
        i2.addReaction(3, "angry");
        i2.addReaction(3, "angry");
        
        System.out.println(p.getName());

        //2nd projects
        m.addProjects("Vuejs");
        m.getProject(2).createIssue("Flash of unstyled content",
                "What is expected ?\nExpected correct scrollspy active link highlighting and all links work.\n\nWhat is actually happening?\nPage jumps to top before smooth scroll, which leads to weird links highlighting. Also, sometimes some links doesn't highlight (like Server Rendered in demo video)\n\nAdditional comments?\nDemonstration - https://imgur.com/a/GGvITVB",
                "cmty:bug-report", 6, "Open", "btan", "ang");

        Issue i11 = m.getProject(2).getIssue(1);
        setCStaticCountZero();
        i11.addComment("liew",
                "Here is what I am referring to: https://streamable.com/hkyg3 Apologies for not posting a video when creating the issue! ");
        i11.addReaction(1, "happy");
        i11.addReaction(1, "happy");
        i11.addReaction(1, "angry");

        i11.addComment("ang",
                "Thanks for video, seems interesting, weirdly enough I couldn't reproduce.\nWill see what can be done.");
        i11.addReaction(2, "happy");
        i11.addReaction(2, "happy");
        i11.addReaction(2, "angry");
        i11.addReaction(2, "angry");
        i11.addReaction(2, "angry");
        i11.addReaction(2, "angry");
        i11.addReaction(2, "angry");

        i11.addComment("jhoe",
                "Thanks for everything");
        i11.addReaction(3, "happy");
        i11.addReaction(3, "happy");
        i11.addReaction(3, "angry");

        m.getProject(2).createIssue("User is \"trapped\" on 404 page",
                "When hitting the 404 page, the Nuxt layout isn't applied correctly.",
                "Backend", 8, "Closed", "ang", "jhoe");
        Issue i22 = m.getProject(2).getIssue(2);
        setCStaticCountZero();
        i22.addComment("btan",
                "Still a problem. ");
        i22.addReaction(1, "happy");
        i22.addReaction(1, "happy");
        i22.addReaction(1, "angry");
        i22.addReaction(1, "angry");
        i22.addReaction(1, "angry");
        i22.addReaction(1, "angry");
        i22.addReaction(1, "angry");
        i22.addReaction(1, "angry");

        i22.addComment("ang",
                "/cc @clarkdo");
        i22.addReaction(2, "happy");
        i22.addReaction(2, "happy");
        i22.addReaction(2, "happy");
        i22.addReaction(2, "angry");
        i22.addReaction(2, "angry");
        i22.addReaction(2, "angry");
        i22.addReaction(2, "angry");
        i22.addReaction(2, "angry");

        //3rd Project
        m.addProjects("Reactjs");
        m.getProject(3).createIssue("Flash of unstyled content",
                "What is expected ?\nThere is no flash of unstyled content            \n\nWhat is actually happening?\nThere is a brief flash of unstyled content\n\nAdditional comments? \nDoesn't occur in Chrome",
                "cmty:bug-report", 1, "Open", "liew", "btan");
        Issue i111 = m.getProject(3).getIssue(1);
        setCStaticCountZero();
        i111.addComment("liew",
                "To: https://streamable.com/hkyg3 Apologies for not posting a video when creating the issue!");
        i111.addReaction(1, "happy");
        i111.addReaction(1, "happy");
        i111.addReaction(1, "angry");
        i111.addReaction(1, "thumbsUp");
        i111.addReaction(1, "thumbsUp");
        i111.addReaction(1, "thumbsUp");
        i111.addReaction(1, "thumbsUp");
        i111.addReaction(1, "thumbsUp");

        i111.addComment("ang",
                "Thanks for video, seems interesting, weirdly enough I couldn't reproduce.\nWill see what can be done.");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "angry");
        i111.addReaction(2, "happy");
        i111.addReaction(2, "happy");

        serialize(m);
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
        String path = "dataNew.json";

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
