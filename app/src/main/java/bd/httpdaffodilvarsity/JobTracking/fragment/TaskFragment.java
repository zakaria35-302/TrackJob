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

import java.util.ArrayList;

import bd.httpdaffodilvarsity.JobTracking.R;
import bd.httpdaffodilvarsity.JobTracking.activity.CreateEmployeeTask;
import bd.httpdaffodilvarsity.JobTracking.activity.TaskListDetails;
import bd.httpdaffodilvarsity.JobTracking.other.Task;

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

                ArrayList<String> titleList = new ArrayList<>();
                final ArrayList<Task> taskArrayList = new ArrayList<>();


                for (int i =0; i < response.length(); i++){

                    try {

                        JSONObject jsonObject = (JSONObject) response.get(i);

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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                listEmployeeTask.setAdapter(new ArrayAdapter(getActivity().getApplicationContext(), R.layout.task_list,textviewforTasklist,titleList));

                listEmployeeTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(TaskFragment.this.getActivity(), TaskListDetails.class);
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
