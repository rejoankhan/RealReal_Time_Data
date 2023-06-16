package com.android.realtimedata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, ageEditText;
    private Button saveDataButton,loadDataButton;
    private DatabaseReference databaseReference;

    // ListView listView;
    //ArrayList<Student> stdArrayList;
    //ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.nameEditTextId);
        ageEditText = findViewById(R.id.ageEditTextId);
        saveDataButton = findViewById(R.id.saveDataButtonId);
        loadDataButton = findViewById(R.id.loadDataButtonId);

        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);

            }
        });


        saveDataButton.setOnClickListener(view -> saveData());


        //stdArrayList = new ArrayList<>();
        //adapter = new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1,stdArrayList );
        //listView.setAdapter(adapter);



        // getDataFromFireBase;

    }

    public void saveData() {
        String name = nameEditText.getText().toString().trim();
        String age = ageEditText.getText().toString().trim();

        String key = databaseReference.push().getKey();
        Student student = new Student(name,age);

        databaseReference.child(key).setValue(student);
        Toast.makeText(getApplicationContext(), "Student Added succesfull", Toast.LENGTH_SHORT).show();

        nameEditText.setText("");
        ageEditText.setText("");
    }



}


