package com.example.interviewquestionnaire;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText fullName, age;
    private SeekBar salarySeekBar;
    private TextView salaryValue, resultText;
    private RadioGroup question1, question2, question3, question4, question5;
    private CheckBox experience, teamDevSkills, travelReadiness;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        fullName = findViewById(R.id.full_name);
        age = findViewById(R.id.age);
        salarySeekBar = findViewById(R.id.salary_seekbar);
        salaryValue = findViewById(R.id.salary_value);
        resultText = findViewById(R.id.result_text);
        question1 = findViewById(R.id.question1);
        question2 = findViewById(R.id.question2);
        question3 = findViewById(R.id.question3);
        question4 = findViewById(R.id.question4);
        question5 = findViewById(R.id.question5);
        experience = findViewById(R.id.experience);
        teamDevSkills = findViewById(R.id.team_dev_skills);
        travelReadiness = findViewById(R.id.travel_readiness);
        submitButton = findViewById(R.id.submit_button);


        submitButton.setEnabled(false);

        TextWatcher textWatcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!fullName.getText().toString().isEmpty() && !age.getText().toString().isEmpty()){
                    submitButton.setEnabled(true);
                }
            }
        };
        fullName.addTextChangedListener(textWatcher);
        age.addTextChangedListener(textWatcher);
        // Set SeekBar listener
        salarySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                salaryValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Set Submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle form submission and display results
                int score = 0;
                if (experience.isChecked()) score += 2;
                if (teamDevSkills.isChecked()) score += 1;
                if (travelReadiness.isChecked()) score += 1;

                // Add points for first answers in RadioGroups
                if (question1.getCheckedRadioButtonId() == question1.getChildAt(2).getId()) score += 2;
                if (question2.getCheckedRadioButtonId() == question2.getChildAt(1).getId()) score += 2;
                if (question3.getCheckedRadioButtonId() == question3.getChildAt(3).getId()) score += 2;
                if (question4.getCheckedRadioButtonId() == question4.getChildAt(2).getId()) score += 2;
                if (question5.getCheckedRadioButtonId() == question5.getChildAt(1).getId()) score += 2;

                int ageInt=Integer.parseInt(age.getText().toString());
                int salary = Integer.parseInt(salaryValue.getText().toString());

                if (ageInt<21||ageInt>40) {
                    resultText.setText("Age is beyond company expectations");
                    resultText.setVisibility(View.VISIBLE);
                    return;
                }
                if(salary<900||salary>1700){
                    resultText.setText("Salary is beyond company expectations");
                    resultText.setVisibility(View.VISIBLE);
                    return;
                }
                if(fullName.getText().toString().isEmpty()){
                    resultText.setText("Name is empty");
                    resultText.setVisibility(View.VISIBLE);
                    return;
                }

                if (score>=10){
                    resultText.setText("You passed!\n+companyTel\ncompanyAdress@gmail.com");
                }
                else {
                    resultText.setText("Score not sufficient to pass the questionnaire.");
                }
                resultText.setVisibility(View.VISIBLE);
            }
        });
    }

}
