package com.tes.ffsensi;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int year, month, day, hour, minute;
    TextView  tv_date1, tv_date2,tv_date3,tv_date4,tv_date5,tv_date6;
    Button date, time;
    long pos = 86400000;
    long tigalapan = 38;
    long ttl = pos * tigalapan;
    Spinner spinner;
    String nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_date1 = findViewById(R.id.textView1);
        tv_date2 = findViewById(R.id.textView2);
        tv_date4 = findViewById(R.id.textView4);
        tv_date5 = findViewById(R.id.textView5);
        tv_date6 = findViewById(R.id.textView6);
        spinner = (Spinner) findViewById(R.id.spinner);

        date = findViewById(R.id.date);
        List<Model> movieList = new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nama = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);



                DateFormat dfDate = new SimpleDateFormat("MMM d, yyyy");
                String dates = dfDate.format(Calendar.getInstance().getTime());

                long cd = dfDate.getCalendar().getTimeInMillis();
                long tc = cd;
                DatePickerDialog picker = new DatePickerDialog(MainActivity.this,R.style.MySpinnerDatePickerStyle, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        long dm = datePicker.getCalendarView().getDate();
                        long mm = Math.round((tc-dm)/pos);
                        long mh = (long) Math.floor(mm/7);

                        long tambah = 40 - mh;
                        double inte = 40;
                        double ii = mh;
                        double jm = Math.round(ii/inte*100);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy");
                        String dateString = simpleDateFormat.format(22982400000L+dm);
                        SimpleDateFormat simple = new SimpleDateFormat("MMM d, yyyy");
                        String bbl = simple.format(1209600000L+dm);
                        SimpleDateFormat simpleDate = new SimpleDateFormat("MMM d, yyyy");
                        String date1String = simpleDate.format(25401600000L+dm);

                        tv_date1.setText(getString(R.string.lastM)+" "+formatDate(day,month, year));
                        tv_date5.setText(getString(R.string.hariini)+" "+dates+"\n"+getString(R.string.minggu)+" "+mh+" "+getString(R.string.pregen));
                        tv_date4.setText(getString(R.string.persal)+": "+"\n"+dateString+" - "+date1String);
                        tv_date2.setText( getString(R.string.awal)+" "+bbl);
                        tv_date6.setText(getString(R.string.persen)+" "+jm+"%"+" "+getString(R.string.dataper));


                        SimpleDateFormat w1 = new SimpleDateFormat("MMM d, yyyy");
                        String ws1 = w1.format(604800000L+dm);

                        SimpleDateFormat w2 = new SimpleDateFormat("MMM d, yyyy");
                        String ww2 = w2.format(dm);

                        movieList.clear();
                        TableViewAdapter adapter = new TableViewAdapter(movieList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                        movieList.add(new Model(1, formatDate(day,month, year)+" - "+ws1, ""));
                        movieList.add(new Model(2, "Avengers: Age of Ultron", ""));
                        movieList.add(new Model(3, "Avengers: Infinity War", ""));
                        movieList.add(new Model(4, "Pirates of the Caribbean: At World's End", ""));
                        movieList.add(new Model(5, "Justice League", ""));
                        movieList.add(new Model(6, "Solo: A Star Wars Story", ""));
                        movieList.add(new Model(7, "John Carter", ""));
                        movieList.add(new Model(8, "Batman v Superman: Dawn of Justice", ""));
                        movieList.add(new Model(9, "Star Wars: The Last Jedi", ""));
                        movieList.add(new Model(10, "Tangled", ""));

                        if (mh>42){
                            tv_date2.setVisibility(View.INVISIBLE);
                            tv_date6.setVisibility(View.INVISIBLE);
                            tv_date5.setVisibility(View.INVISIBLE);
                        }else {
                            tv_date2.setVisibility(View.VISIBLE);
                            tv_date6.setVisibility(View.VISIBLE);
                            tv_date5.setVisibility(View.VISIBLE);
                        }


                    }

                    private String formatDate(int day, int month, int year) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTimeInMillis(0);
                        cal.set(year, month, day);
                        Date date = cal.getTime();
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");

                        return sdf.format(date);
                    }
                }, year, month,day);
                picker.setIcon(R.drawable.ic_launcher_foreground);
                picker.setTitle("Please select date.");
                picker.show();
            }
        });

    }

}
