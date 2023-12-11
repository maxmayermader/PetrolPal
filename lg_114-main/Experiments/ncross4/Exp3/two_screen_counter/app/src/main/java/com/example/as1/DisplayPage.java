package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayPage extends AppCompatActivity {

    Button backBtn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaypage);
        backBtn = findViewById(R.id.backBtn);
        text = findViewById(R.id.message);
        Intent intent = getIntent();
        String messageRecieved = intent.getStringExtra("key");
        text.setText(messageRecieved);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(DisplayPage.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}