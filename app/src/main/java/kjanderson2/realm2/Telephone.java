package kjanderson2.realm2;

import io.realm.RealmObject;

/**
 * Created by kjanderson2 on 6/2/15.
 */
public class Telephone extends RealmObject{
    private int number;
    private boolean active;
    private String type;

    public Telephone(){

    }

    public Telephone(int number){
        this.number = number;
        this.active = false;
        this.type = "home";
    }

    public Telephone(int number, boolean active){
        this.number = number;
        this.active = active;
        this.type = "home";
    }

    public Telephone(int number, boolean active, String type){
        this.number = number;
        this.active = active;
        this.type = type;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public int getNumber(){
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
