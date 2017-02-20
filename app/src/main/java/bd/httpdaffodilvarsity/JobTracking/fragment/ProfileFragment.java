package bd.httpdaffodilvarsity.JobTracking.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import bd.httpdaffodilvarsity.JobTracking.activity.CreateEmployeeJob;
import bd.httpdaffodilvarsity.JobTracking.activity.JobDetailsActivity;

import static bd.httpdaffodilvarsity.JobTracking.R.id.text_job_est_date;
import static bd.httpdaffodilvarsity.JobTracking.R.id.text_job_given_date;
import static bd.httpdaffodilvarsity.JobTracking.R.id.text_view_job_department;
import static bd.httpdaffodilvarsity.JobTracking.R.id.text_view_job_owner;
import static bd.httpdaffodilvarsity.JobTracking.R.id.textviewforjoblist;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class ProfileFragment extends Fragment {

    TextView employeeidTv,nameTv,designationTv,departmentTv,emailTv,reportsTv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getContext().setTheme(R.style.AppTheme_NoActionBar);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView= inflater.inflate(R.layout.fragment_profile,container,false);

        employeeidTv=(TextView) rootView.findViewById(R.id.employeeidTv);
        nameTv=(TextView)rootView.findViewById(R.id.nameTv);
        designationTv=(TextView)rootView.findViewById(R.id.designationTv);
        departmentTv=(TextView)rootView.findViewById(R.id.departmentTv);
        emailTv=(TextView)rootView.findViewById(R.id.emailTv);
        reportsTv=(TextView)rootView.findViewById(R.id.reportsTv);

        fetchingData() ;

        FloatingActionButton fab = (FloatingActionButton)rootView. findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileFragment.this.getActivity(), CreateEmployeeJob.class);
                ProfileFragment.this.startActivity(intent);
            }});

        return rootView;
    }


    void fetchingData(){

        String myURL =  "http://jts.diu.edu.bd/personinfo";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final String[] employee_id = new String[response.length()];
                final String[] emp_name = new String[response.length()];
                final String[]  emp_email = new String[response.length()];
                final String[] designation_id = new String[response.length()];
                final String[] current_dept_id = new String[response.length()];
                final String[] password = new String[response.length()];
                final String[] reports_to = new String[response.length()];


                for (int i =0; i < response.length(); i++){

                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        employee_id[i] = jsonObject.getString("employee_id");
                        emp_name[i] = jsonObject.getString("emp_name");
                        emp_email[i] = jsonObject.getString("emp_email");
                        designation_id[i] = jsonObject.getString("designation_id");
                        current_dept_id[i] = jsonObject.getString("current_dept_id");
                        password[i] = jsonObject.getString("password");
                        reports_to[i] = jsonObject.getString("reports_to");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    employeeidTv.setText(employee_id[i]);
                    nameTv.setText(emp_name[i]);
                    designationTv.setText(designation_id[i]);
                    departmentTv.setText(current_dept_id[i]);
                    emailTv.setText(emp_email[i]);

                }




           /*     listEmployeeJobTitle.setAdapter(new ArrayAdapter(getActivity().getApplicationContext(), R.layout.job_list, textviewforjoblist, job_title));

                listEmployeeJobTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(JobFragment.this.getActivity(), JobDetailsActivity.class);
                        intent.putExtra("JobTitle", job_title[position]);
                        intent.putExtra("JobDescription", job_description[position]);
                        intent.putExtra("JobHasDeadline", job_has_deadline[position]);
                        intent.putExtra("JobStartDate", job_startdate[position]);
                        intent.putExtra("JobEndDate", job_enddate[position]);
                        intent.putExtra("JobProgress", job_progress[position]);
                        intent.putExtra("JobPriority", job_priority[position]);
                        intent.putExtra("JobStatus", job_status[position]);
                        intent.putExtra("DepartmentId", department_id[position]);
                        intent.putExtra("CreatedBy", created_by[position]);
                        intent.putExtra("CreatedTime", created_time[position]);
                        intent.putExtra("UpdateBy", update_by[position]);
                        intent.putExtra("UpdateTime", update_time[position]);
                        intent.putExtra("JobOwner", job_owner[position]);
                        startActivity(intent);
                    }
                });*/
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log", error);
            }
        });
        bd.httpdaffodilvarsity.JobTracking.service.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(getActivity().getApplicationContext(), "Data Loaded Successfully!", Toast.LENGTH_SHORT).show();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}