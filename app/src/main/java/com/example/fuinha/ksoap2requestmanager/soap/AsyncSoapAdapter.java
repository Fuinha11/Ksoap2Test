package com.example.fuinha.ksoap2requestmanager.soap;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


/**
 * Created by Fuinha on 11/09/2017.
 */


public abstract class AsyncSoapAdapter<K,V> extends AsyncTask<K, Void, V> {
    protected K request;
    protected MartinsSoapManager soapInterface;
    protected Listener<V> listener;

    public AsyncSoapAdapter(Listener<V> listener) {
        this.listener = listener;
    }

    @Override
    protected V doInBackground(K... params) {
        this.request = params[0];
        this.soapInterface = MartinsSoapManager.getInterface();
        SoapObject soapObject = getSoapObject();
        setProperties(soapObject);
        SoapSerializationEnvelope envelope = getEnvelope();
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE httpTransportSE = getHttpTransportSE();
        try {
            httpTransportSE.call(getSoapAction(), envelope);
            Log.d("dump Request: " ,httpTransportSE.requestDump);
            Log.d("dump response: " ,httpTransportSE.responseDump);
            if (envelope.bodyIn != null) {
                SoapObject soapIn = (SoapObject) envelope.bodyIn;
                V response = handleResponse(soapIn);
                listener.notifySuccess(response);
            }
            else
                listener.notifyError((new ApiError().setMessage("Bad response from server...")));
        } catch (Exception e) {
            e.printStackTrace();
            listener.notifyError(new ApiError().setMessage("Unable to contact server..."));
        }

        return null;
    }

    /**
     * This method is used to create the desired Soap Object
     */
    protected abstract SoapObject getSoapObject();

    /**
     * This method is used to set the Soap Object
     * properties accordingly to request
     */
    protected abstract void setProperties(SoapObject soapObject);

    /**
     * This method is used to create the standard Soap Envelope
     */
    protected SoapSerializationEnvelope getEnvelope(){
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.dotNet = true;
        // TODO: 11/09/2017 define envelope proprieties
        return envelope;
    }

    /**
     * This method is used to create the standard HttpTransportSE
     */
    protected HttpTransportSE getHttpTransportSE(){
        HttpTransportSE ht = new HttpTransportSE(soapInterface.getURL());
        ht.debug = true; // FIXME: 15/09/2017 remove this tag for performance after finish using
        // TODO: 11/09/2017 define Http params
        return ht;
    }

    /**
     * This method is used to get the soap action accordingly with request
     */
    protected abstract String getSoapAction();

    /**
     * This method is used to handle a nonnull valid response
     */
    protected abstract V handleResponse(SoapObject soapResponse) throws Exception;
}
