package com.app.motion.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;

import com.app.motion.ui.R;
import com.app.motion.ui.databinding.ActivityDownloadPlayBinding;
import com.app.motion.ui.dialogs.DialogViewBottom;

import java.util.Objects;

public class DownloadPlayActivity extends BaseActivity {

    ActivityDownloadPlayBinding binding;
    private Handler pHandler = new Handler();
    int fileSize = 30;
    int total = 30;
    int downloadPre = 0;
    Animation bottomUp, sildeRightLeftOne, sildeRightLeftTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_download_play);
        binding.setActivity(this);

        fileSize();
        animationcall();
        setUiDesign();
    }

    private void animationcall() {

        bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        sildeRightLeftOne = AnimationUtils.loadAnimation(this, R.anim.slide_r_to_l_one);
        sildeRightLeftTwo = AnimationUtils.loadAnimation(this, R.anim.slide_r_to_l_two);

    }

    /**
     * Download file size manage according progress bar
     */
    private void fileSize() {
        total = fileSize * 100 / fileSize;
    }

    private void setUiDesign() {

        binding.pbDownloadView.setProgress(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.txtDis.setVisibility(View.VISIBLE);
                binding.txtReadMore.setVisibility(View.VISIBLE);
            }
        }, 300);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.ivScreenOne.setVisibility(View.VISIBLE);
                binding.ivScreenTwo.setVisibility(View.VISIBLE);

                binding.ivScreenOne.startAnimation(sildeRightLeftOne);
                binding.ivScreenTwo.startAnimation(sildeRightLeftTwo);
            }
        }, 300);

        DownloadButtonTouchEffects();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void DownloadButtonTouchEffects() {

        binding.llDownView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        binding.llDownView.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        binding.llDownView.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                    case MotionEvent.ACTION_CANCEL: {
                        binding.llDownView.getBackground().clearColorFilter();
                        binding.llDownView.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

    }

    /**
     * GO Back to Previous screen
     * call in xml(onClick)
     */
    public void cancelProcess() {
        onBackPressed();
    }

    /**
     * Download click method
     * call in xml file (onClick)
     */
    public void downloadClick() {
        binding.llDownView.setVisibility(View.GONE);
        binding.rlProgressView.setVisibility(View.VISIBLE);
        downloadProcess();
    }

    /**
     * Downloading Process show
     * After complete set play button
     */
    public void downloadProcess() {

        binding.llCancelSucessView.setVisibility(View.VISIBLE);
        binding.ivCancelDownload.setVisibility(View.VISIBLE);
        ((Animatable) binding.ivCancelDownload.getDrawable()).start();

        setProgressThread();
    }

    /**
     * Play Button Click
     * Call in XML file (onClick)
     */
    public void playClick() {
        //playClickDialog();
        playClickDialogNew();
    }

    /**
     * Thread Method
     * perform progress bar task
     * Show process on UI
     */
    private void setProgressThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= total; i++) {
                    final int value = i;
                    if (value == 79) {
                        try {
                            Thread.sleep(200);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    binding.pbDownloadView.setProgress(value);
                    downloadPre = (int) (((float) fileSize / 100f) * (float) value);
                    runTaskOnUI(value);

                }
                afterDownloadComplete();
            }
        }).start();

    }

    /**
     * After Complete Progress
     * Perform new Task
     */
    private void afterDownloadComplete() {
        pHandler.post(new Runnable() {
            @Override
            public void run() {
                binding.rlProgressView.setVisibility(View.GONE);
                binding.llCancelSucessView.setVisibility(View.GONE);
                binding.btnPlay.setVisibility(View.VISIBLE);
                binding.btnPlay.startAnimation(bottomUp);
                Animation myFadeInAnimation = AnimationUtils.loadAnimation(DownloadPlayActivity.this, R.anim.blink_alpha);
                binding.btnBlink.startAnimation(myFadeInAnimation);
            }
        });

    }

    /**
     * Display Thread task on UI
     *
     * @param value
     */
    private void runTaskOnUI(int value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Stuff that updates the UI
                if (value == 80) {
                    binding.txtDownloadStatus.setVisibility(View.GONE);
                    binding.ivCancelDownload.setVisibility(View.GONE);
                    binding.txtDownloadStatus.setVisibility(View.GONE);
                    binding.ivDownloadSucess.setVisibility(View.VISIBLE);
                    ((Animatable) binding.ivDownloadSucess.getDrawable()).start();
                }
                binding.txtDownloadStatus.setText(downloadPre + "MB /" + fileSize + " MB");

            }
        });
    }

    private void playClickDialogNew() {
        try {
            DialogViewBottom dialogViewBottom = new DialogViewBottom(this);
            Objects.requireNonNull(dialogViewBottom.getWindow()).setLayout(LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen._280sdp));
            dialogViewBottom.getWindow().setGravity(Gravity.BOTTOM);
            dialogViewBottom.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}