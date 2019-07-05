package jaque.itics.tesoem.edu.mx.sqlite_tes.sqlite7t11;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import jaque.itics.tesoem.edu.mx.sqlite_tes.R;

public class Search extends AppCompatActivity implements View.OnClickListener {
    public static final String user="data";
    private String dataUser[];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dataUser = getIntent().getStringArrayExtra("data");
        Toast.makeText(getApplicationContext(), "Eliminar registro ID: "+dataUser[0], Toast.LENGTH_LONG).show();
        elements();

        id.setText(dataUser[0]);
        name.setText(dataUser[1]);
        age.setText(dataUser[2]);
        email.setText(dataUser[3]);
    }

    Button reg, cancel;
    EditText id, name, age, email;

    public void elements(){
        id = (EditText) findViewById(R.id.id_user);
        name = (EditText) findViewById(R.id.name_user);
        age = (EditText) findViewById(R.id.age_user);
        email = (EditText) findViewById(R.id.email_user);

        cancel  =  (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_cancel:
                Intent update = new Intent (Search.this, MainActivity.class);
                startActivity(update);
                finish();
                Toast.makeText(getApplicationContext(), "Regresar", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
