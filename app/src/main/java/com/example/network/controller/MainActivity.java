package com.example.network.controller;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.network.R;
import com.example.network.controller.adapter.CardRecyclerAdapter;
import com.example.network.controller.fragments.UserInfoFragment;
import com.example.network.controller.fragments.UserListFragment;
import com.example.network.controller.httpTasks.AsyncResponse;
import com.example.network.controller.httpTasks.UserTask;
import com.example.network.model.entities.User;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements AsyncResponse, CardRecyclerAdapter.mOnItemSelected {
    UserTask userTask;
    ArrayList<User> users;

    UserListFragment userListFragment;
    UserInfoFragment userInfoFragment;

    FragmentManager manager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userTask = new UserTask();
        userTask.delegate = this;
        userTask.execute();

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
    }

    @Override
    public void onBackPressed(){
        manager.popBackStack();
    }

    @Override
    public void processFinish(ArrayList<User> output){
        users = output;

        userListFragment = (UserListFragment) manager.findFragmentById(R.id.frameList);

        if(userListFragment!= null){
            userListFragment.setUserList(users);
        }
        else {
            userListFragment = new UserListFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(UserListFragment.USER_LIST, users);
            userListFragment.setArguments(bundle);
            transaction.replace(R.id.frameList, userListFragment).commit();
        }



    }

    @Override
    public void onItemSelect(User user) {
        FragmentTransaction transaction = manager.beginTransaction();

        userInfoFragment = (UserInfoFragment)manager.findFragmentById(R.id.frameInfo);

        if (userInfoFragment != null&&userInfoFragment.isAdded()){
            userInfoFragment.setUser(user);
        }
        else{
            userInfoFragment = new UserInfoFragment();
            userInfoFragment.setUser(user);
            transaction.addToBackStack(null);
            transaction.replace(R.id.frameList, userInfoFragment).commit();

        }

    }
}
