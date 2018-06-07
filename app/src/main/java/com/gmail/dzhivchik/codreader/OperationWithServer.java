package com.gmail.dzhivchik.codreader;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class OperationWithServer extends Activity {
    private String code;
    private String response;

    public OperationWithServer(String code) {
        this.code = code;
        //new RequestTask().execute("http://dzstore.herokuapp.com/test");
        new RequestTask().execute("http://app.bareks.com.ua/bareks_copy/hs/info/getkode");
    }

    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
//                //создаем запрос на сервер
//                DefaultHttpClient hc = new DefaultHttpClient();
//                ResponseHandler<String> res = new BasicResponseHandler();
//                //он у нас будет посылать post запрос
//                HttpPost postMethod = new HttpPost(params[0]);
//                //будем передавать два параметра
//                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
//                //передаем параметры из наших текстбоксов
//                nameValuePairs.add(new BasicNameValuePair("code", code));
//                //собераем их вместе и посылаем на сервер
//                postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//                //получаем ответ от сервера
//                response = hc.execute(postMethod, res);
                DefaultHttpClient hc = new DefaultHttpClient();
                ResponseHandler response = new BasicResponseHandler();
                HttpGet getMethod = new HttpGet(params[0]);
                response = (ResponseHandler)hc.execute(getMethod, response);
//                //посылаем на вторую активность полученные параметры
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("info", "!!!" + response.toString());
//                //то что куда мы будем передавать и что, putExtra(куда, что);
                startActivity(intent);

            } catch (Exception e) {
                System.out.println("Exp=" + e);
            }
            return null;
        }
    }

    public String getResponse() {
        return response;
    }
}
