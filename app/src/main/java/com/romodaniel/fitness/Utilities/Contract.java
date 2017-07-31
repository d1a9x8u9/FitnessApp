package com.romodaniel.fitness.Utilities;

import android.provider.BaseColumns;

/**
 * Created by Daniel on 7/25/2017.
 */

public class Contract {
    public static class TABLE_FITNESS implements BaseColumns {
        public static final String TABLE_NAME = "calories";

        public static final String COLUMN_TOTALCALORIES= "totalcalories";
        public static final String COLUMN_NAME_DATE ="date";
        }
    }
