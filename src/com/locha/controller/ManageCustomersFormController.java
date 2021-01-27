package com.locha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.locha.bo.BOFactory;
import com.locha.bo.custom.CustomerBO;
import com.locha.dto.CustomerDTO;
import com.locha.stages.StageList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ManageCustomersFormController extends StageList {
    public JFXButton btnUpdate;
    @FXML
    private TableView<CustomerDTO> tblCustomers;

    @FXML
    private TableColumn<CustomerDTO, String> clmID;

    @FXML
    private TableColumn<CustomerDTO, String> clmName;

    @FXML
    private TableColumn<CustomerDTO, String> clmAddress;

    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public TextField txtSearch;

    CustomerBO customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        getAllCustomers();
        btnUpdate.setDisable(true);
    }

    private void getAllCustomers() {
        try {
            List<CustomerDTO> customerList = customerBO.getAll();
            ObservableList<CustomerDTO> list = FXCollections.observableArrayList();
            list.addAll(customerList);
            tblCustomers.setItems(list);
            setTblCustomerCellValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTblCustomerCellValue() {
        clmID.setCellValueFactory(new PropertyValueFactory<CustomerDTO, String>("cid"));
        clmName.setCellValueFactory(new PropertyValueFactory<CustomerDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<CustomerDTO, String>("address"));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            String cid = tblCustomers.getSelectionModel().getSelectedItem().getCid();
            customerBO.update(new CustomerDTO(cid, txtName.getText(), txtAddress.getText()));

            new Alert(Alert.AlertType.CONFIRMATION, "Customer updated!", ButtonType.OK).show();
            getAllCustomers();
            txtName.clear();
            txtAddress.clear();
            btnUpdate.setDisable(true);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "error", ButtonType.OK).show();
//            e.printStackTrace();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        try {
            CustomerDTO customer = customerBO.searchCustomer(txtSearch.getText());
            ObservableList<CustomerDTO> list = FXCollections.observableArrayList();
            list.add(customer);
            tblCustomers.setItems(list);
            setTblCustomerCellValue();
        } catch (Exception e) {

        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        manageCustomersFormStage.close();
        mainMenuFormStage.show();
    }

    public void tblCustomersOnMouseClicked(MouseEvent mouseEvent) {
        try {
            btnUpdate.setDisable(false);
            txtName.setText(tblCustomers.getSelectionModel().getSelectedItem().getName());
            txtAddress.setText(tblCustomers.getSelectionModel().getSelectedItem().getAddress());
        } catch (NullPointerException e) {
        }
    }

    public void btnRefreshTable(ActionEvent actionEvent) {
        getAllCustomers();
        btnUpdate.setDisable(true);
        txtName.clear();
        txtAddress.clear();
        txtSearch.clear();
    }
}
