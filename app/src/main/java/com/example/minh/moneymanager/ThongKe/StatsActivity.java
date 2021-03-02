package com.example.minh.moneymanager.ThongKe;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.minh.moneymanager.R;

public class StatsActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        final TabHost tabHost = getTabHost();

        TabHost.TabSpec homnay = tabHost.newTabSpec("Hôm Nay");
        homnay.setIndicator("Hôm Nay", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent hn = new Intent(StatsActivity.this, StatsHomNay.class);
        homnay.setContent(hn);

        TabHost.TabSpec thangnay = tabHost.newTabSpec("Tháng Này");
        thangnay.setIndicator("Tháng Này", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent tn = new Intent(this, StatsThangNay.class);
        thangnay.setContent(tn);

        TabHost.TabSpec namnay = tabHost.newTabSpec("Năm Nay");
        namnay.setIndicator("Năm Nay", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent nn = new Intent(this, StatsNamNay.class);
        namnay.setContent(nn);



        tabHost.addTab(homnay);
        tabHost.addTab(thangnay);
        tabHost.addTab(namnay);
        tabHost.setCurrentTab(0);

    }
}
