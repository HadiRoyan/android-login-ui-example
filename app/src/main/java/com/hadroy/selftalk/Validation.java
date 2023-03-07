package com.hadroy.selftalk;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Validation {

    public boolean validateEmail(
            TextInputLayout textInputLayout,
            TextInputEditText textInputEditText) {

        if (textInputEditText.getText().toString().trim().isEmpty()) {
            textInputLayout.setErrorEnabled(false);
            return false;
        } else {
            String emailId = textInputEditText.getText().toString();
            boolean  isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid) {
                textInputLayout.setError("Invalid Email address, ex: abc@example.com");
                return false;
            } else {
                textInputLayout.setErrorEnabled(false);
            }
        }
        return true;
    }

    public boolean validatePassword(
            TextInputLayout password_layout,
            TextInputEditText password_editText) {

        if (password_editText.getText().toString().trim().isEmpty()) {
            password_layout.setHelperText("Password is required");
            password_layout.setHelperTextEnabled(true);
            return false;
        }else if(password_editText.getText().toString().length() < 8){
            password_layout.setHelperTextEnabled(false);
            password_layout.setError("Password can't be less than 8 digit");
            return false;
        }
        else {
            password_layout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validateConfirmPassword(
            TextInputLayout confirmPassword_layout,
            TextInputEditText confirmPassword_text,
            TextInputEditText password_text) {

        String password = password_text.getText().toString();
        String confirmPassword = confirmPassword_text.getText().toString();

        if (confirmPassword_text.getText().toString().trim().isEmpty()) {
            confirmPassword_layout.setErrorEnabled(false);
            confirmPassword_layout.setHelperTextEnabled(true);
            confirmPassword_layout.setHelperText("Rewrite Password");
            return false;
        } else {
            if (!password.equals(confirmPassword)) {
                confirmPassword_layout.setError("Wrong confirm password");
                return false;
            } else {
                confirmPassword_layout.setErrorEnabled(false);
            }
        }

        return true;
    }

}
