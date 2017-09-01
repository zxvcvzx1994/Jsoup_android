package com.cvcompany.vo.myapplication.Module;

/**
 * Created by vinh on 9/1/2017.
 */

public class TiengAnh {

    private String Ten;
    private String HinhAnh;

    public TiengAnh(){

    }

    public TiengAnh(String ten, String hinhAnh) {
        Ten = ten;
        HinhAnh = hinhAnh;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
