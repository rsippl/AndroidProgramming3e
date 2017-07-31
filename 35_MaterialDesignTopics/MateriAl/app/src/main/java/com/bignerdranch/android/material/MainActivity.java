package com.bignerdranch.android.material;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar.make(container, R.string.munch, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
