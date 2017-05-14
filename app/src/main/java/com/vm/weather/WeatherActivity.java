package com.vm.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vm.weather.adapters.DateRecyclerAdapter;
import com.vm.weather.jsonmodels.FiveDayWeatherDetails;
import com.vm.weather.jsonmodels.WeatherForecast;
import com.vm.weather.models.DateRecyclerItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    WeatherActivity curActivity = this;
    private Button btnGet;
    private RequestQueue queue;
    private HashMap<String, List<FiveDayWeatherDetails>> weatherDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        queue = Volley.newRequestQueue(this);
        weatherDetails = new HashMap<>();
        getWeatherData();

/*
        convertJsonToJava(Helper.SAMPLE_JSON_STRING);
*/
    }

    private void convertJsonToJava(String jsonResponse)
    {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            WeatherForecast forecast = gson.fromJson(jsonResponse, WeatherForecast.class);
            setData(forecast);
        }
        catch (Exception e){
        }
    }

    private void getWeatherData()
    {
        String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=Hyderabad,IN&APPID="+Helper.API_KEY+"&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.d("LOGGER", "response" + response);
                convertJsonToJava(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(curActivity, "No Response/Error Occured", Toast.LENGTH_LONG);
//                Log.d("LOGGER", "Error " + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    private void setData(WeatherForecast forecast){
        TextView txt = (TextView) findViewById(R.id.txtCity);
        txt.setText(forecast.getCity().getName() + "\t - \t" + forecast.getCity().getCountry());

        final List<DateRecyclerItem> datesList = new ArrayList<DateRecyclerItem>();
        boolean isTodaysDateFound = false;

        for (FiveDayWeatherDetails item : forecast.getWeatherList()){
            if(item.getDateText().equalsIgnoreCase(""))
                continue;

            Date date = Helper.getDateFromString(item.getDateText());
            String dateYear = Helper.getDisplayDateYear(date);
            boolean isTodaysDate = false;

            if(!weatherDetails.containsKey(dateYear)) {
                String dayOfWeek = Helper.getDayOfWeek(date);
                String dateToDisplay = Helper.getDisplayDate(date);
                if(!isTodaysDateFound)
                    isTodaysDate = Helper.isTodaysDate(date);
                if(isTodaysDate)
                    isTodaysDateFound = true;

                if(datesList.size() != 5) {
                    DateRecyclerItem dateItem = new DateRecyclerItem(dayOfWeek, dateToDisplay, dateYear, isTodaysDate);
                    datesList.add(dateItem);
                    isTodaysDate = false;
                }

                weatherDetails.put(dateYear, new ArrayList<FiveDayWeatherDetails>());
            }
            weatherDetails.get(dateYear).add(item);
        }

        RecyclerView datesRecyclerView = (RecyclerView)findViewById(R.id.dates_recycler_view);
        datesRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        datesRecyclerView.setHasFixedSize(true);

        DateRecyclerAdapter adapter = new DateRecyclerAdapter(datesList);
        adapter.setClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String dateYear = (String)view.getTag();
                if(null == dateYear)
                    return;
                DateRecyclerItem dateItem = null;
                for(DateRecyclerItem item : datesList){
                    if(item.getDateYear().equalsIgnoreCase(dateYear)){
                        dateItem = item;
                        break;
                    }
                }
                if(null == dateItem)
                    return;

                List<FiveDayWeatherDetails> list = weatherDetails.get(dateItem.getDateYear());
                TextView txt = (TextView) findViewById(R.id.txtSelectedDate);
                txt.setText(dateItem.getDayofWeek() + "\t - \t" + dateItem.getDateYear());

                LinearLayout llDetails = (LinearLayout) findViewById(R.id.llWeatherDetails);
                llDetails.removeAllViews();

                String curTime, temperature;
                for(FiveDayWeatherDetails details : list){
                    View itemView = LayoutInflater.from(curActivity).inflate(R.layout.date_weather_details, null);

                    Date date = Helper.getDateFromString(details.getDateText());
                    curTime = Helper.getDisplayTime(date);
                    txt = (TextView)  itemView.findViewById(R.id.txtTime);
                    txt.setText(curTime);

                    if(null == details.getMainDetails())
                        continue;

                    temperature = Helper.formatTemperature(details.getMainDetails().getTemp());
                    txt = (TextView)  itemView.findViewById(R.id.txtTempDetails);
                    txt.setText(temperature);

                    temperature = Helper.formatTemperature(details.getMainDetails().getTemp_min());
                    txt = (TextView)  itemView.findViewById(R.id.txtTempMin);
                    txt.setText(temperature);

                    temperature = Helper.formatTemperature(details.getMainDetails().getTemp_max());
                    txt = (TextView)  itemView.findViewById(R.id.txtTempMax);
                    txt.setText(temperature);

                    llDetails.addView(itemView);
                }
            }
        });
        datesRecyclerView.setAdapter(adapter);
    }
}
