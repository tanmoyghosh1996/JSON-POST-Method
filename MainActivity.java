package com.example.root.jsonproject2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2, et3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText)findViewById(R.id.et1);
        et2= (EditText)findViewById(R.id.et2);
        et3= (EditText)findViewById(R.id.et3);
        b1= (Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaddatafromurl();
                //Toast.makeText(MainActivity.this, "server Message", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void loaddatafromurl()
    {
        final String url_logininsert="http://103.230.103.142/employee/Sample.asmx/InsertRecord";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_logininsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("resp",response);

                try {

                    Log.i("url_logininsert",url_logininsert);
                    JSONObject rootobj=new JSONObject(response);
                    JSONArray jsonArray=rootobj.getJSONArray("Response");
                    JSONObject inerobj=jsonArray.getJSONObject(0);
                    String Messagetext=inerobj.getString("Messagetext");
                    //if (Messagetext.equalsIgnoreCase("Success")){
                        Toast.makeText(MainActivity.this, "server Message : "+Messagetext, Toast.LENGTH_SHORT).show();
                   // }
                } 
				catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> obj=new HashMap<>();
                obj.put("EmpCode",et1.getText().toString());
                Log.i("codeet",et1.getText().toString());
                obj.put("EmpName",et2.getText().toString());
                obj.put("EmpAddress",et3.getText().toString());
                return obj;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }
}
