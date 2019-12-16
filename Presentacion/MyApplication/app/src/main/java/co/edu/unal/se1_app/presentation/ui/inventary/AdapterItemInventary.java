package co.edu.unal.se1_app.presentation.ui.inventary;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.presentation.ui.history.Category;


public class AdapterItemInventary extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Category> items;

    public AdapterItemInventary(Activity activity, ArrayList<Category> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Category> category) {
        for (int i =0 ; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.fragment_inventary, null);
        }

        Category dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(dir.getTitle());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(dir.getDescription());



        return v;
    }
}
