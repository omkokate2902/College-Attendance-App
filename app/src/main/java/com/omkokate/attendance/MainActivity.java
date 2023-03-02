package com.omkokate.attendance;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    EditText rollNoEditText;
    TextView datePickerTV, div_tv,star1,star2,star3;
    Button submitButton;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources ().getColor(R.color.purple_700));

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("response")
                .document("service")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String fieldValue = String.valueOf(documentSnapshot.get(String.valueOf(0)));
                            String message=String.valueOf(documentSnapshot.get("error_msg"));
                            if(fieldValue.equals("1")){
                                Intent i=new Intent(MainActivity.this,ServiceDown.class);
                                i.putExtra("error_msg", message);
                                startActivity(i);
                            }
                        } else {
                            // Handle error

                        }
                    }
                });


        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
//            Toast.makeText(this, "Connection success", Toast.LENGTH_SHORT).show();
            Log.d("myapp","Connection success");
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.internet_dialog, null);
            builder.setView(dialogView);

            Button quitButton = dialogView.findViewById(R.id.dialog_quit);
            quitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

            alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        rollNoEditText = findViewById(R.id.enterroll);
        submitButton = findViewById(R.id.submit_button);
        datePickerTV = findViewById(R.id.date_pick);

        div_tv = findViewById(R.id.div_tv);
        spinner = findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.divisons);
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, 23, android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        datePickerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        datePickerTV.setText(day + " " + (month + 1) + " " + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String division = div_tv.getText().toString();
                String rollNo = rollNoEditText.getText().toString();
                String date = datePickerTV.getText().toString();

                if (division.isEmpty() || rollNo.isEmpty() || date.isEmpty()) {
                    star1=findViewById(R.id.star1);
                    star2=findViewById(R.id.star2);
                    star3=findViewById(R.id.star3);
                    star1.setVisibility(View.VISIBLE);
                    star2.setVisibility(View.VISIBLE);
                    star3.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(MainActivity.this, "Forms successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, OutputActivity.class);
                    intent.putExtra("division", division);
                    intent.putExtra("rollNo", rollNo);
                    intent.putExtra("date", date);
//                    Log.d("myapp", date+" "+rollNo+" "+division);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner) {
            if (position == 0) {
                div_tv.setText("");
                return;
            }
            String selectedValue = parent.getItemAtPosition(position).toString();
            div_tv.setText(selectedValue);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
