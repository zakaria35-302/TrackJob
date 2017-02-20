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
import bd.httpdaffodilvarsity.JobTracking.activity.CreateEmployeeTask;
import bd.httpdaffodilvarsity.JobTracking.activity.TaskListDetails;

import static bd.httpdaffodilvarsity.JobTracking.R.id.textviewforTasklist;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {

    ListView listEmployeeTask;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public TaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance(String param1, String param2) {
        TaskFragment fragment = new TaskFragment();
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
        View rootView=inflater.inflate(R.layout.fragment_task, container, false);

        listEmployeeTask=(ListView)rootView.findViewById(R.id.listEmployeeTask);

        fetchingData() ;

        FloatingActionButton buttonCreateTask = (FloatingActionButton) rootView.findViewById(R.id.fab);
        buttonCreateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskFragment.this.getActivity(), CreateEmployeeTask.class);
                startActivity(intent);
            }
        });
        return rootView;
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                listEmployeeTask.setAdapter(new ArrayAdapter(getActivity().getApplicationContext(), R.layout.task_list,textviewforTasklist,task_title));

                listEmployeeTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(TaskFragment.this.getActivity(), TaskListDetails.class);
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
    /*    if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
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
