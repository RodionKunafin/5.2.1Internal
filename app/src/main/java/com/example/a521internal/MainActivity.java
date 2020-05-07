package com.example.a521internal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                    writer.write(login.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("PasswordFile", MODE_PRIVATE)))) {
                    writer.write(password.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("LoginFile")))) {
                    String line = reader.readLine();

                    String loginText = login.getText().toString();

                    if (!loginText.equals(line)) {
                        Toast.makeText(MainActivity.this,R.string.tos, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, R.string.tos, Toast.LENGTH_SHORT).show();
                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("PasswordFile")))) {
                    String line = reader.readLine();

                    String passwordText = password.getText().toString();

                    if (!passwordText.equals(line)) {
                        Toast.makeText(MainActivity.this, R.string.tos1, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, R.string.tos2, Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

