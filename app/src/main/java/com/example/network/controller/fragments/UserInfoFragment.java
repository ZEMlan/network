package com.example.network.controller.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.network.R;
import com.example.network.model.entities.User;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {
    public static final String USER = "user";

    View view;
    private User mUser;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUser = (User)getArguments().getSerializable(USER);
        }
    }

    public void setUser(User user){
        mUser = user;
        try {
            setInfo(view);
        }catch (Exception e){

        }


    }

    private void setInfo(View view){
        TextView name = view.findViewById(R.id.name);
        name.setText(mUser.getName());
        TextView username = view.findViewById(R.id.username);
        username.setText(mUser.getUsername());
        TextView email = view.findViewById(R.id.email);
        email.setText(mUser.getEmail());
        TextView street = view.findViewById(R.id.street);
        street.setText("Street: " + mUser.getAddress().getStreet());
        TextView suite = view.findViewById(R.id.suite);
        suite.setText("Suite: "+ mUser.getAddress().getSuite());
        TextView city = view.findViewById(R.id.city);
        city.setText("City: " + mUser.getAddress().getCity());
        TextView zipcode = view.findViewById(R.id.zipcode);
        zipcode.setText("Zipcode: "+ mUser.getAddress().getZipcode());
        TextView lat = view.findViewById(R.id.lat);
        lat.setText("Lat: " + Double.toString(mUser.getAddress().getGeo().getLat()));
        TextView lng = view.findViewById(R.id.lng);
        lng.setText("Lng: " + Double.toString(mUser.getAddress().getGeo().getLng()));
        TextView phone = view.findViewById(R.id.phone);
        phone.setText("Phone: "+ mUser.getPhone());
        TextView website = view.findViewById(R.id.website);
        website.setText("Website: "+ mUser.getWebsite());
        TextView companyName = view.findViewById(R.id.companyName);
        companyName.setText(mUser.getCompany().getName());
        TextView catchPhrase = view.findViewById(R.id.catchPhrase);
        catchPhrase.setText("«" + mUser.getCompany().getCatchPhrase() + "»");
        TextView bs = view.findViewById(R.id.bs);
        bs.setText("BS: "+ mUser.getCompany().getBs());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_user_info, container, false);
        try {
            setInfo(view);
        }catch (Exception e){

        }
        return view;
    }

}
