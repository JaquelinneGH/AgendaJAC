package jaque.itics.tesoem.edu.mx.sqlite_tes.sqlite7t11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import jaque.itics.tesoem.edu.mx.sqlite_tes.R;
import jaque.itics.tesoem.edu.mx.sqlite_tes.database.DatosOrigen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    GridView grid = null;
    String dataRestuls[] = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elements();
        showRegisters();
    }

    public void showRegisters(){
        grid = (GridView) findViewById(R.id.gridView);
        DatosOrigen obj_db = new DatosOrigen(this);

        obj_db.open();
        dataRestuls = obj_db.llenagridview();
        if (dataRestuls.length != 0){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataRestuls);
            grid.setAdapter(adapter);
            obj_db.close();
        } else {
            Toast.makeText(getApplicationContext(), "No hay registros", Toast.LENGTH_LONG).show();
        }

    }

    Button agregar, update, delete, search, out;
    EditText id;

    public void elements(){
        id = (EditText) findViewById(R.id.txt_id);
        agregar  =  (Button) findViewById(R.id.btn_registrar);
        update  =  (Button) findViewById(R.id.btn_actualizar);
        delete  =  (Button) findViewById(R.id.btn_eliminar);
        search  =  (Button) findViewById(R.id.btn_consultar);
        out  =  (Button) findViewById(R.id.btn_out);
        agregar.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        search.setOnClickListener(this);
        out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id_user[] = new String[1];

        switch (v.getId()){
            case R.id.btn_registrar:
                Intent vistaAgregar  =new Intent(MainActivity.this,Register.class);
                startActivity(vistaAgregar);
                finish();
                break;
            case R.id.btn_actualizar:
                Intent update = new Intent (MainActivity.this, Update.class);
                if (id.getText().toString() != "") {
                    id_user[0] = id.getText().toString();
                    DatosOrigen obj_db = new DatosOrigen(this);
                    obj_db.open();
                    String dataR[] = obj_db.buscar(id_user);
                    obj_db.close();

                    if (dataR == null) {
                        Toast.makeText(getApplicationContext(), "No hay registros con ese ID", Toast.LENGTH_LONG).show();
                    } else {
                        update.putExtra(Update.user, dataR);
                        startActivity(update);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese un ID", Toast.LENGTH_LONG).show();
                }
            break;
            case R.id.btn_eliminar:
                Intent delete = new Intent (MainActivity.this, Delete.class);
                if (id.getText().toString() != "") {
                    id_user[0] = id.getText().toString();
                    DatosOrigen obj_db = new DatosOrigen(this);
                    obj_db.open();
                    String dataR[] = obj_db.buscar(id_user);
                    obj_db.close();

                    if (dataR == null) {
                        Toast.makeText(getApplicationContext(), "No hay registros con ese ID", Toast.LENGTH_LONG).show();
                    } else {
                        delete.putExtra(Delete.user, dataR);
                        startActivity(delete);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese un ID", Toast.LENGTH_LONG).show();
                }
            break;
            case R.id.btn_consultar:
                Intent search = new Intent (MainActivity.this, Search.class);
                if (id.getText().toString() != "") {
                    id_user[0] = id.getText().toString();
                    DatosOrigen obj_db = new DatosOrigen(this);
                    obj_db.open();
                    String dataR[] = obj_db.buscar(id_user);
                    obj_db.close();

                    if (dataR == null) {
                        Toast.makeText(getApplicationContext(), "No hay registros con ese ID", Toast.LENGTH_LONG).show();
                    } else {
                        search.putExtra(Search.user, dataR);
                        startActivity(search);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese un ID", Toast.LENGTH_LONG).show();
                }
            break;
            case R.id.btn_out:
                Toast.makeText(getApplicationContext(), "Saliendo", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            break;
        }
    }
}
