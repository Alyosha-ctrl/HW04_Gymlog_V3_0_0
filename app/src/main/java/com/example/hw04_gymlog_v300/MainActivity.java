package com.example.hw04_gymlog_v300;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hw04_gymlog_v300.Database.GymLogRepository;
import com.example.hw04_gymlog_v300.Database.entities.GymLog;
import com.example.hw04_gymlog_v300.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "DAC_GYMLOG";

    private ActivityMainBinding binding;

    String exercise = "";
    double weight = 0.0;
    int reps = 0;

    private GymLogRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = GymLogRepository.getRepository(getApplication());

        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());

        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                insertGymLogRecord();
                updateDisplay();
                Toast.makeText(MainActivity.this, "It Worked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertGymLogRecord(){
        GymLog log = new GymLog(exercise, weight, reps);
        repository.insertGymLog(log);
    }

    /**
     * This takes in the information from the binding converts into variables that java can use,
     * and then adds the stuff to the screen.
     */
    private void updateDisplay(){
        String currentInfo = binding.logDisplayTextView.getText().toString();
        String newDisplay = String.format(Locale.US, "Exercise:%s%nWeight:%.2f%nReps:%d%n=-=-=-=%n%s", exercise, weight, reps, currentInfo);
        binding.logDisplayTextView.setText(newDisplay);
        Log.i(TAG, repository.getAllLogs().toString());
    }

    private void getInformationFromDisplay(){
        exercise = binding.ExerciseInputEditText.getText().toString();
        try {
            weight = Double.parseDouble(binding.WeightInputEditText.getText().toString());
        }
        catch (NumberFormatException e){
            Log.d(TAG, "Error reading vale from WeightInputEditText");
        }
        try {
            reps = Integer.parseInt(binding.RepsInputEditText.getText().toString());
        }
        catch (NumberFormatException e){
            Log.d(TAG, "Error reading vale from RepsInputEditText");
        }
    }
}