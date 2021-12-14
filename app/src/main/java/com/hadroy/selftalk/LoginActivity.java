package com.hadroy.selftalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextInputLayout et_emailInputLayout;
    TextInputLayout et_passwordInputLayout;
    TextInputEditText password_textInputEditText;
    TextInputEditText email_textInputEditText;

    TextView forgotPassword;
    TextView signUp;

    Button btn_login;
    String email;
    char[] password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);

        signUp = (TextView) findViewById(R.id.tv_signup);
        forgotPassword = (TextView) findViewById(R.id.tv_forgotPassword);

        et_emailInputLayout = (TextInputLayout) findViewById(R.id.email_textInputLayout);
        et_passwordInputLayout = (TextInputLayout) findViewById(R.id.password_textInputLayout);
        email_textInputEditText = (TextInputEditText) findViewById(R.id.email_editTextLayout);
        password_textInputEditText = (TextInputEditText) findViewById(R.id.password_editTextLayout);

        email_textInputEditText.addTextChangedListener(new ValidationTextWitcher(email_textInputEditText));
        password_textInputEditText.addTextChangedListener(new ValidationTextWitcher(password_textInputEditText));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = email_textInputEditText.getText().toString();
                password = password_textInputEditText.getText().toString().toCharArray();

                if (validateEmail() && validatePassword()) {
                    login(email, password);
                    Log.i(TAG, "onClick: login SUCCESS, email: "+ validateEmail() + ", pass: "+ validatePassword());
                } else {
                    Log.i(TAG, "onClick: login FAIL, email: "+ validateEmail() + ", pass: "+ validatePassword());
                }
            }
        });
    }

    private void login(String email, char[] password) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("EMAIL", email);
        intent.putExtra("PASSWORD", password);
        startActivity(intent);
    }
    public boolean validateEmail() {
        if (email_textInputEditText.getText().toString().trim().isEmpty()) {
            et_emailInputLayout.setErrorEnabled(false);
        } else {
            String emailId = email_textInputEditText.getText().toString();
            boolean  isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid) {
                et_emailInputLayout.setError("Invalid Email address, ex: abc@example.com");
                return false;
            } else {
                et_emailInputLayout.setErrorEnabled(false);
            }
        }
        return true;
    }

    public boolean validatePassword() {
        if (password_textInputEditText.getText().toString().trim().isEmpty()) {
            et_passwordInputLayout.setError("Password is required");
            return false;
        }else if(password_textInputEditText.getText().toString().length() < 8){
            et_passwordInputLayout.setError("Password can't be less than 8 digit");
            return false;
        }
        else {
            et_passwordInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private class ValidationTextWitcher implements TextWatcher {

        final private View view;

        ValidationTextWitcher(View view) {
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
            switch (view.getId()) {
                case R.id.email_editTextLayout:
                    validateEmail();
                    break;

                case R.id.password_editTextLayout:
                    validatePassword();
                    break;
            }
        }
    }
}