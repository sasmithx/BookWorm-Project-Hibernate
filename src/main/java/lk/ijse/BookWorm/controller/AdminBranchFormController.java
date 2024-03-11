package lk.ijse.BookWorm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.dto.BranchDTO;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.BranchBO;
import lk.ijse.BookWorm.tm.BranchTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminBranchFormController implements Initializable {

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<BranchTM> tblBranch;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;


    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colName;


    BranchBO branchBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BranchBO);

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(id);
        boolean deleted = branchBO.deleteBranches(branchDTO);
        if(deleted){
//            System.out.println("delete Successfully");
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String location = txtLocation.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();

        BranchDTO branchDTO = new BranchDTO(id,name,location,mobile,email);
        boolean saved = branchBO.saveBranches(branchDTO);
        if(saved){
            System.out.println("Saved Successfully");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtID.getText();
        String name = txtName.getText();
        String location = txtLocation.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();

        BranchDTO branchDTO = new BranchDTO(id,name,location,mobile,email);
        boolean updated = branchBO.updatedBranches(branchDTO);
        if(updated){
            System.out.println("Update Successfully");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllBranches();
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadAllBranches() {
        ObservableList<BranchTM> obList = FXCollections.observableArrayList();
        tblBranch.getItems().clear();
        try {
            List<BranchDTO> list = branchBO.getAllBranches();
            for(BranchDTO dto:list){
                BranchTM branchTM = new BranchTM(
                        dto.getId(),
                        dto.getName(),
                        dto.getLocation(),
                        dto.getMobile(),
                        dto.getEmail()
                );
                obList.add(branchTM);
            }
            tblBranch.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
