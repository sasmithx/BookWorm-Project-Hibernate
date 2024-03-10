package lk.ijse.BookWorm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.dto.BranchDTO;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.BranchBO;

public class AdminBranchFormController {

    @FXML
    private Pane pagingPane;

    @FXML
    private TableView<?> tblBranch;

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

    public void initialize(){

    }

}
