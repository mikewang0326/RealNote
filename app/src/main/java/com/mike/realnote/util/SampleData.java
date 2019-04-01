package com.mike.realnote.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.mike.realnote.model.NoteEntity;

public class SampleData {
    private static final String TEXT_1 = "This is a sample text";
    private static final String TEXT_2 = "This is a sample\n text";
    private static final String TEXT_3 = "An Android App Bundle is a new upload format that includes all your app’s "
            + "compiled code and resources, but defers APK generation and signing to Google Play.\n"
            + "\n"
            + "Google Play’s new app serving model, called Dynamic Delivery, then uses your app bundle to generate "
            + "and serve optimized APKs for each user’s device configuration, so they download only the code and "
            + "resources they need to run your app. You no longer have to build, sign, and manage multiple APKs to "
            + "support different devices, and users get smaller, more optimized downloads.\n"
            + "\n"
            + "Additionally, you can add dynamic feature modules to your app project and include them in your app "
            + "bundle. These modules contain features and assets that you can decide not to include when users first "
            + "download and install your app. Using the Play Core Library, your app can later request to download "
            + "those modules as dynamic feature APKs, and, through Dynamic Delivery, Google Play serves only the code"
            + " and resources for that module to the device.";

    private static Date getDate(int diff) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MILLISECOND, diff);
        return cal.getTime();
    }

    public static List<NoteEntity> getNotes() {
        List<NoteEntity> notes = new ArrayList<>();
        notes.add(new NoteEntity(1, getDate(0), TEXT_1));
        notes.add(new NoteEntity(1, getDate(-1), TEXT_2));
        notes.add(new NoteEntity(1, getDate(-2), TEXT_3));

        return notes;
    }

}
