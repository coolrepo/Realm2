package kjanderson2.realm2;

import io.realm.RealmObject;

/**
 * Created by kjanderson2 on 6/2/15.
 * This is the Model class for a Telephone object.
 */
public class Telephone extends RealmObject{
    private String number;
    private boolean active;
    private String type;

    public Telephone(){

    }


    public Telephone(String number, boolean active){
        this.number = number;
        this.active = active;
        this.type = "home";
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getNumber(){
        return this.number;
    }

    public void setActive(boolean isActive){
        this.active = isActive;
    }

    public boolean getActive(){
        return this.active;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
