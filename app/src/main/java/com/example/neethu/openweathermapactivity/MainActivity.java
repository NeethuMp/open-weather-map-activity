package com.example.neethu.openweathermapactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    public static final String ROOT_URL = "http://api.openweathermap.org";
    public static final String CITY_NAME = "city_name";
    public static final String COUNTRY_CODE = "country_code";
    public static final String TEMPERATURE = "temprature";
    public static final String WIND = "wind";
    public static final String DESCRIPTION = "description";
    public static final String PRESSURE = "pressure";
    public static final String HUMIDITY = "humidity";
    public static final String SUNRISE = "sunrise";
    public static final String SUNSET = "sunset";
    public static final String CO_ORDLAT = "coordlat";
    public static final String CO_ORDLON = "coordlon";
    private static final String TAG = "WeatherApp";
    public static final String CLOUD = "cloud";
    public  static final String IMG_ICON="img_icon";

    Button saveButton;
    EditText cityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityId = (EditText) findViewById(R.id.city_id);
        cityId.setSingleLine();
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();

            }
        });

    }
    private void getData() {
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please Wait",
                false, false);

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        WeatherAPI api = adapter.create(WeatherAPI.class);
        String appID = "44db6a862fba0b067b1930da0d769e98";
        String units="metric";
        api.getWeatherData(cityId.getText().toString(), appID,units, new Callback<WeatherData>() {
            @Override
            public void success(WeatherData weatherData, Response response) {
                loading.dismiss();
                Intent weatherdataIntent = new Intent(MainActivity.this, WeatherDetailsActivity.class);
                weatherdataIntent.putExtra(DESCRIPTION, weatherData.getWeather().description);
                weatherdataIntent.putExtra(CLOUD, weatherData.getWeather().main);
                weatherdataIntent.putExtra(IMG_ICON,weatherData.getWeather().icon);
                weatherdataIntent.putExtra(CITY_NAME, weatherData.getName());
                weatherdataIntent.putExtra(WIND, weatherData.getWind().speed);
                weatherdataIntent.putExtra(TEMPERATURE, weatherData.getMain().temp);
                weatherdataIntent.putExtra(PRESSURE, weatherData.getMain().pressure);
                weatherdataIntent.putExtra(HUMIDITY, weatherData.getMain().humidity);
                weatherdataIntent.putExtra(COUNTRY_CODE, weatherData.getSys().country);
                weatherdataIntent.putExtra(SUNRISE, weatherData.getSys().sunrise);
                weatherdataIntent.putExtra(SUNSET, weatherData.getSys().sunset);
                weatherdataIntent.putExtra(CO_ORDLAT, weatherData.getCoord().lat);
                weatherdataIntent.putExtra(CO_ORDLON, weatherData.getCoord().lon);
                startActivity(weatherdataIntent);
            }
            @Override
            public void failure(RetrofitError error) {
                loading.dismiss();
                Log.e(TAG, "error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
