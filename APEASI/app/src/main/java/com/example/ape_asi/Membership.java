package com.example.ape_asi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Membership extends AppCompatActivity {

    EditText street, city, postalcode, pname, email, relationship, sname, birthdate, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);
        Button submitB= findViewById(R.id.submitButton);
        street= findViewById(R.id.strt);
        city=findViewById(R.id.cty);
        postalcode=findViewById(R.id.pstlcd);
        pname=findViewById(R.id.parentname);
        email=findViewById(R.id.mail);
        relationship=findViewById(R.id.relationship);
        sname=findViewById(R.id.studentname);
        birthdate=findViewById(R.id.birthdate);
        level=findViewById(R.id.classgrade);


        submitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSubmission();
            }
        });
    }

    public void sendSubmission(){
        String submission="Dear APE-ASI Board Member,\n\nYou have a new membership request, please find below their information.\n\n\tAddress:\n-Street Address: "+street.getText().toString()+"\n-City: "+city.getText().toString()+"\n-Postal Code: "+postalcode.getText().toString()+"\n\n\tParent Information:\n-Parent's Name:"+pname.getText().toString()+"\n-Parent's Email Address: "+email.getText().toString()+"\n-Relationship To Student"+relationship.getText().toString()+"\n\n\tStudent Information:\n-Student's Name: "+sname.getText().toString()+"\n-Student's Birthdate: "+birthdate.getText().toString()+"\n-Class: "+level.getText().toString()+"\n\nSincerely yours,\nAPE-ASI Bot";

        JavaMailAPI javaMailAPI= new JavaMailAPI(this, "o.tanji@aui.ma", "[NO-REPLY]: New Membership Request", submission);
        javaMailAPI.execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent;

        switch(item.getItemId()) {
            case R.id.itemhome:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.item1:
                intent = new Intent(this, AboutUs.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                return true;
            case R.id.item3:
                intent = new Intent(this, Schools.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
