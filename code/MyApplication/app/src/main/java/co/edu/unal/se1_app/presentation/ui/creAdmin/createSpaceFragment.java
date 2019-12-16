package co.edu.unal.se1_app.presentation.ui.creAdmin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;
import co.edu.unal.se1_app.businessLogic.controller.SpaceController;
import co.edu.unal.se1_app.dataAccess.callback.AdminCallback;
import co.edu.unal.se1_app.dataAccess.callback.SpaceCallback;
import co.edu.unal.se1_app.dataAccess.model.Admin;
import co.edu.unal.se1_app.dataAccess.model.Space;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class createSpaceFragment extends Fragment {


    ImageButton imagen;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_space, container, false);
        imagen = (ImageButton) root.findViewById(R.id.imageButton);
        Button boton= (Button) root.findViewById(R.id.crearSpace);
        final EditText nameSpace= (EditText) root.findViewById(R.id.createSpace1);

        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String name= nameSpace.getText().toString();


                        Space space= new Space(name,true);
                        SpaceController spaceController= new SpaceController();
                        spaceController.createSpace(space,new SpaceCallback() {
                            @Override
                            public void onSuccess(@NonNull Space space) {
                                //System.out.println( "ID " + student.getId() );
                                Toast toast1 =
                                        Toast.makeText(getActivity().getApplicationContext(),
                                                "Espacio Creado " , Toast.LENGTH_SHORT);
                                toast1.show();
                            }

                            @Override
                            public void onError(@NonNull Throwable throwable) {
                                Toast toast1 =
                                        Toast.makeText(getActivity().getApplicationContext(),
                                                "Error" , Toast.LENGTH_SHORT);
                                toast1.show();
                            }
                        });

                        System.out.println(name);

                    }
                });

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(v);
            }
        });

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load(v);
            }
        });


        return root;
    }


    public void load(View view) {
        cargarImagen();

    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), 10);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imagen.setImageURI(path);
            Context context = (Context) this.getContext();

            try {
                InputStream inputStream = context.getContentResolver().openInputStream(path);
                Drawable yourDrawable = Drawable.createFromStream(inputStream, path.toString());
                imagen.setImageDrawable(yourDrawable);
            } catch (FileNotFoundException e) {


            }
        }


    }

}