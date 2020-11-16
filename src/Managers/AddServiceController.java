package Managers;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddServiceController {
    @FXML
    private TextField name_textfield;
    @FXML
    private TextField price_textfield;
    @FXML
    private ComboBox seasons_combobox;
    @FXML
    private Button add_button;

    public void initialize(){
        ObservableList<String> options = FXCollections.observableArrayList();

        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getSeasons = "SELECT * FROM seasons";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getSeasons);

            while (query.next()) {
                options.add(query.getString("name_season"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        seasons_combobox.setPromptText("Choose:");
        seasons_combobox.setItems(options);
    }
    public int get_id_season(String name_season){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getSeasons = "SELECT * FROM seasons where name_season='" + name_season + "'";
        int id_season = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getSeasons);

            if (query.next()) {
                id_season = query.getInt("id_season");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return id_season;
    }
    public void add_button_on_action(ActionEvent event){
        if(name_textfield.getText().equals("") || price_textfield.getText().equals(""))
            JOptionPane.showMessageDialog(null, "Fields can not be empty!");
        else{
            String name_season = seasons_combobox.getValue().toString();
            int id_season = get_id_season(name_season);
            int price = Integer.parseInt(price_textfield.getText());
            String name = name_textfield.getText();

            DBConnection connect = new DBConnection();
            Connection connection = connect.getConnection();

            String addService = "INSERT INTO extra_services (name_service, price_service, id_season) VALUES ('" + name + "','" + price + "','" +id_season+ "')";

            try {
                Statement statement = connection.createStatement();
                int check = statement.executeUpdate(addService);
                if (check > 0)
                    JOptionPane.showMessageDialog(null,"Service added succesfully!");
                else
                    JOptionPane.showMessageDialog(null,"An error occured! Please try again!");
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}
