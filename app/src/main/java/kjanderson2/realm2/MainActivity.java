package kjanderson2.realm2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import io.realm.Realm;


public class MainActivity extends ActionBarActivity{

    private TextView idView;
    private EditText nameBox, ageBox, email1Box, email2Box, phone1Box, phone2Box;
    private boolean email1Active, email2Active, phone1Active, phone2Active;
    private CheckBox email1Checkbox, email2Checkbox, phone1Checkbox, phone2Checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = (TextView) findViewById(R.id.productID);
        nameBox = (EditText) findViewById(R.id.productName);
        ageBox = (EditText) findViewById(R.id.productQuantity);

        //Initialize EditTexts... These will be accessed for email and phone values
        email1Box = (EditText) findViewById(R.id.email1);
        email2Box = (EditText) findViewById(R.id.email2);
        phone1Box = (EditText) findViewById(R.id.phone1);
        phone2Box = (EditText) findViewById(R.id.phone2);

        //Initialize Checkboxes and boolean values for activity
        email1Checkbox = (CheckBox) findViewById(R.id.email1_activity_box);
        email2Checkbox = (CheckBox) findViewById(R.id.email2_activity_box);
        phone1Checkbox = (CheckBox) findViewById(R.id.phone1_activity_box);
        phone2Checkbox = (CheckBox) findViewById(R.id.phone2_activity_box);
        email1Active = email1Checkbox.isChecked();
        email2Active = email2Checkbox.isChecked();
        phone1Active = phone1Checkbox.isChecked();
        phone2Active = phone2Checkbox.isChecked();

        exportDatabase();
    }

    public void newProduct(View view){
        Realm realm = Realm.getInstance(this);

        realm.beginTransaction();

        int age = Integer.parseInt(ageBox.getText().toString());
        Person person = new Person(nameBox.getText().toString(), age);

        if(email1Box.getText() != null) {
            Email email1 = new Email(email1Box.getText().toString(), email1Active);
            person.setEmail1(email1);
        }
        if(email2Box.getText() != null) {
            Email email2 = new Email(email2Box.getText().toString(), email2Active);
            person.setEmail2(email2);
        }
        if(phone1Box.getText() != null) {
            Telephone phone1 = new Telephone(phone1Box.getText().toString(),phone1Active);
            person.setPhone1(phone1);
        }
        if(phone2Box.getText() != null) {
            Telephone phone2 = new Telephone (phone2Box.getText().toString(),phone2Active);
            person.setPhone2(phone2);
        }
        realm.copyToRealm(person);

        idView.setText("Record Added");
        clearBoxes();

        realm.commitTransaction();
    }

    public void lookupProduct (View view) {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        Person person = realm.where(Person.class)
                .contains("name", nameBox.getText().toString())
                .findFirst();
        if(person != null){
            idView.setText("Match Found");
            fillBoxes(person);
        } else {
            idView.setText("No Match Found");
        }
        realm.commitTransaction();
    }

    public void removeProduct(View view) {
        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();

        Person person = realm.where(Person.class)
                .contains("name", nameBox.getText().toString())
                .findFirst();

        if(person !=null){
            person.removeFromRealm();
            idView.setText("Record Deleted");
            clearBoxes();
        } else {
            idView.setText("No Match Found");
        }
        realm.commitTransaction();
    }

    private void clearBoxes(){
        nameBox.setText("");
        ageBox.setText("");
        email1Box.setText("");
        email2Box.setText("");
        phone1Box.setText("");
        phone2Box.setText("");
        email1Checkbox.setChecked(false);
        email2Checkbox.setChecked(false);
        phone1Checkbox.setChecked(false);
        phone2Checkbox.setChecked(false);
    }

    private void fillBoxes(Person person){
        ageBox.setText(String.valueOf(person.getAge()));
        email1Box.setText(String.valueOf(person.getEmail1().getAddress()));
        email1Checkbox.setChecked(person.getEmail1().getActive());
        email2Box.setText(String.valueOf(person.getEmail2().getAddress()));
        email2Checkbox.setChecked(person.getEmail2().getActive());
        phone1Box.setText(String.valueOf(person.getPhone1().getNumber()));
        phone2Checkbox.setChecked(person.getPhone1().getActive());
        phone2Box.setText(String.valueOf(person.getPhone2().getNumber()));
        phone2Checkbox.setChecked(person.getPhone2().getActive());
    }


    //This method makes the .realm file for the Browser much easier to attain.
    //It sends it to your email address which will place the file in an accessible folder
    //on the device.
    //Code credit to bokebe from StackOverflow
    public void exportDatabase() {

        // init realm
        Realm realm = Realm.getInstance(this);

        File exportRealmFile = null;
        try {
            // get or create an "export.realm" file
            exportRealmFile = new File(this.getExternalCacheDir(), "export.realm");

            // if "export.realm" already exists, delete
            exportRealmFile.delete();

            // copy current realm to "export.realm"
            realm.writeCopyTo(exportRealmFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        realm.close();

        // init email intent and add export.realm as attachment
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, "kjanderson@hmc.edu");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Realm File");
        intent.putExtra(Intent.EXTRA_TEXT, "Here's the realm file.");
        Uri u = Uri.fromFile(exportRealmFile);
        intent.putExtra(Intent.EXTRA_STREAM, u);

        // start email intent
        startActivity(Intent.createChooser(intent, "YOUR CHOOSER TITLE"));
    }


}
