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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zlf.bbzs.R;
import com.zlf.bbzs.adapter.AnimationAdapter;
import com.zlf.bbzs.bean.FuncBean;
import com.zlf.bbzs.entity.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu on 2019/6/17.
 */

public class HomeFragment extends Fragment {
    private RecyclerView mRvList;
    private AnimationAdapter mAnimationAdapter;


    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        mRvList = view.findViewById(R.id.rv_list);
        mRvList.setHasFixedSize(true);
        mRvList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        initAdapter();

        return view;
    }

    private void initAdapter() {
        List<FuncBean> funcs = new ArrayList<>();
        funcs.add(new FuncBean());
        funcs.add(new FuncBean());
        funcs.add(new FuncBean());
        funcs.add(new FuncBean());
        funcs.add(new FuncBean());
        funcs.add(new FuncBean());
        funcs.add(new FuncBean());
        funcs.add(new FuncBean());
        funcs.add(new FuncBean());
        mAnimationAdapter = new AnimationAdapter(funcs);
        mAnimationAdapter.openLoadAnimation();
        int mFirstPageItemCount = 3;
        mAnimationAdapter.setNotDoAnimationCount(mFirstPageItemCount);
        mAnimationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String content = null;
                Status status = (Status) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.img:
                        content = "img:" + status.getUserAvatar();
                        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.tweetName:
                        content = "name:" + status.getUserName();
                        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.tweetText:
                        content = "tweetText:" + status.getUserName();
                        Toast.makeText(getContext(), content, Toast.LENGTH_LONG).show();
                        // you have set clickspan .so there should not solve any click event ,just empty
                        break;
                    default:
                        break;

                }
            }
        });
        mRvList.setAdapter(mAnimationAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
