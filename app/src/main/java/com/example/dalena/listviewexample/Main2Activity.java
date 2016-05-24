package com.example.dalena.listviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    public static final String TAG = Main2Activity.class.getSimpleName();
    private String username;
    private track[] trackList;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        username = intent.getStringExtra("name");
        list = (ListView) findViewById(R.id.listView);

        String blogCommand = "https://api.tumblr.com/v2/blog/" + username + ".tumblr.com/posts/audio?api_key=" + getString(R.string.apiKey);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(blogCommand).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
             try {
                String jsonData = response.body().string();
                trackList = getTracks(jsonData);
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         trackAdapter adapter = new trackAdapter(Main2Activity.this, trackList);
                         list.setAdapter(adapter);
                     }
                 });
            } catch (JSONException e) {
                 Log.e(TAG, "Exception caught: ", e);
             }
            }
        });
    }

    private track[] getTracks(String jsonData) throws JSONException {
        JSONObject blogInfo = new JSONObject(jsonData);
        JSONArray data = blogInfo.getJSONObject("response").getJSONArray("posts");

        track[] mList = new track[20];
        for(int i = 0; i < 20; i++) {
            JSONObject mPost = data.getJSONObject(i);
            track mtrack = new track();
            mtrack.setNoteCount(mPost.getString("note_count"));
            mtrack.setPlayNumber(mPost.getString("plays"));
            mtrack.setTrackName(mPost.getString("track_name"));
            mList[i] = mtrack;
        }
        return mList;
    }
}
