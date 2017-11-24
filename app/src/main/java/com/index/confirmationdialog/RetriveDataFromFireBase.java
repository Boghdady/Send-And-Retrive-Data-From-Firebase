package com.index.confirmationdialog;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RetriveDataFromFireBase extends AppCompatActivity {

    EditText edValue , edKey;
    Button btnRetriveData , btnCreateRandomKey ;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_data_from_fire_base);

        Init();

        database = FirebaseDatabase.getInstance();
        databaseReference  = database.getReference();

        btnRetriveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database.getReference("-Kze5dA4FlgXw4vyF4O_").addValueEventListener(new ValueEventListener() {
                    // called when value change in the database
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String Val= dataSnapshot.getValue(String.class);
                        System.out.println(Val);
                        Toast.makeText(RetriveDataFromFireBase.this, Val, Toast.LENGTH_SHORT).show();
                    }

                    // called if exist error when getting data like internet connection
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


        // create unique key for reference of database in firebase

        btnCreateRandomKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uniqueID = databaseReference.push().getKey();
                databaseReference.child(uniqueID).setValue("Value");
                databaseReference.child(uniqueID).setValue("Value");

                // create unique parent with one child
                databaseReference.push().child("Key").setValue("Value");

                edKey.setText(uniqueID);
            }
        });


    }


    private void Init(){

        edKey = (EditText)findViewById(R.id.edKey);
        edValue = (EditText)findViewById(R.id.edValue);
        btnRetriveData = (Button)findViewById(R.id.btnRetriveData);
        btnCreateRandomKey = (Button)findViewById(R.id.btnCreateRandomKey);


    }
}
