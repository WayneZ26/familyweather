package app.familyweather.com.familyweather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.familyweather.com.familyweather.db.FamilyWeatherOpenHelper;

/**
 * Created by Administrator on 2015/7/15.
 */
public class FamilyWeatherDB {

    /**
     * ���ݿ���
     */
    public static final String DB_NAME = "family_weather";
    /**
     * ���ݿ�汾
     */
    public static final int VERSION = 1;

    private static FamilyWeatherDB familyWeatherDB;

    private SQLiteDatabase db;

    private FamilyWeatherDB(Context context){
        FamilyWeatherOpenHelper dbHelper = new FamilyWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static FamilyWeatherDB getInstance(Context context){
        if(familyWeatherDB  == null){
            familyWeatherDB = new FamilyWeatherDB(context);
        }
        return familyWeatherDB;
    }

    /**
     * ��Provinceʵ���洢�����ݿ�
     */
    public void saveProvince(Province province){
        if (province!=null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }

    /**
     * �����ݿ��ȡȫ��ʡ����Ϣ
     */
    public List<Province> loadProvince(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("prvoince_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * ��Cityʵ���洢�����ݿ�
     */
    public void saveCity(City city){
        if (city!=null){
            ContentValues values = new ContentValues();
            values.put("city_code",city.getCityCode());
            values.put("city_name",city.getCityName());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }

    /**
     * �����ݿ�����ȡĳʡ������Ϣ
     */
    public List<City> loadCity(){
        List<City> list =new ArrayList<City>();
        Cursor cursor = db.query("City",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
                list.add(city);
            }while (cursor.moveToNext());
        }
        return list;
    }

    /**
     * ��ȫ��Countyʵ���洢�����ݿ�
     */
    private void saveCounty(County county){
        if (county!=null){
            ContentValues values = new ContentValues();
            values.put("city_id",county.getCityid());
            values.put("county_code",county.getCountyCode());
            values.put("county_name",county.getCountyName());
            db.insert("County",null,values);
        }
    }

    /**
     * �����ݿ�����ȡĳ��������������
     */
    private List<County> loadCounty(){
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCityid(cursor.getInt(cursor.getColumnIndex("city_id")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
            }while (cursor.moveToNext());
        }

        return list;
    }
}
