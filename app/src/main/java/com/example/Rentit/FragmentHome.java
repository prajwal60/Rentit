package com.example.Rentit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FragmentHome extends Fragment {
    private ArrayList<DatabaseModel> Roomdata = new ArrayList<>();

    private DatabaseHandler objectDatabaseHandler;
    private RecyclerView objectRecyclerView;
    private RViewAdapter objectRViewAdapter;

    private RViewAdapter mViewModel;

    public static FragmentHome newInstance() {
        return new FragmentHome();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        try {

            objectRecyclerView = (RecyclerView) rootview.findViewById(R.id.RVdetails);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            objectRecyclerView.setLayoutManager(llm);
            objectRecyclerView.setAdapter( objectRViewAdapter );


        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return rootview;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}
