package com.msc.mobileapps.mwanabiashara;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.msc.mobileapps.mwanabiashara.constants.AppConstants.PASSWORD;

public class LoginActivity extends AppCompatActivity {

    EditText pin;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pin = findViewById(R.id.pin);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogin();
            }
        });
    }

    private void mLogin() {
        String s = pin.getText().toString();
        if (s == null || s.length() != 4) {
            Toast.makeText(this, "Invalid PIN, must be 4 numbers", Toast.LENGTH_SHORT).show();
            return;
        }
        evaluatePassword(s);
    }

    private void evaluatePassword(String p) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String pwd = sharedPref.getString(PASSWORD, "");

        if (pwd.isEmpty()) {
            // first login, alert user and run setup
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(getString(R.string.first_time_login));
            dialog.setPositiveButton(getString(R.string.proceed), (dialogInterface, i) -> runSetup(p));
            dialog.setNegativeButton("Cancel", (dialogInterface, i) -> {
                // do nothing
            });
            dialog.show();
        } else {
            if (pwd.equals(p)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                // TODO: 21/12/20 limit number of tries and lock app
                Toast.makeText(this, "Wrong PIN, try again", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void runSetup(String p) {
        // save PIN
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PASSWORD, p);
        editor.apply();
        // TODO: 21/12/20 run database setup

        // proceed to main
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}