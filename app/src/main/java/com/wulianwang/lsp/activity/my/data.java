package com.wulianwang.lsp.activity.my;

public class data {

    private  String question;
    private  String address;
    private  String doing;

    public data(String question,String address,String doing){
        this.question=question;
        this.address=address;
        this.doing=doing;
    }

    public String getQuestion(){
        return question;
    }
    public String getAddress(){
        return address;
    }
    public String getDoing(){
        return doing;
    }

}

