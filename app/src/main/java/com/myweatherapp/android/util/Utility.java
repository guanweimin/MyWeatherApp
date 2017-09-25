package com.myweatherapp.android.util;

import android.text.TextUtils;

import com.myweatherapp.android.db.City;
import com.myweatherapp.android.db.County;
import com.myweatherapp.android.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by min on 2017/9/24.
 */

public class Utility {
    //解析和处理服务器返回的省级数据
    public  static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for (int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObj = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.save();
                }
                return  true;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    //解析和处理服务器的市级数据
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCites = new JSONArray(response);
                for (int i=0;i< allCites.length();i++){
                    JSONObject cityObj = allCites.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return  true;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    //解析和处理服务器的县级数据
    public static boolean handleCountyResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for (int i=0;i< allCounties.length();i++){
                    JSONObject cityObj = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(cityObj.getString("name"));
                    county.setWeatherId(cityObj.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return  true;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
