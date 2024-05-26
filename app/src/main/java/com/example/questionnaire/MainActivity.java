package com.example.questionnaire;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}

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
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText etName, etAge;
    private SeekBar sbSalary;
    private TextView tvSalary, tvResult;
    private RadioGroup rgQuestion1, rgQuestion2, rgQuestion3, rgQuestion4, rgQuestion5;
    private CheckBox cbExperience, cbTeamwork, cbTravel;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        sbSalary = findViewById(R.id.sb_salary);
        tvSalary = findViewById(R.id.tv_salary);
        tvResult = findViewById(R.id.tv_result);
        rgQuestion1 = findViewById(R.id.rg_question1);
        rgQuestion2 = findViewById(R.id.rg_question2);
        rgQuestion3 = findViewById(R.id.rg_question2);
        rgQuestion4 = findViewById(R.id.rg_question2);
        rgQuestion5 = findViewById(R.id.rg_question2);
        cbExperience = findViewById(R.id.cb_experience);
        cbTeamwork = findViewById(R.id.cb_teamwork);
        cbTravel = findViewById(R.id.cb_travel);
        btnSubmit = findViewById(R.id.btn_submit);

        sbSalary.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSalary.setText("Зарплата: " + progress + "$");
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
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        etName.addTextChangedListener(textWatcher);
        etAge.addTextChangedListener(textWatcher);

        btnSubmit.setOnClickListener(v -> submitForm());
    }

    private void validateForm() {
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        btnSubmit.setEnabled(!name.isEmpty() && !ageStr.isEmpty());
    }

    private void submitForm() {
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        int age = Integer.parseInt(ageStr);
        int salary = sbSalary.getProgress();

        if (age < 21 || age > 40) {
            showToast("Вік повинен бути від 21 до 40 років");
            return;
        }

        if (salary < 900 || salary > 1700) {
            showToast("Зарплата повинна бути від 900 до 1700 USD");
            return;
        }

        int score = calculateScore();
        if (score >= 10) {
            tvResult.setVisibility(View.VISIBLE);
            tvResult.setText("Вітаємо! Ви пройшли тест. Контакти компанії: info@itcompany.com");
        } else {
            tvResult.setVisibility(View.VISIBLE);
            tvResult.setText("На жаль, ви не пройшли тест.");
        }
    }

    private int calculateScore() {
        int score = 0;

        if (rgQuestion1.getCheckedRadioButtonId() == R.id.rb_q1_c) score += 2;
        // Повторіть для інших питань (rgQuestion2, rgQuestion3, rgQuestion4, rgQuestion5)

        if (cbExperience.isChecked()) score += 2;
        if (cbTeamwork.isChecked()) score += 1;
        if (cbTravel.isChecked()) score += 1;

        return score;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
