package com.app.motion.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.motion.ui.R;
import com.app.motion.ui.databinding.DialogBottomViewBinding;

public class DialogViewBottom extends Dialog {

    Context context;
    Animation bottomUpLayout, bottomUpButton, sildeRightLeft, sildeRightLeftOne,
            sildeRightLeftTwo, sildeRightLeftThree, sildeRightLeftFour, fideIn, bottomUpNextBtn;

    public DialogViewBottom(@NonNull Context context) {
        super(context, R.style.MaterialDialogSheet);
        this.context = context;
        bottomUpLayout = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up_dialog_layout);
        bottomUpButton = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up);
        sildeRightLeft = AnimationUtils.loadAnimation(getContext(), R.anim.slide_right_to_left);
        sildeRightLeftOne = AnimationUtils.loadAnimation(getContext(), R.anim.slide_r_to_l_one);
        sildeRightLeftTwo = AnimationUtils.loadAnimation(getContext(), R.anim.slide_r_to_l_two);
        sildeRightLeftThree = AnimationUtils.loadAnimation(getContext(), R.anim.slide_r_to_l_three);
        sildeRightLeftFour = AnimationUtils.loadAnimation(getContext(), R.anim.slide_r_to_l_four);

        fideIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        bottomUpNextBtn = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up_more_time);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DialogBottomViewBinding binding = DialogBottomViewBinding.inflate(LayoutInflater.from(getContext()));
        setContentView(binding.getRoot());

        /*  First Screen Animation Code  */

        binding.llMainDialogUI.startAnimation(bottomUpLayout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.ivAfterTopViewOne.startAnimation(fideIn);
                binding.ivAfterTopViewOne.setVisibility(View.VISIBLE);
            }
        }, 300);

        binding.llAfterDFirstOne.startAnimation(sildeRightLeft);
        binding.llAfterDFirstTwo.startAnimation(sildeRightLeftTwo);

        /*  Second Screen Animation Code  */

        binding.btnPlay.startAnimation(bottomUpNextBtn);

        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.llMainViewData.getVisibility() == View.VISIBLE) {
                    /*Second Screen Call and set ui Changes*/
                    binding.llMainViewData.setVisibility(View.GONE);
                    binding.llNextWalletData.setVisibility(View.VISIBLE);
                    binding.llWalletViewShow.setVisibility(View.VISIBLE);
                    binding.llWalletViewShow.startAnimation(bottomUpButton);

                    binding.includedNext.ivSecondScreenOne.startAnimation(fideIn);
                    binding.includedNext.ivSecondScreenTwo.startAnimation(fideIn);

                    binding.includedNext.txtSecondNoOne.startAnimation(sildeRightLeftOne);
                    binding.includedNext.txtSecondNoTwo.startAnimation(sildeRightLeftTwo);
                    binding.includedNext.txtSecondNoThree.startAnimation(sildeRightLeftThree);

                } else if (binding.includedNext.llwalletAdd.getVisibility() == View.VISIBLE) {
                    /*Third Screen Call and set ui Changes*/
                    binding.includedNext.llwalletAdd.setVisibility(View.GONE);
                    binding.includedNext.llReady.setVisibility(View.VISIBLE);
                    binding.btnPlay.setText(context.getString(R.string.confirm));
                    binding.llWalletViewShow.setVisibility(View.VISIBLE);
                    binding.btnPlay.setBackground(context.getResources().getDrawable(R.drawable.button_home_bg_gray));
                    binding.btnPlay.startAnimation(bottomUpButton);
                    binding.includedNext.includedReady.txtThirdThree.startAnimation(sildeRightLeftOne);
                    binding.includedNext.includedReady.txtThirdFour.startAnimation(sildeRightLeftTwo);
                    binding.includedNext.includedReady.txtThirdFive.startAnimation(sildeRightLeftThree);

                    binding.includedNext.includedReady.txtThirdOne.startAnimation(fideIn);
                    binding.includedNext.includedReady.txtThirdTwo.startAnimation(fideIn);
                    binding.includedNext.includedReady.ivThirdOne.startAnimation(fideIn);
                    binding.includedNext.includedReady.ivThirdTwo.startAnimation(fideIn);

                    binding.includedNext.includedReady.txtThirdOne.setVisibility(View.VISIBLE);
                    binding.includedNext.includedReady.txtThirdTwo.setVisibility(View.VISIBLE);
                    binding.includedNext.includedReady.ivThirdOne.setVisibility(View.VISIBLE);
                    binding.includedNext.includedReady.ivThirdTwo.setVisibility(View.VISIBLE);

                } else if (binding.includedNext.includedReady.llReadyMain.getVisibility() == View.VISIBLE) {
                    Toast.makeText(getContext(), "Ready", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}
