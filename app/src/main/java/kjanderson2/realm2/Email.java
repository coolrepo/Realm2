package kjanderson2.realm2;

import io.realm.RealmObject;

/**
 * Created by kjanderson2 on 6/2/15.
 */
public class Email extends RealmObject {
    private String address;
    private boolean active;

    public Email(){

    }

    //When not specified, email address defaults to inactive)
    public Email(String address){
        this.address = address;
        this.active = false;
    }

    public Email(String address, boolean isActive){
        this.address = address;
        this.active = isActive;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setActive(boolean isActive){
        this.active = isActive;
    }

    public boolean getActive(){
        return this.active;
    }
}
