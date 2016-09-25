package cl.japple.ejemplosqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarActivity extends AppCompatActivity {

    private TextView texto;

    public void sayToast(String str){
        Toast.makeText(MostrarActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        texto = (TextView)findViewById(R.id.textMostrar);
        DataBaseHelper dbh = new DataBaseHelper(this, "DBUsuarios", null ,1);
        final SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT codigo, nombre FROM Usuarios", null);
        texto.setText("");
        if (c.moveToFirst()){
            do{
                String cod = c.getString(0);
                String nom = c.getString(1);

                texto.append(" " + cod + " - " + nom + "\n");
            }while(c.moveToNext());
        }
    }
}
