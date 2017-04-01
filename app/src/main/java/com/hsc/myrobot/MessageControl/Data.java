package com.hsc.myrobot.MessageControl;

/**
 * Created by 15827 on 2017/3/30.
 */

public class Data {
    public static final int RECEIVE = 1;
    public static final int SEND = 2;
    private String content;
    private int falg;
    

    public Data(String content, int falg) {
        this.content = content;
        this.falg = falg;
        //this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFalg(int falg) {
        this.falg = falg;
    }

    public String getContent() {
        return content;
    }

    public int getFalg() {
        return falg;
    }
    
}
