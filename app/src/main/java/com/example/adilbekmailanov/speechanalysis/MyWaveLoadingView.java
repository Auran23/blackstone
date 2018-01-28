package com.example.adilbekmailanov.speechanalysis;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import me.itangqi.waveloadingview.WaveLoadingView;

/**
 * Created by adilbekmailanov on 28.01.18.
 */

public class MyWaveLoadingView extends WaveLoadingView {

    private ImageView imageView;
    public MyWaveLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        setProgressValue(50);
        setAmplitudeRatio(60);
        setAnimDuration(4000);
        setWaveColor(getResources().getColor(R.color.colorPrimary));
        startAnimation();
    }

    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            setBorderColor(getResources().getColor(R.color.colorYellow));
            setWaveColor(getResources().getColor(R.color.colorYellow));
            setProgressValue(0);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_light_grey));
        } else {
            setBorderColor(getResources().getColor(R.color.colorPrimary));
            setWaveColor(getResources().getColor(R.color.colorPrimary));
            setProgressValue(50);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_mic_yellow));
        }
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
