package com.example.network.controller.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.network.R;
import com.example.network.controller.adapter.CardRecyclerAdapter;
import com.example.network.model.entities.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserListFragment extends Fragment {

    public static final String USER_LIST = "users";
    private RecyclerView recyclerView;
    private CardRecyclerAdapter infoAdapter;
    private ArrayList<User> users;

    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            users = (ArrayList<User>)getArguments().getSerializable(USER_LIST);
        }
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.fragment_user_list, container, false);
        recyclerView = layout.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(layout.getContext()));
        recyclerView.setHasFixedSize(true);
        infoAdapter = new CardRecyclerAdapter();
        recyclerView.setAdapter(infoAdapter);
        infoAdapter.setItems(users);
        return layout;
    }

    public void setUserList(ArrayList<User> users){
        this.users = users;
        infoAdapter.setItems(users);
    }


}
