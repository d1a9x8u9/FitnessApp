package com.romodaniel.fitness.data;

import android.provider.BaseColumns;

/**
 * Created by Angie on 7/25/2017.
 */

public class Contract {
    public static class TABLE_INFO implements BaseColumns {

        public static final String TABLE_NAME = "userinfo";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_WEIGHT = "weight";
    }
}
