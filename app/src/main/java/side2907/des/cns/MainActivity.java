package side2907.des.cns;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarSelector;
    ListView listOfCurrent;
    SQLiteDatabase mainDB;
    ListAdapterNew listAdapter;
    private List<CardBlank> profiles = new ArrayList<>();
    String currentDate;
    SimpleDateFormat dateFormat;

    @Override
    protected void onResume() {
        super.onResume();
        preloaderData(currentDate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDB = new SQLiteDataBaseCNS(MainActivity.this).getReadableDatabase();
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        calendarSelector = (CalendarView) findViewById(R.id.calendarView);

        currentDate = dateFormat.format(new Date());

        listOfCurrent = (ListView) findViewById(R.id.listView);
        preloaderData(currentDate);

        calendarSelector.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                currentDate = makeMoreThanTwo(day) + "." + makeMoreThanTwo(month+1) + "." + String.valueOf(year);
                preloaderData(currentDate);

            }
        });


        listOfCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
                intent.putExtra("isAble", true);
                intent.putExtra("info_img", profiles.get(i).getImage());
                intent.putExtra("info_name", profiles.get(i).getName());
                intent.putExtra("info_date", profiles.get(i).getDate());
                intent.putExtra("info_time", profiles.get(i).getTime());
                intent.putExtra("info_text", profiles.get(i).getText());
                intent.putExtra("id", profiles.get(i).getId());
                startActivity(intent);
            }
        });

    }

    private String makeMoreThanTwo(int i){
        if (i<10) return "0"+String.valueOf(i);
        return String.valueOf(i);
    }

    private void preloaderData(String currentDate) {
        listAdapter = new ListAdapterNew(loadData(currentDate));
        listOfCurrent.setAdapter(listAdapter);
    }


    private List<CardBlank> loadData(String currentXDate) {
        profiles.clear();
        Cursor cursor = mainDB.query(SQLiteDataBaseCNS.DATABASE_TABLE, null, SQLiteDataBaseCNS.DATE + " = '" + currentXDate + "'", null, null, null, null);
        while (cursor.moveToNext()) {
            int image = R.drawable.no_img; //// TODO: 21.08.2016 make image changer and images
            String name = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.NAME));
            String date = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.DATE));
            String time = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.TIME));
            String text = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.TEXT));
            int id = cursor.getInt(cursor.getColumnIndex(SQLiteDataBaseCNS._ID));
            profiles.add(new CardBlank(image, name, date, time, text, id));
        }
        return profiles;
    }

    //// TODO: 02.09.2016 New activity for @show all@
//    public void loadAllData(View view){
//        listAdapter = new ListAdapterNew(loadDataAll());
//        listOfCurrent.setAdapter(listAdapter);
//        currentDate="";
//    }
//
//    private List<CardBlank> loadDataAll() {
//        profiles.clear();
//        Cursor cursor = mainDB.query(SQLiteDataBaseCNS.DATABASE_TABLE, null, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            int image = R.drawable.no_img;
//            String name = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.NAME));
//            String date = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.DATE));
//            String time = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.TIME));
//            String text = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.TEXT));
//            int id = cursor.getInt(cursor.getColumnIndex(SQLiteDataBaseCNS._ID));
//            profiles.add(new CardBlank(image, name, date, time, text, id));
//        }
//        return profiles;
//    }


    public void addRecord(View view) {
        Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
        intent.putExtra("isAble", false);
        startActivity(intent);

    }

}
