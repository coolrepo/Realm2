package kjanderson2.realm2;

/**
 * Created by kjanderson2 on 6/2/15.
 * This is the Model class for a Person object.
 */

import io.realm.RealmObject;

public class Person extends RealmObject{

    private String name;
    private int age;
    private Email email1;
    private Email email2;
    private Telephone phone1;
    private Telephone phone2;

    public Person(){
        //Default constructor
    }

    //Constructor when the name and age are given
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


    public void setEmail1(Email email){
        this.email1 = email;
    }

    public Email getEmail1(){
        return this.email1;
    }

    public void setEmail2(Email email){
        this.email2 = email;
    }

    public Email getEmail2(){
        return this.email2;
    }

    public void setPhone1(Telephone phone) {
        this.phone1 = phone;
    }

    public Telephone getPhone1(){
        return this.phone1;
    }

    public void setPhone2(Telephone phone) {
        this.phone2 = phone;
    }

    public Telephone getPhone2(){
        return this.phone2;
    }
}
