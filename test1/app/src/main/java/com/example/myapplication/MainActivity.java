package com.example.myapplication;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etAge;
    private SeekBar sbSalary;
    private RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    private CheckBox cbExperience, cbTeamSkills, cbTravel;
    private Button btnSubmit;
    private TextView tvResult, tvSalary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        sbSalary = findViewById(R.id.sbSalary);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup4 = findViewById(R.id.radioGroup4);
        radioGroup5 = findViewById(R.id.radioGroup5);
        cbExperience = findViewById(R.id.cbExperience);
        cbTeamSkills = findViewById(R.id.cbTeamSkills);
        cbTravel = findViewById(R.id.cbTravel);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResult = findViewById(R.id.tvResult);
        tvSalary = findViewById(R.id.tvSalary);

        sbSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSalary.setText("Зарплата: " + (900 + progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSubmit.setEnabled(validateInput());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        etName.addTextChangedListener(textWatcher);
        etAge.addTextChangedListener(textWatcher);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    int score = calculateScore();
                    if (score >= 10) {
                        tvResult.setText("Вітаємо! Ви пройшли тест. Контакти компанії: ...");
                    } else {
                        tvResult.setText("На жаль, ви не пройшли тест.");
                    }
                    tvResult.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private boolean validateInput() {
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Будь ласка, введіть ПІБ.", LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(ageStr)) {
            Toast.makeText(this, "Будь ласка, введіть вік.", LENGTH_SHORT).show();
            return false;
        }

        int age = Integer.parseInt(ageStr);
        if (age < 21 || age > 40) {
            Toast.makeText(this, "Вік повинен бути від 21 до 40 років.", LENGTH_SHORT).show();
            return false;
        }

        int salary = 900 + sbSalary.getProgress();
        if (salary < 900 || salary > 1700) {
            Toast.makeText(this, "Зарплата повинна бути від 900 до 1700 USD.", LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private int calculateScore() {
        int score = 0;

        if (((RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId())).getText().equals("8")) {
            score += 2;
        }
        if (cbExperience.isChecked()) {
            score += 2;
        }

        if (cbTeamSkills.isChecked()) {
            score += 1;
        }

        if (cbTravel.isChecked()) {
            score += 1;
        }

        return score;
    }
}
