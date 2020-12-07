package Managers;

import Database.DBConnection;
import Login.LoginController;
import Reservations.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerController {
    @FXML
    private Button add_receptionist_button;
    @FXML
    private Button add_service_button;
    @FXML
    private ComboBox receptionists_combobox;
    @FXML
    private Button info_receptionist_button;
    @FXML
    private Button reservations_receptionist_button;
    @FXML
    private Button view_client_rating_button;
    @FXML
    private Button view_room_rating_button;
    @FXML
    private Button view_service_rating_button;
    @FXML
    private Button info_client_button;
    @FXML
    private Button reservations_client_button;
    @FXML
    private ComboBox clients_combobox;
    @FXML
    private TableView reservations_tableview;
    @FXML
    private Button logout_button;
    @FXML
    private ImageView imageview;

    ObservableList<Integer> id_reservations = FXCollections.observableArrayList();
    ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    ObservableList<Integer> id_rooms = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws IOException {
        Image image = new Image(new FileInputStream("C:/Users/HP/Desktop/managerlogo.png"));
        imageview.setImage(image);

        ObservableList<String> options = FXCollections.observableArrayList();

        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getReceptionists = "SELECT * FROM users u join hotel_users h on h.id_user=u.id_user where h.id_hotel='"+LoginController.hotel.get_ID_hotel() + "' AND u.id_position=4";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getReceptionists);

            while (query.next()) {
                options.add(query.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        receptionists_combobox.setItems(options);

        ObservableList<String> options2 = FXCollections.observableArrayList();

        String getClients = "SELECT * FROM clients c join reservations res on res.id_client=c.id_client where res.id_hotel='"+LoginController.hotel.get_ID_hotel() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getClients);

            while (query.next()) {
                options2.add(query.getString("name_client"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        clients_combobox.setItems(options2);
    }
    public void logout_button_on_action(ActionEvent event)throws IOException {
        Stage stage1 = (Stage) logout_button.getScene().getWindow();
        stage1.close();
        Parent root = FXMLLoader.load(getClass().getResource("/Login/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void add_receptionist_button_on_action(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Managers/add_receptionist.fxml"));
            Stage registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AddReceptionistController addreceptionistcontroller = (AddReceptionistController) loader.getController();
            registerStage.setTitle("ADD RECEPTIONIST");
            registerStage.setScene(new Scene(root, 520, 400));
            registerStage.show();
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error loading FXML!");
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    public void add_service_button_on_action(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Managers/add_service.fxml"));
            Stage registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AddServiceController addservicecontroller = (AddServiceController) loader.getController();
            registerStage.setTitle("ADD SERVICE");
            registerStage.setScene(new Scene(root, 520, 400));
            registerStage.show();
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error loading FXML!");
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    public void view_client_rating_button_on_action(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Managers/view_client_rating.fxml"));
            Stage registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            ViewClientRatingController viewclientratingcontroller = (ViewClientRatingController) loader.getController();
            registerStage.setTitle("View Client Rating");
            registerStage.setScene(new Scene(root, 700, 600));
            registerStage.show();
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error loading FXML!");
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    public void view_room_rating_button_on_action(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Managers/view_room_rating.fxml"));
            Stage registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            ViewRoomRatingController viewroomratingcontroller = (ViewRoomRatingController) loader.getController();
            registerStage.setTitle("View Room Rating");
            registerStage.setScene(new Scene(root, 700, 500));
            registerStage.show();
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error loading FXML!");
            ex.getMessage();
            ex.printStackTrace();
        }
    }

    public void view_service_rating_button_on_action(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Managers/view_service_rating.fxml"));
            Stage registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            ViewServiceRatingController viewserviceratingcontroller = (ViewServiceRatingController) loader.getController();
            registerStage.setTitle("View Service Rating");
            registerStage.setScene(new Scene(root, 700, 600));
            registerStage.show();
            root.requestFocus();
        } catch (Exception ex) {
            System.out.println("Error loading FXML!");
            ex.getMessage();
            ex.printStackTrace();
        }
    }
    public void info_receptionist_button_on_action(ActionEvent event){
        if(!check_receptionist()) {
            String name = receptionists_combobox.getValue().toString();

            DBConnection connect = new DBConnection();
            Connection connection = connect.getConnection();

            String getReceptionist = "SELECT * FROM users where name='" + name + "'";

            try {
                Statement statement = connection.createStatement();
                ResultSet query = statement.executeQuery(getReceptionist);

                if (query.next()) {
                    int id_user = query.getInt("id_user");
                    String phone = query.getString("phone");
                    String hotel = LoginController.hotel.get_name_hotel();
                    JOptionPane.showMessageDialog(null, "ID: " + id_user + "\nName: " + name + "\nPhone: " + phone + "\nHotel: " + hotel);
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    public void receptionists_combobox_on_action(ActionEvent event){
        clear_tableview();
    }
    public void reservations_receptionist_button_on_action(ActionEvent event){
        if(!check_receptionist()) {
            get_receptionist_reservations();
            get_receptionist_rooms();

            String id_res = "";
            String id_ro = "";
            String name_cl = "";
            String type_res = "";
            String date_res = "";
            String arr = "";
            String dep = "";

            for (int i = 0; i < id_reservations.size(); i++) {
                id_res = Integer.toString(id_reservations.get(i));
                name_cl = get_name_client(id_reservations.get(i));
                id_ro = Integer.toString(id_rooms.get(i));
                type_res = get_type_reservation(id_reservations.get(i));
                date_res = get_date_reservation(id_reservations.get(i));
                arr = get_arrival_date(id_reservations.get(i));
                dep = get_departure_date(id_reservations.get(i));
                Login.LoginController.reservation = new Reservation(id_res, name_cl, id_ro, type_res, date_res, arr, dep);
                reservations.add(Login.LoginController.reservation);
            }

            TableColumn<Reservation, String> id_reservation_tablecolumn = new TableColumn("№");
            id_reservation_tablecolumn.setMinWidth(50);
            id_reservation_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("id_reservation"));

            TableColumn<Reservation, String> name_client_tablecolumn = new TableColumn("Client");
            name_client_tablecolumn.setMinWidth(150);
            name_client_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("name_client"));

            TableColumn<Reservation, String> id_room_tablecolumn = new TableColumn("Room");
            id_room_tablecolumn.setMinWidth(50);
            id_room_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("id_room"));

            TableColumn<Reservation, String> type_reservation_tablecolumn = new TableColumn("Type");
            type_reservation_tablecolumn.setMinWidth(100);
            type_reservation_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("type_reservation"));

            TableColumn<Reservation, String> date_reservation_tablecolumn = new TableColumn("Date Reservation");
            date_reservation_tablecolumn.setMinWidth(130);
            date_reservation_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("date_reservation"));

            TableColumn<Reservation, String> arrival_date_tablecolumn = new TableColumn("Arrival Date");
            arrival_date_tablecolumn.setMinWidth(100);
            arrival_date_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("arrival_date"));

            TableColumn<Reservation, String> departure_date_tablecolumn = new TableColumn("Departure Date");
            departure_date_tablecolumn.setMinWidth(120);
            departure_date_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("departure_date"));

            reservations_tableview.getColumns().addAll(id_reservation_tablecolumn, name_client_tablecolumn, id_room_tablecolumn, type_reservation_tablecolumn, date_reservation_tablecolumn, arrival_date_tablecolumn, departure_date_tablecolumn);
            reservations_tableview.setItems(reservations);
        }
    }
    public void info_client_button_on_action(ActionEvent event){
        if(!check_client()) {
            String name_client = clients_combobox.getValue().toString();

            DBConnection connect = new DBConnection();
            Connection connection = connect.getConnection();

            String getClient = "SELECT * FROM clients where name_client='" + name_client + "'";

            try {
                Statement statement = connection.createStatement();
                ResultSet query = statement.executeQuery(getClient);

                if (query.next()) {
                    int id_client = query.getInt("id_client");
                    String address_client = query.getString("address_client");
                    String phone_client = query.getString("phone_client");
                    String hotel = LoginController.hotel.get_name_hotel();
                    JOptionPane.showMessageDialog(null, "ID: " + id_client + "\nName: " + name_client + "\nAddress: " + address_client + "\nPhone: " + phone_client + "\nHotel: " + hotel);
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    public void reservations_client_button_on_action(ActionEvent event){
        if(!check_client()) {
            get_client_reservations();
            get_client_rooms();

            String id_res = "";
            String id_ro = "";
            String name_cl = "";
            String type_res = "";
            String date_res = "";
            String arr = "";
            String dep = "";
            String name_rec;

            for (int i = 0; i < id_reservations.size(); i++) {
                id_res = Integer.toString(id_reservations.get(i));
                name_cl = get_name_client(id_reservations.get(i));
                name_rec = get_name_receptionist(id_reservations.get(i));
                id_ro = Integer.toString(id_rooms.get(i));
                type_res = get_type_reservation(id_reservations.get(i));
                date_res = get_date_reservation(id_reservations.get(i));
                arr = get_arrival_date(id_reservations.get(i));
                dep = get_departure_date(id_reservations.get(i));
                Login.LoginController.reservation = new Reservation(id_res, name_cl, name_rec, id_ro, type_res, date_res, arr, dep);
                reservations.add(Login.LoginController.reservation);
            }

            TableColumn<Reservation, String> id_reservation_tablecolumn = new TableColumn("№");
            id_reservation_tablecolumn.setMinWidth(50);
            id_reservation_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("id_reservation"));

            TableColumn<Reservation, String> name_client_tablecolumn = new TableColumn("Client");
            name_client_tablecolumn.setMinWidth(150);
            name_client_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("name_client"));

            TableColumn<Reservation, String> name_receptionist_tablecolumn = new TableColumn("Receptionist");
            name_receptionist_tablecolumn.setMinWidth(150);
            name_receptionist_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("name_receptionist"));

            TableColumn<Reservation, String> id_room_tablecolumn = new TableColumn("Room");
            id_room_tablecolumn.setMinWidth(50);
            id_room_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("id_room"));

            TableColumn<Reservation, String> type_reservation_tablecolumn = new TableColumn("Type");
            type_reservation_tablecolumn.setMinWidth(100);
            type_reservation_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("type_reservation"));

            TableColumn<Reservation, String> date_reservation_tablecolumn = new TableColumn("Date Reservation");
            date_reservation_tablecolumn.setMinWidth(130);
            date_reservation_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("date_reservation"));

            TableColumn<Reservation, String> arrival_date_tablecolumn = new TableColumn("Arrival Date");
            arrival_date_tablecolumn.setMinWidth(100);
            arrival_date_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("arrival_date"));

            TableColumn<Reservation, String> departure_date_tablecolumn = new TableColumn("Departure Date");
            departure_date_tablecolumn.setMinWidth(120);
            departure_date_tablecolumn.setCellValueFactory(
                    new PropertyValueFactory<>("departure_date"));

            reservations_tableview.getColumns().addAll(id_reservation_tablecolumn, name_client_tablecolumn, name_receptionist_tablecolumn, id_room_tablecolumn, type_reservation_tablecolumn, date_reservation_tablecolumn, arrival_date_tablecolumn, departure_date_tablecolumn);
            reservations_tableview.setItems(reservations);
        }
    }
    public void clients_combobox_on_action(ActionEvent event) {
        clear_tableview();
    }
    public void all_reservations_button_on_action(ActionEvent event){
        clear_tableview();
        get_all_reservations();
        get_all_rooms();

        String id_res="";
        String id_ro="";
        String name_cl = "";
        String type_res = "";
        String date_res = "";
        String arr = "";
        String dep = "";
        String name_rec;

        for(int i=0; i< id_reservations.size();i++){
            id_res = Integer.toString(id_reservations.get(i));
            name_cl = get_name_client(id_reservations.get(i));
            name_rec = get_name_receptionist(id_reservations.get(i));
            id_ro = Integer.toString(id_rooms.get(i));
            type_res = get_type_reservation(id_reservations.get(i));
            date_res = get_date_reservation(id_reservations.get(i));
            arr = get_arrival_date(id_reservations.get(i));
            dep = get_departure_date(id_reservations.get(i));
            Login.LoginController.reservation=new Reservation(id_res,name_cl,name_rec,id_ro,type_res,date_res,arr,dep);
            reservations.add(Login.LoginController.reservation);
        }

        TableColumn<Reservation,String> id_reservation_tablecolumn = new TableColumn("№");
        id_reservation_tablecolumn.setMinWidth(50);
        id_reservation_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("id_reservation"));

        TableColumn<Reservation,String> name_client_tablecolumn = new TableColumn("Client");
        name_client_tablecolumn.setMinWidth(150);
        name_client_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("name_client"));

        TableColumn<Reservation,String> name_receptionist_tablecolumn = new TableColumn("Receptionist");
        name_receptionist_tablecolumn.setMinWidth(150);
        name_receptionist_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("name_receptionist"));

        TableColumn<Reservation,String> id_room_tablecolumn = new TableColumn("Room");
        id_room_tablecolumn.setMinWidth(50);
        id_room_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("id_room"));

        TableColumn<Reservation,String> type_reservation_tablecolumn = new TableColumn("Type");
        type_reservation_tablecolumn.setMinWidth(100);
        type_reservation_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("type_reservation"));

        TableColumn<Reservation,String> date_reservation_tablecolumn = new TableColumn("Date Reservation");
        date_reservation_tablecolumn.setMinWidth(130);
        date_reservation_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("date_reservation"));

        TableColumn<Reservation,String> arrival_date_tablecolumn = new TableColumn("Arrival Date");
        arrival_date_tablecolumn.setMinWidth(100);
        arrival_date_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("arrival_date"));

        TableColumn<Reservation,String> departure_date_tablecolumn = new TableColumn("Departure Date");
        departure_date_tablecolumn.setMinWidth(120);
        departure_date_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("departure_date"));

        reservations_tableview.getColumns().addAll( id_reservation_tablecolumn, name_client_tablecolumn, name_receptionist_tablecolumn, id_room_tablecolumn, type_reservation_tablecolumn, date_reservation_tablecolumn, arrival_date_tablecolumn, departure_date_tablecolumn);
        reservations_tableview.setItems(reservations);
    }
    public void clear_tableview(){
        reservations_tableview.getItems().clear();
        reservations_tableview.getColumns().clear();
        id_reservations.clear();
        reservations.clear();
        id_rooms.clear();
    }
    public void get_receptionist_reservations(){
        String name_receptionist = receptionists_combobox.getValue().toString();
        int id_receptionist = get_id_receptionist(name_receptionist);
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getReservations = "SELECT * FROM reservation_room rr join reservations res on res.id_reservation=rr.id_reservation where id_user='" + id_receptionist +"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getReservations);

            while (query.next()) {
                id_reservations.add(query.getInt("id_reservation"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
    }
    public void get_receptionist_rooms(){
        String name_receptionist = receptionists_combobox.getValue().toString();
        int id_receptionist = get_id_receptionist(name_receptionist);
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getRooms = "SELECT * FROM reservation_room rr join reservations res on res.id_reservation=rr.id_reservation where id_user='" + id_receptionist +"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getRooms);

            while (query.next()) {
                id_rooms.add(query.getInt("id_room"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
    }
    public int get_id_receptionist(String name_receptionist) {
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getID_receptionist = "SELECT * FROM users where name='" + name_receptionist + "'";
        int id_receptionist = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getID_receptionist);

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
    public String get_name_client(int id_reservation){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getName_client = "SELECT * FROM clients c join reservations res on res.id_client=c.id_client where res.id_reservation='" + id_reservation + "'";

        String name_client="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getName_client);

            if (query.next()) {
                name_client=query.getString("name_client");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return name_client;
    }
    public String get_type_reservation(int id_reservation){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getID_typeres = "SELECT * FROM reservations where id_reservation='" + id_reservation + "'";
        int id_type_res=0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getID_typeres);

            if (query.next()) {
                id_type_res=query.getInt("id_type_reservation");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }

        String getName_typeres = "SELECT * FROM type_reservation where id_type_reservation='" + id_type_res + "'";
        String name_type_res="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getName_typeres);

            if (query.next()) {
                name_type_res=query.getString("name_type_reservation");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return name_type_res;
    }
    public String get_date_reservation(int id_reservation){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getDateRes = "SELECT * FROM reservations where id_reservation='" + id_reservation + "'";
        String date_res="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getDateRes);

            if (query.next()) {
                date_res=query.getString("date_reservation");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return date_res;
    }
    public String get_arrival_date(int id_reservation){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getArrivalDate = "SELECT * FROM reservation_room where id_reservation='" + id_reservation + "'";
        String arrival="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getArrivalDate);

            if (query.next()) {
                arrival=query.getString("arrival_date");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return arrival;
    }
    public String get_departure_date(int id_reservation){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getDepartureDate = "SELECT * FROM reservation_room where id_reservation='" + id_reservation + "'";
        String departure="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getDepartureDate);

            if (query.next()) {
                departure=query.getString("departure_date");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return departure;
    }
    public void get_client_reservations(){
        String name_client = clients_combobox.getValue().toString();
        int id_client = get_id_client(name_client);

        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getReservations = "SELECT * FROM reservation_room rr join reservations res on res.id_reservation=rr.id_reservation where res.id_client='" + id_client +"' AND res.id_hotel='" + LoginController.hotel.get_ID_hotel() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getReservations);

            while (query.next()) {
                id_reservations.add(query.getInt("id_reservation"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
    }
    public int get_id_client(String name_client) {
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getID_client = "SELECT * FROM clients where name_client='" + name_client + "'";
        int id_client = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getID_client);

            if (query.next()) {
                id_client = query.getInt("id_client");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return id_client;
    }
    public void get_client_rooms(){
        String name_client = clients_combobox.getValue().toString();
        int id_client = get_id_client(name_client);

        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getRooms = "SELECT * FROM reservation_room rr join reservations res on res.id_reservation=rr.id_reservation where id_client='" + id_client +"' AND id_hotel='" + LoginController.hotel.get_ID_hotel() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getRooms);

            while (query.next()) {
                id_rooms.add(query.getInt("id_room"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
    }
    public String get_name_receptionist(int id_reservation){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getName_receptionist = "SELECT * FROM users u join reservations res where res.id_reservation='" + id_reservation + "'";
        String name_receptionist="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getName_receptionist);

            if (query.next()) {
                name_receptionist=query.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return name_receptionist;
    }

    public void get_all_reservations(){
        clear_tableview();
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getReservations = "SELECT * FROM reservation_room rr join reservations res on res.id_reservation=rr.id_reservation where res.id_hotel='" + LoginController.hotel.get_ID_hotel() + "'";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getReservations);

            while (query.next()) {
                id_reservations.add(query.getInt("id_reservation"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
    }
    public void get_all_rooms(){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getRooms = "SELECT * FROM reservation_room rr join reservations res on res.id_reservation=rr.id_reservation where res.id_hotel='" + LoginController.hotel.get_ID_hotel() +"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getRooms);

            while (query.next()) {
                id_rooms.add(query.getInt("id_room"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
    }
    public boolean check_receptionist(){
        boolean empty=true;
        if(receptionists_combobox.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("You must choose receptionist first!");
            alert.showAndWait();
            empty=true;
        }
        else
            empty=false;

        return empty;
    }
    public boolean check_client(){
        boolean empty=true;
        if(clients_combobox.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("You must choose client first!");
            alert.showAndWait();
            empty=true;
        }
        else
            empty=false;

        return empty;
    }
    
      public static void notifications_client() {
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();
        try {
            String manager_id = "Select id_hotel from hotel_users where id_user = '" + LoginController.user.getId_user() + "'";
            Statement statement2 = connection.createStatement();
            ResultSet query2 = statement2.executeQuery(manager_id);

            while (query2.next()) {
                int id_hotel = query2.getInt("id_hotel");

                String sql = "SELECT id_client from reservations where id_hotel='" + id_hotel + "' Group by id_client";
                Statement statement = connection.createStatement();
                ResultSet query = statement.executeQuery(sql);

                while (query.next()) {
                    int client=query.getInt("id_client");
                    Client clients=new Client();
                    clients.setId_client(client);
                    int count_res=Managers.ViewClientRatingController.get_count_reservations(client);
                    String countres=String.valueOf(count_res);
                    int count_ser=Managers.ViewClientRatingController.get_count_services(client);
                    String countser=String.valueOf(count_ser);
                    int rating=Managers.ViewClientRatingController.get_rating(countres,countser);
                    if(rating<200){
                        Notifications notificationBuilder = Notifications.create()
                                .title("Risky Client")
                                .text("Reservation with ID " + query.getInt("id_client") + " is made from risky client!")
                                .graphic(null)
                                .hideAfter(Duration.seconds(5))
                                .position(Pos.TOP_RIGHT);
                        notificationBuilder.darkStyle();
                        notificationBuilder.showWarning();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
