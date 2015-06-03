package kjanderson2.realm2;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import io.realm.Realm;


public class MainActivity extends ActionBarActivity{

    TextView idView;
    EditText nameBox;
    EditText ageBox;
    EditText email1Box;
    EditText email2Box;
    EditText phone1Box;
    EditText phone2Box;
    boolean email1Active;
    boolean email2Active;
    boolean phone1Active;
    boolean phone2Active;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 5 lines
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = (TextView) findViewById(R.id.productID);
        nameBox = (EditText) findViewById(R.id.productName);
        ageBox = (EditText) findViewById(R.id.productQuantity);
        email1Box = (EditText) findViewById(R.id.email1);
        phone1Box = (EditText) findViewById(R.id.phone1);
        email1Active = ((CheckBox) findViewById(R.id.email1_activity_box)).isChecked();
        email2Active = ((CheckBox) findViewById(R.id.email2_activity_box)).isChecked();
        phone1Active = ((CheckBox) findViewById(R.id.phone1_activity_box)).isChecked();
        phone2Active = ((CheckBox) findViewById(R.id.phone2_activity_box)).isChecked();
    }

    public void newProduct(View view){ // 12 lines
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        int age = Integer.parseInt(ageBox.getText().toString());
        Person person = new Person(nameBox.getText().toString(), age);
        if(email1Box.getText()!= null) {
            Email email1 = new Email(email1Box.getText().toString(), email1Active);
            person.setEmailList(email1);
        }
        if(email2Box.getText() != null) {
            Email email2 = new Email(email2Box.getText().toString(), email2Active);
            person.setEmailList(email2);
        }
        if(phone1Box.getText()!= null) {
            Telephone phone1 = new Telephone(Integer.parseInt(phone1Box.getText().toString()),phone1Active);
            person.setPhoneList(phone1);
        }
        if(phone2Box.getText()!=null) {
            Telephone phone2 = new Telephone(Integer.parseInt(phone2Box.getText().toString()),phone2Active);
            person.setPhoneList(phone2);
        }
        realm.copyToRealm(person);

        nameBox.setText("");
        ageBox.setText("");

        realm.commitTransaction();
    }

    public void lookupProduct (View view) { //11 lines total
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        Person person = realm.where(Person.class)
                .contains("name", nameBox.getText().toString())
                .findFirst();
        if(person != null){
            idView.setText(phone1Box.getText());//
            ageBox.setText(String.valueOf(person.getAge()));
            email1Box.setText(String.valueOf(person.getEmailList().first()));
            email2Box.setText(String.valueOf(person.getEmailList().get(1)));
            phone1Box.setText(String.valueOf(person.getPhoneList().first()));
            phone2Box.setText(String.valueOf(person.getPhoneList().get(1)));
        } else {
            idView.setText("No Match Found");
        }
        realm.commitTransaction();
    }

    public void removeProduct(View view) { // 14 lines total
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        Person person = realm.where(Person.class)
                .contains("name", nameBox.getText().toString())
                .findFirst();

        if(person !=null){
            person.removeFromRealm();
            idView.setText("Record Deleted");
            nameBox.setText("");
            ageBox.setText("");
            email1Box.setText("");
            email2Box.setText("");
            phone1Box.setText("");
            phone2Box.setText("");
            CheckBox phone1check = (CheckBox) findViewById(R.id.phone1_activity_box);
        } else {
            idView.setText("No Match Found");
        }
        realm.commitTransaction();
    }

}
