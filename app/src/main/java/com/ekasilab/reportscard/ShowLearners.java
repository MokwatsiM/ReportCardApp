package com.ekasilab.reportscard;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowLearners extends AppCompatActivity implements AdapterView.OnItemClickListener, android.widget.SearchView.OnQueryTextListener {

    private ArrayList<String> nameList = new ArrayList<>();
    private LearnerReport[] arrLearner;
    private ListView list;
    private android.widget.SearchView searchStd;
    private EditText txtSearchName;
    DatabaseHandler db = new DatabaseHandler(this);
    LearnerReport learner = new LearnerReport();
    ArrayAdapter<String> listAdapter;
    List<LearnerReport> learnerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_learners);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Students");
        DatabaseHandler myDatabase = new DatabaseHandler(ShowLearners.this);
        learnerList = myDatabase.getAllLearner();
        searchStd = (android.widget.SearchView) findViewById(R.id.searchStudent);
        searchStd.setSubmitButtonEnabled(true);

        for (int i = 0; i < learnerList.size(); i++) {
            LearnerReport temp = learnerList.get(i);
            nameList.add(temp.getName());

        }
        listAdapter = new ArrayAdapter<>(this, R.layout.text, nameList);


        list = (ListView) findViewById(R.id.listLearners);
        list.setAdapter(listAdapter);
        list.setOnItemClickListener(this);
        searchStd.setOnQueryTextListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//
//        if (learner == null) {
//            Toast.makeText(ShowLearners.this, "No learner wwas found", Toast.LENGTH_LONG).show();
//        } else {
        String selectedName = listAdapter.getItem(position);

        learner = db.getLearner(selectedName);
        Intent in = new Intent(ShowLearners.this, LearnerDetails.class);
        in.putExtra("learner", learner);
        startActivity(in);

//        }

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

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;


    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        int count=0;
try {
    learner = db.getLearner(query);
    count = db.getLearnerCounts();
}catch(Exception ex)
{
    Toast.makeText(ShowLearners.this, "Error!! \n"+ex.getMessage(), Toast.LENGTH_LONG).show();
}

        if (count<1) {
            Toast.makeText(ShowLearners.this, query + " Sorry was not found or does not exist!!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Intent in = new Intent(ShowLearners.this, LearnerDetails.class);
            in.putExtra("learner", learner);
            startActivity(in);
            finish();
            return true;

        }
    }
}
