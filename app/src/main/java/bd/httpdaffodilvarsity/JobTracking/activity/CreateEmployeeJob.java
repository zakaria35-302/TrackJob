package bd.httpdaffodilvarsity.JobTracking.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import bd.httpdaffodilvarsity.JobTracking.R;

public class CreateEmployeeJob extends AppCompatActivity {

    Button btnJobSave;
    EditText edittextCreateJobTittle, edittextCreateJobDescription, editTextJobDeadCreate;
    CheckBox checkboxCreateJobHadDeadline;
    // Spinner edittextCreateJobProgress,getEdittextCreateJobProgress,edittextCreateStatus,edittextCreateJobRole;


    String server_url = "http://192.168.10.223:8080/jobmanagement";
    private static String TAG = MainActivity.class.getSimpleName();
    AlertDialog.Builder builder;

    //Button btn;
    int year_x, month_x, day_x;
    Spinner spinnerEmployeeJobProgress, spinnerEmployeeJobPriority, spinnerEmployeeJobStatus, spinnerEmployeejobDepartment;

    ArrayAdapter<CharSequence> empJobProgressAdapter, empJobPriorityAdapter,
            empJobStatusAdapter, empJobDepartmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee_job);

        edittextCreateJobTittle = (EditText) findViewById(R.id.edit_text_job_title_create);
        edittextCreateJobDescription = (EditText) findViewById(R.id.edit_text_job_description_create);
        checkboxCreateJobHadDeadline = (CheckBox) findViewById(R.id.checkBox_job_had_deadline_create);
        editTextJobDeadCreate = (EditText) findViewById(R.id.edit_text_job_dead_create);


        // edittextCreateJobAssignto = (EditText) findViewById(R.id.edit_text_job_asign_to_create);

        btnJobSave = (Button) findViewById(R.id.btn_job_save);

        final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        /* calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);*/
        showDate(year_x, month_x + 1, day_x);

       /* spinnerEmployeeJobAccessibility = (Spinner) findViewById(R.id.spinner_job_Accesibility_create);
        empJobAccessibilityAdapter = ArrayAdapter.createFromResource(this,
                R.array.employee_task_accessability, android.R.layout.simple_spinner_item);*/
        // Specify the layout to use when the list of choices appears
        //  empJobAccessibilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
//        spinnerEmployeeJobAccessibility.setAdapter(empJobAccessibilityAdapter);

        spinnerEmployeeJobProgress = (Spinner) findViewById(R.id.spinner_job_progress_create);
        empJobProgressAdapter = ArrayAdapter.createFromResource(this, R.array.employee_task_progress,
                android.R.layout.simple_spinner_item);
        empJobProgressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeeJobProgress.setAdapter(empJobProgressAdapter);

        spinnerEmployeeJobPriority = (Spinner) findViewById(R.id.spinner_job_priority_create);
        empJobPriorityAdapter = ArrayAdapter.createFromResource(this, R.array.employee_task_priority,
                android.R.layout.simple_spinner_item);
        empJobPriorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeeJobPriority.setAdapter(empJobPriorityAdapter);

        spinnerEmployeeJobStatus = (Spinner) findViewById(R.id.spinner_job_status_create);
        empJobStatusAdapter = ArrayAdapter.createFromResource(this, R.array.employee_task_status,
                android.R.layout.simple_spinner_item);
        empJobStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeeJobStatus.setAdapter(empJobStatusAdapter);

        spinnerEmployeejobDepartment = (Spinner) findViewById(R.id.spinner_job_department_create);
        empJobDepartmentAdapter = ArrayAdapter.createFromResource(this, R.array.employee_department,
                android.R.layout.simple_spinner_item);
        empJobDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeejobDepartment.setAdapter(empJobDepartmentAdapter);

       /*  getSpinnerEmployeeJobRole = (Spinner) findViewById(R.id.spinner_job_role_create);
         empJobRoleAdapter = ArrayAdapter.createFromResource(this, R.array.employee_task_role,
               android.R.layout.simple_spinner_item);
         empJobRoleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         getSpinnerEmployeeJobRole.setAdapter(empJobRoleAdapter);*/

        addJobManagement();


    }

    private void showDate(int year, int month, int day) {
        editTextJobDeadCreate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @SuppressWarnings("deprecation")
    public void showDatePicDialog(View view) {
        showDialog(999);
        //Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year_x, month_x, day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            /* arg1 = year;
             arg2 = month;
             arg3 = day;*/
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }

    public void addJobManagement() {
        btnJobSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject js = new JSONObject();
                try {
                    js.put("job_title", edittextCreateJobTittle.getText().toString());
                    js.put("job_description", edittextCreateJobDescription.getText().toString());
                    js.put("job_has_deadline", checkboxCreateJobHadDeadline.getText().toString());
                    js.put("job_startdate", editTextJobDeadCreate.getText().toString());
                    js.put("job_progress", spinnerEmployeeJobProgress.getSelectedItem().toString());
                    js.put("job_priority", spinnerEmployeeJobPriority.getSelectedItem().toString());
                    js.put("job_status", spinnerEmployeeJobStatus.getSelectedItem().toString());
                    // js.put("department_id", spinnerEmployeejobDepartment.getSelectedItem().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                        Request.Method.POST, server_url, js,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                String jobTitle = edittextCreateJobTittle.getText().toString();
                                //Log.d(TAG, response.toString() + "i am queen");

                                builder.setTitle("Job" + " " + jobTitle + " " + "Successfully Created");
                                // builder.setMessage("Response:"+response);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        edittextCreateJobTittle.setText("");
                                        edittextCreateJobDescription.setText("");
                                        checkboxCreateJobHadDeadline.setText("");
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                    }
                }) {
                    /*
                      Passing some request headers
                     */
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }
                };
                // Adding request to request queue
                Volley.newRequestQueue(CreateEmployeeJob.this).add(jsonObjReq);
            }
        });
    }
}
