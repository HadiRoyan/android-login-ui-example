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

import java.util.Arrays;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    Validation validation = new Validation();
    TextInputLayout name_layout;
    TextInputLayout email_layout;
    TextInputLayout password_layout;
    TextInputLayout confirmPassword_layout;

    TextInputEditText name;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText confirmPassword;

    TextView login;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name_layout = (TextInputLayout) findViewById(R.id.name_inputLayout);
        email_layout = (TextInputLayout) findViewById(R.id.email_signupLayout);
        password_layout = (TextInputLayout) findViewById(R.id.password_signupLayout);
        confirmPassword_layout = (TextInputLayout) findViewById(R.id.confirmPassword_signupLayout);

        // set text changed listener
        name = (TextInputEditText) findViewById(R.id.name_inputEditText);

        email = (TextInputEditText) findViewById(R.id.email_signEditText);
        email.addTextChangedListener(new ValidationTextWitcher(email));

        password = (TextInputEditText) findViewById(R.id.password_signupEditText);
        password.addTextChangedListener(new ValidationTextWitcher(password));

        confirmPassword = (TextInputEditText) findViewById(R.id.confirmPassword_signupEditText);
        confirmPassword.addTextChangedListener(new ValidationTextWitcher(confirmPassword));

        login = (TextView) findViewById(R.id.tv_login_signupActivity);
        btn_signup = (Button) findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameUser = name.getText().toString();
                String emailUser = email.getText().toString();
                char[] passwordUser = password.getText().toString().toCharArray();

                if (validationSignup() ) {
                    Log.i(TAG, "onClick: SIGN UP SUCCESS, validation: "+validationSignup());
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.putExtra("NAME", nameUser);
                    intent.putExtra("EMAIL", emailUser);
                    intent.putExtra("PASSWORD", passwordUser);

                    startActivity(intent);
                } else {
                    Log.i(TAG, "onClick: SIGN UP FAIL, validation: "+ validationSignup());
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    boolean validationSignup() {
        return validation.validateEmail(email_layout, email) &&
                validation.validatePassword(password_layout, password) &&
                !name.getText().toString().trim().isEmpty();
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
                case R.id.email_signEditText:
                    validation.validateEmail(email_layout, email);
                    break;

                case R.id.password_signupEditText:
                    validation.validatePassword(password_layout, password);
                    break;

                case R.id.confirmPassword_signupEditText:
                    validation.validateConfirmPassword(confirmPassword_layout, confirmPassword, password);
                    break;
            }
        }
    }
}