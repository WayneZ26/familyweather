package app.familyweather.com.familyweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/7/8.
 */
public class FamilyWeatherOpenHelper extends SQLiteOpenHelper {

    private static final String CREATE_PROVINCE = "creat table Province("
            +"id integer primary key autonincrement"
            +"province_name text"
            +"province_code text";
    private static final String CREATE_CITY = "creat table City("
            +"id integer primary key autonincrement"
            +"city_name text"
            +"city_code text"
            +"province_id integer";
    private static final String CREAT_COUNTY = "creat table City("
            +"id integer primary key autonincrement"
            +"county_name text"
            +"county_code text"
            +"city_id integer";

    public FamilyWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREAT_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
