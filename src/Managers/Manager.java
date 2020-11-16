package Managers;

public class Manager {
    public String name;
    public String username;
    public String password;
    public String phone;
    public int id_position;
    public int id_user;

    public Manager(){}
    public Manager(int id_user,String name,String username,String password,String phone){
        this.id_user=id_user;
        this.name=name;
        this.username=username;
        this.password=password;
        this.phone=phone;
        this.id_position=3;
    }
    public int get_id_user(){
        return this.id_user;
    }
    public void set_id_user(int id_user){
        this.id_user=id_user;
    }
}

