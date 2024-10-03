package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAdd, buttonSubs, buttonMul, buttonDiv, buttonClear, buttonSin, buttonCos, buttonTan;
    EditText editTextN1, editTextN2, editTextTrig;
    TextView textView, textViewTrig;
    RadioButton radians, degrees;
    int num1, num2;
    boolean useRadians = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        buttonAdd = findViewById(R.id.btn_add);
        buttonSubs = findViewById(R.id.btn_subs);
        buttonMul = findViewById(R.id.btn_mul);
        buttonDiv = findViewById(R.id.btn_div);
        buttonClear = findViewById(R.id.btn_clear);
        buttonSin = findViewById(R.id.btn_sin);
        buttonCos = findViewById(R.id.btn_cos);
        buttonTan = findViewById(R.id.btn_tan);

        editTextN1 = findViewById(R.id.number1);
        editTextN2 = findViewById(R.id.number2);
        textView = findViewById(R.id.answer);
        editTextTrig = findViewById(R.id.number3);
        textView = findViewById(R.id.answer);
        textViewTrig = findViewById(R.id.answer2);

        radians = findViewById(R.id.radians);
        degrees = findViewById(R.id.degrees);

        buttonAdd.setOnClickListener(this);
        buttonSubs.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonSin.setOnClickListener(this);
        buttonCos.setOnClickListener(this);
        buttonTan.setOnClickListener(this);

        radians.setOnClickListener(view -> useRadians = true);
        degrees.setOnClickListener(view -> useRadians = false);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    public int getIntegerFromEditText(EditText editText){

        if (editText.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter number", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return Integer.parseInt(editText.getText().toString());
    }

    public double getDoubleFromEditText(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter number", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return Double.parseDouble(editText.getText().toString());
    }

    private void clearFields() {
        editTextN1.setText("");
        editTextN2.setText("");
        editTextTrig.setText("");
        textView.setText("");
        textViewTrig.setText("");
    }






    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        num1 = getIntegerFromEditText(editTextN1);
        num2 = getIntegerFromEditText(editTextN2);
        if (view.getId() == R.id.btn_add) {
            textView.setText("Answer = " + (num1 + num2));
        } else if (view.getId() == R.id.btn_subs) {
            textView.setText("Answer = " + (num1 - num2));
        } else if (view.getId() == R.id.btn_mul) {
            textView.setText("Answer = " + (num1 * num2));
        } else if (view.getId() == R.id.btn_div) {
            if (num2 != 0) {
                textView.setText("Answer = " + ((float) num1 / (float) num2));
            } else {
                Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
            }
        }
        else if (view.getId() == R.id.btn_clear) {
            clearFields();
        }
        else if (view.getId() == R.id.btn_sin) {
            performTrigonometricOperation("sin");
        } else if (view.getId() == R.id.btn_cos) {
            performTrigonometricOperation("cos");
        } else if (view.getId() == R.id.btn_tan) {
            performTrigonometricOperation("tan");
        }





    }
    @SuppressLint("SetTextI18n")
    private void performTrigonometricOperation(String operation) {
        double num = getDoubleFromEditText(editTextTrig);

        if (!useRadians) {
            num = Math.toRadians(num);
        }

        double result = 0;
        switch (operation) {
            case "sin":
                result = Math.sin(num);
                break;
            case "cos":
                result = Math.cos(num);
                break;
            case "tan":
                result = Math.tan(num);
                break;
        }

        textViewTrig.setText("Answer = " + result);
    }
}
