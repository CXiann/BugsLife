package bugslife.MainClasses;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project {

    protected transient static Integer projectCount = 0;
    private Integer id;
    private String name;
    ArrayList<Issue> issues = new ArrayList<>();
    private Date projectTime;
    //private transient SimpleDateFormat ft; 
    //ArrayList<Update> history = new ArrayList<>();                              
    //dunno how to implement update for creating project(diif. class)           

    public Project(String projectName) {
        this.name = projectName;
        id = ++projectCount;
        projectTime = new Date();
        //history.add(new Update(name, this.getProjectTime()));
    }

    public void createIssue(String issueTitle, String issueDescrip, String tag, int priority, String status, String creatorUser, String assigneeUser) {
        Update.updateCount = 0; //reset static updateCount before creating new issue
        issues.add(new Issue(issueTitle, issueDescrip, tag, priority, status, creatorUser, assigneeUser));
    }

//    public void printAllProjects() {
//        System.out.println(this.getProjectId() + "\t\t" + this.getProjectName() + "\t\t" + this.issues.size() + "\n");
//    }
//    public void printAllIssues() {
//        for (Issue i : issues) {
//            i.printSingleIssue();
//        }
//    }
    public String[] printAllProjects() {
        String str = this.getProjectId() + "," + this.getName() + "," + this.issues.size();
        String[] print = str.split(",", 0);
        return print;
    }

//    public void printAllIssues() {
//        for (Issue i : issues) {
//            i.printSingleIssue();
//        }
//    }

    /**
     * search issueTitle,issueDescription,issueComment for matching keywords
     *
     * @param searchString keyword to be searched
     * @return ArrayList<Issue>
     */
    public List<Issue> searchIssue(String searchString) {
        List<Issue> match = new ArrayList<>();
        for (Issue i : issues) {
            if (isContain(i.getTitle(), searchString) || isContain(i.getDescriptionText(), searchString)
                    || isCommentContain(i.getComments(), searchString)) {
                match.add(i);
            }
        }
        return match;
    }

    /**
     * sort issues according to criteria in String only sorted for display(no
 changes on the actual position in issues)
     *
     * @param sortType criteria to sort
     */
    
    public void sortIssue(String sortType) {
        issues.get(0).sortType = sortType;                    //sortType is static and is created for determining which statement to use in compareTo() 
        PriorityQueue<Issue> p = new PriorityQueue(issues);   //create temporary PriorityQueue to automatically sort according to criteria               
        while (!p.isEmpty()) {
            p.poll().printSingleIssue();                         //remove head element from the PriorityQueue(already sorted) and print it one by one until empty
        }
    }
     
    private boolean isCommentContain(ArrayList<Comment> comment, String searchString) {   //to check for exact string in an ArrayList<Comment>(local method)
        for (Comment c : comment) {
            if (isContain(c.getText(), searchString)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContain(String fullText, String searchString) {                 //to check for exact string in the source string(local method)
        String pattern = "\\b" + searchString + "\\b";
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);               //make the keyword case insensitive
        Matcher m = p.matcher(fullText);
        return m.find();
    }

    public static void setProjectCount(Integer projectCount) {
        Project.projectCount = projectCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIssues(ArrayList<Issue> issues) {
        this.issues = issues;
    }

    public void setProjectTime(Date projectTime) {
        this.projectTime = projectTime;
    }

    public List<Issue> getIssuesList() {
        return issues;
    }

    public Issue getIssue(int issueId) {
        return issues.get(issueId - 1);
    }
    
    public int getLastIssueNum(){
        return issues.size();
    }

    public int getProjectId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //** Changed **
//    public String getTimestamp() {
//        ft = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
//        return ft.format(projectTime);
//    }
    public Date getProjectTime() {
        return projectTime;
    }
}
