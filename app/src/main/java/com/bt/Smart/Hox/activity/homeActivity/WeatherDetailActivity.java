package com.bt.Smart.Hox.activity.homeActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bt.Smart.Hox.BaseActivity;
import com.bt.Smart.Hox.R;
import com.bt.Smart.Hox.messegeInfo.WeatherInfo;
import com.google.gson.Gson;

/**
 * @创建者 AndyYan
 * @创建时间 2018/12/11 11:02
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class WeatherDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView img_back;
    private ImageView img_weather;
    private TextView  tv_weather;
    private TextView  tv_city;
    private TextView  tv_temp;
    private TextView  tv_pm25;
    private TextView  tv_air;
    private TextView  tv_hum;
    private TextView  tv_wind;
    private String    weatherString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_weather_detail);
        getView();
        setData();
    }

    private void getView() {
        img_back = (ImageView) findViewById(R.id.img_back);
        img_weather = (ImageView) findViewById(R.id.img_weather);
        tv_weather = (TextView) findViewById(R.id.tv_weather);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_temp = (TextView) findViewById(R.id.tv_temp);
        tv_pm25 = (TextView) findViewById(R.id.tv_pm25);
        tv_air = (TextView) findViewById(R.id.tv_air);
        tv_hum = (TextView) findViewById(R.id.tv_hum);
        tv_wind = (TextView) findViewById(R.id.tv_wind);
    }

    private void setData() {
        img_back.setOnClickListener(this);
        weatherString = getIntent().getStringExtra("weatherInfo");
        Gson gson = new Gson();
        WeatherInfo weatherInfo = gson.fromJson(weatherString, WeatherInfo.class);
        String weather = weatherInfo.getTemperaturelist().getWeather();
        tv_weather.setText(weather);
        if (weather.contains("雨")) {
            img_weather.setImageResource(R.drawable.icon_rain);
        } else if (weather.contains("雪")) {
            img_weather.setImageResource(R.drawable.icon_xue);
        } else if (weather.contains("阴")) {
            img_weather.setImageResource(R.drawable.icon_yin);
        }else if (weather.contains("多云")) {
            img_weather.setImageResource(R.drawable.icon_yin);
        } else {
            img_weather.setImageResource(R.drawable.clear);
        }
        tv_city.setText(weatherInfo.getTemperaturelist().getCityname());
        tv_temp.setText(weatherInfo.getTemperaturelist().getTemp());
        tv_pm25.setText(weatherInfo.getTemperaturelist().getPm25()+"μg/m³");
        tv_air.setText(weatherInfo.getTemperaturelist().getAqi_levnm());
        tv_hum.setText(weatherInfo.getTemperaturelist().getSD());
        tv_wind.setText(weatherInfo.getTemperaturelist().getWind() + weatherInfo.getTemperaturelist().getWinp());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
