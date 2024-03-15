package lk.ijse.BookWorm.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.AdminDashBO;
import lk.ijse.BookWorm.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class UserDashboardFormController implements Initializable {

    @FXML
    private Label lblBookCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTime1;

    @FXML
    private Label lblUsers;

    @FXML
    private Pane pagingPane;

    AdminDashBO adminDashBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.AdminDashBO);

    @FXML
    void chngePwdOnAction(MouseEvent event) throws IOException {
        Navigation.switchNavigation1("userChange_PwdForm.fxml",event);
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        TimeNow();
        bookCount();
        userCount();
    }

    private void TimeNow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm ");
            SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM,  dd, yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat(" a");
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                String timenow1 = sdf1.format(new Date());
                final String timenow2 = sdf2.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(timenow);
                    lblTime1.setText(timenow2);
                    lblDate.setText(timenow1);
//                    labelTime.setStyle("-fx-font-size: 25px; -fx-text-fill: white");
//                    labelTime1.setText(timenow1);
//                    labelTime1.setStyle("-fx-font-size: 15px; -fx-text-fill: white");
                });
            }
        });
        thread.start();
    }

    public void bookCount(){
        lblBookCount.setText(String.valueOf(adminDashBO.bookCount()));
    }

    public void userCount(){
        lblUsers.setText(String.valueOf(adminDashBO.userCount()));
    }
}
