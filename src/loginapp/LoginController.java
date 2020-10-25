package loginapp;


import Admin.AdminController;
import Manager.ManagerController;
import Owner.OwnerController;
import Receptionist.ReceptionistController;
import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

   Connection connection;
    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginstatus;




    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbstatus.setText("Connected");
        } else {
            this.dbstatus.setText("Not Connected");
        }

    }

    @FXML
    public void Login(ActionEvent event) throws SQLException {
            dbConnection connect = new dbConnection();
            Connection connection = connect.getConnection();
        String verifyLogin = "SELECT * FROM users  WHERE Username = '" +username.getText() + "' AND Password = '" + password.getText()+ "'";
            try {

                Statement statement = connection.createStatement();
                ResultSet query = statement.executeQuery(verifyLogin);
               if (query.next()) {
                   //while (query.next()) {
                       String user_name = query.getString("Username");
                       String pass = query.getString("Password");
                       if (user_name.equals(username.getText()) && pass.equals(password.getText())) {
                           Stage stage = (Stage) this.loginButton.getScene().getWindow();
                           stage.close();
                           String UID = query.getString("Position_ID"); //Get user ID of the user
                           switch (UID) {
                               case "1":
                                   AdminLogin();
                                   break;
                               case "2":
                                   OwnerLogin();
                                   break;
                               case "3":
                                   ManagerLogin();
                                   break;
                               case "4":
                                   ReceptionistLogin();
                                   break;
                           }

                       }
                   //}
               }
                else{
                    JOptionPane.showMessageDialog(null, "Wrong username/password");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }
        }

   public void ManagerLogin(){
           try {
               Stage userStage = new Stage();
               FXMLLoader loader = new FXMLLoader();
               Pane root = (Pane) loader.load(getClass().getResource("/Manager/Manager.fxml").openStream());
               ManagerController managercontroller = (ManagerController) loader.getController();

               Scene scene = new Scene(root);
               userStage.setScene(scene);
               userStage.setTitle("Manager Dashboard");
               userStage.setResizable(false);
               userStage.show();
           } catch (IOException e) {
               e.printStackTrace();
           }

       }
    public void OwnerLogin() {
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/Owner/Owner.fxml").openStream());
            OwnerController ownercontroller = (OwnerController) loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Owner Dashboard");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void ReceptionistLogin()
    {

            try {
                Stage userStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = (Pane) loader.load(getClass().getResource("/Receptionist/Receptionist.fxml").openStream());
                ReceptionistController receptionistcontroller = (ReceptionistController) loader.getController();

                Scene scene = new Scene(root);
                userStage.setScene(scene);
                userStage.setTitle("Receptionist Dashboard");
                userStage.setResizable(false);
                userStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public void AdminLogin() {
                try {
                    Stage userStage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    Pane root = (Pane) loader.load(getClass().getResource("/Admin/Admin.fxml").openStream());
                    AdminController admincontroller = (AdminController) loader.getController();

                    Scene scene = new Scene(root);
                    userStage.setScene(scene);
                    userStage.setTitle("Admin Dashboard");
                    userStage.setResizable(false);
                    userStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

