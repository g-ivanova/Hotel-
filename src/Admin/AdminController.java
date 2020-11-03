package Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    @FXML
    private Button new_owner;
    @FXML
    private Button showdata;


    public void addNewOwner(ActionEvent event) {
        Stage stage = (Stage) this.new_owner.getScene().getWindow();
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Admin/NewOwner.fxml").openStream());
            NewOwnerController newownercontroller = (NewOwnerController) loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Add new owner");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showData(ActionEvent event) {
        Stage stage = (Stage) this.showdata.getScene().getWindow();
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Admin/ShowData.fxml").openStream());
            ShowDataController showdatacontroller = (ShowDataController) loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Show Data");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}