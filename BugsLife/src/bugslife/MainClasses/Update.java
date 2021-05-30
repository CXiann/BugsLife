package bugslife.MainClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Update {

    private transient char c;
    protected transient static int updateCount = 0;
    private int updateNumber;
    private String projectNameU;
    private String titleU;
    private String commentTitleCreatedU;
    private String commentUserCreatedU;
    private String commentU;
    private int commentIdU;
    private String statusBefore;
    private String statusU;
//    private Date timeU;
    private long timeU;

    public Update(int commentIdU, String commentU, long time) {    //update comment
        this.commentU = commentU;
        this.commentIdU = ++commentIdU;
        this.timeU = time;
        updateNumber = ++updateCount;
    }

    public Update(String commentTitleCreatedU, String commentUserCreatedU, long timeU, char c) {       //create comment
        this.c = c;
        this.commentTitleCreatedU = commentTitleCreatedU;
        this.commentUserCreatedU = commentUserCreatedU;
        this.timeU = timeU;
        updateNumber = ++updateCount;
    }

    public Update(String titleU, long time, char c) {  //creating issue
        this.titleU = titleU;
        this.c = c;
        this.timeU = time;
        updateNumber = ++updateCount;
    }

    public Update(String projectNameU, long time) {   //creating project
        this.timeU = time;
        this.projectNameU = projectNameU;
        updateNumber = ++updateCount;
    }

    public Update(String statusB, String statusU, long time) {    //update status
        this.statusU = statusU;
        this.timeU = time;
        this.statusBefore = statusB;
        updateNumber = ++updateCount;
    }

    public String getProjectNameU() {
        return projectNameU;
    }

    public int getUpdateNumber() {
        return updateNumber;
    }

//    public Date getTimeU() {
//        return timeU;
//    }
    public String getTimeU() {
        return formatDate(timeU);
    }

    public String getStatusBefore() {
        return statusBefore;
    }

    public String getCommentTitleCreatedU() {
        return commentTitleCreatedU;
    }

    public String getCommentUserCreatedU() {
        return commentUserCreatedU;
    }

    public String getTitleU() {
        return titleU;
    }

    public char getC() {
        return c;
    }

    public String getCommentU() {
        return commentU;
    }

    public String getStatusU() {
        return statusU;
    }

    public int getCommentIdU() {
        return commentIdU;
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
}
