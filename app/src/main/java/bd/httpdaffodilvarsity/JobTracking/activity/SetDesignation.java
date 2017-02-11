package bd.httpdaffodilvarsity.JobTracking.activity;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.httpdaffodilvarsity.JobTracking.R;

import static bd.httpdaffodilvarsity.JobTracking.R.id.textViewRowDesignatioName;

public class SetDesignation extends AppCompatActivity {


    ListView designationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_designation);
        designationList=(ListView)findViewById(R.id.designationList);
        FloatingActionButton designationFloatingButton = (FloatingActionButton) findViewById(R.id.fabSetDesignation);

        fetchingData() ;

        FloatingActionButton buttonCreateJob = (FloatingActionButton) findViewById(R.id.fabSetDesignation);

        designationFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(SetDesignation.this);
                dialog.setTitle("Add New Designation");
                dialog.setContentView(R.layout.create_designation_dialog);
                dialog.show();
            }});
    }

        void fetchingData(){

        String myURL =  "http://192.168.10.223:8080/designationinfo";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                final String[] designation = new String[response.length()];


                for (int i =0; i < response.length(); i++){

                    try {

                        JSONObject jsonObject = (JSONObject) response.get(i);

                        designation[i] = jsonObject.getString("designation");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                designationList.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.row_designation,textViewRowDesignatioName,designation));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log", error);
            }
        });

        bd.httpdaffodilvarsity.JobTracking.service.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(getApplicationContext(), "Data Loaded Successfully!", Toast.LENGTH_SHORT).show();

    }
}