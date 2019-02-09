package com.example.network.controller.httpTasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.network.model.entities.User;
import com.example.network.model.ip.JsonPlaceholderApi;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UserTask extends AsyncTask<Void, Void, ArrayList<User>> {
    public AsyncResponse delegate = null;

    @Override
    protected void onPostExecute(ArrayList<User> result) {
        delegate.processFinish(result);
    }

    @Override
    protected ArrayList<User> doInBackground(Void... voids) {
        ArrayList<User> users = null;
        JsonPlaceholderApi api = new JsonPlaceholderApi("https://jsonplaceholder.typicode.com/users");
        try {
            users = api.getUserList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return users;
    }
}


