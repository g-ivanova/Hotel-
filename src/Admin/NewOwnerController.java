package Admin;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;

public class NewOwnerController {

    Connection connection;
    @FXML
    private TextField owner_name;
    @FXML
    private TextField owner_username;
    @FXML
    private TextField owner_password;
    @FXML
    private TextField owner_phone;
    @FXML
    private ComboBox hotel_combobox;
    @FXML
    private Button addnewowner;

    @FXML
    public void initialize() throws SQLException {
        ObservableList<String> hotels=FXCollections.observableArrayList();

        dbConnection connect = new dbConnection();
        Connection connection = connect.getConnection();

        String hotels_name = "Select name_hotel from hotels";
        try{

        Statement st = connection.createStatement();
        // int id_hot = 0;
        ResultSet rs = st.executeQuery(hotels_name);
        //int last_id=rs.getInt("id_user");
        while(rs.next()) {
            hotels.add(rs.getString("name_hotel"));
        }
        }
        catch(SQLException e){
            e.printStackTrace();
            e.getCause();
        }
        hotel_combobox.setItems(hotels);
    }
    public void AddOwner(ActionEvent event) throws SQLException {

        String ownername=owner_name.getText();
        String ownerusername=owner_username.getText();
        String ownerpassword=owner_password.getText();
        String ownerphone=owner_phone.getText();

        int hotel_index=hotel_combobox.getSelectionModel().getSelectedIndex();

        dbConnection connect = new dbConnection();
        Connection connection = connect.getConnection();
        boolean action = false;
      //  String hotel= "INSERT INTO hotel_users(id_user,id_hotel) VALUES (? , ?)";
        String newowner = "INSERT INTO users ( name, username, password, phone, id_position) VALUES (?, ?, ?, ?, 2)";
        try {
            Stage stage = (Stage) this.addnewowner.getScene().getWindow();
stage.close();

            PreparedStatement statement = connection.prepareStatement(newowner);
            statement.setString(1,ownername);
            statement.setString(2,ownerusername);
            statement.setString(3,ownerpassword);
            statement.setString(4,ownerphone);


            int affectedRows = statement.executeUpdate();
            if(affectedRows>0)
            {
                JOptionPane.showMessageDialog(null, "Succesfully added new owner");
                AddHotelUser(hotel_index);

            }
            else
                JOptionPane.showMessageDialog(null, "Wrong credentials");
        }
        catch(SQLException e){
            e.printStackTrace();

        }
    }

    public void AddHotelUser(int hotel) throws SQLException {

        dbConnection connect = new dbConnection();
        Connection connection = connect.getConnection();

        String max_id = "SELECT MAX(id_user) AS id_user FROM users";

        PreparedStatement st = connection.prepareStatement(max_id);
        int id_hotel = 0;
        ResultSet rs = st.executeQuery(max_id);

        if (rs.next()) {
            int user_id = rs.getInt("id_user");
            rs.close();

            switch (hotel) {
                case 0:
                    id_hotel=1;
                    break;
                case 1:
                id_hotel = 2;
                break;
                case 2:
                id_hotel = 3;
                break;
                case 3:
                id_hotel = 4;
                break;
                case 4:
                    id_hotel = 5;
                    break;
                case 5:
                    id_hotel = 6;
                    break;
                case 6:
                    id_hotel = 7;
                    break;

            }

            String addhotel = "INSERT INTO hotel_users(id_user,id_hotel) VALUES (?,?)";

            PreparedStatement statement1 = connection.prepareStatement(addhotel);
            statement1.setInt(1, user_id);
            statement1.setInt(2, id_hotel);
            statement1.executeUpdate();
            JOptionPane.showMessageDialog(null,"Successfully added owner to hotel");

        }

    }
    
      
}
