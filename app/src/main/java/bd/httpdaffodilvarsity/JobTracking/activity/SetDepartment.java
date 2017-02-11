package bd.httpdaffodilvarsity.JobTracking.activity;

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

import static bd.httpdaffodilvarsity.JobTracking.R.id.textviewforDeptName;

public class SetDepartment extends AppCompatActivity {
    ListView listEmployeeDepartment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_department);
        listEmployeeDepartment=(ListView)findViewById(R.id.listEmployeeDepartment);
        fetchingData();

        FloatingActionButton departmentFloatingButton = (FloatingActionButton) findViewById(R.id.fabSetDepartment);
        departmentFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SetDepartment.this, DeptSetActivity.class);
             //   startActivity(intent);
            }
        });

    }
    void fetchingData(){

        String myURL =  "http://192.168.10.223:8080/departmentinfo";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                final String[] dept_name = new String[response.length()];


                for (int i =0; i < response.length(); i++){

                    try {

                        JSONObject jsonObject = (JSONObject) response.get(i);

                        dept_name[i] = jsonObject.getString("dept_name");



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                listEmployeeDepartment.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.row_department,textviewforDeptName, dept_name));

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