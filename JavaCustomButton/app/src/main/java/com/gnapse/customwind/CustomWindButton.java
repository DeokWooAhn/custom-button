package com.gnapse.customwind;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

public class CustomWindButton extends LinearLayout implements OnWindStatusChangedListener, View.OnClickListener {

    LinearLayout bg;
    ImageView image;

    private String mWindStatus = "";

    private OnWindStatusChangedListener onWindStatusChangedListener;

    public CustomWindButton(Context context) {
        super(context);
        initView();
        setOnClickListener(this);
    }


    public CustomWindButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
        setOnClickListener(this);
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
        View view = layoutInflater.inflate(R.layout.custom_wind_button, this, false);
        addView(view);

        bg = findViewById(R.id.fanBg);
        image = findViewById(R.id.fanButtonImage);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomWindButton);

        try {
        setTypeArray(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    private void setTypeArray(TypedArray typedArray) {
        int bg_resID = typedArray.getResourceId(R.styleable.CustomWindButton_bg, R.color.black);
        bg.setBackgroundResource(bg_resID);

        int image_resID = typedArray.getResourceId(R.styleable.CustomWindButton_buttonImage, R.drawable.btn_wind_selector);
        image.setImageResource(image_resID);
    }

    private void changeWindStatus(String windStatus) {

    }

    @Override
    public void onClick(View v) {

        if (onWindStatusChangedListener != null) {
            onWindStatusChangedListener.onWindStatusChanged(mWindStatus);
        }
    }

    @Override
    public void onWindStatusChanged(String windStatus) {
        changeWindStatus(windStatus);
    }

    public void setOnWindStatusChangedListener(OnWindStatusChangedListener listener) {
        this.onWindStatusChangedListener = listener;
    }
}
