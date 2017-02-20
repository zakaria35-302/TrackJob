package bd.httpdaffodilvarsity.JobTracking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bd.httpdaffodilvarsity.JobTracking.R;

/**
 * Created by Zakaria on 02-Feb-17.
 */

public class JobManagementAdapter extends ArrayAdapter {
    private List list = new ArrayList();
    public JobManagementAdapter(Context context, int resource) {
        super(context, resource);
    }

    private class ViewHolder{
        TextView employeeJobTittle;
        TextView employeeJobOwner;
        TextView employeeJobDepartment;
        TextView employeeJobGivenDate;
        TextView employeeJobEstDate;
        TextView employeeJobStatus;
        TextView employeeJobProgress;
        TextView employeeJobPriority;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_employee_job_list, parent, false);

            //convertView = inflater.inflate(R.layout.row_superior_setting_view,null);
            holder = new ViewHolder();
            holder.employeeJobTittle = (TextView) row.findViewById(R.id.text_view_job_title);
            holder.employeeJobOwner = (TextView) row.findViewById(R.id.text_view_job_owner);
            holder.employeeJobDepartment = (TextView) row.findViewById(R.id.text_view_job_department);
            holder.employeeJobGivenDate = (TextView) row.findViewById(R.id.text_job_given_date);
            holder.employeeJobEstDate = (TextView) row.findViewById(R.id.text_job_est_date);
            holder.employeeJobStatus = (TextView) row.findViewById(R.id.text_view_job_status);
            holder.employeeJobProgress = (TextView) row.findViewById(R.id.text_job_progress);
            holder.employeeJobPriority = (TextView) row.findViewById(R.id.text_job_priority);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
            /*SuperiorClass SC = (SuperiorClass) getItem(position);
            holder.imgSuperior.setImageResource(SC.getImage_resource());*/
        }

       /* //HashMap<String, String> map = list.get(position);
        JobManagementClass SC = (JobManagementClass) getItem(position);
        holder.employeeJobTittle.setText(SC.getEmployeeJobTittle());
        holder.employeeJobOwner.setText(SC.getEmployeeJobOwner());
        holder.employeeJobDepartment.setText(SC.getEmployeeJobDepartment());
        holder.employeeJobGivenDate.setText(SC.getEmployeeJobGivenDate());
        holder.employeeJobEstDate.setText(SC.getEmployeeJobEstDate());
        holder.employeeJobStatus.setText(SC.getEmployeeJobStatus());
        holder.employeeJobProgress.setText(SC.getEmployeeJobProgress());
        holder.employeeJobPriority.setText(SC.getEmployeeJobPriority());
        return row;*/
        return super.getView(position, convertView, parent);
    }}