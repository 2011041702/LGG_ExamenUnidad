package com.example.lgg_examenunidad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Se declara objetos los cuales trabajaremos

    private EditText nombre;
    private CheckBox electro;
    private CheckBox compu;
    private TextView total, mayor, ticket;
    private TextView ganador, estado;
    private Button comprar, sorteo;

    // Indicamos variables a usar
    int producto1 = 3000;
    int producto2 = 1700;
    int resultado = 0;
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.edtNombre);
        electro = (CheckBox)findViewById(R.id.checkElectro);
        compu = (CheckBox)findViewById(R.id.checkComputadora);
        total = (TextView)findViewById(R.id.txtTotal);
        mayor = (TextView)findViewById(R.id.txtMayor);
        ticket = (TextView)findViewById(R.id.txtTicket);
        comprar = (Button)findViewById(R.id.btnComprar);
        sorteo = (Button)findViewById(R.id.btnSortear);

        ganador = (TextView)findViewById(R.id.txtGanador);
        estado = (TextView)findViewById(R.id.txtEstado);

        // Indicamos que el boton sorteo este deshabilitado apenas se inicie la activity
        sorteo.setEnabled(false);

        // usaremos el metodo clickListener en los botones.
        comprar.setOnClickListener(this);
        sorteo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view == comprar){

            if (!electro.isChecked() && !compu.isChecked()){
                Toast.makeText(MainActivity.this, "No se ha elegido ningun producto", Toast.LENGTH_SHORT).show();
            }
            else if (electro.isChecked() && compu.isChecked()){
                // Indicamos el resultado de los productos si los 2 fuesen elegidos
                resultado = producto1 + producto2;

                // Mostramos los resultados en los objetos señalados más arriba
                total.setText(String.valueOf(resultado));
                mayor.setText(String.valueOf(producto1));

                // Este metodo Math.random nos permitira tener un numero random del 1 al 10
                num = (int) (Math.random()*10+1);

                // Al generar el numero random, lo visualizamos en el objeto TextView llamado ticket
                ticket.setText(String.valueOf(num));

                // y por ultimo habilitamos el boton sorteo

                // El resto de else if son los mismos pasos
                sorteo.setEnabled(true);
            }
            else if (compu.isChecked()){
                total.setText(String.valueOf(producto2));
                mayor.setText(String.valueOf(producto2));
                num = (int) (Math.random()*10+1);

                ticket.setText(String.valueOf(num));
                sorteo.setEnabled(true);
            }
            else if (electro.isChecked()){
                total.setText(String.valueOf(producto1));
                mayor.setText(String.valueOf(producto1));
                num = (int) (Math.random()*10+1);

                ticket.setText(String.valueOf(num));
                sorteo.setEnabled(true);

            }


        }
        else if (view == sorteo){

            // Usaremos nuevamente el metodo random, pero en este caso lo usaremos para generar el numero ganador
            int win = (int) (Math.random()*10+1);

            // Al tener ya el numero ganador lo visualizamos
            ganador.setText(String.valueOf(win));

            // Aqui solo comparamos el numero ganador con el numero anterior a este, en el cual fue generado a base de las compras
            // Mostraremos un mensaje y deshabilitaremos los botones comprar y sortear
            
            if (ganador.getText().equals(ticket.getText())){
                estado.setText("El ticket a sido el ganador");
                sorteo.setEnabled(false);
                comprar.setEnabled(false);
            }else{
                estado.setText("Vuelva a Intentarlo");
                sorteo.setEnabled(false);
                comprar.setEnabled(false);
            }

        }

    }
}

