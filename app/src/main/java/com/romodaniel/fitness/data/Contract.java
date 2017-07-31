package com.romodaniel.fitness.data;

import android.provider.BaseColumns;

/**

 * Created by drdan on 7/26/2017.
 */

public class Contract {

    public static class TABLE_RUNS implements BaseColumns {
        public static final String TABLE_NAME = "runs";
        public static final String COLUMN_NAME_MILES = "miles";
        public static final String COLUMN_NAME_CAL = "calories";
        public static final String COLUMN_NAME_TIME= "time";
        public static final String COLUMN_NAME_STEPS = "step";

    }


    public static class TABLE_USER implements BaseColumns {

        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_HEIGHT= "height";
        public static final String COLUMN_NAME_WEIGHT = "weight";
    }
}
