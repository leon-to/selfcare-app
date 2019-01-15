package com.ece496.selfcare;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

import static java.util.TimeZone.getAvailableIDs;

public class Scheduler{
    private ContentResolver contentResolver;
    private String title;
    private String description;
    private String location;
    private Calendar begin_time;
    private Calendar end_time;

    public Scheduler(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    public Scheduler title(String title)            {this.title = title; return this;}
    public Scheduler description(String description){this.description = description; return this;}
    public Scheduler location(String location)      {this.location = location; return this;}
    public Scheduler begin_time(int year, int month, int date, int hour, int minute){
        this.begin_time = Calendar.getInstance();
        this.begin_time.set(year, month, date, hour, minute);
        return this;
    }
    public Scheduler end_time(int year, int month, int date, int hour, int minute){
        this.end_time = Calendar.getInstance();
        this.end_time.set(year, month, date, hour, minute);
        return this;
    }

    public void schedule(){
        ContentValues values = new ContentValues();

        values.put(Events.DTSTART, begin_time.getTimeInMillis());
        values.put(Events.DTEND, end_time.getTimeInMillis());
        values.put(Events.TITLE, title);
        values.put(Events.DESCRIPTION, description);
        values.put(Events.EVENT_LOCATION, location);
        values.put(Events.CALENDAR_ID, Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? 3:1);
        values.put(Events.EVENT_TIMEZONE, getAvailableIDs()[0]);

        Uri uri = contentResolver.insert(Events.CONTENT_URI, values);
    }
}
