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
import jaque.itics.tesoem.edu.mx.sqlite_tes.database.DatosOrigen;

public class Update extends AppCompatActivity implements View.OnClickListener {

    public static final String user="data";
    private String dataUser[];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        dataUser = getIntent().getStringArrayExtra("data");
        Toast.makeText(getApplicationContext(), "Editar registro ID: "+dataUser[0], Toast.LENGTH_LONG).show();
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

        reg  =  (Button) findViewById(R.id.btn_upd);
        cancel  =  (Button) findViewById(R.id.btn_cancel);
        reg.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String id_user[] = new String[1];

        boolean dataRestuls;
        switch (v.getId()){
            case R.id.btn_upd:
                Intent vistaAgregar  =new Intent(Update.this,MainActivity.class);
                DatosOrigen obj_db = new DatosOrigen(this);
                obj_db.open();
                ContentValues values = new ContentValues();
                values.put("id",id.getText().toString());
                values.put("nombre",name.getText().toString());
                values.put("edad",age.getText().toString());
                values.put("correo",email.getText().toString());
                id_user[0] = dataUser[0].toString();

                dataRestuls = obj_db.actualiza(values, id_user);
                obj_db.close();
                if  (dataRestuls)
                    Toast.makeText(getApplicationContext(), "Operación realizada", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Fallo en operación", Toast.LENGTH_LONG).show();

                startActivity(vistaAgregar);
                finish();
                break;
            case R.id.btn_cancel:
                Intent update = new Intent (Update.this, MainActivity.class);
                startActivity(update);
                finish();
                Toast.makeText(getApplicationContext(), "Operación cancelada", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
