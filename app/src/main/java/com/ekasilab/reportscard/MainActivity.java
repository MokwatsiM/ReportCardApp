package com.ekasilab.reportscard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText txtUsername, txtPassword;
    private Button btnSignIn;

    @Override
    public void onClick(View v) {

        if (txtUsername.getText().toString().equalsIgnoreCase("poison") && txtPassword.getText().toString().equalsIgnoreCase("12345")) {

            startActivity(new Intent(this, OptionsActivity.class));
            txtPassword.setText("");
            txtUsername.setText("");
        } else {
            Toast.makeText(this, "Wrong credentials", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtPassword = (EditText) findViewById(R.id.password);
        txtUsername = (EditText) findViewById(R.id.username);

        btnSignIn = (Button) findViewById(R.id.sign_in);
btnSignIn.setAllCaps(true);

        btnSignIn.setOnClickListener(this);


    }
}
