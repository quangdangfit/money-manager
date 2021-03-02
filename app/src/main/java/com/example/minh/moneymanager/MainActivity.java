package com.example.minh.moneymanager;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.ChineseCalendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.minh.moneymanager.ThongKe.StatsActivity;

public class MainActivity extends TabActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("account", MODE_PRIVATE);
        String storedPin = preferences.getString("pin", "1234");
        if (storedPin.equals("1234")) {
            Toast.makeText(MainActivity.this, "Vui lòng thay đổi PIN để bảo mật", Toast.LENGTH_SHORT).show();
        }
        final TabHost tabHost = getTabHost();

        TabHost.TabSpec thuchi = tabHost.newTabSpec("Thu Chi");
        thuchi.setIndicator("Thu Chi", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent i = new Intent(MainActivity.this, ThuChiMainActivity.class);
        thuchi.setContent(i);

        TabHost.TabSpec thongke = tabHost.newTabSpec("Thống Kê");

        thongke.setIndicator("Thống Kê", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent o = new Intent(this, StatsActivity.class);
        thongke.setContent(o);

        TabHost.TabSpec caidat = tabHost.newTabSpec("Cài Đặt");
        caidat.setIndicator("Cài Đặt", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent p = new Intent(this, CaiDatActivity.class);
        caidat.setContent(p);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.open();

        String[] thu = {"Lương", "Thưởng", "Tiết kiệm", "Lãi suất", "Thu hồi nợ"};
        String[] chi = {"Ăn uống", "Sức khỏe", "Giáo dục", "Du lịch", "Giải trí", "Nhà cửa",
                "Hóa đơn", "Cho vay", "Gửi tiết kiệm", "Quà tặng"};

        for (int j = 0; j < thu.length; j++) {
            if (db.kiemtra("Khoản Thu", thu[j]) == true) {
                db.themkhoanthuchi("Khoản Thu", thu[j]);
            }
        }

        for (int j = 0; j < chi.length; j++) {
            if (db.kiemtra("Khoản Chi", chi[j]) == true) {
                db.themkhoanthuchi("Khoản Chi", chi[j]);
            }
        }

        db.close();

        tabHost.addTab(thuchi);
        tabHost.addTab(thongke);
        tabHost.addTab(caidat);

        tabHost.setCurrentTab(0);

    }

}

