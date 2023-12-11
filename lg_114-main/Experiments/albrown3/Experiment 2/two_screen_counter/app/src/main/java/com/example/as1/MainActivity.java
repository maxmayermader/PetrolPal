package com.example.as1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button button;

    TextView theySaid;
    EditText entryBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theySaid = findViewById(R.id.textView);
        entryBox = findViewById(R.id.editText);
        button = findViewById(R.id.submitButton);
        theySaid.setText("Your Text Here!");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                theySaid.setText(entryBox.getText());
            }
        });


    }


}