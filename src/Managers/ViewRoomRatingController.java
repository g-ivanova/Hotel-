package Managers;

import Database.DBConnection;
import Login.LoginController;
import Rooms.Room;
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

public class ViewRoomRatingController {
    @FXML
    private TableView rooms_tableview;

    ObservableList<Integer> id_rooms = FXCollections.observableArrayList();
    ObservableList<Room> rooms = FXCollections.observableArrayList();

    public void initialize() {
        get_all_rooms();

        String id_ro="";
        String type="";
        String number_use="";
        String rate = "";

        for(int i=0; i< id_rooms.size();i++){
            id_ro = Integer.toString(id_rooms.get(i));
            type = get_type_room(id_rooms.get(i));
            number_use = Integer.toString(get_number_of_use(id_rooms.get(i)));
            rate = Integer.toString(get_rating(number_use));
            Login.LoginController.room=new Room(id_ro,type,number_use,rate);
            rooms.add(Login.LoginController.room);
        }

        TableColumn<Room,String> id_room_tablecolumn = new TableColumn("ID Room");
        id_room_tablecolumn.setMinWidth(150);
        id_room_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("id_room"));

        TableColumn<Room,String> type_room_tablecolumn = new TableColumn("Type");
        type_room_tablecolumn.setMinWidth(150);
        type_room_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("type_room"));

        TableColumn<Room,String> number_of_use_tablecolumn = new TableColumn("№ of uses");
        number_of_use_tablecolumn.setMinWidth(150);
        number_of_use_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("number_of_use"));

        TableColumn<Room,String> rating_tablecolumn = new TableColumn("Rating");
        rating_tablecolumn.setMinWidth(150);
        rating_tablecolumn.setCellValueFactory(
                new PropertyValueFactory<>("rating"));

        rooms_tableview.getColumns().addAll(id_room_tablecolumn,type_room_tablecolumn, number_of_use_tablecolumn, rating_tablecolumn);
        rooms_tableview.setItems(rooms);
    }
    public void get_all_rooms(){

        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getRooms = "SELECT * FROM rooms r where id_hotel='" + LoginController.hotel.get_ID_hotel() + "'";

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
    public String get_type_room(int id_room){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getTypeRoom = "SELECT * from room_types rt join rooms r on r.id_type_room=rt.id_type_room where id_room='" + id_room + "'";
        String name_type_room="";

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getTypeRoom);

            if (query.next()) {
                name_type_room = query.getString("name_type_room");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return name_type_room;
    }
    public int get_number_of_use(int id_room){
        DBConnection connect = new DBConnection();
        Connection connection = connect.getConnection();

        String getNumberUse = "SELECT * from reservation_room where id_room='" + id_room + "'";
        int number=0;

        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(getNumberUse);

            while (query.next()) {
                number++;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            e.getCause();
        }
        return number;
    }
    public int get_rating(String number_use){
        int number = Integer.parseInt(number_use);
        int rating = number*50;

        return rating;
    }
}
