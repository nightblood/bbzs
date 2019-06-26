package com.zlf.bbzs.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlf.bbzs.R;
import com.zlf.bbzs.adapter.AnimationAdapter;
import com.zlf.bbzs.bean.FuncBean;
import com.zlf.bbzs.entity.Status;
import com.zlf.bbzs.event.MyEvent;
import com.zlf.bbzs.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu on 2019/6/17.
 */

public class HomeFragment extends BaseFragment {
    private RecyclerView mRvList;
    private AnimationAdapter mAnimationAdapter;
    private FrameLayout mFlContent;
    private BaseFragment mBreastMilkFragment;
    private BaseFragment mBabyDiperFragment;
    private BaseFragment mSleetFragment;
    private BaseFragment mBottleFragment;
    private BaseFragment mPooFragment;
    private BaseFragment mHangOutFragment;
    private BaseFragment mSportFragment;


    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected void initView(View view) {
        mRvList = view.findViewById(R.id.rv_list);
        mRvList.setHasFixedSize(true);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mFlContent = view.findViewById(R.id.fl_content);
        initAdapter();

        mBreastMilkFragment = BreastMilkFragment.newInstance();
        mSleetFragment = SleepFragment.newInstance();
        mBabyDiperFragment = BabyDiaperFragment.newInstance();
        mBottleFragment = BottleFragment.newInstance();
        mPooFragment = PooFragment.newInstance();
        mHangOutFragment = HangOutFragment.newInstance();
        mSportFragment = SportFragment.newInstance();

        getFragmentManager().beginTransaction().add(R.id.fl_content, mBreastMilkFragment, BreastMilkFragment.class.getSimpleName()).commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    private void initAdapter() {
        String[] funcStr = {"哺乳", "尿不湿", "睡觉", "奶瓶","便便", "外出", "运动"};
        List<FuncBean> funcs = new ArrayList<>();
        for (int i = 0; i < funcStr.length; i++) {
            funcs.add(new FuncBean(i, funcStr[i]));
        }

        mAnimationAdapter = new AnimationAdapter(funcs);
        mAnimationAdapter.openLoadAnimation();
        int mFirstPageItemCount = 3;
        mAnimationAdapter.setNotDoAnimationCount(mFirstPageItemCount);
//        mAnimationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                String content = null;
//                FuncBean funcBean = (FuncBean) adapter.getItem(position);
//                switch (view.getId()) {
//                    case R.id.img:
//                        content = "img:";
//                        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.tweetName:
//                        content = "name:" ;
//                        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();
//                        break;
//                    case R.id.tweetText:
//                        content = "tweetText:" ;
//                        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
        mAnimationAdapter.setOnItemClickListener((adapter, view, position) -> {
            FuncBean item = (FuncBean) adapter.getData().get(position);
            ToastUtils.showShort("clicked " + position);
//                showFragment(item.funcId);
            if (mBreastMilkFragment.isCounting() || mBabyDiperFragment.isCounting()|| mSleetFragment.isCounting()|| mBottleFragment.isCounting()
                    || mPooFragment.isCounting()|| mHangOutFragment.isCounting()|| mSportFragment.isCounting()) {
                ToastUtils.showShort("计时中，不能使用其他功能。");
                return;
            }
            showFragment(position);
        });
        mRvList.setAdapter(mAnimationAdapter);
    }

    private void showFragment(int funcId) {

        if (funcId == 0) {
            getFragmentManager().beginTransaction().replace(R.id.fl_content, mBreastMilkFragment, BreastMilkFragment.class.getSimpleName()).commit();
        }else if (funcId == 1) {
            getFragmentManager().beginTransaction().replace(R.id.fl_content, mBabyDiperFragment, BabyDiaperFragment.class.getSimpleName()).commit();
        }else if (funcId == 2) {
            getFragmentManager().beginTransaction().replace(R.id.fl_content, mSleetFragment, SleepFragment.class.getSimpleName()).commit();
        }else if (funcId == 3) {
            getFragmentManager().beginTransaction().replace(R.id.fl_content, mBottleFragment, BottleFragment.class.getSimpleName()).commit();
        }else if (funcId == 4) {
            getFragmentManager().beginTransaction().replace(R.id.fl_content, mPooFragment, PooFragment.class.getSimpleName()).commit();
        }else if (funcId == 5) {
            getFragmentManager().beginTransaction().replace(R.id.fl_content, mHangOutFragment, HangOutFragment.class.getSimpleName()).commit();
        }else if (funcId == 6) {
            getFragmentManager().beginTransaction().replace(R.id.fl_content, mSportFragment, SportFragment.class.getSimpleName()).commit();
        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
