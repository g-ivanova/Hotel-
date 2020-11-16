package Managers;

import Services.Service;
import Database.DBConnection;
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

public class ViewServiceRatingController {
    @FXML
    private TableView services_tableview;

    ObservableList<Integer> id_services = FXCollections.observableArrayList();
    ObservableList<Service> services = FXCollections.observableArrayList();

    public void initialize(){
        get_all_services();

        String name_s = "";
        String price_s = "";
        String number = "";
        String rate = "";

        for(int i=0; i< id_services.size();i++){
            name_s = get_name_service(id_services.get(i));
            price_s = Integer.toString(get_price_service(id_services.get(i)));
            number = Integer.toString(get_count_services(id_services.get(i)));
            rate = Integer.toString(get_rating(number));
            Login.LoginController.service=new Service(name_s,price_s,number,rate);
            services.add(Login.LoginController.service);
        }

        TableColumn<Service,String> name_service_tablecolumn = new TableColumn("Name Service");
        name_service_tablecolumn.setMinWidth(150);
        name_service_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("name_service"));

        TableColumn<Service,String> price_service_tablecolumn = new TableColumn("Price");
        price_service_tablecolumn.setMinWidth(150);
        price_service_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("price_service"));


        TableColumn<Service,String> number_of_use_tablecolumn = new TableColumn("Number of Uses");
        number_of_use_tablecolumn.setMinWidth(150);
        number_of_use_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("number_of_use"));

        TableColumn<Service,String> rating_tablecolumn = new TableColumn("Rating");
        rating_tablecolumn.setMinWidth(150);
        rating_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("rating"));

        services_tableview.getColumns().addAll(name_service_tablecolumn, price_service_tablecolumn, number_of_use_tablecolumn, rating_tablecolumn);
        services_tableview.setItems(services);

    }
    public void get_all_services(){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getRooms = "SELECT * FROM extra_services";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getRooms);

            while (query.next()) {
                id_services.add(query.getInt("id_service"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
    }
    public String get_name_service(int id_service){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getNameService = "SELECT * from extra_services where id_service='" + id_service + "'";
        String name_service="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getNameService);

            if (query.next()) {
                name_service = query.getString("name_service");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return name_service;
    }
    public int get_price_service(int id_service){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getPriceService = "SELECT * from extra_services where id_service='" + id_service + "'";
        int price_service=0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getPriceService);

            if (query.next()) {
                price_service = query.getInt("price_service");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return price_service;
    }
    public int get_count_services(int id_service){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getCountServices = "SELECT * from client_services where id_service='" + id_service + "' AND id_hotel='"+ LoginController.hotel.get_ID_hotel() + "'";
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
    public int get_rating(String count_services){
        int number = Integer.parseInt(count_services);
        int rating = number*50;

        return rating;
    }
}
