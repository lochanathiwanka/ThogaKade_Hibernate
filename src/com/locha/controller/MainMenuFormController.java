package com.locha.controller;

import com.locha.stages.StageList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenuFormController extends StageList {
    public void btnManageItems(ActionEvent actionEvent) throws IOException {
        mainMenuFormStage.close();
        manageItemsFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageItemsForm.fxml"))));
        manageItemsFormStage.setResizable(false);
        manageItemsFormStage.show();
    }

    public void btnManageOrders(ActionEvent actionEvent) throws IOException {
        mainMenuFormStage.close();
        manageOrdersFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageOrdersForm.fxml"))));
        manageOrdersFormStage.setResizable(false);
        manageOrdersFormStage.show();
    }

    public void btnManageCustomers(ActionEvent actionEvent) throws IOException {
        mainMenuFormStage.close();
        manageCustomersFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageCustomersForm.fxml"))));
        manageCustomersFormStage.setResizable(false);
        manageCustomersFormStage.show();
    }
}
