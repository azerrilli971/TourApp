package uniba.di.sms.ibtourapp.tourapp;

import android.provider.BaseColumns;

public class UsersList {
    private UsersList() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_TITLE = "id";
        public static final String COLUMN_NAME_SUBTITLE = "infopoint";
    }
}
