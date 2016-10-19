package com.ekasilab.reportscard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterStudentDetails extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName, txtSurname, txtAddress, txtSubjectName, txtTest1, txtTest2, txtTest3;
    private Button btnReg;

    DatabaseHandler db = new DatabaseHandler(this);
    List learnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register_student_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Register Student");
        txtAddress = (EditText) findViewById(R.id.address);
        txtSurname = (EditText) findViewById(R.id.surname);
        txtName = (EditText) findViewById(R.id.studentname);
        txtSubjectName = (EditText) findViewById(R.id.subjectname);
        txtTest1 = (EditText) findViewById(R.id.test1);
        txtTest2 = (EditText) findViewById(R.id.test2);
        txtTest3 = (EditText) findViewById(R.id.test3);
        btnReg = (Button) findViewById(R.id.register);
        learnerList = db.getAllLearner();
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name, surname, address, subjectName;
        double test1, test2, test3;
        LearnerReport learner;

        try {
            name = txtName.getText().toString();
            surname = txtSurname.getText().toString();
            address = txtAddress.getText().toString();
            subjectName = txtSubjectName.getText().toString();
            test1 = Double.parseDouble(txtTest1.getText().toString());
            test2 = Double.parseDouble(txtTest2.getText().toString());
            test3 = Double.parseDouble((txtTest3.getText().toString()));
            for (int i = 0; i < learnerList.size(); i++) {
                LearnerReport temp = (LearnerReport) learnerList.get(i);
                if (name.equalsIgnoreCase(temp.getName())) {
                    Toast.makeText(RegisterStudentDetails.this, "Sorry this learner " + temp.getName() + "  already exist\n try different name", Toast.LENGTH_LONG).show();
                    txtName.setText("");
                    txtName.setFocusable(true);
                    return;
                }

            }
            learner = new LearnerReport(name, surname, address, subjectName, test1, test2, test3);
            db.addLearner(learner);
            Toast.makeText(RegisterStudentDetails.this, "Learner added\n" + learner.getName(), Toast.LENGTH_LONG).show();
            txtSurname.setText("");
            txtTest3.setText("");
            txtTest2.setText("");
            txtAddress.setText("");
            txtTest1.setText("");
            txtName.setText("");
            txtTest1.setText("");
            txtSubjectName.setText("");

        } catch (IllegalArgumentException ex) {
            Toast.makeText(RegisterStudentDetails.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }



}
