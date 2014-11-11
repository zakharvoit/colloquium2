package ru.ifmo.md.colloquium2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static final String VOTE_RESULTS = "ru.ifmo.md.colloquium2.VOTE_RESULTS";
    EditText candidateName;
    Button addCandidateButton;
    Button startButton;
    Button stopButton;
    ListView candidatesList;
    ArrayAdapter<String> candidatesListAdapter;

    List<Integer> votes;
    List<String> names;

    boolean votingState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidateName = (EditText)findViewById(R.id.new_candidate_name);
        addCandidateButton = (Button)findViewById(R.id.add_candidate);
        startButton = (Button)findViewById(R.id.start);
        stopButton = (Button)findViewById(R.id.stop);
        candidatesList = (ListView)findViewById(R.id.candidates_list);

        votes = new ArrayList<Integer>();
        names = new ArrayList<String>();

        candidatesListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        candidatesList.setAdapter(candidatesListAdapter);
        candidatesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                votes.set(position, votes.get(position) + 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onStartClick(View view) {
        candidateName.setVisibility(View.INVISIBLE);
        addCandidateButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        stopButton.setVisibility(View.VISIBLE);
        votingState = true;
    }

    public void onAddCandidateClick(View view) {
        names.add(candidateName.getText().toString());
        votes.add(0);
        candidatesListAdapter.notifyDataSetChanged();
    }

    public void onStopClick(View view) {
        int personCount = names.size();
        List<Person> candidates = new ArrayList<Person>();
        for (int i = 0; i < personCount; i++) {
            candidates.add(new Person(names.get(i), votes.get(i)));
        }

        Collections.sort(candidates);

        Intent intent = new Intent(this, ViewResultsActivity.class);
        intent.putExtra(VOTE_RESULTS, (Serializable) candidates);
        startActivity(intent);
    }

    public void onClearClick(View view) {
        votes.clear();
        names.clear();
        candidatesListAdapter.notifyDataSetChanged();
        candidateName.setVisibility(View.VISIBLE);
        addCandidateButton.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.INVISIBLE);
        votingState = false;
    }
}
