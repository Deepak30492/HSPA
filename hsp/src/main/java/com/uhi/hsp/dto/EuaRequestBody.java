package com.uhi.hsp.dto;


import com.dhp.sdk.beans.Context;
import com.dhp.sdk.beans.Message;
import org.springframework.data.annotation.Transient;


public class EuaRequestBody {
    @Transient
    private Context context;

    private Message message;


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
