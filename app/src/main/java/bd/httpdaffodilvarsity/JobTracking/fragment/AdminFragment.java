package bd.httpdaffodilvarsity.JobTracking.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import bd.httpdaffodilvarsity.JobTracking.R;
import bd.httpdaffodilvarsity.JobTracking.activity.CreateEmployeeJob;
import bd.httpdaffodilvarsity.JobTracking.activity.CreateEmployeeTask;
import bd.httpdaffodilvarsity.JobTracking.activity.SetDepartment;
import bd.httpdaffodilvarsity.JobTracking.activity.SetDesignation;


public class AdminFragment extends Fragment {

    Button jobButton;
    Button taskButton;
    Button departmentButton;
    Button designationButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminFragment newInstance(String param1, String param2) {
        AdminFragment fragment = new AdminFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_admin, container, false);

        jobButton=(Button)rootView.findViewById(R.id.gototjob);
        taskButton=(Button)rootView.findViewById(R.id.gototask);
        departmentButton=(Button)rootView.findViewById(R.id.gotodepartment);
        designationButton=(Button)rootView.findViewById(R.id.gotodesignation);


        jobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminFragment.this.getActivity(), CreateEmployeeJob.class);
                AdminFragment.this.startActivity(intent);
            }
        });


        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminFragment.this.getActivity(), CreateEmployeeTask.class);
                AdminFragment.this.startActivity(intent);
            }
        });


        departmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminFragment.this.getActivity(), SetDepartment.class);
                AdminFragment.this.startActivity(intent);
            }
        });

        designationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminFragment.this.getActivity(), SetDesignation.class);
                AdminFragment.this.startActivity(intent);
            }
        });


    /*    tvEmployeeDepartment = (TextView) rootView.findViewById(R.id.textViewAdminSetDepartment);
        tvEmployeeDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminFragment.this.getActivity(), SetDepartment.class);
                AdminFragment.this.startActivity(intent);
            }
        });*/


        return rootView;

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

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
