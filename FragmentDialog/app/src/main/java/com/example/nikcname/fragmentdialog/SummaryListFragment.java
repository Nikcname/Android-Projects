package com.example.nikcname.fragmentdialog;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SummaryListFragment extends ListFragment {

    private String[] mListItems = new String[]{
            "Google",
            "YouTube",
            "Wikipedia"
    };

    private String[] mListItemsUrls = new String[]{
            "http://Google.com",
            "http://YouTube.com",
            "http://Wikipedia.com/"
    };

    public SummaryListFragment() {}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(
                getActivity(), R.layout.frag_summary_textview, mListItems));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        DetailsWebFragment frag = (DetailsWebFragment) getFragmentManager()
                .findFragmentById(R.id.frag_details_veb_view);
        frag.updateDetails(mListItemsUrls[position]);

    }
}
