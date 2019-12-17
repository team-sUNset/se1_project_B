package co.edu.unal.se1_app.presentation.ui.user_list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.StudentController;
import co.edu.unal.se1_app.dataAccess.callback.StudentListCallback;
import co.edu.unal.se1_app.dataAccess.model.Student;
import co.edu.unal.se1_app.presentation.ui.history.Category;

public class userListFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_user_list, container, false);
        Drawable dr= getResources().getDrawable(R.drawable.user);

        StudentController studentController = new StudentController();
        studentController.getStudents(new StudentListCallback() {
            @Override
            public void onSuccess(@NonNull List<Student> students) {
                ArrayList<Category> category = new ArrayList<Category>();
                for( Student st : students ){
                    category.add( new Category( st.getId().toString() ,
                            st.getFirstName() + " " + st.getLastName() ,
                            "ID: " + st.getId().toString() , dr) );
                }
                ListView lv = (ListView) root.findViewById(R.id.listView);
                AdapterItemUserList adapter = new AdapterItemUserList(getActivity(), category);
                lv.setAdapter(adapter);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("Message: " + throwable);
            }
        });

        return root;
    }
}