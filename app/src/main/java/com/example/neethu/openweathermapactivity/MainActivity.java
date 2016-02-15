package com.example.neethu.openweathermapactivity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit.RestAdapter;

public class MainActivity extends AppCompatActivity {
    public static final String ROOT_URL = "http://api.openweathermap.org/data/2.5";
    public  static  final  int CITY_ID=1;
    public  static  final String CITY_NAME="city_name";
    public  static  final String COUNTRY_CODE="country_code";
    public static final float TEMPERATURE=2;
    public  static final float WIND=3;
    public  static  final  String DESCRIPTION="description";
    public  static  final float PRESSURE=4;
    public  static  final int HUMIDITY=5;
    public  static  final  long SUNRISE=6;
    public  static  final long SUNSET=7;
    public  static  final float COORD=8;


    Button saveButton;
    EditText cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityId= (EditText) findViewById(R.id.city_id);
        saveButton= (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=cityId.getId();
                getData();


            }
        });

    }

    private void getData() {

        final ProgressDialog loading= ProgressDialog.show(this, "Fetching Data", "Please Wait",
                false, false);
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}
