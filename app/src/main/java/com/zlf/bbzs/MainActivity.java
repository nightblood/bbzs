package com.zlf.bbzs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.HorizontalScrollView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.liang.jtab.Tab;
import com.liang.jtab.indicator.JIndicator;
import com.liang.jtab.listener.OnTabSelectedListener;
import com.liang.widget.JTabLayout;
import com.zlf.bbzs.entity.TabEntity;
import com.zlf.bbzs.fragment.HomeFragment;
import com.zlf.bbzs.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CommonTabLayout mTabLayout_3;

    ViewPager mVpContainer;
    HorizontalScrollView mHsvContainer;
    ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页","我的"};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout_3 = findViewById(R.id.tl_3);
//        mViewPager = findViewById(R.id.vp);
//        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mFragments.add(HomeFragment.newInstance());
        mFragments.add(MyFragment.newInstance());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout_3.setTabData(mTabEntities, this, R.id.fl_change, mFragments);

        mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        initHrv();
    }

    private void initHrv() {

    }


}
