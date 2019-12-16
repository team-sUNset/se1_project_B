package co.edu.unal.se1_app.presentation.ui.office;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.presentation.ui.history.Category;

import java.util.ArrayList;

public class officeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_office, container, false);
        Drawable dr= getResources().getDrawable(R.drawable.balon);
        ArrayList<Category> category = new ArrayList<Category>();

        Category cat1= new Category("id10","prestamo1","genial",dr);
        Category cat2= new Category("id20","prestamo2","genial",dr);
        ListView lv = (ListView) root.findViewById(R.id.listView);
        category.add(cat1);
        category.add(cat2);

        AdapterItemOffice adapter = new AdapterItemOffice(this.getActivity(), category);

        lv.setAdapter(adapter);

        return root;
    }
}