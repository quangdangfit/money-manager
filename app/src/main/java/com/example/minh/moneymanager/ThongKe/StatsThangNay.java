package com.example.minh.moneymanager.ThongKe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.minh.moneymanager.DatabaseHandler;
import com.example.minh.moneymanager.R;
import com.example.minh.moneymanager.ThongKe.TongTien;

import java.util.ArrayList;

public class StatsThangNay extends Activity {
    private ListView listViewtongthuchiHN;
    private ArrayList<ThongKe> listchi;
    private ArrayList<ThongKe> listthu;
    DatabaseHandler db;
    TextView tvTitle;
    ListView listView;
    Button btthu,btchi;

    // View tổng tiền
    TextView tvTong;
    String donvi = "VNĐ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongke);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        listViewtongthuchiHN=(ListView)findViewById(R.id.listViewhienthithongke);
        btthu=(Button)findViewById(R.id.btthu);
        btchi=(Button)findViewById(R.id.btchi);
        tvTong = (TextView) findViewById(R.id.textView2TongTien);
        db=new DatabaseHandler(getApplicationContext());
        db.open();
        listthu=db.getloggiaodichThongkethangnay("Khoản Thu");
        final ThongKeAdapter tkThu=new ThongKeAdapter(getParent(),R.layout.custom_listview,listthu);
        listViewtongthuchiHN.setAdapter(tkThu);
        listchi=db.getloggiaodichThongkethangnay("Khoản Chi");
        final ThongKeAdapter tkChi = new ThongKeAdapter(getParent(),R.layout.custom_listview,listchi);
        tvTitle.setText("THÁNG NÀY");

        tvTong.setText("0 " + donvi);

        btchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewtongthuchiHN.setAdapter(tkChi);
                tvTong.setText(TongTien.getTongTien(db, "Khoản Chi", 1) + " " + donvi);
            }
        });
        btthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewtongthuchiHN.setAdapter(tkThu);
                tvTong.setText(TongTien.getTongTien(db, "Khoản Thu", 1) + " " + donvi);
            }
        });
    }
    }
