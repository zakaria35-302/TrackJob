package bd.httpdaffodilvarsity.JobTracking.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bd.httpdaffodilvarsity.JobTracking.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
    public void onSignInPressed(View view) {
        Intent intent =new Intent(LogInActivity.this,MainActivity.class);
        //Intent intent =new Intent(LogInActivity.this,ProfileActivity.class);
        startActivity(intent);
    }
}