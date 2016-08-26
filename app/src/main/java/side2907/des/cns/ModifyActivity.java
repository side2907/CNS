package side2907.des.cns;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Demo on 20.08.2016.
 */
public class ModifyActivity extends AppCompatActivity {

    EditText name;
    TextView date;
    TextView time;
    EditText text;
    String id;
    boolean isAble = false;
    SQLiteDatabase mainDB;
    Calendar dateAndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_modification);
        mainDB = new SQLiteDataBaseCNS(this).getWritableDatabase();

        dateAndTime=Calendar.getInstance();

        isAble = getIntent().getExtras().getBoolean("isAble");
        name = (EditText) findViewById(R.id.info_name);
        date = (TextView) findViewById(R.id.info_date);
        time = (TextView) findViewById(R.id.info_time);
        text = (EditText) findViewById(R.id.info_text);

        if (isAble) {
            name.setText(getIntent().getExtras().getString("info_name"));
            date.setText(getIntent().getExtras().getString("info_date"));
            time.setText(getIntent().getExtras().getString("info_time"));
            text.setText(getIntent().getExtras().getString("id")); //// TODO: 21.08.2016 Remove this statement
            id = getIntent().getExtras().getString("id");
        }
    }

    public void saveChanges(View v) {
        if (isAble) updateInDB();
        else insertIntoDB();
    }

    public void insertIntoDB() {
        ContentValues values = new ContentValues();
        values.put(SQLiteDataBaseCNS.NAME, String.valueOf(name.getText()));
        values.put(SQLiteDataBaseCNS.DATE, String.valueOf(text.getText())); //// TODO: 21.08.2016 Change to normal state; add time field
        values.put(SQLiteDataBaseCNS.TEXT, String.valueOf(text.getText()));
        values.put(SQLiteDataBaseCNS.IMAGE, "null");
        mainDB.insert(SQLiteDataBaseCNS.DATABASE_TABLE, null, values);
        this.finish();
    }

    public void updateInDB() {
        ContentValues values = new ContentValues();
        values.put(SQLiteDataBaseCNS.NAME, String.valueOf(name.getText()));
        values.put(SQLiteDataBaseCNS.DATE, String.valueOf(text.getText())); //// TODO: 21.08.2016 Change to normal state; add time field
        values.put(SQLiteDataBaseCNS.TEXT, String.valueOf(text.getText()));
        values.put(SQLiteDataBaseCNS.IMAGE, "null");
        mainDB.update(SQLiteDataBaseCNS.DATABASE_TABLE,values, SQLiteDataBaseCNS._ID +" = "+id,null);
        this.finish();
    }

    public void deletEntry(View v){
        mainDB.delete(SQLiteDataBaseCNS.DATABASE_TABLE,SQLiteDataBaseCNS._ID+" = '"+id+"'",null);
        this.finish();
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();

    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();

    }
    // установка начальных даты и времени
    private void setInitialDateTime() {

        time.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

}
