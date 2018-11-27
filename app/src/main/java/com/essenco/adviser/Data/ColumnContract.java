package com.essenco.adviser.Data;

import android.provider.BaseColumns;

public final class ColumnContract implements BaseColumns {
    public static final String TABLE_NAME = "Advise";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME_ADVISE = "advise";

    public static final String CREATE_TABLE_ADVISE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME_ADVISE
            + " TEXT" + ")";
}
