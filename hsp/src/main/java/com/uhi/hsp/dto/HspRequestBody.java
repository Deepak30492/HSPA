package com.uhi.hsp.dto;


import com.dhp.sdk.beans.Context;
import com.dhp.sdk.beans.Message;

public class HspRequestBody {
    private Context context;
    private Message message;

    public HspRequestBody() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SearchBody{" +
                "context=" + context +
                ", message=" + message +
                '}';
    }
}
