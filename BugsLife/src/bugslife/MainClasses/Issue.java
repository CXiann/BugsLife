package bugslife.MainClasses;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Issue implements Comparable<Issue> {

    protected transient static Integer issueCount = 0;
    protected transient static String sortType;
    transient CommandLineTable lt = new CommandLineTable();
    private Integer id;
    private String title;
    private Integer priority;
    private String status;
    private String tag;
    private String descriptionText;
    private String createdBy;
    private String assignee;
//    private SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss"); --> Date, set at gson
    private long timestamp;
//    private Date timestamp;
    ArrayList<Comment> comments = new ArrayList<>();
    List<Update> historyIssue = new ArrayList<>();

    public Issue(String issueTitle, String issueDescrip, String tag, Integer priority, String status, String creatorUser, String assigneeUser) {
        issueCount++;
        this.id = issueCount;
        this.priority = priority;
        this.title = issueTitle;
        this.descriptionText = issueDescrip;
        this.createdBy = creatorUser;
        this.assignee = assigneeUser;
        this.tag = tag;
        this.status = status;
        this.timestamp = Instant.now().getEpochSecond();
//        this.timestamp = new Date();
        historyIssue.add(new Update(issueTitle, this.getTimestamp(), 'C'));
    }

    public void addComment(String User, String Text) {
        Comment c = new Comment(User, Text);
        comments.add(c);
//        Date commentAddedTime = new Date();
        long commentAddedTime = Instant.now().getEpochSecond();
        historyIssue.add(new Update(Text, User, commentAddedTime, 'c'));
    }

    public void addReaction(int commentNum, String s) {
        comments.get(commentNum - 1).increaseReactionCount(s);
    }

    public void printSingleIssue() { //print in console
        System.out.println(this.getIssueID() + "," + this.getTitle() + "," + this.getStatus() + "," + this.getTag() + "," + this.getPriority() + "," + this.getTimestamp() + "," + this.getAssignee() + "," + this.getCreatedBy());
    }

    public String[] printIssue() {
        String[] print = null;
        String str = this.getIssueID() + "," + this.getTitle() + "," + this.getStatus() + ","
                + this.getTag() + "," + this.getPriority() + "," + this.getTimestamp() + ","
                + this.getAssignee() + "," + this.getCreatedBy();
        print = str.split(",", 0);
        return print;
    }

    public String getIssuePage() {
        String s = "Issue ID: " + this.getIssueID() + "\tStatus: " + this.getStatus()
                + "\nTag: " + this.getStatus() + "\tPriority: " + this.getPriority() + "\tCreated On: " + this.getTimestamp()
                + "\nTitle: " + this.getTitle()
                + "\nAssigned to: " + this.getAssignee() + "\t\tCreated By: " + this.getCreatedBy()
                + "\nIssue Description\n----------------\n"
                + this.getDescriptionText() + "\n";
        return s;
    }

    public String getCommentPage() {
        String s = "Comments\n---------\n";
        if (!comments.isEmpty()) {
            for (Comment c : comments) {
                s += c.toString();
            }
        } else {
            s += "No Comments :(";
        }
        return s;
    }

    public void updateStatus(String newStatus) {
        String currentStatus = this.getStatus();
        if (canSetStatus(newStatus)) {
//            Date statusUTime = new Date();
            long statusUTime = Instant.now().getEpochSecond();
            historyIssue.add(new Update(currentStatus, newStatus, statusUTime));
            this.status = newStatus;
        } else {
            System.out.println("There's no such option!");
        }
    }

    public void updateComment(int Id, String newComment) {
        int id = Id - 1;
        if (id >= 0 && id < comments.size()) {
//            Date commentUTime = new Date();
            long commentUTime = Instant.now().getEpochSecond();
            historyIssue.add(new Update(id, newComment, commentUTime));
            this.comments.get(id).text = newComment;
        } else {
            System.out.println("Invalid Comment Id!");
        }
    }

    public void showHistory() {
        System.out.println("Activities History");
        System.out.println("-----------------------------------------\nLatest\n------");  //seperation line
        for (int i = historyIssue.size() - 1; i >= 0; i--) {
            if (historyIssue.get(i).getTitleU() != null) {                     //if have title stored(issue created)
                System.out.println((i + 1) + "# Created new issue with the title of \"" + historyIssue.get(i).getTitleU() + "\"\nat " + historyIssue.get(i).getTimeU());
            } else if (historyIssue.get(i).getStatusU() != null) {             //if have status stored(status updated)
                System.out.println((i + 1) + "# Updated issue status\nfrom \"" + historyIssue.get(i).getStatusBefore() + "\" to \"" + historyIssue.get(i).getStatusU() + "\"\nat " + historyIssue.get(i).getTimeU());
            } else if (historyIssue.get(i).getCommentU() != null) {            //if have comment stored(comment updated)
                System.out.println((i + 1) + "# Updated issue comment of\nID: " + historyIssue.get(i).getCommentIdU() + "\nComment updated: " + historyIssue.get(i).getCommentU() + "\nat " + historyIssue.get(i).getTimeU());
            } else if (historyIssue.get(i).getCommentTitleCreatedU() != null) { //if have title stored(comment created)
                System.out.println((i + 1) + "# Created new comment with the title of \"" + historyIssue.get(i).getCommentTitleCreatedU() + "\" by " + historyIssue.get(i).getCommentUserCreatedU() + "\nat " + historyIssue.get(i).getTimeU());
            }
            /*cannot implement project created, need to print seperately
            else if (historyIssue.get(i).getProjectNameU() != null) {        //if have project name stored(project created)
                System.out.println(historyIssue.get(i).getUpdateNumber() + "# Created new project with the title of \"" + historyIssue.get(i).getProjectNameU() + "\"\nat " + historyIssue.get(i).getTimeU());
            }*/
            System.out.println("------------------------------------------");
        }
        System.out.println("Oldest\n-------\n-----------------------------------------\n");
    }

    private boolean canSetStatus(String s) {
        boolean flag = false;
        if (this.getStatus().equalsIgnoreCase("Open") && (s.equalsIgnoreCase("In Progress") || s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Resolved"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("In Progress") && (s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Resolved") || s.equalsIgnoreCase("Open"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Resolved") && (s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("Reopened"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Reopened") && (s.equalsIgnoreCase("Resolved") || s.equalsIgnoreCase("Closed") || s.equalsIgnoreCase("In Progress"))) {
            flag = true;
        } else if (this.getStatus().equalsIgnoreCase("Closed") && s.equalsIgnoreCase("Reopen")) {
            flag = true;
        }
        return flag;
    }

    public void combinedIssueSection() {
        System.out.println(this.getIssuePage() + this.getCommentPage());

    }

    public int compareTo(Issue i) {
        if (sortType.equalsIgnoreCase("Status")) {
            return this.getStatus().compareTo(i.getStatus());
        } else if (sortType.equalsIgnoreCase("Id")) {
            return i.getIssueID() < this.getIssueID() ? 1 : -1;
        } else if (sortType.equalsIgnoreCase("Priority")) {
            return i.getPriority() < this.getPriority() ? 1 : -1;
        } else if (sortType.equalsIgnoreCase("Tag")) {
            return this.getTag().compareTo(i.getTag());
        } else if (sortType.equalsIgnoreCase("Time")) {
            return i.getTimestamp().compareTo(this.getTimestamp());
        } else {
            return 0;
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public int getIssueID() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getTag() {
        return tag;
    }

    public String getStatus() {
        return status;
    }

    public int getLastCommentNum() {
        return comments.size();
    }

    /**
     * Changed ** public String getTimestampIssue() { return
     * ft.format(timestamp); }
     */
//    public Date getTimestamp() {
//        return timestamp;
//    }
    public String getTimestamp() {
        return formatDate(timestamp);
    }

    private static String formatDate(long date) {
        // convert seconds to milliseconds
        Date systemDate = new java.util.Date(date * 1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        String formattedDate = sdf.format(systemDate);
        return formattedDate;
    }

    /*same as printSingleIssue()
    public String toString() {
        return this.getIssueNum() + " | " + this.getIssueTitle() + " | " + this.getStatus() + " | " + this.getTag() + " | \t" + this.getPriority() + " | " + this.getTimestampIssue() + " | " + this.getAssigneeUser() + "\t | " + this.getCreatorUser() + "\t|\n";   //write in main
    }
    /*
    public String deleteComment(){
        
    }
     */
}
