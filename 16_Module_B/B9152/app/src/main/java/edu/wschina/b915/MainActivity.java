package edu.wschina.b915;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.wschina.b915.model.InfoModel;

/**
 * 首页
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner type, date, time;
    private Button btn;
    private String[] items = {"请选择餐桌类型", "小桌（2座）", "中桌（4座）", "大桌（8座）", "房间（12座）"};
    private String[] times = {"请选择时间段", "08:00-10:00", "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00"};
    private List<String> dates = new ArrayList<>();
    private InfoModel infoModel = new InfoModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("首页");
        type = (Spinner) findViewById(R.id.type);
        date = (Spinner) findViewById(R.id.date);
        time = (Spinner) findViewById(R.id.time);
        btn = (Button) findViewById(R.id.submit);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> bb = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, times);
        type.setAdapter(aa);
        time.setAdapter(bb);
        Calendar calendar = Calendar.getInstance();//获取当前日期
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dates.add("请选择日期（未来7天）");
        dates.add(day + "号");
        for (int i = 0; i < 6; i++) {
            if (++day > 30) {
                day = 1;
            }
            dates.add(day+"号");
        }
        ArrayAdapter<String> cc = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,dates);
        date.setAdapter(cc);
        date.setOnItemSelectedListener(this);
        type.setOnItemSelectedListener(this);
        time.setOnItemSelectedListener(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(infoModel.getDate().isEmpty()||infoModel.getType().isEmpty()||infoModel.getTime().isEmpty()){
                    Toast.makeText(MainActivity.this, "请选择完整信息！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, OrderDishes.class);
                intent.putExtra("data",infoModel);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.date:
                if(i==0){
                    infoModel.setDate("");
                    return;
                }
                infoModel.setDate(dates.get(i));
                Log.i("tag", "onItemSelected: 1");
                break;
            case R.id.type:
                if(i==0){
                    infoModel.setType("");
                    return;
                }
                infoModel.setType(items[i]);
                Log.i("tag", "onItemSelected: 2");
                break;
            case R.id.time:
                if(i==0){
                    infoModel.setTime("");
                    return;
                }
                infoModel.setTime(times[i]);
                Log.i("tag", "onItemSelected: 3");
                Log.i("tag", "onItemSelected: "+infoModel.getTime());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}