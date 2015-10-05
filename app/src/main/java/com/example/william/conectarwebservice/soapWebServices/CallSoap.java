package com.example.william.conectarwebservice.soapWebServices;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.ProgressDialog;

import com.example.william.conectarwebservice.MainActivity;

/**
 * Created by william on 20/08/2015.
 */
public class CallSoap extends AsyncTask<String, String, Boolean> {
    public final String SOAP_ACTION = "http://www.friendship.com.br/HelloWorld";
    public  final String OPERATION_NAME = "HelloWorld";
    public  final String WSDL_TARGET_NAMESPACE = "http://www.friendship.com.br/";
    public  final String SOAP_ADDRESS = "http://diprospero.friendship.com.br/ListaAudiencias.asmx";

    String strResult;
    private ProgressDialog dialog;
    private MainActivity activitySource;

    public CallSoap(MainActivity activity)
    {
        activitySource = activity;
    }

    public String getResult()
    {
        return  strResult;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();
            strResult = response.toString();
            for (int i=0;i<5000;i++) {
                strResult = strResult.toString() + i;
            }
            return true;
        }
        catch (Exception exception)
        {
            response=exception.toString();
            return false;
        }
    }

    @Override
    protected void onPreExecute(){
        dialog = new ProgressDialog(activitySource);
        dialog.setMessage("Loading...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(Boolean result){
        if (dialog.isShowing())
            dialog.dismiss();
        msgBox(activitySource, "Funcionou", strResult);
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
