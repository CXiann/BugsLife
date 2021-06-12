package bugslife.Controllers;

import bugslife.MainClasses.BugsLife;
import bugslife.MainClasses.Issue;
import bugslife.MainClasses.Comment;
import bugslife.MainClasses.Project;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class IssuePageUIController implements Initializable {

    @FXML
    private Label titleLabel;
    @FXML
    private TextField textTitle;
    @FXML
    private Text desText;
    @FXML
    private TextField textDes;
    @FXML
    private Label assigneeLabel;
    @FXML
    private TextField textAssignee;
    @FXML
    private Label tagLabel;
    @FXML
    private TextField textTag;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField textStatus;
    @FXML
    private Label priorityLabel;
    @FXML
    private TextField textPriority;
    @FXML
    private Text textHistory;
    @FXML
    private Label idLabel;
    @FXML
    private Label createdByLabel;
    @FXML
    private Label createDateLabel;
    @FXML
    private Text textComment;

    private BugsLife b = new BugsLife();
    private Project p = ProjectUIController.selectedProject;
    private Issue i = IssueUIController.selectedIssue;
    @FXML
    private RadioButton editComment;
    @FXML
    private RadioButton addNewComment;
    @FXML
    private TextField commentIDEditLabel;
    @FXML
    private JFXRadioButton angry;
    @FXML
    private ToggleGroup react;
    @FXML
    private JFXRadioButton happy;
    @FXML
    private JFXRadioButton thumbsUp;
    @FXML
    private JFXRadioButton smile;
    @FXML
    private AnchorPane commentPagePrompt;
    @FXML
    private ToggleGroup toggleComment;
    @FXML
    private TextField textEditComment;
    @FXML
    private TextField commentIDReactLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Comment.commentCount = i.getLastCommentNum();
        idLabel.setText("Issue #" + i.getId());
        createDateLabel.setText("Created on: " + i.getIssueTime());
        createdByLabel.setText("Created By: " + i.getCreatedBy());
        titleLabel.setText(i.getTitle());
        statusLabel.setText("Status: " + i.getStatus());
        priorityLabel.setText("Priority: " + i.getPriority());
        tagLabel.setText("Tag: " + i.getTag());
        assigneeLabel.setText("Assignee: " + i.getAssignee());
        desText.setText("Description:\n" + i.getDescriptionText());
        editComment.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                commentIDEditLabel.setVisible(true);
            }
        });
        addNewComment.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() > 0) {
                commentIDEditLabel.setVisible(false);
            }
        });
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void editDescription(MouseEvent event) {
        if (!textDes.isVisible()) {
            desText.setVisible(false);
            textDes.setVisible(true);
        } else {
            textDes.setVisible(false);
            i.updateDescription(textDes.getText());
            desText.setText("Description: \n" + i.getDescriptionText());
            desText.setVisible(true);
        }
    }

    @FXML
    private void editAssignee(MouseEvent event) {
        if (!textAssignee.isVisible()) {
            assigneeLabel.setVisible(false);
            textAssignee.setVisible(true);
        } else {
            textAssignee.setVisible(false);
            i.updateAssignee(textAssignee.getText());
            assigneeLabel.setText("Assignee: " + i.getAssignee());
            assigneeLabel.setVisible(true);
        }
    }

    @FXML
    private void editStatus(MouseEvent event) {
        if (!textStatus.isVisible()) {
            statusLabel.setVisible(false);
            textStatus.setVisible(true);
        } else {
            textStatus.setVisible(false);
            i.updateStatus(textStatus.getText());
            statusLabel.setText("Status: " + i.getStatus());
            statusLabel.setVisible(true);
        }
    }

    @FXML
    private void editTag(MouseEvent event) {
        if (!textTag.isVisible()) {
            tagLabel.setVisible(false);
            textTag.setVisible(true);
        } else {
            textTag.setVisible(false);
            i.updateTag(textTag.getText());
            tagLabel.setText("Tag: " + i.getTag());
            tagLabel.setVisible(true);
        }
    }

    @FXML
    private void editPriority(MouseEvent event) {
        if (!textPriority.isVisible()) {
            priorityLabel.setVisible(false);
            textPriority.setVisible(true);
        } else {
            textPriority.setVisible(false);
            i.updatePriority(textPriority.getText());
            priorityLabel.setText("Priority: " + textPriority.getText());
            priorityLabel.setVisible(true);
        }
    }

    @FXML
    private void editTitle(MouseEvent event) {
        if (!textTitle.isVisible()) {
            titleLabel.setVisible(false);
            textTitle.setVisible(true);
        } else {
            textTitle.setVisible(false);
            i.updateTitle(textTitle.getText());
            titleLabel.setText(i.getTitle());
            titleLabel.setVisible(true);
        }

    }

    @FXML
    private void backToIssueUI(MouseEvent event) throws Exception {
        b.changeScene("bugslife/FXML/IssueUI.fxml");
    }

    @FXML
    private void showHistory(Event event) {
        textHistory.setText(i.showHistory());
    }

    @FXML
    private void showComment(Event event) {
        textComment.setText(i.getCommentPage());
    }

    @FXML
    private void modifyCommentPage(ActionEvent event) {
        if (!commentPagePrompt.isVisible()) {
            commentPagePrompt.setVisible(true);
        }
    }

    @FXML
    private void backToIssuePageUI(ActionEvent event) {
        if (commentPagePrompt.isVisible()) {
            if (toggleComment.getSelectedToggle() != null) {
                if (toggleComment.getSelectedToggle().equals(addNewComment) && !textEditComment.getText().isEmpty()) {
                    i.addComment(LoginPageController.usernameC, textEditComment.getText());
                } else if (toggleComment.getSelectedToggle().equals(editComment) && !textEditComment.getText().isEmpty() && !commentIDEditLabel.getText().isEmpty()) {
                    i.updateComment(Integer.parseInt(commentIDEditLabel.getText()), textEditComment.getText());
                }
            }
            if (react.getSelectedToggle() != null) {
                if (react.getSelectedToggle().equals(angry)) {
                    i.addReaction(Integer.parseInt(commentIDReactLabel.getText()), "angry");
                } else if (react.getSelectedToggle().equals(smile)) {
                    i.addReaction(Integer.parseInt(commentIDReactLabel.getText()), "smile");
                } else if (react.getSelectedToggle().equals(happy)) {
                    i.addReaction(Integer.parseInt(commentIDReactLabel.getText()), "happy");
                } else if (react.getSelectedToggle().equals(thumbsUp)) {
                    i.addReaction(Integer.parseInt(commentIDReactLabel.getText()), "thumbsUp");
                }
            }
            commentPagePrompt.setVisible(false);
        }
    }

    @FXML
    private void closeCommentPagePrompt(MouseEvent event) {
        if (commentPagePrompt.isVisible()) {
            commentPagePrompt.setVisible(false);
        }
    }

    @FXML
    private void cleartoggles(MouseEvent event) {
        commentIDEditLabel.setVisible(false);
        angry.setSelected(false);
        smile.setSelected(false);
        thumbsUp.setSelected(false);
        happy.setSelected(false);
        editComment.setSelected(false);
        addNewComment.setSelected(false);
    }

}
