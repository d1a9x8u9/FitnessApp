package com.romodaniel.fitness.data;

import android.provider.BaseColumns;

/**
 * Created by drdan on 7/26/2017.
 */

public class Contract {

    public static class TABLE_RUNS implements BaseColumns {
        public static final String TABLE_NAME = "runs";
        public static final String COLUMN_NAME_MILES = "miles";
        public static final String COLUMN_NAME_CAL = "cslories";
        public static final String COLUMN_NAME_MINUTES = "minutes";
        public static final String COLUMN_NAME_SECONDS = "seconds";
        public static final String COLUMN_NAME_STEPS = "step";

    }


}
