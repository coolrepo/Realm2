package kjanderson2.realm2;

/**
 * Created by kjanderson2 on 6/2/15.
 */

import io.realm.RealmList;
import io.realm.RealmObject;

public class Person extends RealmObject{

    private String name;
    private int age;
    private RealmList<Email> emailList;
    private RealmList<Telephone> phoneList;

    //Default constructor
    public Person(){

    }

    //Constructor when the ID is given.
    public Person(int id, String name, int age) {
        this.name = name;
        this.age = age;
        this.emailList = null;
        this.phoneList =null;
    }

    //Constructor where the id is not given.
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }


    public void setEmailList(Email emailList){
        this.emailList.add(emailList);
    }

    public RealmList<Email> getEmailList(){
        return this.emailList;
    }

    public void setPhoneList(Telephone phoneList) {
        this.phoneList.add(phoneList);
    }

    public RealmList<Telephone> getPhoneList(){
        return this.phoneList;
    }

}
