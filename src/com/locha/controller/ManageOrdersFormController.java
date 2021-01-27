package com.locha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.locha.bo.BOFactory;
import com.locha.bo.custom.CustomerBO;
import com.locha.bo.custom.ItemBO;
import com.locha.bo.custom.OrderDetailBO;
import com.locha.bo.custom.OrdersBO;
import com.locha.dto.*;
import com.locha.stages.StageList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageOrdersFormController extends StageList {
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCID;
    public JFXTextField txtQTY;
    public JFXButton btnRemove;
    public JFXButton btnAddtoCart;
    public JFXButton btnPlaceOrder;
    public TextField txtTotalPrice;
    ItemBO itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);
    CustomerBO customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    OrdersBO ordersBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERS);
    OrderDetailBO orderDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERDETAIL);
    @FXML
    private TableView<ItemDTO> tblItems;
    @FXML
    private TableColumn<ItemDTO, String> clmItemCode;
    @FXML
    private TableColumn<ItemDTO, String> clmDescription;
    @FXML
    private TableColumn<ItemDTO, String> clmItemQTY;
    @FXML
    private TableColumn<ItemDTO, String> clmPrice;
    @FXML
    private TableView<CustomeDTO> tblOrders;
    @FXML
    private TableColumn<CustomeDTO, String> clmCode;
    @FXML
    private TableColumn<CustomeDTO, String> clmQTY;
    @FXML
    private TableColumn<CustomeDTO, String> clmTotalPrice;
    private int tblOrdersSelectedRow;
    private final List<ItemDTO> itemList = new ArrayList<>();

    public void initialize() {
        getAllItem();
        generateCustomerId();
        generateOrderId();
        txtTotalPrice.setText("0.0");
        btnRemove.setDisable(true);
        btnAddtoCart.setDisable(true);
        btnPlaceOrder.setDisable(true);
    }

    private void getAllItem() {
        try {
            List<ItemDTO> all = itemBO.getAll();
            ObservableList<ItemDTO> list = FXCollections.observableArrayList();
            list.addAll(all);
            tblItems.setItems(list);
            setTblItemCellValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateCustomerId() {
        try {
            String lastId = customerBO.getLastId();
            int newId = Integer.parseInt(lastId.substring(1, 4)) + 1;
            if (newId < 10) {
                txtCID.setText("C00" + newId);
            } else if (newId < 100) {
                txtCID.setText("C0" + newId);
            } else {
                txtCID.setText("C" + newId);
            }
        } catch (Exception e) {
            txtCID.setText("C001");
        }
    }

    private String generateOrderId() {
        String generatedId;
        try {
            String lastId = ordersBO.getLastId();
            int newId = Integer.parseInt(lastId.substring(1, 4)) + 1;
            if (newId < 10) {
                generatedId = "D00" + newId;
            } else if (newId < 100) {
                generatedId = "D0" + newId;
            } else {
                generatedId = "D" + newId;
            }
        } catch (Exception e) {
            generatedId = "D001";
        }
        return generatedId;
    }

    private void setTblItemCellValue() {
        clmItemCode.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("code"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("description"));
        clmItemQTY.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("qty"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("price"));
    }

    private void setTblOrdersCellValue() {
        clmCode.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("code"));
        clmQTY.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("qty"));
        clmTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("totalPrice"));
    }

    private int isExist(String code) {
        for (int i = 0; i < tblOrders.getItems().size(); i++) {
            if (code.equals(tblOrders.getItems().get(i).getCode())) {
                return i;
            }
        }
        return -1;
    }

    public void btnAddtoCartOnAction(ActionEvent actionEvent) {
        int isExistRow = isExist(tblItems.getSelectionModel().getSelectedItem().getCode());
        if (isExistRow == -1) {
            String code = tblItems.getSelectionModel().getSelectedItem().getCode();
            int newQTY = Integer.parseInt(txtQTY.getText());
            double newPrice = newQTY * tblItems.getSelectionModel().getSelectedItem().getPrice();
            tblOrders.getItems().add(new CustomeDTO(code, newQTY, newPrice));
            setTblOrdersCellValue();

//            Update new ItemRow
            String itemDescription = tblItems.getSelectionModel().getSelectedItem().getDescription();
            int updatedQTY = tblItems.getSelectionModel().getSelectedItem().getQty() - newQTY;
            double oldPrice = tblItems.getSelectionModel().getSelectedItem().getPrice();
            ItemDTO item = new ItemDTO(code, itemDescription, updatedQTY, oldPrice);
            itemList.add(item);

//            Calculate Total
            txtTotalPrice.setText(calculateTotal() + "");
        }
        txtQTY.clear();
        btnPlaceOrder.setDisable(false);
        btnAddtoCart.setDisable(true);
    }

    private double calculateTotal() {
        double totalPrice = 0;
        for (int i = 0; i < tblOrders.getItems().size(); i++) {
            totalPrice += tblOrders.getItems().get(i).getTotalPrice();
        }
        return totalPrice;
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        CustomerDTO customer = new CustomerDTO(txtCID.getText(), txtName.getText(), txtAddress.getText());
        OrdersDTO order = new OrdersDTO(generateOrderId(), LocalDate.now().toString(), txtCID.getText());

        List<OrderDetailDTO> orderDetalList = new ArrayList<>();
        for (int i = 0; i < tblOrders.getItems().size(); i++) {
            String itemCode = tblOrders.getItems().get(i).getCode();
            int qty = tblOrders.getItems().get(i).getQty();
            double price = tblOrders.getItems().get(i).getTotalPrice();
            orderDetalList.add(new OrderDetailDTO(generateOrderId(), itemCode, qty, price));
        }

        try {
            ordersBO.placeOrder(customer, order, orderDetalList, itemList);
            new Alert(Alert.AlertType.CONFIRMATION, "Order placed!", ButtonType.OK).show();
            generateOrderId();
            generateCustomerId();
            tblOrders.getItems().clear();
            getAllItem();
            itemList.clear();
            txtName.clear();
            txtAddress.clear();
            txtTotalPrice.clear();
            btnPlaceOrder.setDisable(true);
        } catch (Exception e) {
//            e.printStackTrace();
            new Alert(Alert.AlertType.CONFIRMATION, "error", ButtonType.OK).show();
        }
    }

    public void btnRemoveItemOnAction(ActionEvent actionEvent) {
        try {
            tblOrders.getItems().remove(tblOrdersSelectedRow);
            txtTotalPrice.setText(calculateTotal() + "");
            btnRemove.setDisable(true);
            if (tblOrders.getItems().size() == 0) {
                btnPlaceOrder.setDisable(true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        manageOrdersFormStage.close();
        mainMenuFormStage.show();
    }

    public void tblItemsOnMouseClicked(MouseEvent mouseEvent) {
        try {
            btnAddtoCart.setDisable(false);
            txtQTY.setText(tblItems.getSelectionModel().getSelectedItem().getQty() + "");
        } catch (NullPointerException ex) {
            btnAddtoCart.setDisable(true);
        }
    }

    public void tblOrdersOnMouseClicked(MouseEvent mouseEvent) {
        try {
            btnRemove.setDisable(false);
            tblOrdersSelectedRow = tblOrders.getSelectionModel().getSelectedIndex();
            if (tblOrders.getItems().size() == 0) {
                btnRemove.setDisable(true);
            }
        } catch (NullPointerException ex) {
        }
    }
}
