package com.ekasilab.reportscard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static String[] arrOptions = {"Register Learner", "Show All Learners"};
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Choose Task");
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.text, arrOptions);
        list = (ListView) findViewById(R.id.list);

        list.setAdapter(listAdapter);
        list.setOnItemClickListener(OptionsActivity.this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {
            startActivity(new Intent(OptionsActivity.this, RegisterStudentDetails.class));
        } else if (position == 1) {
            startActivity(new Intent(OptionsActivity.this, ShowLearners.class));
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


//    public View getView(int position, View convertView, ViewGroup parent) {
//        View returnedView = super.getView(position, convertView, parent);
//
//        if (position == specialPosition) {
//            TextView text = (TextView) returnedView.findViewById(R.id.text);
//            text.setTypeface(null, Typeface.BOLD);
//        }
//
//        return returnedView;
//    }

}
