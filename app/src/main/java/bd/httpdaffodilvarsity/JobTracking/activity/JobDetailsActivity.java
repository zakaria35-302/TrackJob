package bd.httpdaffodilvarsity.JobTracking.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.httpdaffodilvarsity.JobTracking.R;
import bd.httpdaffodilvarsity.JobTracking.fragment.TaskFragment;

import static bd.httpdaffodilvarsity.JobTracking.R.id.textviewforTasklist;

public class JobDetailsActivity extends AppCompatActivity {

    TextView text_view_job_title,text_view_job_owner,text_view_job_department,text_job_given_date,text_job_est_date,text_view_job_status,text_job_progress,text_job_priority;
    private TextView txtTaskHeader;
    private ListView lsvTasks;
    private String jobId;
    private Boolean lsvTasksFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);

        text_view_job_title = (TextView) findViewById(R.id.text_view_job_title);
        text_view_job_owner = (TextView) findViewById(R.id.text_view_job_owner);
        text_view_job_department = (TextView) findViewById(R.id.text_view_job_department);
        text_job_given_date = (TextView) findViewById(R.id.text_job_given_date);
        text_job_est_date = (TextView) findViewById(R.id.text_job_est_date);
        text_view_job_status = (TextView) findViewById(R.id.text_view_job_status);
        text_job_progress = (TextView) findViewById(R.id.text_job_progress);
        text_job_priority = (TextView) findViewById(R.id.text_job_priority);
        txtTaskHeader = (TextView) findViewById(R.id.txtTaskHeader);
        lsvTasks = (ListView) findViewById(R.id.lsvTask);

         jobId = getIntent().getStringExtra("JobId");
        String job_title = getIntent().getStringExtra("JobTitle");
        String job_owner = getIntent().getStringExtra("JobOwner");
        String department_id = getIntent().getStringExtra("DepartmentId");
        String job_enddate = getIntent().getStringExtra("JobEndDate");
        String job_startdate = getIntent().getStringExtra("JobStartDate");
        String job_status = getIntent().getStringExtra("JobStatus");
        String job_progress = getIntent().getStringExtra("JobProgress");
        String job_priority = getIntent().getStringExtra("JobPriority");


        text_view_job_title.setText(job_title);
        text_view_job_owner.setText(job_owner);
        text_view_job_department.setText(department_id);
        text_job_given_date.setText(job_enddate);
        text_job_est_date.setText(job_startdate);
        text_view_job_status.setText(job_status);
        text_job_progress.setText(job_progress);
        text_job_priority.setText(job_priority);

        fetchingData();

        Toast.makeText(getApplicationContext(), jobId, Toast.LENGTH_SHORT).show();
    }


    void fetchingData(){

        String myURL =  "http://jts.diu.edu.bd/task";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final String[] task_title = new String[response.length()];
                final String[] task_description = new String[response.length()];
                final String[] startdate = new String[response.length()];
                final String[] estimated_date = new String[response.length()];
                final String[] actual_enddate = new String[response.length()];
                final String[] status = new String[response.length()];
                final String[] percent_done = new String[response.length()];
                final String[] priority = new String[response.length()];
                final String[] owner_comments = new String[response.length()];
                final String[] incharge_comments = new String[response.length()];
                final String[] task_owner_id = new String[response.length()];
                final String[] job_id = new String[response.length()];
                final String[] accesibility = new String[response.length()];
                final String[] prev_owner_id = new String[response.length()];
                final String[] created_by = new String[response.length()];
                final String[] created_time = new String[response.length()];
                final String[] update_by = new String[response.length()];
                final String[] updated_time = new String[response.length()];

                for (int i =0; i < response.length(); i++){

                    try {

                        JSONObject jsonObject = (JSONObject) response.get(i);

                        if (jobId.equals(jsonObject.getString("job_id"))){
                            task_title[i] = jsonObject.getString("task_title");
                            task_description[i] = jsonObject.getString("task_description");
                            startdate[i] = jsonObject.getString("startdate");
                            estimated_date[i] = jsonObject.getString("estimated_date");
                            actual_enddate[i] = jsonObject.getString("actual_enddate");
                            status[i] = jsonObject.getString("status");
                            percent_done[i] = jsonObject.getString("percent_done");
                            priority[i] = jsonObject.getString("priority");
                            owner_comments[i] = jsonObject.getString("owner_comments");
                            incharge_comments[i] = jsonObject.getString("incharge_comments");
                            task_owner_id[i] = jsonObject.getString("task_owner_id");
                            job_id[i] = jsonObject.getString("job_id");
                            accesibility[i] = jsonObject.getString("accesibility");
                            prev_owner_id[i] = jsonObject.getString("prev_owner_id");
                            created_by[i] = jsonObject.getString("created_by");
                            created_time[i] = jsonObject.getString("created_time");
                            update_by[i] = jsonObject.getString("update_by");
                            updated_time[i] = jsonObject.getString("updated_time");

                            lsvTasksFlag = true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (lsvTasksFlag){
                    txtTaskHeader.setVisibility(View.VISIBLE);
                    lsvTasks.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.task_list,textviewforTasklist,task_title));
                }

                lsvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(JobDetailsActivity.this, TaskListDetails.class);
                        intent.putExtra("TaskTitle", task_title[position]);
                        intent.putExtra("TaskDescription", task_description[position]);
                        intent.putExtra("TaskStartDate", startdate[position]);
                        intent.putExtra("TaskEstDate", estimated_date[position]);
                        intent.putExtra("TaskEndDate", actual_enddate[position]);
                        intent.putExtra("TaskStatus", status[position]);
                        intent.putExtra("PercentDone", percent_done[position]);
                        intent.putExtra("TaskPriority", priority[position]);
                        intent.putExtra("TaskOwnerComment", owner_comments[position]);
                        intent.putExtra("TaskInchargeComment", incharge_comments[position]);
                        intent.putExtra("TaskOwnerId", task_owner_id[position]);
                        intent.putExtra("JobId", job_id[position]);
                        intent.putExtra("TaskAccessibility", accesibility[position]);
                        intent.putExtra("PreOwnerId", prev_owner_id[position]);
                        intent.putExtra("TaskCreatedBy", created_by[position]);
                        intent.putExtra("TaskCreatedTime", created_time[position]);
                        intent.putExtra("TaskUpdatedBy", update_by[position]);
                        intent.putExtra("TaskUpdatedTime", updated_time[position]);
                        startActivity(intent);

                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log", error);
            }
        });


        bd.httpdaffodilvarsity.JobTracking.service.AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }
}