package com.dhp.sdk.beans;

public class OnTBody {
    private Context context;
    private OnMessage message;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public OnMessage getMessage() {
        return message;
    }

    public void setMessage(OnMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OnTBody{" +
                "context=" + context +
                ", message=" + message +
                '}';
    }
}
