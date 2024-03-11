package lk.ijse.BookWorm.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import lk.ijse.BookWorm.dto.UserDTO;
import lk.ijse.BookWorm.service.BOFactory;
import lk.ijse.BookWorm.service.custom.UserBO;
import lk.ijse.BookWorm.tm.UserTM;
import lk.ijse.BookWorm.util.DataValidateController;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUserFormController implements Initializable {

    @FXML
    private Pane pagingPane;

    @FXML
    private DatePicker txtDob;

    @FXML
    private TableView<UserTM> tblUser;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtUserName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colMobile;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private Label userIdValiidate;

    @FXML
    private Label userAddressValiidate;

    @FXML
    private Label userEmailValiidate;

    @FXML
    private Label userMobileValiidate;

    @FXML
    private Label userNameValiidate;


    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UserBO);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        new Alert(Alert.AlertType.CONFIRMATION,"Clear Successfully").show();
    }

    private void clearFields(){
        txtID.clear();
        txtUserName.clear();
        txtMobile.clear();
        txtEmail.clear();
        txtAddress.clear();
        txtDob.setValue(null);
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String id = txtID.getText();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        boolean deleted = userBO.deleteUsers(userDTO);
        if(deleted){
            System.out.println("Delete Successfully");
            new Alert(Alert.AlertType.CONFIRMATION,"Delete Successfully").show();
            loadAllUsers();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        if(txtID.getText().isEmpty() || txtUserName.getText().isEmpty() || txtMobile.getText().isEmpty() || txtEmail.getText().isEmpty() || txtEmail.getText().isEmpty() || txtDob.getValue() == null){
            new Alert(Alert.AlertType.ERROR,"Please Fill All Empty Fileds Before Add New User !").show();
        }  else {
                String id = txtID.getText();
                String name = txtUserName.getText();
                String mobile = txtMobile.getText();
                String email = txtEmail.getText();
                String address = txtAddress.getText();
                LocalDate dob = txtDob.getValue();

                UserDTO userDTO = new UserDTO(id,name,mobile,email,address,dob);
                /*boolean saved = userBO.saveUsers(userDTO);
                if(saved){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved Successfully").show();
                loadAllUsers();*/


            //////////////////////////////////////////////////////////////////////////////////////

            if(DataValidateController.userIdValidate(txtID.getText())){
                userIdValiidate.setText("");

                if (DataValidateController.userNameValidate(txtUserName.getText())) {
                    userNameValiidate.setText("");

                    if (DataValidateController.userMobileValidate(txtMobile.getText())){
                        userMobileValiidate.setText("");

                        if(DataValidateController.emailCheck(txtEmail.getText())){
                            userEmailValiidate.setText("");

                            if(DataValidateController.addressValidate(txtAddress.getText())){
                                userAddressValiidate.setText("");
                                try {
                                    boolean saved = userBO.saveUsers(userDTO);
                                    if(saved){
                                        new Alert(Alert.AlertType.CONFIRMATION,"User Saved Successfully").show();
                                        loadAllUsers();
                                    }
                                } catch (SQLException e) {
                                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                                } /*catch (ClassNotFoundException e) {
                                    throw new RuntimeException(e);
                                }*/
                            }else {
                                userAddressValiidate.setText("Invalid Address !");
                            }


                        }else {
                            userEmailValiidate.setText("Invalid Email !");
                        }

                    }else {
                        userMobileValiidate.setText("Invalid tel.Include 10 charcters !");
                    }

                }else{
                    userNameValiidate.setText("Include atleast 4 characters !");
                }

            }else {
                userIdValiidate.setText("Invalid customer Id !");
            }



        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String id = txtID.getText();
        String name = txtUserName.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        LocalDate dob = txtDob.getValue();

        UserDTO userDTO = new UserDTO(id,name,mobile,email,address,dob);
        boolean updated = userBO.updateUsers(userDTO);
        if (updated){
            System.out.println("Update Successfully");
            new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully").show();
            loadAllUsers();
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllUsers();
    }

    private void setCellValueFactory(){
        colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
    }

    private void loadAllUsers() throws SQLException {
        tblUser.getItems().clear();
        ArrayList<UserDTO> allUsers = userBO.getAllUsers();
        for(UserDTO allUser : allUsers){
            tblUser.getItems().addAll(new UserTM(
                    allUser.getId(),
                    allUser.getName(),
                    allUser.getMobile(),
                    allUser.getEmail(),
                    allUser.getAddress(),
                    allUser.getDob()
            ));
        }
    }



    /*private void loadAllUsers() {
        ObservableList<UserTM> obList = FXCollections.observableArrayList();
            tblUser.getItems().clear();
            try {
            List<UserDTO> list = userBO.getAllUsers();
            for(UserDTO dto:list){
                UserTM userTM = new UserTM(
                        dto.getId(),
                        dto.getName(),
                        dto.getMobile(),
                        dto.getEmail(),
                        dto.getAddress(),
                        dto.getDob()
                );
                obList.add(userTM);
            }
            tblUser.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


}
