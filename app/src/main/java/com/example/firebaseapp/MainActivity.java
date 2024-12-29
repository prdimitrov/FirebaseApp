package com.example.firebaseapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database

        // Initialize and Access the Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to a specific node in the database
        DatabaseReference myReference = database.getReference("users");

        TextView textView = findViewById(R.id.myTextview);

        // Write a value to the specified database location
//        myReference.setValue("Hello from Petar!");
        User user = new User("Asen", "asenasenovasenov@abv.bg", 15);
        myReference.setValue(user);

        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String newValue = snapshot.getValue(String.class);
                User newUser = snapshot.getValue(User.class);

                StringBuilder sb = new StringBuilder();
                sb.append("name: ").append(newUser.getName()).append("\nemail: ")
                                .append(newUser.getEmail()).append("\nage: ")
                                .append(newUser.getAge());
                textView.setText(sb);

//                textView.setText(newValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here!
            }
        });
    }
}