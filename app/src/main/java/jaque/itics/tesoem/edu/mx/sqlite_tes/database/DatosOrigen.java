package jaque.itics.tesoem.edu.mx.sqlite_tes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import jaque.itics.tesoem.edu.mx.sqlite_tes.database.DatosHelper.tabladatos;

public class DatosOrigen {
    private SQLiteDatabase basedatos;
    private DatosHelper datosHelper;

    public DatosOrigen(Context context){
        datosHelper = DatosHelper.getInstance(context);
    }

    public void open(){
        basedatos = datosHelper.getWritableDatabase();
    }

    public void close(){
        basedatos.close();
    }

    public String[] llenagridview(){
        String[] datos;
        int fila=0;
        Cursor cursor = basedatos.rawQuery("select * from " + DatosHelper.tabladatos.TABLA, null);
        if(cursor.getCount() <= 0 ){
            datos = new String[4];
            datos[0] = tabladatos.COLUMNA_ID;
            datos[1] = tabladatos.COLUMNA_NOMBRE;
            datos[2] = tabladatos.COLUMNA_CORREO;
            datos[3] = tabladatos.COLUMNA_EDAD;
            Log.e("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "Es menor que 0 el cursor");

        } else {
            Log.e("Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "Es mayor que 0 el cursor");

            datos = new String[(cursor.getCount())];
            //datos[0] = tabladatos.COLUMNA_ID;
            //datos[1] = tabladatos.COLUMNA_NOMBRE;
            //datos[2] = tabladatos.COLUMNA_CORREO;
            //datos[3] = tabladatos.COLUMNA_EDAD;
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                datos[fila] = "ID: "+String.valueOf(cursor.getInt(0))+" \nNombre: "+cursor.getString(1)+" \nEdad:"+cursor.getString(2)+" \nEmail:"+cursor.getString(3);
                //datos[fila+1] = cursor.getString(1);
                //datos[fila+2] = cursor.getString(2);
                //datos[fila+3] = cursor.getString(3);
                fila+=1;
                cursor.moveToNext();
            }
        }
        return datos;
    }

    public boolean insertar(ContentValues parametros){
        boolean estado = true;
        basedatos.isOpen();
        Long resulta = basedatos.insert(tabladatos.TABLA,null,parametros);
        if (resulta <0 ) estado = false;
        return estado;
    }

    public boolean actualiza(ContentValues parametros,String[] Condicion){
        boolean estado = true;
        basedatos.isOpen();
        long resulta = basedatos.update(tabladatos.TABLA,parametros,tabladatos.COLUMNA_ID + "=?", Condicion);
        if (resulta<0) estado = false;
        return estado;
    }

    public boolean eliminar(String[] Condicion){
        boolean estado = true;
        basedatos.isOpen();
        long resulta = basedatos.delete(tabladatos.TABLA, tabladatos.COLUMNA_ID + "=?",Condicion);
        if (resulta<0) estado = false;
        return estado;
    }

    public String[] buscar(String[] Condicion){
        String[] datos = null;

        String[] campos = new String[] {
                tabladatos.COLUMNA_ID,
                tabladatos.COLUMNA_NOMBRE,
                tabladatos.COLUMNA_EDAD,
                tabladatos.COLUMNA_CORREO
        };

        Cursor c = basedatos.query(tabladatos.TABLA, campos, tabladatos.COLUMNA_ID+"=?", Condicion, null, null, null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                datos = new String[4];
                datos[0] = String.valueOf(c.getInt(0));
                datos[1] = c.getString(1);
                datos[2] = c.getString(2);
                datos[3] = c.getString(3);
            } while(c.moveToNext());
        }

        return datos;
    }
}
