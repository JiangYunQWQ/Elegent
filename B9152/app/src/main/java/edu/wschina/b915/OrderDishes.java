package edu.wschina.b915;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//点菜界面
public class OrderDishes extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dishes);
        setTitle("点菜",true);
    }
}