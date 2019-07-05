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

public class Register extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        elements();
    }

    Button reg, cancel;
    EditText id, name, age, email;

    public void elements(){
        id = (EditText) findViewById(R.id.id_user);
        name = (EditText) findViewById(R.id.name_user);
        age = (EditText) findViewById(R.id.age_user);
        email = (EditText) findViewById(R.id.email_user);

        reg  =  (Button) findViewById(R.id.btn_reg);
        cancel  =  (Button) findViewById(R.id.btn_cancel);
        reg.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean dataRestuls;
        switch (v.getId()){
            case R.id.btn_reg:
                Intent vistaAgregar  =new Intent(Register.this,MainActivity.class);
                DatosOrigen obj_db = new DatosOrigen(this);
                obj_db.open();
                ContentValues values = new ContentValues();
                values.put("id",id.getText().toString());
                values.put("nombre",name.getText().toString());
                values.put("edad",age.getText().toString());
                values.put("correo",email.getText().toString());
                dataRestuls = obj_db.insertar(values);
                obj_db.close();
                if  (dataRestuls)
                    Toast.makeText(getApplicationContext(), "Operación realizada", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), "Fallo en operación", Toast.LENGTH_LONG).show();

                startActivity(vistaAgregar);
            break;
            case R.id.btn_cancel:
                Intent update = new Intent (Register.this, MainActivity.class);
                startActivity(update);
                Toast.makeText(getApplicationContext(), "Operación cancelada", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
