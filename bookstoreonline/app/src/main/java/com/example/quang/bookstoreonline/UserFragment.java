package com.example.quang.bookstoreonline;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    Button btnViewOrders;
    Button btnViewCart;
    Button btnEdit;
    Button btnLogout;


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = null;
        view = inflater.inflate(R.layout.fragment_user_layout, container, false);

        btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_info_user_layout);
                dialog.setTitle("Edit profile");
                dialog.show();
            }
        });

        btnLogout = (Button) view.findViewById(R.id.btnLogout);

        ActionBar actionBar = ((AppCompatActivity) getActivity()). getSupportActionBar();
        actionBar.setTitle("User");

        return view;
    }

}
