package com.example.dalena.listviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private EditText mUserName;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = (EditText)findViewById(R.id.editUserName);
        mSubmitButton = (Button)findViewById(R.id.submitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = mUserName.getText().toString();
                catapultNewActivity(name);
            }
        });
    }

    private void catapultNewActivity(String name) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
}
