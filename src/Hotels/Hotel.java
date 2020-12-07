package Hotels;

public class Hotel {
    public int id_hotel;
    public String name_hotel;

    public Hotel(){ }
    public Hotel(int id_hotel, String name_hotel){
        this.id_hotel=id_hotel;
        this.name_hotel=name_hotel;
    }
    public  void set_id_hotel(int id_hotel){
        if(id_hotel>0){
            this.id_hotel=id_hotel;
        }
        else
        {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
    }
    public int get_ID_hotel(){
        return this.id_hotel;
    }

    public void set_name_hotel(String name_hotel){
        if(name_hotel.length()>3){
            this.name_hotel=name_hotel;
        }
        else
        {
            throw new IllegalArgumentException("Name of the hotel must be greater than 3 characters");
        }
    }
    public String get_name_hotel(){
        return this.name_hotel;
    }

}
