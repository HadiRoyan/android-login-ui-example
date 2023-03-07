package com.hadroy.selftalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String TAG = "ForgotPasswordActivity";

    Validation validation = new Validation();
    TextInputLayout email_layout;
    TextInputLayout newPassword_layout;
    TextInputLayout confirmNewPassword_layout;

    TextInputEditText email;
    TextInputEditText newPassword;
    TextInputEditText confirmNewPassword;

    Button btn_process;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email_layout = (TextInputLayout) findViewById(R.id.email_forgotPasswordLayout);
        newPassword_layout = (TextInputLayout) findViewById(R.id.newPassword_forgotPasswordLayout);
        confirmNewPassword_layout = (TextInputLayout) findViewById(R.id.confirmPassword_forgotPasswordLayout);

        email = (TextInputEditText) findViewById(R.id.email_forgotPasswordText);
        newPassword = (TextInputEditText) findViewById(R.id.newPassword_forgotPasswordText);
        confirmNewPassword = (TextInputEditText) findViewById(R.id.confirmPassword_forgotPasswordText);

        email.addTextChangedListener(new ValidationTextWatcher(email));
        newPassword.addTextChangedListener(new ValidationTextWatcher(newPassword));
        confirmNewPassword.addTextChangedListener(new ValidationTextWatcher(confirmNewPassword));

        btn_process = (Button) findViewById(R.id.button_process);
        btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = email.getText().toString();
                char[] newPasswordUser = newPassword.getText().toString().toCharArray();
                char[] confirmPasswordUser = confirmNewPassword.getText().toString().toCharArray();

                if (validationProcess()) {
                    Log.i(TAG, "onClick: process SUCCESS");
                    startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                } else {
                    Log.i(TAG, "onClick: process FAIL");
                }
            }
        });
    }

    boolean validationProcess() {
        return validation.validateEmail(email_layout, email) &&
                validation.validatePassword(newPassword_layout, newPassword) &&
                validation.validateConfirmPassword(confirmNewPassword_layout, confirmNewPassword, newPassword);
    }

    public class ValidationTextWatcher implements TextWatcher {

        private final View view;
        ValidationTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch(view.getId()) {
                case R.id.email_forgotPasswordText:
                    validation.validateEmail(email_layout, email);
                    break;

                case R.id.newPassword_forgotPasswordText:
                    validation.validatePassword(newPassword_layout, newPassword);
                    break;

                case R.id.confirmPassword_forgotPasswordText:
                    validation.validateConfirmPassword(confirmNewPassword_layout, confirmNewPassword, newPassword);
            }
        }
    }

}