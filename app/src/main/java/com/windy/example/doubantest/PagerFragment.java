package com.windy.example.doubantest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by windog on 2016/5/25.
 */
public class PagerFragment extends Fragment {
    private int pageNum;
    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pager = inflater.inflate(R.layout.pager_fragment, container, false);

        tv = (TextView) pager.findViewById(R.id.tv);
        Bundle bundle = getArguments();
        pageNum = bundle.getInt("page_num");
        tv.setText("" + pageNum);

        return pager;
    }


}
