package bugslife.MainClasses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Comment {

    protected transient static int commentCount = 0;
    private int commentId;
    protected String text;
    private List<React> react = new ArrayList<>(Arrays.asList(new React("angry"), new React("happy"), new React("thumbsUp"), new React("smile")));
    private Date timestamp;
    private String user;
//    private SimpleDateFormat ft;

    public Comment(String user, String text) {
        this.text = text;
        this.user = user;
        this.commentId = commentId;
        this.timestamp = new Date();
        commentCount++;
        commentId = commentCount;
    }

    public void increaseReactionCount(String s) {
        int index = getIndex(s);
        React r = react.get(index);
        r.increaseCount();
    }

    public String getAllReactionsData() {
        String s = "$$\n";
        for (int i = 0; i < react.size(); i++) {
            if (react.get(i).getCount() != 0) {
                s += react.get(i).getCount() + " people react with " + react.get(i).getReaction() + "\n";
            }
        }
        return s;
    }
    
    public int getIndex(String s) {
        int index = 0;
        switch (s) {
            case "angry" :
                index = 0;
                break;
            case "happy" :
                index = 1;
                break;
            case "thumbsUp" :
                index = 2;
                break;
            case "smile" :
                index = 3;
                break;
        }
        return index;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public int getCommentId() {
        return commentId;
    }
    
    public Date getTimestamp(){
        return timestamp;
    }
    
    /** Changed **
    public String getTimestamp() {
        ft =new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        return ft.format(timestampComment);
    }
    */
    
    public String toString() {
        String s = "#" + this.getCommentId() + "\tCreated on: " + this.getTimestamp() + "\tBy: " + this.getUser() + "\n"
                + this.getText() + "\n"
                + this.getAllReactionsData() + "\n";
        return s;

    }
    
    /*    
    public String getReactionType(String s) {
        int index = getIndex(s);
        React r = reaction.get(index);
        return r.getReaction();
    }

    public int getReactionCount(String s) {
        int index = getIndex(s);
        React r = reaction.get(index);
        return r.getCount();
    }

    
     */

}
