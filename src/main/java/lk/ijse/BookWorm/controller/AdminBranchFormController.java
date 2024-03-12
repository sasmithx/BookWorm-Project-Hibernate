package lk.ijse.BookWorm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.dto.BranchDTO;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.BranchBO;
import lk.ijse.BookWorm.tm.BranchTM;
import lk.ijse.BookWorm.util.DataValidateController;

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

    @FXML
    private Label branchEmailValidate;

    @FXML
    private Label branchIdValidate;

    @FXML
    private Label branchLocationValidate;

    @FXML
    private Label branchMobileValidate;

    @FXML
    private Label branchNameValidate;


    BranchBO branchBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BranchBO);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        new Alert(Alert.AlertType.CONFIRMATION,"Clear Successfully").show();
    }

    private void clearFields(){
        txtID.clear();
        txtName.clear();
        txtLocation.clear();
        txtMobile.clear();
        txtEmail.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(id);
        boolean deleted = branchBO.deleteBranches(branchDTO);
        if(deleted){
//            System.out.println("delete Successfully");
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted Successfully").show();
            loadAllBranches();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if(txtID.getText().isEmpty() || txtName.getText().isEmpty() || txtLocation.getText().isEmpty() || txtMobile.getText().isEmpty() || txtEmail.getText().isEmpty()){
            new Alert(Alert.AlertType.WARNING,"Please Fill All Empty Fileds Before Add New Branch !").show();
        }else {
            String id = txtID.getText();
            String name = txtName.getText();
            String location = txtLocation.getText();
            String mobile = txtMobile.getText();
            String email = txtEmail.getText();

            BranchDTO branchDTO = new BranchDTO(id,name,location,mobile,email);
           /* boolean saved = branchBO.saveBranches(branchDTO);
            if(saved){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
                loadAllBranches();
            }*/

            ////////////////////////////////////// VALIDATION ////////////////////////////////////////////////

            if(DataValidateController.branchIdValidate(txtID.getText())){
                branchIdValidate.setText("");

                if (DataValidateController.branchNameValidate(txtName.getText())) {
                    branchNameValidate.setText("");

                    if(DataValidateController.branchLocationValidate(txtLocation.getText())) {
                        branchLocationValidate.setText("");

                        if (DataValidateController.branchMobileValidate(txtMobile.getText())) {
                            branchMobileValidate.setText("");

                            if (DataValidateController.branchEmailValidate(txtEmail.getText())) {
                                branchEmailValidate.setText("");

                                    boolean saved = branchBO.saveBranches(branchDTO);
                                    if (saved) {
                                        new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
                                        loadAllBranches();
                                    }

                            } else {
                                branchEmailValidate.setText("Invalid Email !");
                            }

                        } else {
                            branchMobileValidate.setText("Invalid tel.Include 10 charcters !");
                        }
                    }else {
                        branchLocationValidate.setText("Invalid Location !");
                    }

                }else{
                    branchNameValidate.setText("Invalid Branch Name !");
                }

            }else {
                branchIdValidate.setText("Invalid branch Id !");
            }
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
            new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully").show();
            loadAllBranches();
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
