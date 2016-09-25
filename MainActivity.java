package cl.japple.ejemplosqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnAgregar;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnMostrar;
    private EditText inputNom;
    private EditText inputCod;


    public void sayToast(String str){
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputCod = (EditText)findViewById(R.id.inputCodigo);
        inputNom = (EditText)findViewById(R.id.inputNombre);
        btnAgregar = (Button)findViewById(R.id.buttonAgregar);
        btnActualizar = (Button)findViewById(R.id.buttonActualizar);
        btnEliminar = (Button)findViewById(R.id.buttonEliminar);
        btnMostrar = (Button)findViewById(R.id.buttonMostrar);

        DataBaseHelper dbh = new DataBaseHelper(this, "DBUsuarios", null, 1);

        final SQLiteDatabase db = dbh.getWritableDatabase();

        //Agregar
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cod = inputCod.getText().toString();
                String nom = inputNom.getText().toString();
                if(cod.equals(" ") || nom.equals("")){
                    sayToast("Ingrese los parametros señalados");
                }else {
                    String sql = "INSERT INTO Usuarios (codigo,nombre) VALUES('" + cod + "','" + nom + "') ";
                    db.execSQL(sql);
                    inputNom.setText("");
                    inputCod.setText("");
                    sayToast("Usuario Ingresado con Exito!");
                    /*
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("codigo", cod);
                    nuevoRegistro.put("nombre", nom);

                    db.insert("Usuarios", null, nuevoRegistro);
                    */
                }
            }
        });

        //Actualizar
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cod = inputCod.getText().toString();
                String nom = inputNom.getText().toString();
                if(cod.equals(" ") || nom.equals("")){
                    sayToast("Ingrese los parametros señalados");
                }else {
                    String sql = "UPDATE Usuarios SET Nombre ='" + nom + "' WHERE codigo=" + cod;
                    db.execSQL(sql);
                    inputNom.setText("");
                    inputCod.setText("");
                    sayToast("Usuario Actualizado con Exito!");
                }
            }
        });

        //Eliminar
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cod = inputCod.getText().toString();
                if(cod.equals(" ")){
                    sayToast("Ingrese los parametros señalados");
                }else {
                    String sql = "DELETE FROM Usuarios WHERE codigo=" + cod;
                    db.execSQL(sql);
                    inputNom.setText("");
                    inputCod.setText("");
                    sayToast("Usuario Eliminado con Exito!");
                }
            }
        });

        //Mostrar
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrar = new Intent(getApplication(), MostrarActivity.class);
                startActivity(mostrar);
            }
        });

    }
}
