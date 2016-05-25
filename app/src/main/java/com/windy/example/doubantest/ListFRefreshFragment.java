package com.windy.example.doubantest;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by windog on 2016/5/25.
 */
public class ListFRefreshFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView lv;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 先找到加载出 view ，每个 Fragment 都这样写
        View view = inflater.inflate(R.layout.fragment_list_refresh, container, false);
        //再通过 view 找到 fragment 中的控件
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        lv = (ListView) view.findViewById(R.id.lv);
        //设置刷新监听 ，类似 setOnclickLinstener()
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //刷新的圈圈动画会轮流显示这四个颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        // 给 ListView 设置 Adapter
        lv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getData()));
        return view;
    }


    // 一般都在此方法里执行更新操作
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // 通过setRefreshing(false)使动画停止
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 4000);  // 下拉时显示动画4000毫秒
    }

    //给 Listview 提供数据
    public List<String> getData() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= 20; i++) {
            list.add("" + i);
        }
        return list;
    }
}
