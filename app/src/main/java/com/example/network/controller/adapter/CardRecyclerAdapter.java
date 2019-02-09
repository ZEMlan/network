package com.example.network.controller.adapter;
;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.network.R;
import com.example.network.controller.fragments.UserListFragment;
import com.example.network.model.entities.User;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardRecyclerAdapter extends RecyclerView.Adapter<CardRecyclerAdapter.CardViewHolder> {

    private RecyclerView.ViewHolder holder;
    private ArrayList<User> users;


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_card_layout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, final int position) {
        final User mUser = users.get(position);
        holder.bind(mUser.getUsername(), mUser.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemSelected mListener = (mOnItemSelected)v.getContext();
                mListener.onItemSelect(mUser);
            }
        });
        this.holder = holder;
    }

    @Override
    public int getItemCount() {
        try {
            return users.size();
        }
        catch (Exception e){
            return 0;
        }
    }

    public void setItems(ArrayList<User> userList) {
        users = userList;
        notifyDataSetChanged();
    }

    class CardViewHolder extends RecyclerView.ViewHolder{
        private TextView username, name;

        CardViewHolder(View view){
            super(view);
            username = view.findViewById(R.id.usernameText);
            name = view.findViewById(R.id.nameText);

        }

        private void bind(String username, String name){
            this.username.setText(username);
            this.name.setText(name);
        }
    }

    public interface mOnItemSelected{
        void onItemSelect(User user);
    }
}
