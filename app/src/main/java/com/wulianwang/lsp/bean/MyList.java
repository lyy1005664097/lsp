package com.wulianwang.lsp.bean;


import android.widget.ImageView;

/**
 *     成员：刘长恩 曹彬
 *     3.5 企业服务
 *     下拉框，不显示时间，所有企业未接工单，分页显示
 */

public class MyList {
    private String objname;

    public MyList(String objname){
        super();
        this.objname=objname;
    }
    public String getObjname(){
        return objname;
    }
    public  void setObjname(String objname){
        this.objname=objname;
    }
}
