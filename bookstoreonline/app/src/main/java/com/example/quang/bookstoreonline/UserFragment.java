package com.example.quang.bookstoreonline;


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
        btnViewOrders = (Button) view.findViewById(R.id.btnViewOrders);
        btnViewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ViewOrdersActivity.class));
            }
        });

        btnViewCart = (Button) view.findViewById(R.id.btnViewCart);
        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CartActivity.class));
            }
        });

        btnEdit = (Button) view.findViewById(R.id.btnEdit);

        btnLogout = (Button) view.findViewById(R.id.btnLogout);

        ActionBar actionBar = ((AppCompatActivity) getActivity()). getSupportActionBar();
        actionBar.setTitle("User");

        return view;
    }

}
