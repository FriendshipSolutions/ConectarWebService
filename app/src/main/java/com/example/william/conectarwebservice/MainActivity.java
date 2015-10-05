package com.example.william.conectarwebservice;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;



import com.example.william.conectarwebservice.soapWebServices.CallSoap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConsumir;
    String strMsg = "Não";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Carregar a variavel do botão e associar ao evento click.
        btnConsumir = (Button)findViewById(R.id.btnConsumir);
        btnConsumir.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        CallSoap callSoap = new CallSoap(MainActivity.this);
        try
        {
            callSoap.execute();
        }
        catch (Exception ex)
        {
            msgBox(this, "Erro", "Erro ao carregar dados");
        }
    }

    private void msgBox(Context context, String titulo, String msg)
    {
        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setTitle(titulo);
        dlg.setMessage(msg);
        dlg.setNeutralButton("OK", null);
        dlg.show();
    }

}
