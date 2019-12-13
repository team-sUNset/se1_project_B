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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import co.edu.unal.se1_app.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class createOfficeFragment extends Fragment {


    ImageButton imagen;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_office, container, false);
        imagen = (ImageButton) root.findViewById(R.id.imageButton2);
        Button boton= (Button) root.findViewById(R.id.crearOffice);
        final EditText nameOffice= (EditText) root.findViewById(R.id.createOffice1);
        final EditText idOffice=  (EditText) root.findViewById(R.id.createOffice2);
        boton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        String name= nameOffice.getText().toString();
                        String id= idOffice.getText().toString();
                        System.out.println(name);

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