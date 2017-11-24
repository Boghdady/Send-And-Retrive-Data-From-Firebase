package com.index.confirmationdialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendDataToFirebase extends AppCompatActivity {

    EditText edId , edName,edPower,edSpeed,edStamina ;
    Button btnEnter , btnSwitchActivity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // get a reference from the location of database on firebase
        final DatabaseReference myRef = database.getReferenceFromUrl("https://confirmationdialog-3cd6d.firebaseio.com/");

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                   this section declare how to insert data in
                   firebase
                   @param myRef this the parent of the reference of the database
                 */

                myRef.child(edId.getText().toString()).child("ID")
                        .setValue(edId.getText().toString());

                myRef.child(edId.getText().toString()).child("Name")
                        .setValue(edName.getText().toString());

                myRef.child(edId.getText().toString()).child("Power")
                        .setValue(edPower.getText().toString());

                myRef.child(edId.getText().toString()).child("Speed")
                        .setValue(edSpeed.getText().toString());

                myRef.child(edId.getText().toString()).child("Stamina")
                        .setValue(edStamina.getText().toString());

                myRef.child("A").child("B").child("C").child("D").setValue("E");

            }
        });





        // Switch to another activity
       btnSwitchActivity.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(SendDataToFirebase.this , RetriveDataFromFireBase.class );
               startActivity(i);
           }
       });
    }


    private void Init(){
        edId = (EditText)findViewById(R.id.edId);
        edName = (EditText)findViewById(R.id.edName);
        edPower = (EditText)findViewById(R.id.edPower);
        edSpeed = (EditText)findViewById(R.id.edSpeed);
        edStamina = (EditText)findViewById(R.id.edStamina);
        btnEnter = (Button)findViewById(R.id.btnEnter);
        btnSwitchActivity = (Button)findViewById(R.id.btnSwitchActivity);
    }
}
