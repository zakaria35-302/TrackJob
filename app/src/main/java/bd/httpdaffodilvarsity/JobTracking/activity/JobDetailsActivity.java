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

import java.util.ArrayList;

import bd.httpdaffodilvarsity.JobTracking.R;
import bd.httpdaffodilvarsity.JobTracking.fragment.TaskFragment;
import bd.httpdaffodilvarsity.JobTracking.other.Task;

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

                ArrayList<String> titleList = new ArrayList<>();
                final ArrayList<Task> taskArrayList = new ArrayList<>();

                for (int i =0; i < response.length(); i++){

                    try {

                        JSONObject jsonObject = (JSONObject) response.get(i);

                        if (jobId.equals(jsonObject.getString("job_id"))){

                            Task task = new Task();
                            titleList.add(jsonObject.getString("task_title"));
                            task.setTaskTitle(jsonObject.getString("task_title"));
                            task.setTaskDescription(jsonObject.getString("task_description"));
                            task.setTaskStartDate(jsonObject.getString("startdate"));
                            task.setTaskEstDate(jsonObject.getString("estimated_date"));
                            task.setTaskEndDate(jsonObject.getString("actual_enddate"));
                            task.setTaskStatus(jsonObject.getString("status"));
                            task.setPercentDone(jsonObject.getString("percent_done"));
                            task.setTaskPriority(jsonObject.getString("priority"));
                            task.setTaskOwnerComment(jsonObject.getString("owner_comments"));
                            task.setTaskInchargeComment(jsonObject.getString("incharge_comments"));
                            task.setTaskOwnerId(jsonObject.getString("task_owner_id"));
                            task.setJobId(jsonObject.getString("job_id"));
                            task.setTaskAccessibility(jsonObject.getString("accesibility"));
                            task.setPreOwnerId(jsonObject.getString("prev_owner_id"));
                            task.setTaskCreatedBy(jsonObject.getString("created_by"));
                            task.setTaskCreatedTime(jsonObject.getString("created_time"));
                            task.setTaskUpdatedBy(jsonObject.getString("update_by"));
                            task.setTaskUpdatedTime(jsonObject.getString("updated_time"));

                            taskArrayList.add(task);

                            lsvTasksFlag = true;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (lsvTasksFlag){
                    txtTaskHeader.setVisibility(View.VISIBLE);
                    lsvTasks.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.task_list,textviewforTasklist,titleList));
                }

                lsvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(JobDetailsActivity.this, TaskListDetails.class);
                        intent.putExtra("TaskInstance", taskArrayList.get(position));
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