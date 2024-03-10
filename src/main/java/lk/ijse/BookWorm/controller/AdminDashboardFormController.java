package lk.ijse.BookWorm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.util.Navigation;

import java.io.IOException;

public class AdminDashboardFormController {

    @FXML
    private BarChart<?, ?> barChart;

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

    @FXML
    private PieChart pieChart;

    @FXML
    void chngePwdOnAction(MouseEvent event) throws IOException {
        Navigation.switchNavigation1("adminChange_PwdForm.fxml",event);
    }

}
