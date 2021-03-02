package com.example.minh.moneymanager;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minh.moneymanager.DatabaseHandler;

public class XemGiaoDichActivity extends AppCompatActivity {
    TextView vi;
    TextView soTien;
    TextView nhom;
    TextView ngayGiaoDich;
    Button xoa;

    DatabaseHandler db;
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_giao_dich);

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        final Intent intent = getIntent();

        vi = (TextView) findViewById(R.id.tv_ViTien);
        nhom = (TextView) findViewById(R.id.tv_PhanNhom);
        soTien = (TextView) findViewById(R.id.tv_SoTien);
        ngayGiaoDich = (TextView) findViewById(R.id.tv_NgayGiaoDich);
        xoa = (Button) findViewById(R.id.btnDel);

        vi.setText(intent.getStringExtra("Vi"));
        nhom.setText(intent.getStringExtra("Nhom"));
        soTien.setText(intent.getStringExtra("SoTien") + " VNĐ");
        ngayGiaoDich.setText(intent.getStringExtra("Ngay"));
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delcaidat();
            }
        });
    }

    public void delcaidat() {
        new AlertDialog.Builder(XemGiaoDichActivity.this)
                .setTitle("Chú ý")
                .setMessage("Bạn có chắc xóa không")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent get_intent = getIntent();
                        LayoutInflater inflater = getLayoutInflater();
                        View mToastView = inflater.inflate(R.layout.xoa_custom,
                                null);
                        mToast = new Toast(XemGiaoDichActivity.this);
                        mToast.setView(mToastView);
                        mToast.show();
                        db.Deletels(
                                get_intent.getStringExtra("Ngay"),
                                get_intent.getStringExtra("Nhom"),
                                get_intent.getStringExtra("SoTien"),
                                get_intent.getStringExtra("Vi"));
                        Intent intent = new Intent(getApplicationContext(),
                                ListGiaoDichMainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Không",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

    }

}
