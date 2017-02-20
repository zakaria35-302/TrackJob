package bd.httpdaffodilvarsity.JobTracking.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import bd.httpdaffodilvarsity.JobTracking.R;

public class CreateEmployeeTask extends AppCompatActivity {

    EditText editTextTaskDeadCreate;
    //Button btn;
    int year_x,month_x,day_x;

    Spinner spinnerEmployeeTaskAccessibility, spinnerEmployeeTaskProgress, spinnerEmployeeTaskPriority,
            spinnerEmployeeTaskStatus, spinnerEmployeeTaskDepartment, getSpinnerEmployeeTaskRole,
            spinnerEmployeeJobSelect;
    ArrayAdapter<CharSequence> empTaskAccessibilityAdapter, empTaskProgressAdapter, empTaskPriorityAdapter,
            empTaskStatusAdapter, empTaskDepartmentAdapter, empTaskRoleAdapter, empJobSelectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee_task);

        editTextTaskDeadCreate = (EditText) findViewById(R.id.edit_text_task_dead_create);
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        /* calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);*/
        showDate(year_x, month_x+1, day_x);

        spinnerEmployeeJobSelect = (Spinner) findViewById(R.id.spinner_job_select_task_create);
        empJobSelectAdapter = ArrayAdapter.createFromResource(this,
                R.array.employee_job_title, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        empJobSelectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerEmployeeJobSelect.setAdapter(empJobSelectAdapter);

        spinnerEmployeeTaskAccessibility = (Spinner) findViewById(R.id.spinner_task_Accesibility_create);
        empTaskAccessibilityAdapter = ArrayAdapter.createFromResource(this,
                R.array.employee_task_accessability, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        empTaskAccessibilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerEmployeeTaskAccessibility.setAdapter(empTaskAccessibilityAdapter);

        spinnerEmployeeTaskProgress = (Spinner) findViewById(R.id.spinner_task_progress_create);
        empTaskProgressAdapter = ArrayAdapter.createFromResource(this, R.array.employee_task_progress,
                android.R.layout.simple_spinner_item);
        empTaskProgressAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeeTaskProgress.setAdapter(empTaskProgressAdapter);

        spinnerEmployeeTaskPriority = (Spinner) findViewById(R.id.spinner_task_priority_create);
        empTaskPriorityAdapter = ArrayAdapter.createFromResource(this, R.array.employee_task_priority,
                android.R.layout.simple_spinner_item);
        empTaskPriorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeeTaskPriority.setAdapter(empTaskPriorityAdapter);

        spinnerEmployeeTaskStatus = (Spinner) findViewById(R.id.spinner_task_status_create);
        empTaskStatusAdapter = ArrayAdapter.createFromResource(this, R.array.employee_task_status,
                android.R.layout.simple_spinner_item);
        empTaskStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeeTaskStatus.setAdapter(empTaskStatusAdapter);

        spinnerEmployeeTaskDepartment = (Spinner) findViewById(R.id.spinner_task_department_create);
        empTaskDepartmentAdapter = ArrayAdapter.createFromResource(this, R.array.employee_department,
                android.R.layout.simple_spinner_item);
        empTaskDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEmployeeTaskDepartment.setAdapter(empTaskDepartmentAdapter);

        getSpinnerEmployeeTaskRole = (Spinner) findViewById(R.id.spinner_task_role_create);
        empTaskRoleAdapter = ArrayAdapter.createFromResource(this, R.array.employee_task_role,
                android.R.layout.simple_spinner_item);
        empTaskRoleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSpinnerEmployeeTaskRole.setAdapter(empTaskRoleAdapter);

    }

    private void showDate(int year, int month, int day) {
        editTextTaskDeadCreate.setText(new StringBuilder().append(day).append("/")
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
            return new DatePickerDialog(this, myDateListener, year_x,month_x,day_x);
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
            showDate(arg1, arg2+1, arg3);
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

  /*  public void taskBack(View view){
        Intent taskBack = new Intent(CreateEmployeeTask.this, TaskManagement.class);
        startActivity(taskBack);
    }*/

}