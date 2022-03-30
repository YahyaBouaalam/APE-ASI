package com.example.ape_asi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Schools extends AppCompatActivity {

    NotificationCompat.Builder notif;
    Button submitB2;
    String schoolstr, gradestr, pfname, plname,emailadd, number, studname, lvl, msg;
    Spinner school, grade;
    ArrayAdapter<CharSequence> schooladapter, gradeadapter;
    String[]pre={"petite section", "moyenne section", "grande section"};
    String[]elementary={"1st grade", "2nd grade", "3rd grade", "4th grade", "5th grade"};
    String[]middle={"6th grade", "7th grade", "8th grade"};
    String[]high={"9th grade", "10th grade","11th grade", "12th grade"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schools);

        submitB2=findViewById(R.id.submitButton2);
        submitB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSubmission2();
            }
        });

        school=findViewById(R.id.spinner);
        grade=findViewById(R.id.spinner2);
        schooladapter = ArrayAdapter.createFromResource(this, R.array.schoolsarr, android.R.layout.simple_spinner_item);
        schooladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        school.setAdapter(schooladapter);
        school.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0) {
                    gradeadapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, pre);
                }
                else if(i==1) {
                    gradeadapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, elementary);
                }
                else if(i==2) {
                    gradeadapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, middle);
                }
                else if(i==3) {
                    gradeadapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, high);
                }

                grade.setAdapter(gradeadapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                intent = new Intent(this, Membership.class);
                startActivity(intent);
                return true;
            case R.id.item3:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendSubmission2(){
        schoolstr=school.getSelectedItem().toString().trim();
        gradestr=grade.getSelectedItem().toString().trim();
        pfname=((EditText) findViewById(R.id.pfname)).getText().toString().trim();
        plname=((EditText) findViewById(R.id.plname)).getText().toString().trim();
        emailadd=((EditText) findViewById(R.id.mailschools)).getText().toString().trim();
        number=((EditText) findViewById(R.id.num)).getText().toString().trim();
        studname=((EditText) findViewById(R.id.studname)).getText().toString().trim();
        lvl=((EditText) findViewById(R.id.lvlclass)).getText().toString().trim();
        msg=((EditText) findViewById(R.id.msg)).getText().toString().trim();
        String toSend="Dear APE-ASI Board Member,\n\nYou just received a message from "+pfname+", parent of "+studname+". Please find more details below:\n\nParent's name: "+pfname+" "+plname+"\nPhone Number: "+number+"\nEmail Address: "+emailadd+"\n\nStudent's Name: "+studname+"\nstudent's school: "+schoolstr+"\nLevel: "+gradestr+"\nClass: "+lvl+"\n\n\tThe message:\n"+msg+"\n\n\nSincerely yours,\nAPE-ASI Bot";

        JavaMailAPI javaMailAPI= new JavaMailAPI(this, "o.tanji@aui.ma", "[NO-REPLY]: New Message/Comment", toSend);
        javaMailAPI.execute();

    }

}