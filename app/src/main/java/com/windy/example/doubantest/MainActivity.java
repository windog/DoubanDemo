package com.windy.example.doubantest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;

import com.astuetz.PagerSlidingTabStrip;

public class MainActivity extends FragmentActivity {

    private PagerSlidingTabStrip pagerTab;
    private ViewPager pager;
    // 可用的页签数目，应该在 Adapter 的 getCount() 中动态获取 ，这里写死了
    public final int NUM_PAGES = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        pagerTab = (PagerSlidingTabStrip) findViewById(R.id.pager_tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        //设置预加载页数 , 不设置的话默认一页（只会预加载相邻的一页）
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pagerTab.setViewPager(pager);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);

    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = getResources().getStringArray(R.array.pager_name);

        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * This method may be called by the ViewPager to obtain a title string
         * to describe the specified page. This method may return null
         * indicating no title for this page. The default implementation returns
         * null.
         *
         * @param position The position of the title requested
         * @return A title for the requested page
         */
        // 使tab可以显示对应fragment的title
        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        // 返回对应position的fragment 设置pagerFragment的arguments使得该fragment可以在TextView中显示数字
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            Fragment pagerFragment;

            if (position%2 == 0) {
                pagerFragment = new ListFRefreshFragment();
            } else {
                pagerFragment = new PagerFragment();
            }

            bundle.putInt("page_num", position);
            pagerFragment.setArguments(bundle);

            return pagerFragment;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
