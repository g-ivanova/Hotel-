package Services;

public class Service {
    public String name_service;
    public String price_service;
    public String number_of_use;
    public String rating;

    public Service(String name_service, String price_service, String number_of_use, String rating){
        this.name_service=name_service;
        this.price_service=price_service;
        this.number_of_use=number_of_use;
        this.rating=rating;
    }
    public void setName_service(String name_service){
        if(name_service.length()>2){
            this.name_service=name_service;
        }
        else
        {
            throw new IllegalArgumentException("Name of the service must be greater than 2 characters");
        }
    }
    public String getName_service(){
        return this.name_service;
    }

    public void setPrice_service(String price_service){
        if(price_service!=""){
            this.price_service=price_service;
        }
        else
        {
            throw new IllegalArgumentException("Price of the services must not be empty");
        }
    }
    public String getPrice_service(){
        return this.price_service;
    }
    public String getNumber_of_use(){
        return this.number_of_use;
    }
    public String getRating(){
        return this.rating;
    }
}
