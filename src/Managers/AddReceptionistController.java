package Managers;

import Database.DBConnection;
import Hotels.HotelUsers;
import Login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddReceptionistController {
    @FXML
    private TextField name_textfield;
    @FXML
    private TextField username_textfield;
    @FXML
    private TextField password_textfield;
    @FXML
    private TextField phone_textfield;
    @FXML
    private Button add_button;

    public void add_button_on_action(ActionEvent event) {
        if (name_textfield.getText().equals("") || username_textfield.getText().equals("") || password_textfield.getText().equals("") || phone_textfield.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Fields can not be empty!");
        else {
            String name = name_textfield.getText();
            String username = username_textfield.getText();
            String password = password_textfield.getText();
            String phone = phone_textfield.getText();
            int id_position = 4;

            DBConnection connect = new DBConnection();
            Connection connection = connect.getConnection();

            String insertOwner = "INSERT INTO users (name, username, password, phone, id_position) VALUES ('" + name + "','" + username + "','" + password + "','" + phone + "','" + id_position + "')";
            LoginController.hotel_user = new HotelUsers();

            try {
                Statement statement = connection.createStatement();
                int check = statement.executeUpdate(insertOwner);
                if (check > 0) {
                    LoginController.hotel_user.add(LoginController.hotel.get_ID_hotel(),get_id_receptionist(name));
                    JOptionPane.showMessageDialog(null, "Receptionist added successfully!");
                } else
                    JOptionPane.showMessageDialog(null, "Invalid adding! Try Again!");
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    public int get_id_receptionist(String name){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getIDTypeRoom = "SELECT * FROM users where name='" + name + "'";
        int id_receptionist = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getIDTypeRoom);

            if (query.next()) {
                id_receptionist = query.getInt("id_user");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return id_receptionist;
    }
}
