package side2907.des.cns;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDB = new SQLiteDataBaseCNS(MainActivity.this).getReadableDatabase();

        calendarSelector = (CalendarView) findViewById(R.id.calendarView);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.M.dd");
        currentDate = dateFormat.format(new Date());

        listOfCurrent = (ListView) findViewById(R.id.listView);
        preloaderData(currentDate);

        calendarSelector.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                currentDate = String.valueOf(year) +"."+ String.valueOf(month) +"."+ String.valueOf(day);
                preloaderData(currentDate);
            }
        });

        listOfCurrent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
                intent.putExtra("isAble", true);
                intent.putExtra("info_name", profiles.get(i).getName());
                intent.putExtra("info_date", profiles.get(i).getTime());
                intent.putExtra("info_time", String.valueOf(calendarSelector.getDate()));
                intent.putExtra("id", String.valueOf(profiles.get(i).getId()));
                startActivity(intent);
            }
        });

    }

    private void preloaderData(String currentDate){
        listAdapter = new ListAdapterNew(loadData(currentDate));
        listOfCurrent.setAdapter(listAdapter);
    }


    private List<CardBlank> loadData(String currentXDate) {
        profiles.clear();
        Cursor cursor = mainDB.query(SQLiteDataBaseCNS.DATABASE_TABLE, null,SQLiteDataBaseCNS.DATE+" = '"+currentXDate+"'",null,null,null,null);//// TODO: 21.08.2016 FIX Month and check it!!!
        while (cursor.moveToNext()){
                int image = R.drawable.no_img; //// TODO: 21.08.2016 make image changer and images
                String name = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.NAME));
                int id = cursor.getInt(cursor.getColumnIndex(SQLiteDataBaseCNS._ID));
                String date = cursor.getString(cursor.getColumnIndex(SQLiteDataBaseCNS.DATE));
                profiles.add(new CardBlank(image, name, date, id));
        }
        return profiles;
    }



    public void addRecord(View view) {
        Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
        intent.putExtra("isAble", false);
        startActivity(intent);

    }

}
