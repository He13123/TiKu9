package com.example.tiku9.net;

import org.json.JSONObject;

import java.io.IOException;

public interface OkHttpLo {
    void onResponse(JSONObject obj);

    void OnFailure(IOException obj);
}
