package Rooms;

public class Room {
    String id_room;
    String type_room;
    String number_of_use;
    String rating;

    public Room(String id_room, String type_room, String number_of_use, String rating){
        this.id_room=id_room;
        this.type_room=type_room;
        this.number_of_use=number_of_use;
        this.rating=rating;
    }
    public void setId_room(String id_room){
        if(id_room.length()>0){
            this.id_room=id_room;
        }
        else
        {
            throw new IllegalArgumentException("ID must not be empty");
        }
    }
    public String getId_room(){
        return this.id_room;
    }
    public void setType_room(String type_room){
        if(type_room.length()>0){
            this.type_room=type_room;
        }
        else
        {
            throw new IllegalArgumentException("Type of the room must not be empty");
        }
    }
    public String getType_room(){
        return this.type_room;
    }
    public String getNumber_of_use(){
        return this.number_of_use;
    }
    public void setRating(String rating){
        if(Integer.parseInt(rating)>=50){
            this.rating=rating;
        }
        else
        {
            throw new IllegalArgumentException("Rating must be bigger than or equal to 50");
        }
    }
    public String getRating(){
        return this.rating;
    }
}
