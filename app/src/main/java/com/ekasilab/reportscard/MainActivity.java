package com.ekasilab.reportscard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsername, txtPassword;
    private Button btnSignIn;

    @Override
    public void onClick(View v) {
        int pass = Integer.parseInt(txtPassword.getText().toString());
        if(txtUsername.getText().toString().equalsIgnoreCase("poison")&& pass==12345)
        {
            startActivity(new Intent(this,OptionsActivity.class));
            txtPassword.setText("");
            txtUsername.setText("");
        }else
        {
            Toast.makeText(this,"Wrong credentials",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        txtPassword = (EditText) findViewById(R.id.password);
        txtUsername = (EditText) findViewById(R.id.username);

        btnSignIn = (Button) findViewById(R.id.sign_in);

        btnSignIn.setOnClickListener(this);



    }
}
