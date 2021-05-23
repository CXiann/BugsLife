
package bugslife;


import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Comment2 {

@SerializedName("comment_id")
@Expose
private Integer commentId;
@SerializedName("text")
@Expose
private String text;
@SerializedName("react")
@Expose
private List<React> react = null;
@SerializedName("timestamp")
@Expose
private Integer timestamp;
@SerializedName("user")
@Expose
private String user;

public Integer getCommentId() {
return commentId;
}

public void setCommentId(Integer commentId) {
this.commentId = commentId;
}

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

public List<React> getReact() {
return react;
}

public void setReact(List<React> react) {
this.react = react;
}

public Integer getTimestamp() {
return timestamp;
}

public void setTimestamp(Integer timestamp) {
this.timestamp = timestamp;
}

public String getUser() {
return user;
}

public void setUser(String user) {
this.user = user;
}

}
