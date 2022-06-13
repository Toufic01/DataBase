package com.app.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText first_name, last_name, profession, contract, address;
    Button click;
FirebaseFirestore FirebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore = FirebaseFirestore.getInstance();


        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        profession = findViewById(R.id.profession);
        contract = findViewById(R.id.Contract);
        address = findViewById(R.id.Address);
        click = findViewById(R.id.Butto);


        click .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = first_name.getText().toString().trim();
                String last = last_name.getText().toString().trim();
                String Profession = profession.getText().toString().trim();
                String direct = address.getText().toString().trim();
                String Number = contract.getText().toString().trim();


                if(TextUtils.isEmpty(first)||TextUtils.isEmpty(last)||
                        TextUtils.isEmpty(Profession)||TextUtils.isEmpty(direct)||
                        TextUtils.isEmpty(Number))
                {
                    Toast.makeText(MainActivity.this, "full fill your information", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Map<String, Object> MainDataBase = new HashMap<>();
                    MainDataBase.put("first", first);
                    MainDataBase.put("last", last);
                    MainDataBase.put("profession", Profession);
                    MainDataBase.put("Address", direct);
                    MainDataBase.put("contact number", Number);
                    FirebaseFirestore.collection("Datas").add(MainDataBase)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }



            }
        });




    }
}