package com.ece496.selfcare;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import static java.util.TimeZone.getAvailableIDs;

public class Scheduler{
    private static final String LOCAL_ACCOUNT = "com.ece496.selfcare";
    private static final String SELFCARE = "selfcare";
    private static final Logger logger = Logger.getLogger("Scheduler");

    private ContentResolver contentResolver;
    private long calendar_id = -1;

    private String title;
    private String description;
    private String location;
    private Calendar begin_time;
    private Calendar end_time;

    // SINGLETON
    private static Scheduler scheduler = null;
    private Scheduler(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }
    public static Scheduler getInstance(ContentResolver contentResolver){
        if (scheduler == null) {
            scheduler = new Scheduler(contentResolver);
            scheduler   .query_calendar()
                        .create_calendar();
        }
        scheduler.contentResolver = contentResolver;
        return scheduler;
    }
    // SINGLETON

    // ADVANCE DSL
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
    // ADVANCE DSL

//    public void query_calendar

    public void schedule(){
        ContentValues values = new ContentValues();

        values.put(Events.DTSTART, begin_time.getTimeInMillis());
        values.put(Events.DTEND, end_time.getTimeInMillis());
        values.put(Events.TITLE, title);
        values.put(Events.DESCRIPTION, description);
        values.put(Events.EVENT_LOCATION, location);
        values.put(Events.CALENDAR_ID, calendar_id);
        values.put(Events.EVENT_TIMEZONE, getAvailableIDs()[0]);
//        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? 3:1
        Uri uri = contentResolver.insert(Events.CONTENT_URI, values);
        long eventID = Long.parseLong(uri.getLastPathSegment());
        System.out.println(eventID);
    }




    private Scheduler query_calendar(){
        final String[] EVENT_PROJECTION = new String[] {
                Calendars._ID,                           // 0
                Calendars.ACCOUNT_NAME,                  // 1
                Calendars.CALENDAR_DISPLAY_NAME,         // 2
                Calendars.OWNER_ACCOUNT                  // 3
        };
        final int PROJECTION_ID_INDEX = 0;
        final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
        final int PROJECTION_DISPLAY_NAME_INDEX = 2;
        final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

        Cursor cursor = contentResolver.query(
                Calendars.CONTENT_URI,
                EVENT_PROJECTION,
                "(("+Calendars.ACCOUNT_NAME+"=?)AND("+ Calendars.ACCOUNT_TYPE+"=?))",
                new String[] {"com.ece496.selfcare", CalendarContract.ACCOUNT_TYPE_LOCAL},
                null);

        while (cursor.moveToNext()) {
            long calendar_id = cursor.getLong(PROJECTION_ID_INDEX);
            String display_name = cursor.getString(PROJECTION_DISPLAY_NAME_INDEX);

            if (display_name.equals(SELFCARE)){
                logger.info("CALENDAR WITH SELFCARE NAME FOUND! (ID: " + calendar_id+ ")");
                this.calendar_id = calendar_id;
                return this;
            }
        }

        return this;
    }

    public Scheduler create_calendar(){
        if (this.calendar_id!=-1) {
            return this;
        }

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Calendars.ACCOUNT_NAME,LOCAL_ACCOUNT);
        values.put(CalendarContract.Calendars.ACCOUNT_TYPE,CalendarContract.ACCOUNT_TYPE_LOCAL);
        values.put(CalendarContract.Calendars.NAME, SELFCARE);
        values.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, SELFCARE);
        values.put(CalendarContract.Calendars.CALENDAR_COLOR,0xffff0000);
        values.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
//        values.put(CalendarContract.Calendars.OWNER_ACCOUNT,"some.account@googlemail.com");
//        values.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE,"Europe/Berlin");
        values.put(CalendarContract.Calendars.SYNC_EVENTS,1);

        Uri.Builder builder = CalendarContract.Calendars.CONTENT_URI.buildUpon();
        builder .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME,LOCAL_ACCOUNT)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE,CalendarContract.ACCOUNT_TYPE_LOCAL)
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER,"true");
        Uri uri = contentResolver.insert(builder.build(), values);

        return this;
    }
}
