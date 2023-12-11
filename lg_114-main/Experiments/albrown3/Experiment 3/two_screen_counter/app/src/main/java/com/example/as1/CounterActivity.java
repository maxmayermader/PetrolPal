package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class CounterActivity extends AppCompatActivity {


    Button backBtn;

    TextView message;

    TextView label;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        backBtn = findViewById(R.id.backBtn);
        label = findViewById(R.id.label);
        message = findViewById(R.id.textView);

        Intent recieverIntent = getIntent();
        String sent = recieverIntent.getStringExtra("theirMessage");
        message.setText(sent);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(CounterActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });


    }
}