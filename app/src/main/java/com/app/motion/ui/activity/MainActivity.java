package com.app.motion.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.app.motion.ui.R;
import com.app.motion.ui.adapter.CommonAdapter;
import com.app.motion.ui.data.DummyDataGenerator;
import com.app.motion.ui.databinding.ActivityMainBinding;
import com.app.motion.ui.model.HomeModel;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    HomeModel homeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);

        setAdapterView();

    }

    private void setAdapterView() {
        CommonAdapter<HomeModel> commonAdapter = new CommonAdapter<>(R.layout.row_home_play, new ArrayList<>(), this::onItemClick);
        binding.includedRv.rvDefault.setAdapter(commonAdapter);

        commonAdapter.add(DummyDataGenerator.generateDataHome());
        commonAdapter.notifyDataSetChanged();
    }

    private void onItemClick(View view, Object o) {
        homeModel = (HomeModel) o;

        startActivity(new Intent(this, DownloadPlayActivity.class));
        Slide slideTransition = new Slide(Gravity.TOP);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));

    }

}