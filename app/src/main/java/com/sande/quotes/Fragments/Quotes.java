package com.sande.quotes.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sande.quotes.Adapters.StackAdapter;
import com.sande.quotes.Network.Api;
import com.sande.quotes.Network.MySingleton;
import com.sande.quotes.Pojo.QuotesClass;
import com.sande.quotes.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 */
public class Quotes extends Fragment implements Api {


    private StackAdapter mAdapter;
    private SwipeStack mSwipeStack;

    public Quotes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView= inflater.inflate(R.layout.fragment_quotes, container, false);
        mSwipeStack=(SwipeStack)mView.findViewById(R.id.swst_fq);
        mSwipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                if(position==7){
                    //paginate
                }
                Toast.makeText(getContext(), "Dismissed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewSwipedToRight(int position) {
                if(position==7){
                    //paginate
                }
                Toast.makeText(getContext(), "Liked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStackEmpty() {
                mSwipeStack.resetStack();
            }
        });
        getQuotes();
        return mView;
    }

    private void getQuotes() {
        JsonArrayRequest mArrayReq=new JsonArrayRequest(Request.Method.GET, QUOTE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson mGson = new GsonBuilder().create();
                ArrayList<QuotesClass> mQuotes = new ArrayList<>(Arrays.asList(mGson.fromJson(response.toString(), QuotesClass[].class)));
                mAdapter = new StackAdapter(mQuotes, getContext());
                mSwipeStack.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(getContext()).addToRequestQueue(mArrayReq);
    }

}
