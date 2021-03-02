package com.example.minh.moneymanager.ThongKe;

/**
 * Created by ADMIN on 1/7/2018.
 */
import com.example.minh.moneymanager.DatabaseHandler;

import java.util.ArrayList;

public class TongTien {
    // index = 0 : Hôm nay
    // index = 0 : Tháng này
    // index = 0 : Năm nay
    public static String getTongTien(DatabaseHandler db, String khoanthuchi, int index){
        ArrayList<String> tien = new ArrayList<String>();
        switch (index) {
            case 0:
                tien = db.getlogTienThongkehomnay(khoanthuchi);
                break;
            case 1:
                tien = db.getlogTienThongkethangnay(khoanthuchi);
                break;
            case 2:
                tien = db.getlogTienThongkenamnay(khoanthuchi);
                break;
            default:
                return "0";
        }
        int temp = 0;
        for (int i = 0; i < tien.size(); i++){
            temp += Integer.parseInt(tien.get(i));
        }
        return Integer.toString(temp);
    }
}
