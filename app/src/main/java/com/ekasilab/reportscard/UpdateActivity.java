package com.ekasilab.reportscard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName, txtSurname, txtAddress, txtSubjectName, txtTest1, txtTest2, txtTest3;
    private TextView txtStdNo;
    private Button btnUpdate;
    LearnerReport learner;
    List learnerList;
    String name, surname, address, subjectName;
    double test1, test2, test3;

    int rows,studentNo;

    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtAddress = (EditText) findViewById(R.id.updateAddress);
        txtSurname = (EditText) findViewById(R.id.updateSurname);
        txtName = (EditText) findViewById(R.id.updateName);
        txtSubjectName = (EditText) findViewById(R.id.updateSubjectname);
        txtTest1 = (EditText) findViewById(R.id.updateTest1);
        txtTest2 = (EditText) findViewById(R.id.updateTest2);
        txtTest3 = (EditText) findViewById(R.id.updateTest3);
        txtStdNo = (TextView) findViewById(R.id.studentNo);
        btnUpdate = (Button) findViewById(R.id.updateStudent);

        Intent in = getIntent();
        learner = (LearnerReport) getIntent().getExtras().getSerializable("learner");

        txtName.setText(learner.getName());
        txtSurname.setText(learner.getSurname());
        txtAddress.setText(learner.getLearnerAddress());
        txtSubjectName.setText(learner.getSubjectName());
        txtTest1.setText(String.format("" + learner.getTest1()));
        txtTest2.setText(String.format("" + learner.getTest2()));
        txtTest3.setText(String.format("" + learner.getTest3()));
        txtStdNo.setText(""+learner.getStdNo());
        learnerList = db.getAllLearner();
        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        try {
            studentNo = Integer.parseInt(txtStdNo.getText().toString());
            name = txtName.getText().toString();
            surname = txtSurname.getText().toString();
            address = txtAddress.getText().toString();
            subjectName = txtSubjectName.getText().toString();
            test1 = Double.parseDouble(txtTest1.getText().toString());
            test2 = Double.parseDouble(txtTest2.getText().toString());
            test3 = Double.parseDouble((txtTest3.getText().toString()));
//            for (int i = 0; i < learnerList.size(); i++) {
//                LearnerReport temp = (LearnerReport) learnerList.get(i);
//                if (name.equalsIgnoreCase(temp.getName())) {
//                    Toast.makeText(UpdateActivity.this, "Sorry this learner " + temp.getName() + "  already exist\n try different name", Toast.LENGTH_LONG).show();
//                    txtName.setText("");
//                    txtName.setFocusable(true);
//                    return;
//                }
//
//            }


            new AlertDialog.Builder(this).setTitle("Update Record")
                    .setMessage("Are you sure You want to update this learners details").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    update();

                }
            })
                    .setNegativeButton("No", null).show();


        } catch (IllegalArgumentException ex) {
            Toast.makeText(UpdateActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    public void update() {
        learner = new LearnerReport(studentNo,name, surname, address, subjectName, test1, test2, test3);
        rows = db.updateLearner(learner);
        Toast.makeText(UpdateActivity.this, "Learner " + learner.getName() + " is updated", Toast.LENGTH_LONG).show();
        txtSurname.setText("");
        txtTest3.setText("");
        txtTest2.setText("");
        txtAddress.setText("");
        txtTest1.setText("");
        txtName.setText("");
        txtSubjectName.setText("");
        Intent in = new Intent(UpdateActivity.this,LearnerDetails.class);
        in.putExtra("learner",learner);
        startActivity(in);
        finish();
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

