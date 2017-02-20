package bd.httpdaffodilvarsity.JobTracking.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bd.httpdaffodilvarsity.JobTracking.R;

public class RegistrationActivity extends AppCompatActivity {

    String server_url= "http://jts.diu.edu.bd/personinfo";
    private static String TAG = MainActivity.class.getSimpleName();
    AlertDialog.Builder builder;
    EditText editTextEmployeeId;
    EditText editTextEmployeePassword;
    EditText editTextEmployeenameCreate;
    EditText editTextEmailCreate;
    Spinner spinnerEmployeeDepartment;
    Button btnCreateEmployeeAccount;
    ArrayAdapter<CharSequence> empDepartmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        builder = new AlertDialog.Builder(this);
        editTextEmployeeId = (EditText) findViewById(R.id.edit_text_employee_id);
        editTextEmployeePassword=(EditText)findViewById(R.id.editTextEmployeePassword);
        editTextEmployeenameCreate = (EditText) findViewById(R.id.edit_text_employeename_create);
        editTextEmailCreate = (EditText) findViewById(R.id.edit_text_email_create);
        spinnerEmployeeDepartment = (Spinner) findViewById(R.id.spinner_employee_department) ;
        btnCreateEmployeeAccount = (Button) findViewById(R.id.btn_create_employee_account);

        empDepartmentAdapter = ArrayAdapter.createFromResource(this,
                R.array.employee_department, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        empDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerEmployeeDepartment.setAdapter(empDepartmentAdapter);
        spinnerEmployeeDepartment.setSelection(0);

        btnCreateEmployeeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject js = new JSONObject();
                try {

                    js.put("employee_id", editTextEmployeeId.getText().toString());
                    js.put("password", editTextEmployeePassword.getText().toString());
                    js.put("emp_name", editTextEmployeenameCreate.getText().toString());
                    js.put("emp_email", editTextEmailCreate.getText().toString());
                    js.put("current_dept_id", spinnerEmployeeDepartment.getSelectedItem().toString ());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                        Request.Method.POST, server_url, js,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Log.d(TAG, response.toString() + "i am queen");

                                builder.setTitle("Registration Successfull");
                                // builder.setMessage("Response:"+response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        editTextEmployeeId.setText("");
                                        editTextEmployeePassword.setText("");
                                        editTextEmployeenameCreate.setText("");
                                        editTextEmailCreate.setText("");

                                    }
                                });

                                AlertDialog alertDialog=builder.create();
                                alertDialog.show();

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }) {


                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };
                // Adding request to request queue
                Volley.newRequestQueue(RegistrationActivity.this).add(jsonObjReq);
            }
        });
    }

    public void GoToLoginActivity(View view) {
        Intent intent =new Intent(RegistrationActivity.this,LogInActivity.class);
        startActivity(intent);
    }

    public void onCreateAccountPressed(View view) {
        Intent intent =new Intent(RegistrationActivity.this,LogInActivity.class);
        startActivity(intent);
    }

}