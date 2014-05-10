package com.divinedube.yamba;

import android.provider.BaseColumns;

/**
 * Created by Divine on 2014/05/10.
 */
public class StatusContract {
    public static final String DB_NAME = "timeline.db",
            TABLE = "status",
            DEFAULT_SORT = Column.CREATED_AT + " DESC";

    public  static final int DB_VERSION = 1;


    public class Column {

        public static final String ID = BaseColumns._ID,
                 USER = "user",
                 MASSAGE = "massage",
                 CREATED_AT = "created_at";
    }
}

