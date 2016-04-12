package com.example.vladimir.weather.parser;

import com.example.vladimir.weather.model.City5DTO;
import com.example.vladimir.weather.model.Clouds5DTO;
import com.example.vladimir.weather.model.CloudsDTO;
import com.example.vladimir.weather.model.Coords;
import com.example.vladimir.weather.model.Main5DTO;
import com.example.vladimir.weather.model.MainDTO;
import com.example.vladimir.weather.model.Sys5DTO;
import com.example.vladimir.weather.model.SysDTO;
import com.example.vladimir.weather.model.WeatherCondition5DTO;
import com.example.vladimir.weather.model.WeatherData;
import com.example.vladimir.weather.model.WeatherData5DTO;
import com.example.vladimir.weather.model.WeatherResponse5DTO;
import com.example.vladimir.weather.model.WeatherResponseDTO;
import com.example.vladimir.weather.model.Wind5DTO;
import com.example.vladimir.weather.model.WindDTO;
import com.example.vladimir.weather.util.AppUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vladimir on 4/12/2016.
 */
public class WeatherDataParser {

    public static WeatherResponse5DTO groupParsedWeatherData5(WeatherResponse5DTO weatherResponseDTO) {
        Map<String, List<WeatherData5DTO>> weatherGroups = new HashMap<>();
        for (WeatherData5DTO weatherCondition5DTO : weatherResponseDTO.getWeatherData5DTOs()) {
            String keyDate = AppUtils.parseDateDDmmYYYY(weatherCondition5DTO.getDateText());
            List<WeatherData5DTO> list = weatherGroups.get(keyDate) != null ? weatherGroups.get(keyDate) : new ArrayList<WeatherData5DTO>(1);
            list.add(weatherCondition5DTO);
            weatherGroups.put(keyDate, list);
        }
        weatherResponseDTO.setGroupedWeathers(weatherGroups);
        return weatherResponseDTO;
    }


    public static WeatherResponseDTO parseWeatherData(String rawJson) {
        WeatherResponseDTO responseDTO = new WeatherResponseDTO();
        try {
            JSONObject jsonObject = new JSONObject(rawJson);
            //get root data
            String cityName = jsonObject.getString("name");
            long date = jsonObject.getLong("dt");


            responseDTO.setCityName(cityName);
            responseDTO.setDate(date);

            //get 'weather'
            JSONArray weather = jsonObject.getJSONArray("weather");
            List<WeatherData> weatherDataList = new ArrayList<WeatherData>();//lista od weather obiekti
            for (int i = 0; i < weather.length(); i++) {
                WeatherData data = new WeatherData();//kreirame prazen weather obiekt koj treba da go dodajme vo 'weatherDataList'
                JSONObject currentWeather = weather.getJSONObject(i);
                data.setId(currentWeather.getInt("id"));
                data.setMain(currentWeather.getString("main"));
                data.setDescription(currentWeather.getString("description"));
                data.setIcon(currentWeather.getString("icon"));


                weatherDataList.add(data);//add weather to the list
            }
            responseDTO.setWeatherData(weatherDataList);


            //wind
            JSONObject windJsonObject = jsonObject.getJSONObject("wind");
            WindDTO wind = new WindDTO();
            wind.setSpeed(windJsonObject.getDouble("speed"));
            wind.setDeg(windJsonObject.getInt("deg"));
            responseDTO.setWind(wind);


            //main
            JSONObject mainJsonObject = jsonObject.getJSONObject("main");
            MainDTO main = new MainDTO();
            main.setTemp(mainJsonObject.getDouble("temp"));
            main.setHumidity(mainJsonObject.getInt("humidity"));
            main.setPressure(mainJsonObject.getDouble("pressure"));
            main.setTemp_max(mainJsonObject.getDouble("temp_max"));
            main.setTemp_min(mainJsonObject.getDouble("temp_min"));

            responseDTO.setMain(main);

            //sys

            JSONObject sysJsonObject = jsonObject.getJSONObject("sys");
            SysDTO sys = new SysDTO();
            sys.setType(sysJsonObject.has("type") ? sysJsonObject.getInt("type") : 0);
            sys.setId(sysJsonObject.has("id") ? sysJsonObject.getInt("id") : 0);
            sys.setMessage(sysJsonObject.getDouble("message"));
            sys.setCountry(sysJsonObject.getString("country"));
            sys.setSunset(sysJsonObject.getLong("sunset"));
            sys.setSunrise(sysJsonObject.getLong("sunrise"));
            responseDTO.setSys(sys);

            //clouds

            JSONObject cloudsJsonObject = jsonObject.getJSONObject("clouds");
            CloudsDTO clouds = new CloudsDTO();
            clouds.setAll(cloudsJsonObject.getInt("all"));
            responseDTO.setClouds(clouds);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    public static WeatherResponse5DTO parseWeatherData5(String rawJson) {
        WeatherResponse5DTO response5DTO = new WeatherResponse5DTO();
        try {
            JSONObject jsonObject = new JSONObject(rawJson);

            // city
            JSONObject city5DTOJsonObject = jsonObject.getJSONObject("city");
            City5DTO city = new City5DTO();
            city.setId(city5DTOJsonObject.getInt("id"));
            city.setName(city5DTOJsonObject.getString("name"));
            city.setCountry(city5DTOJsonObject.getString("country"));
            city.setPopulation(city5DTOJsonObject.getInt("population"));

            if (city5DTOJsonObject.has("coord")) {
                JSONObject cityCoordsJsonObj = city5DTOJsonObject.getJSONObject("coord");
                Coords cityCoords = new Coords();
                cityCoords.setLon(cityCoordsJsonObj.getDouble("lon"));
                cityCoords.setLat(cityCoordsJsonObj.getDouble("lat"));
                city.setCoords(cityCoords);
            }

            response5DTO.setCity(city);

            //weatherCondition

            JSONArray listJsonArray = jsonObject.getJSONArray("list");
            List<WeatherData5DTO> weatherData5DTOs = new ArrayList<>();
            for (int i = 0; i < listJsonArray.length(); i++) {
                WeatherData5DTO data = new WeatherData5DTO();
                JSONObject currentWeather = listJsonArray.getJSONObject(i);
                data.setDate(currentWeather.getLong("dt"));
                data.setDateText(currentWeather.getString("dt_txt"));

                //main5dto
                JSONObject main5DTOJsonObject = currentWeather.getJSONObject("main");
                Main5DTO main = new Main5DTO();
                main.setTemp(main5DTOJsonObject.getDouble("temp"));
                main.setHumidity(main5DTOJsonObject.getInt("humidity"));
                main.setPressure(main5DTOJsonObject.getDouble("pressure"));
                main.setTemp_max(main5DTOJsonObject.getDouble("temp_max"));
                main.setTemp_min(main5DTOJsonObject.getDouble("temp_min"));
                main.setTemp_kf(main5DTOJsonObject.getInt("temp_kf"));
                data.setMain(main);

                // clouds
                JSONObject cloudsJsonObject = currentWeather.getJSONObject("clouds");
                Clouds5DTO clouds = new Clouds5DTO();
                clouds.setAll(cloudsJsonObject.getInt("all"));
                data.setClouds5DTO(clouds);

                //sys
                JSONObject sys5DTOJsonObject = currentWeather.getJSONObject("sys");
                Sys5DTO sys = new Sys5DTO();
                sys.setPod(sys5DTOJsonObject.getString("pod"));
                data.setSys5DTO(sys);

                //wind
                JSONObject wind5DTOJsonObject = currentWeather.getJSONObject("wind");
                Wind5DTO wind = new Wind5DTO();
                wind.setSpeed(wind5DTOJsonObject.getDouble("speed"));
                wind.setDeg(wind5DTOJsonObject.getDouble("deg"));
                data.setWind5DTO(wind);


                //weather
                JSONArray weatherArray = currentWeather.getJSONArray("weather");
                List<WeatherCondition5DTO> weatherCondition5DTOs = new ArrayList<>();
                for (int j = 0; j < weatherArray.length(); j++) {
                    WeatherCondition5DTO currentWeatherConditionDTO = new WeatherCondition5DTO();
                    JSONObject currentConditionWeather = weatherArray.getJSONObject(j);
                    currentWeatherConditionDTO.setId(currentConditionWeather.getInt("id"));
                    currentWeatherConditionDTO.setDescription(currentConditionWeather.getString("description"));
                    currentWeatherConditionDTO.setMain(currentConditionWeather.getString("main"));
                    currentWeatherConditionDTO.setIcon(currentConditionWeather.getString("icon"));

                    weatherCondition5DTOs.add(currentWeatherConditionDTO);
                }
                data.setWeather(weatherCondition5DTOs);
                weatherData5DTOs.add(data);
            }
            response5DTO.setWeatherData5DTOs(weatherData5DTOs);

            //group weather data
            groupParsedWeatherData5(response5DTO);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response5DTO;
    }
}
