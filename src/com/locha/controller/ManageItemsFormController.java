package com.locha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.locha.bo.BOFactory;
import com.locha.bo.custom.impl.ItemBOImpl;
import com.locha.dto.ItemDTO;
import com.locha.stages.StageList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ManageItemsFormController extends StageList {
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    @FXML
    private TableView<ItemDTO> tblItems;

    @FXML
    private TableColumn<ItemDTO, String> clmCode;

    @FXML
    private TableColumn<ItemDTO, String> clmDescription;

    @FXML
    private TableColumn<ItemDTO, String> clmQTY;

    @FXML
    private TableColumn<ItemDTO, String> clmPrice;

    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQTY;
    public JFXTextField txtPrice;

    ItemBOImpl itemBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ITEM);

    public void initialize() {
        generateItemCode();
        getAllItems();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void generateItemCode() {
        try {
            String lastId = itemBO.getLastId();
            int newId = Integer.parseInt(lastId.substring(1, 4))+1;
            if (newId < 10) {
                txtCode.setText("P00"+newId);
            }else if (newId < 100) {
                txtCode.setText("P0"+newId);
            }else {
                txtCode.setText("P"+newId);
            }
        } catch (Exception e) {
            txtCode.setText("P001");
        }
    }

    private void setTblItemCellValue() {
        clmCode.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("code"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("description"));
        clmQTY.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("qty"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<ItemDTO, String>("price"));
    }

    private void getAllItems() {
        try {
            List<ItemDTO> itemList = itemBO.getAll();
            ObservableList<ItemDTO> list = FXCollections.observableArrayList();
            list.addAll(itemList);
            tblItems.setItems(list);
            setTblItemCellValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            boolean isAdded = itemBO.addItem(new ItemDTO
                    (txtCode.getText(), txtDescription.getText(), Integer.parseInt(txtQTY.getText()), Double.parseDouble(txtPrice.getText())));
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item added!", ButtonType.OK).show();
                generateItemCode();
                getAllItems();
                txtDescription.clear();
                txtQTY.clear();
                txtPrice.clear();
            }else {
                new Alert(Alert.AlertType.ERROR, "error!", ButtonType.OK).show();
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdated = itemBO.updateItem(new ItemDTO(
                    txtCode.getText(), txtDescription.getText(), Integer.parseInt(txtQTY.getText()), Double.parseDouble(txtPrice.getText())));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item updated!", ButtonType.OK).show();
                generateItemCode();
                getAllItems();
                txtDescription.clear();
                txtQTY.clear();
                txtPrice.clear();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
            else {
                new Alert(Alert.AlertType.ERROR, "error!", ButtonType.OK).show();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            boolean isDeleted = itemBO.deleteItem(new ItemDTO(
                    txtCode.getText(), txtDescription.getText(), Integer.parseInt(txtQTY.getText()), Double.parseDouble(txtPrice.getText())));
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!", ButtonType.OK).show();
                generateItemCode();
                getAllItems();
                txtDescription.clear();
                txtQTY.clear();
                txtPrice.clear();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
            else {
                new Alert(Alert.AlertType.ERROR, "error!", ButtonType.OK).show();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        manageItemsFormStage.close();
        mainMenuFormStage.show();
    }

    public void tblItemsOnMouseClicked(MouseEvent mouseEvent) {
        try {
            txtCode.setText(tblItems.getSelectionModel().getSelectedItem().getCode());
            txtDescription.setText(tblItems.getSelectionModel().getSelectedItem().getDescription());
            txtQTY.setText(tblItems.getSelectionModel().getSelectedItem().getQty()+"");
            txtPrice.setText(tblItems.getSelectionModel().getSelectedItem().getPrice()+"");
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        } catch (NullPointerException ex) {

        }
    }
}
