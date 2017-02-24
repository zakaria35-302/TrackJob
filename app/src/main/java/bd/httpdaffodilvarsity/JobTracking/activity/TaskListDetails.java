package bd.httpdaffodilvarsity.JobTracking.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bd.httpdaffodilvarsity.JobTracking.R;
import bd.httpdaffodilvarsity.JobTracking.other.Task;

public class TaskListDetails extends AppCompatActivity {
    TextView text_view_job_title,text_view_task_title,text_view_task_owner,text_view_task_department, text_task_given_date,text_task_est_date,
            text_view_task_status,text_task_progress,text_task_priority;

    private Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list_details);



        text_view_job_title = (TextView) findViewById(R.id.text_view_job_title);
        text_view_task_title = (TextView) findViewById(R.id.text_view_task_title);
        text_view_task_owner = (TextView) findViewById(R.id.text_view_task_owner);
        // text_view_task_department = (TextView) findViewById(R.id.text_view_task_department);
        text_task_given_date = (TextView) findViewById(R.id.text_task_given_date);
        text_task_est_date = (TextView) findViewById(R.id.text_task_est_date);
        text_view_task_status = (TextView) findViewById(R.id.text_view_task_status);
        // text_task_progress = (TextView) findViewById(R.id.text_task_progress);
        text_task_priority = (TextView) findViewById(R.id.text_task_priority);

        task = (Task) getIntent().getSerializableExtra("TaskInstance");


//        String task_title = getIntent().getStringExtra("TaskTitle");
//        //String job_id = getIntent().getStringExtra("JobOwner");
//        String task_owner_id = getIntent().getStringExtra("TaskOwnerId");
//        //String job_enddate = getIntent().getStringExtra("JobEndDate");
//        String startdate = getIntent().getStringExtra("TaskStartDate");
//        String estimated_date = getIntent().getStringExtra("TaskEstDate");
//        String status = getIntent().getStringExtra("TaskStatus");
//        // String progress = getIntent().getStringExtra("JobProgress");
//        String priority = getIntent().getStringExtra("TaskPriority");


        text_view_job_title.setText(task.getTaskTitle());
        text_view_task_title.setText(task.getTaskTitle());
        text_view_task_owner.setText(task.getTaskOwnerId());
        // text_view_task_department.setText(job_enddate);
        text_task_given_date.setText(task.getTaskStartDate());
        text_task_est_date.setText(task.getTaskEstDate());
        text_view_task_status.setText(task.getTaskStatus());
        // text_task_progress.setText(job_priority);
        text_task_priority.setText(task.getTaskPriority());
    }
}
