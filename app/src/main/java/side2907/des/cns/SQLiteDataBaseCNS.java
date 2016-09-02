package side2907.des.cns;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Demo on 21.08.2016.
 */
public class SQLiteDataBaseCNS extends SQLiteOpenHelper implements BaseColumns {
    public static final String DATABASE_NAME = "CNSDB.db";
    public static final String DATABASE_TABLE = "MAIN";
    public static final int DATABASE_VERSION = 3;
    public static final String NAME = "NAME";
    public static final String DATE = "FULL_DATE";
    public static final String TIME = "FULL_TIME";
    public static final String TEXT = "TEXT";
    public static final String IMAGE = "IMG";
    private static final String DB_SCRIPT = "CREATE TABLE " + DATABASE_TABLE + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + DATE + " TEXT, " + TIME + " TEXT, " + TEXT + " TEXT, " + IMAGE + " TEXT);";

    public SQLiteDataBaseCNS(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public SQLiteDataBaseCNS(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    SQLiteDataBaseCNS(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(sqLiteDatabase);
    }
}
