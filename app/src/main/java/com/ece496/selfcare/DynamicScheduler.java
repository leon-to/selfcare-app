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

import static java.util.TimeZone.getAvailableIDs;

public class DynamicScheduler {
    public static final String[] EVENT_PROJECTION = new String[] {
            Calendars._ID,                           // 0
            Calendars.ACCOUNT_NAME,                  // 1
            Calendars.CALENDAR_DISPLAY_NAME,         // 2
            Calendars.OWNER_ACCOUNT                  // 3
    };

    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    private ContentResolver contentResolver;

    private static DynamicScheduler dynamicScheduler = null;

    private DynamicScheduler(){}

    public static DynamicScheduler getInstance(ContentResolver contentResolver){
        if (dynamicScheduler == null){
            dynamicScheduler = new DynamicScheduler();
        }
        dynamicScheduler.contentResolver = contentResolver;
        return dynamicScheduler;
    }

    String read_events() {
        Cursor cursor = contentResolver.query(
                Calendars.CONTENT_URI,
                EVENT_PROJECTION,
                null, null, null
        );
        StringBuilder string_builder = new StringBuilder();

        while (cursor.moveToNext()) {
            long calendar_id = cursor.getLong(PROJECTION_ID_INDEX);
            String calendar_name = cursor.getString(PROJECTION_DISPLAY_NAME_INDEX);

            string_builder  .append(calendar_id)
                            .append(": ")
                            .append(calendar_name)
                            .append("\n");
        }

        return string_builder.toString();
    }

}
