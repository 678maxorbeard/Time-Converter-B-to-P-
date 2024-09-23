package com.example.timeconverterbtop;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private EditText inputTime;
    private TextView outputTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputTime = findViewById(R.id.input_time);
        outputTime = findViewById(R.id.output_time);
        Button convertButton = findViewById(R.id.convert_button);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTime();
            }
        });
    }

    private void convertTime() {
        String inputValue = inputTime.getText().toString();
        if (inputValue.isEmpty()) {
            outputTime.setText("Please enter a time in HH:mm format.");
            return;
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
        inputFormat.setTimeZone(TimeZone.getTimeZone("Europe/Brussels"));
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
        outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Karachi"));

        try {
            Date date = inputFormat.parse(inputValue);
            String convertedTime = outputFormat.format(date);
            outputTime.setText("Converted Time: " + convertedTime);
        } catch (ParseException e) {
            outputTime.setText("Invalid time format. Please use HH:mm.");
        }
    }
}
