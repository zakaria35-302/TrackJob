package bd.httpdaffodilvarsity.JobTracking.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bd.httpdaffodilvarsity.JobTracking.R;

public class JobDetailsActivity extends AppCompatActivity {

    TextView text_view_job_title,text_view_job_owner,text_view_job_department,text_job_given_date,text_job_est_date,text_view_job_status,text_job_progress,text_job_priority;

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
    }
}