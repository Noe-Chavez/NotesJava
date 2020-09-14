package com.dissekcorportion.notesjava.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dissekcorportion.notesjava.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = findViewById(R.id.buttonLogin);
    }

    public void onClickButtonLogin(View view) {
        Intent intent = new Intent(MainActivity.this, NotasActivity.class);
        startActivity(intent);
    }

}