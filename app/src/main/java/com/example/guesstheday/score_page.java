package com.example.guesstheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class score_page extends AppCompatActivity {
    TextView result;
    Button rst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        result=(TextView)findViewById(R.id.result);
        rst=(Button)findViewById(R.id.ret);
        Intent result_page=getIntent();
        String score= result_page.getStringExtra("final_score");
        result.setText(score);

        rst.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent start_new = new Intent(score_page.this, MainActivity.class);
                startActivity(start_new);
                finish();
            }
        });

    }
}