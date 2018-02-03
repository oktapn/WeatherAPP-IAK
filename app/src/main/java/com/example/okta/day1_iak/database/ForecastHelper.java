package com.example.okta.day1_iak.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.okta.day1_iak.model.forecast.Forecast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Okta on 27/01/2018.
 */

public class ForecastHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ForecastManager";
    private static final String TABLE_NAME = "forecast";

    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_TEMP_MAX = "temp_max";
    private static final String KEY_TEMP_MIN = "temp_min";
    private static final String KEY_DESC = "desc";
    private static final String KEY_IMG_URL = "img_url";

    public ForecastHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FORECAST_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DATE + " TEXT,"
                + KEY_TEMP_MAX + " TEXT,"
                + KEY_TEMP_MIN + " TEXT,"
                + KEY_DESC + " TEXT,"
                + KEY_IMG_URL + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_FORECAST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //create table again
        onCreate(sqLiteDatabase);
    }

    public void addForecast(ForecastDB forecastDB) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, forecastDB.getDate());
        values.put(KEY_TEMP_MIN, forecastDB.getTemp_min());
        values.put(KEY_TEMP_MAX, forecastDB.getTemp_max());
        values.put(KEY_DESC, forecastDB.getDesc());
        values.put(KEY_IMG_URL, forecastDB.getImg_url());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<ForecastDB> getAllForecast() {
        List<ForecastDB> contactList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ForecastDB contact = new ForecastDB();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setDate(cursor.getString(1));
                contact.setTemp_max(cursor.getString(2));
                contact.setTemp_min(cursor.getString(3));
                contact.setDesc(cursor.getString(4));
                contact.setImg_url(cursor.getString(5));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }
}
