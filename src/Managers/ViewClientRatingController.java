package Managers;

import Database.DBConnection;
import Clients.Client;
import Login.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewClientRatingController {
    @FXML
    private TableView rating_tableview;

    ObservableList<Integer> id_clients = FXCollections.observableArrayList();
    ObservableList<Client> clients = FXCollections.observableArrayList();

    public void initialize() {
        get_all_clients();

        String name = "";
        String number_res = "";
        String number_ser = "";
        String rate = "";

        for(int i=0; i< id_clients.size();i++){
            name = get_name_client(id_clients.get(i));
            number_res = Integer.toString(get_count_reservations(id_clients.get(i)));
            number_ser = Integer.toString(get_count_services(id_clients.get(i)));
            rate = Integer.toString(get_rating(number_res,number_ser));
            Login.LoginController.client=new Client(name,number_res,number_ser,rate);
            clients.add(Login.LoginController.client);
        }

        TableColumn<Client,String> name_client_tablecolumn = new TableColumn("Name Client");
        name_client_tablecolumn.setMinWidth(150);
        name_client_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("name_cl"));

        TableColumn<Client,String> number_reservations_tablecolumn = new TableColumn("Number Reservations");
        number_reservations_tablecolumn.setMinWidth(200);
        number_reservations_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("number_reservations"));


        TableColumn<Client,String> number_services_tablecolumn = new TableColumn("Number Services");
        number_services_tablecolumn.setMinWidth(150);
        number_services_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("number_services"));

        TableColumn<Client,String> rating_tablecolumn = new TableColumn("Rating");
        rating_tablecolumn.setMinWidth(150);
        rating_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("rating"));

        rating_tableview.getColumns().addAll(name_client_tablecolumn, number_reservations_tablecolumn, number_services_tablecolumn, rating_tablecolumn);
        rating_tableview.setItems(clients);
    }
    public void get_all_clients(){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getRooms = "SELECT * FROM clients c join reservations res on res.id_client=c.id_client where res.id_hotel='" + LoginController.hotel.get_ID_hotel() + "' Group by c.name_client";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getRooms);

            while (query.next()) {
                id_clients.add(query.getInt("id_client"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
    }
    public String get_name_client(int id_client){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getNameClient = "SELECT * from clients where id_client='" + id_client + "'";
        String name_client="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getNameClient);

             if (query.next()) {
                name_client = query.getString("name_client");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return name_client;
    }
    public int get_count_reservations(int id_client){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getCountReservations = "SELECT * from reservations where id_client='" + id_client + "' AND id_hotel='" + LoginController.hotel.get_ID_hotel() + "'";
        int count = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getCountReservations);

            while (query.next()) {
                count++;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return count;
    }
    public int get_count_services(int id_client){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getCountServices = "SELECT * from client_services where id_client='" + id_client + "' AND id_hotel='"+ LoginController.hotel.get_ID_hotel() + "'";
        int count = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getCountServices);

            while (query.next()) {
                count++;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return count;
    }
    public int get_rating(String count_reservations, String count_services){
        int number_reservations = Integer.parseInt(count_reservations);
        int number_services = Integer.parseInt(count_services);
        int reservation_rating = number_reservations*50;
        int service_rating = number_services*100;
        int rating = reservation_rating+service_rating;

        return rating;
    }
}
