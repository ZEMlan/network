package com.example.network.controller.httpTasks;

import com.example.network.model.entities.User;

import java.util.ArrayList;

public interface AsyncResponse {
    void processFinish(ArrayList<User> output);
}
