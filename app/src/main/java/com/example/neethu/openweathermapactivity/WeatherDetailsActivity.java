package com.example.neethu.openweathermapactivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by neethu on 15/2/16.
 */
public class WeatherDetailsActivity extends AppCompatActivity {
    TextView viewName,viewCountry,viewTemp,viewWind,viewDescrip,viewPress,viewHumi,
            viewSunrise,viewSunset,viewCoord, viewMain,viewGet;
    ImageView viewIcon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_details_activity);

        viewName= (TextView) findViewById(R.id.city_view);
        viewCountry= (TextView) findViewById(R.id.country_view);
        viewTemp= (TextView) findViewById(R.id.temp_view);
        viewWind= (TextView) findViewById(R.id.wind_view);
        viewDescrip= (TextView) findViewById(R.id.descri_view);
        viewPress= (TextView) findViewById(R.id.pressure_view);
        viewHumi= (TextView) findViewById(R.id.humidity_view);
        viewSunrise= (TextView) findViewById(R.id.sunrise_view);
        viewSunset= (TextView) findViewById(R.id.sunset_view);
        viewCoord= (TextView) findViewById(R.id.coord_view);
        viewMain= (TextView) findViewById(R.id.cloud);
        viewGet= (TextView) findViewById(R.id.get);
        viewIcon= (ImageView) findViewById(R.id.icon_view);

        Intent weatherIntent=getIntent();
        viewName.setText(weatherIntent.getStringExtra(MainActivity.CITY_NAME));
        viewCountry.setText(weatherIntent.getStringExtra(MainActivity.COUNTRY_CODE));
        String imageIcon=weatherIntent.getStringExtra(MainActivity.IMG_ICON);
        Picasso.with(this)
                .load("http://openweathermap.org/img/w/"+imageIcon+".png")
                        .resize(50, 50)
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                        .into(viewIcon);
        //        Glide
//                .with(myFragment)
//                .load(url)
//                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
//                .crossFade()
//                .into(myImageView);
        float temperature=weatherIntent.getFloatExtra(MainActivity.TEMPERATURE,0);
        viewTemp.setText(String.valueOf(temperature+"\u00b0C"));

        viewMain.setText(weatherIntent.getStringExtra(MainActivity.CLOUD));

        long time=System.currentTimeMillis()/1000;
        DateFormat formt = new SimpleDateFormat("yyyy.MM.dd HH:mm  ");
        Calendar calend = Calendar.getInstance();
        calend.setTimeInMillis(time*1000L);
        String newTime=formt.format(calend.getTime());
        viewGet.setText(String.format("get at "+newTime));

        float windData=weatherIntent.getFloatExtra(MainActivity.WIND,0);
        viewWind.setText(String.format("Calm %s m/s",String.valueOf(windData)));

        viewDescrip.setText(weatherIntent.getStringExtra(MainActivity.DESCRIPTION));

        float pres=weatherIntent.getFloatExtra(MainActivity.PRESSURE,0);
        viewPress.setText(String.valueOf(pres+"hpa"));

        int hum=weatherIntent.getIntExtra(MainActivity.HUMIDITY,0);
        viewHumi.setText(String.valueOf(hum+"%"));

        long rise=weatherIntent.getLongExtra(MainActivity.SUNRISE,0);
        DateFormat formatter = new SimpleDateFormat("HH:mm  ");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(rise*1000L);
        String newRise=formatter.format(calendar.getTime());
        viewSunrise.setText(newRise);

        long set=weatherIntent.getLongExtra(MainActivity.SUNSET,0);
        DateFormat formatter2 = new SimpleDateFormat("HH:mm  ");
        Calendar cale = Calendar.getInstance();
        calendar.setTimeInMillis(set*1000L);
        String newSet=formatter2.format(cale.getTime());
        viewSunset.setText(newSet);

        final float ord=weatherIntent.getFloatExtra(MainActivity.CO_ORDLON,0);
        final float ord2=weatherIntent.getFloatExtra(MainActivity.CO_ORDLAT,0);
        viewCoord.setText(String.format("[%s,%s]",String.valueOf(ord),String.valueOf(ord2)));
        viewCoord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//              String url="http://openweathermap.org/Maps?zoom=12&lat="+ord2+"&lon="+ord+"&layers=B0FTTFF";
//              String Url="https://www.google.co.in/maps/@9.9899569,76.2884479,15z";
//              String uri = String.format(Locale.ENGLISH, "geo:%f,%f", ord, ord2);
                String Url= "http://maps.google.com/maps?q=loc:" + ord2 + "," + ord + " (" + viewName.getText() + ")";
                Intent mapIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
}

