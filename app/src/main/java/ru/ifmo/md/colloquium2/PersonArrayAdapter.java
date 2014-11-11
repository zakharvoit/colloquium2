package ru.ifmo.md.colloquium2;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonArrayAdapter extends BaseAdapter {
    private final Person[] persons;
    private final Context context;

    public PersonArrayAdapter(Context context, Person[] persons) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public int getCount() {
        return persons.length;
    }

    @Override
    public Object getItem(int position) {
        return persons[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            TextView newView = new TextView(context);
            if (position == 0) {
                newView.setTextColor(Color.RED);
            }
            newView.setText(persons[position].getName() + " has " + persons[position].getVotesCount() + " votes.");
            convertView = newView;
        }

        return convertView;
    }
}
