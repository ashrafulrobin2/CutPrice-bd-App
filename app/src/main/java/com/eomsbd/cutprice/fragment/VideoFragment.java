package com.eomsbd.cutprice.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eomsbd.cutprice.OnBackPressed;
import com.eomsbd.cutprice.R;

import com.eomsbd.cutprice.activity.ShoppingActivity;
import com.eomsbd.cutprice.adapters.YoutubeAdapter;
import com.eomsbd.cutprice.model.DataSetList;

import java.util.ArrayList;
import java.util.Vector;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment implements OnBackPressed {
    RecyclerView recyclerView;
    ArrayList<DataSetList> arrayList;
    Vector<DataSetList> dataSetLists;
    ProgressDialog progressDialog;

    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_video, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        recyclerView = view.findViewById(R.id.recyclerview);
        dataSetLists = new Vector<DataSetList>();



        if (dataSetLists != null) {
            progressDialog.dismiss();
            recyclerView = view.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            //dataSetLists = ;
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/fPjMOh2hEF0\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/t16622XYNlg\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7H8PonrHJ-o\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EcF3zGI_QUc?list=PLayNXybR2WtwVvrXLzKA1eoDKJWLlj58J\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/WXQqA6XHDLE?list=PLayNXybR2WtwVvrXLzKA1eoDKJWLlj58J\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/OsRN2Q_8iAM?list=PLayNXybR2WtwVvrXLzKA1eoDKJWLlj58J\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ohW2THbUKC4\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/BOmqzEUUi4w?list=PLayNXybR2WtzKzYM_dTF8NjplmBbyvLjC\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/Wnq0VE8xc9A?list=PLayNXybR2WtzKzYM_dTF8NjplmBbyvLjC\" frameborder=\"0\" allowfullscreen></iframe>"));
            dataSetLists.add(new DataSetList("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/33BmJ0sfIfw?list=PLayNXybR2WtzKzYM_dTF8NjplmBbyvLjC\" frameborder=\"0\" allowfullscreen></iframe>"));
            YoutubeAdapter youtubeAdapter = new YoutubeAdapter(dataSetLists, getContext());
            recyclerView.setAdapter(youtubeAdapter);
        } else {
            Toasty.error(getContext(), "Can Not Load Data", Toasty.LENGTH_LONG).show();
        }

        return view;
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getActivity(), ShoppingActivity.class));
    }


}
