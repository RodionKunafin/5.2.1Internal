package com.example.a521internal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText login;
    EditText password;
    Button btnOk;
    Button btnRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        btnOk = findViewById(R.id.btnOk);
        btnRegistration = findViewById(R.id.btnRegistration);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("LoginFile", MODE_PRIVATE)))) {
                    writer.write("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("PasswordFile", MODE_PRIVATE)))) {
                    writer.write("Password");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(openFileInput("LoginFile")));
                    StringBuilder sb = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        sb.append(line);
                        line = reader.readLine();
                    }
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    reader = new BufferedReader(new InputStreamReader(openFileInput("PasswordFile")));
                    StringBuilder sb = new StringBuilder();
                    String line = reader.readLine();
                    while (line != null) {
                        sb.append(line);
                        line = reader.readLine();
                    }
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}
