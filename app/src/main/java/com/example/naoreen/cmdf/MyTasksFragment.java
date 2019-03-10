package com.example.naoreen.cmdf;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyTasksFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] tempData = new String[]{"test1", "test2", "test3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstantState){
        View rootView= inflater.inflate(R.layout.fragment_mytasks, parent, false);
        recyclerView = rootView.findViewById(R.id.my_tasks_recycler_view);

        // create an adapter
        mAdapter = new MyAdapter(tempData);

        // set adapter
        recyclerView.setAdapter(mAdapter);

        // set layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        // set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;


    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);


    }

}
