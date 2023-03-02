package com.omkokate.attendance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class OutputActivity extends AppCompatActivity {
    RecyclerView recyclerViewpa;
    List<PA> PAList;
    PAAdapter PAAdapter;
    RecyclerView recyclerView;
    List<Attendance> attendance;
    Adapter adapter;
    TextView name_tv,roll_tv,date_tv,div_text;

    ShimmerFrameLayout shimmerFrameLayout,shimmerFrameLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_output);
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources ().getColor(R.color.purple_700));


        shimmerFrameLayout=findViewById(R.id.shimmerFrameLayout);
        shimmerFrameLayout.startShimmer();

        String division = getIntent().getStringExtra("division");
        String rollNo = getIntent().getStringExtra("rollNo");
        int roll= Integer.parseInt(rollNo)-1;
        String date = getIntent().getStringExtra("date");
        Log.d("myapp",date);

        div_text=findViewById(R.id.div_text);
        div_text.setText("DIVISION  : "+division);

        String[] urls=new String[10];

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("urls")
                .document("DIV"+division)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String fieldValue2 = String.valueOf(documentSnapshot.get("num"));
                            int nos=Integer.parseInt(fieldValue2);
                            Log.d("myapp",""+nos);
                            for (int i = 0; i < urls.length; i++) {
                                String fieldValue = String.valueOf(documentSnapshot.get(String.valueOf(i)));
                                urls[i]=fieldValue;
                                Log.d("myapp",""+urls[i]);
                            }

                            LinearLayout linearLayout = findViewById(R.id.linear_shimmer);
                            for (int i = 0; i < nos; i++) {
                                View view = getLayoutInflater().inflate(R.layout.attendance_shimmer, linearLayout, false);
                                linearLayout.addView(view);
                            }
                            extractAttendance(roll,date,urls, nos);

                        } else {
                            // Handle error
                            Log.d("myapp","haha");
                            Intent i4=new Intent(OutputActivity.this,ErrorActivity.class);
                            startActivity(i4);
                        }
                    }
                });

        recyclerView = findViewById(R.id.attendanceList);
        recyclerViewpa = findViewById(R.id.paList);
        attendance = new ArrayList<>();
        PAList = new ArrayList<>();
    }

    private void extractAttendance(int roll, String date, String[] urls, int nos) {

        name_tv=findViewById(R.id.name_tv);
        roll_tv=findViewById(R.id.roll_tv);
        date_tv=findViewById(R.id.tv_date);

        int i;
        for (i = 0; i < nos; i++) {

            String url = urls[i];

            RequestQueue queue = Volley.newRequestQueue(this);
            int finalI = i;
            int finalI1 = i;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    JSONObject atte = null;
                    try {

                        JSONArray name = response.getJSONArray("name");
                        JSONArray subname = response.getJSONArray("subname");
                        JSONArray avg = response.getJSONArray("avg");
                        JSONArray avgth = response.getJSONArray("avgth");
                        JSONArray avgpr = response.getJSONArray("avgpr");
                        JSONArray avg3d = response.getJSONArray("avg3d");
                        JSONArray avg7d = response.getJSONArray("avg7d");
                        JSONArray avgmonth = response.getJSONArray("avgmonth");
                        atte = response.getJSONObject("att");

                        String nametv = name.getString(roll);
                        name_tv.setText(nametv);
                        int roll_real = roll + 1;
                        String Roll = String.valueOf(roll_real);
                        roll_tv.setText("ROLL NO :  "+Roll);

                        if(atte.has(date)) {
                            date_tv.setText("Attendance on :  " + date);
                        }

                        if(!(atte.has(date))) {
                            RelativeLayout layout = (RelativeLayout) findViewById(R.id.rel_pa);
                            layout.setVisibility(View.GONE);
                        }

                        PA eve = new PA();

                        if (atte.has(date)) {
                            JSONObject atte_date = atte.getJSONObject(date);
                            Iterator<String> keys = atte_date.keys();
                            while (keys.hasNext()) {
                                String key = keys.next();
                                JSONArray pa = atte_date.getJSONArray(key);

                                String PA = pa.getString(roll+1);
                                String priority=pa.getString(0);
                                eve.setPriority(priority);

                                String s_name=subname.getString(0);
                                s_name=s_name.substring(3);
                                eve.setSubject(s_name);
                                eve.setTime(key);
                                eve.setPresenty(PA);
                                Log.d("myapp", subname.getString(0) + " " + key + " " + PA);
                            }
                            PAList.add(eve);
                        }


                        Attendance att = new Attendance();

                        att.setSubname(valueSub(subname, 0));

                        att.setAvg(valueS(avg, roll));
                        att.setAvgPB(valueI(avg, roll));

                        att.setAvgth(valueS(avgth, roll));
                        att.setAvgthPB(valueI(avgth, roll));

                        att.setAvgpr(valueS(avgpr, roll));
                        att.setAvgprPB(valueI(avgpr, roll));

                        att.setAvg3d(valueS(avg3d, roll));
                        att.setAvg3dPB(valueI(avg3d, roll));

                        att.setAvg7d(valueS(avg7d, roll));
                        att.setAvg7dPB(valueI(avg7d, roll));

                        att.setAvgmonth(valueS(avgmonth, roll));
                        att.setAvgmonthPB(valueI(avgmonth, roll));

                        attendance.add(att);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("myapp","hehe");
                        Intent i2=new Intent(OutputActivity.this,ErrorActivity.class);
                        startActivity(i2);
                    }

                    Collections.sort(attendance, new Comparator<Attendance>() {
                        @Override
                        public int compare(Attendance t1, Attendance t2) {
                            return t1.getSubname().compareToIgnoreCase(t2.getSubname());
                        }
                    });

                    Collections.sort(PAList, new Comparator<PA>() {
                        @Override
                        public int compare(PA t1, PA t2) {
                            return t1.getPriority().compareToIgnoreCase(t2.getPriority());
                        }
                    });
//                    Collections.reverse(PAList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter = new Adapter(getApplicationContext(), attendance);
                    recyclerView.setAdapter(adapter);

                    if (atte.has(date)) {
                        recyclerViewpa.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        PAAdapter = new PAAdapter(getApplicationContext(), PAList);
                        recyclerViewpa.setAdapter(PAAdapter);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("myapp", "onErrorResponse: " + error.getMessage());
                    Intent i3=new Intent(OutputActivity.this,ErrorActivity.class);
                    startActivity(i3);
                }
            });


            DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            jsonObjectRequest.setRetryPolicy(retryPolicy);

            queue.add(jsonObjectRequest);
        }
    }


    public String valueS(JSONArray jArray,int roll) throws JSONException {
        String value1=jArray.getString(roll);
        float value2=(Float.parseFloat(value1));
        float value3=value2*100;
        int value4=Math.round(value3);
        String valuefin = value4 +"%";
        return valuefin;
    }

    public int valueI(JSONArray jArray, int roll) throws JSONException {
        String value1 = jArray.getString(roll);
        float value2 = (Float.parseFloat(value1));
        float value3 = value2 * 100;
        int valuefin = Math.round(value3);
        return valuefin;
    }

    public String valueSub(JSONArray jArray,int pos) throws JSONException {
        String value1=jArray.getString(pos);
        return value1;
    }
}