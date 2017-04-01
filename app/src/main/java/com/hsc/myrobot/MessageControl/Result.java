package com.hsc.myrobot.MessageControl;

/**
 * Created by 15827 on 2017/3/30.
 */

public class Result {
    private int code;
    private String text;

    public Result()
    {
    }

    public Result(int resultCode, String msg)
    {
        this.code = resultCode;
        this.text = msg;
    }

    public Result(int resultCode)
    {
        this.code = resultCode;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
