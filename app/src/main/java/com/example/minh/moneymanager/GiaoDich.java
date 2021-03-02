package com.example.minh.moneymanager;

/**
 * Created by ADMIN on 1/12/2018.
 */

public class GiaoDich {
    String _vi = "Abc";
    String _loaiGiaoDich;
    String _soTien;
    String _ghiChu;
    String _nhom;
    String _trangThai;
    String _ngayGD;

    public GiaoDich(String _vi, String _loaiGiaoDich, String _soTien, String _ghiChu, String _nhom, String _trangThai, String _ngayGD) {
        this._vi = _vi;
        this._loaiGiaoDich = _loaiGiaoDich;
        this._soTien = _soTien;
        this._ghiChu = _ghiChu;
        this._nhom = _nhom;
        this._trangThai = _trangThai;
        this._ngayGD = _ngayGD;
    }

    public String get_vi() {
        return _vi;
    }

    public String get_loaiGiaoDich() {
        return _loaiGiaoDich;
    }

    public String get_soTien() {
        return _soTien;
    }

    public String get_ghiChu() {
        return _ghiChu;
    }

    public String get_nhom() {
        return _nhom;
    }

    public String get_trangThai() {
        return _trangThai;
    }

    public String get_ngayGD() {
        return _ngayGD;
    }
}
