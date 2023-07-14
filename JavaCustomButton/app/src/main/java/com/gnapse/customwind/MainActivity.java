package com.gnapse.customwind;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    CustomWindButton customWindButton;
    CustomFanButton customFanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customWindButton = findViewById(R.id.windButton);
        customFanButton = findViewById(R.id.fanButton);

        customWindButton.setOnWindStatusChangedListener(windStatus ->
                customWindButton.onWindStatusChanged(windStatus));

        customFanButton.setFanLevelChangedListener(fanLevel ->
                customFanButton.fanLevelChanged(fanLevel));
    }
}