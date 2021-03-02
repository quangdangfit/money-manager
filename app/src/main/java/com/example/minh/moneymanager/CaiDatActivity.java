package com.example.minh.moneymanager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minh.moneymanager.ThongKe.CaiDat;
import com.example.minh.moneymanager.ThongKe.CaiDatAdapter;
import com.example.minh.moneymanager.ThongKe.ThongKe;

import java.util.ArrayList;

public class CaiDatActivity extends Activity {
    ListView listView;
    EditText editText;
    private ArrayList<String> lists;
    private ArrayList<CaiDat> llist;
    DatabaseHandler db;
    Spinner spinnerthemthuchi;
    Toast mToast;
    private String tvkhoanthu = "Khoản Thu";
    private String tvkhoanchi = "Khoản Chi";
    ImageView addcaidat;

    Button btThu, btChi;
    int flagthuchi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_dat);
        editText=(EditText)findViewById(R.id.edcaidat);
        addcaidat=(ImageView)findViewById(R.id.addcaidat);
        btThu = (Button) findViewById(R.id.btnThu);
        btChi = (Button) findViewById(R.id.btnChi);
        db = new DatabaseHandler(getApplicationContext());
        db.open();
        spinnerthemthuchi=(Spinner)findViewById(R.id.spinthemthuchicaidat);
        listView=(ListView)findViewById(R.id.listviewCaiDat);

        ArrayList<String> themthuchi=new ArrayList<>();
        themthuchi.add(tvkhoanchi);
        themthuchi.add(tvkhoanthu);
        ArrayAdapter themthuchiadapter=new ArrayAdapter(CaiDatActivity.this,android.R.layout.simple_list_item_1,themthuchi);
        spinnerthemthuchi.setAdapter(themthuchiadapter);

        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Chọn - "+tvkhoanchi);
        arrayList.add("Chọn - "+tvkhoanthu);
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);

        llist=db.getALLS("");
        CaiDatAdapter arrayAdapter1=new CaiDatAdapter(getParent(),R.layout.custom_tong,llist);
        listView.setAdapter(arrayAdapter1);

        // List mặc định
        lists= (ArrayList<String>) db.getCaiDatphanloai("Khoản Thu");
        ArrayAdapter adapterthu = new ArrayAdapter(getParent(),android.R.layout.simple_list_item_1,lists);
        listView.setAdapter(adapterthu);
        flagthuchi = 1;

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(flagthuchi == 0){
                    xoa(position,tvkhoanchi);
                }if(flagthuchi == 1){
                    xoa(position,tvkhoanthu);
                }
                return false;
            }
        });

        addcaidat.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().length() > 0) {
                    String spinthuchi = spinnerthemthuchi.getSelectedItem().toString();

                    if (db.kiemtra(spinthuchi, editText.getText().toString()) == true) {
                        db.themkhoanthuchi(spinnerthemthuchi.getSelectedItem().toString(), editText.getText().toString());
                        LayoutInflater inflater = getLayoutInflater();
                        View mToastView = inflater.inflate(R.layout.them_thanhcong,
                                null);
                        mToast = new Toast(CaiDatActivity.this);
                        mToast.setView(mToastView);
                        mToast.show();
                        Intent intent = new Intent(CaiDatActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        editText.setText("");
                        db.close();
                    } else {
                        Toast.makeText(getApplicationContext(), "Nhóm đã tồn tại", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập gì cả",Toast.LENGTH_SHORT).show();
                    editText.requestFocus();
                }


            }
        });

        btThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lists= (ArrayList<String>) db.getCaiDatphanloai("Khoản Thu");
                ArrayAdapter adapterthu = new ArrayAdapter(getParent(),android.R.layout.simple_list_item_1,lists);
                listView.setAdapter(adapterthu);
                flagthuchi = 1;
            }
        });


        btChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lists= (ArrayList<String>) db.getCaiDatphanloai("Khoản Chi");
                ArrayAdapter adapterthu = new ArrayAdapter(getParent(),android.R.layout.simple_list_item_1,lists);
                listView.setAdapter(adapterthu);

                flagthuchi = 0;
            }
        });

    }
    public void xoa(final int location, final String thuchi) {
        new AlertDialog.Builder(CaiDatActivity.this)
                .setTitle("Chú ý?")
                .setMessage("Bạn có muốn xóa không?")
                .setPositiveButton("Có",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                db.Delete(listView.getItemAtPosition(location).toString(), thuchi);

                                LayoutInflater inflater = getLayoutInflater();
                                View mToastView = inflater.inflate(R.layout.xoa_custom,
                                        null);
                                mToast = new Toast(CaiDatActivity.this);
                                mToast.setView(mToastView);
                                mToast.show();
                                db.close();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivityForResult(intent, 8);
                                finish();
                            }
                        })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}
