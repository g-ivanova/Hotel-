package Clients;

public class Client {
    public int id_client;
    public String name_client;
    public String address_client;
    public String phone_client;

    public Client(){}
    public Client(int id_client, String name_client, String address_client, String phone_client){
        this.id_client=id_client;
        this.name_client=name_client;
        this.address_client=address_client;
        this.phone_client=phone_client;
    }
    public int getId_client(){
        return this.id_client;
    }
    public String getName_client(){
        return this.name_client;
    }
    public String getAddress_client(){
        return this.address_client;
    }
    public String getPhone_client(){
        return this.phone_client;
    }
}
