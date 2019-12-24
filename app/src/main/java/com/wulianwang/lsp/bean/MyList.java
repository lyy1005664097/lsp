package com.wulianwang.lsp.bean;


import android.widget.ImageView;

/**
 *     成员：刘长恩 曹彬
 *     3.5 企业服务
 *     下拉框，不显示时间，所有企业未接工单，分页显示
 */

public class MyList {
    private String objname;

    private String imageUrl;

    private String address;

    private String time;

    public MyList(String objname, String imageUrl, String address, String time){
        this.objname = objname;
        this.imageUrl = imageUrl;
        this.address = address;
        this.time = time;
    }
    public String getObjname(){
        return objname;
    }
    public  void setObjname(String objname){
        this.objname=objname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
