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

import static bd.httpdaffodilvarsity.JobTracking.R.id.textviewforjoblist;


public class JobFragment extends Fragment {

    ListView listEmployeeJobTitle;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public JobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JobFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JobFragment newInstance(String param1, String param2) {
        JobFragment fragment = new JobFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_jobs, container, false);


        listEmployeeJobTitle=(ListView) rootView.findViewById(R.id.listEmployeeJobTitle);

        fetchingData() ;

        FloatingActionButton buttonCreateJob = (FloatingActionButton)rootView. findViewById(R.id.fabJob);

        buttonCreateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobFragment.this.getActivity(), CreateEmployeeJob.class);
                JobFragment.this.startActivity(intent);
            }});

        return rootView;
    }

    void fetchingData(){

        String myURL =  "http://192.168.10.223:8080/jobmanagement";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final String[] job_title = new String[response.length()];
                final String[] job_description = new String[response.length()];
                final String[]  job_has_deadline = new String[response.length()];
                final String[] job_startdate = new String[response.length()];
                final String[] job_enddate = new String[response.length()];
                final String[] job_progress = new String[response.length()];
                final String[] job_priority = new String[response.length()];
                final String[] job_status = new String[response.length()];
                final String[] department_id = new String[response.length()];
                final String[] created_by = new String[response.length()];
                final String[] created_time = new String[response.length()];
                final String[] update_by = new String[response.length()];
                final String[] update_time = new String[response.length()];
                final String[] job_owner = new String[response.length()];


                for (int i =0; i < response.length(); i++){

                    try {

                        JSONObject jsonObject = (JSONObject) response.get(i);

                        job_title[i] = jsonObject.getString("job_title");
                        job_description[i] = jsonObject.getString("job_description");
                        job_has_deadline[i] = jsonObject.getString("job_has_deadline");
                        job_startdate[i] = jsonObject.getString("job_startdate");
                        job_enddate[i] = jsonObject.getString("job_enddate");
                        job_progress[i] = jsonObject.getString("job_progress");
                        job_priority[i] = jsonObject.getString("job_priority");
                        job_status[i] = jsonObject.getString("job_status");
                        department_id[i] = jsonObject.getString("department_id");
                        created_by[i] = jsonObject.getString("created_by");
                        created_time[i] = jsonObject.getString("created_time");
                        update_by[i] = jsonObject.getString("update_by");
                        update_time[i] = jsonObject.getString("update_time");
                        job_owner[i] = jsonObject.getString("job_owner");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                listEmployeeJobTitle.setAdapter(new ArrayAdapter(getActivity().getApplicationContext(), R.layout.job_list, textviewforjoblist, job_title));

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
                });


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