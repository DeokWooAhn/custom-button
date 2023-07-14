package com.gnapse.customwind;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class CustomFanButton extends LinearLayout implements OnFanLevelChangedListener, View.OnClickListener {

    ConstraintLayout bg;
    ImageView image;
    TextView fanText;

    private int mFanLevel = 1;
    private OnFanLevelChangedListener onFanLevelChangedListener;

    public CustomFanButton(Context context) {
        super(context);
        initView();
        setOnClickListener(this);
    }

    public CustomFanButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        getAttrs(attrs);
        setOnClickListener(this);
    }

    private void initView() {
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(infService);
        View view = layoutInflater.inflate(R.layout.custom_fan_button, this, false);
        addView(view);

        bg = findViewById(R.id.fanBg);
        image = findViewById(R.id.fanButtonImage);
        fanText = findViewById(R.id.fanText);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CustomFanButton);

        try {
            setTypeArray(typedArray);
        } finally {
            typedArray.recycle();
        }
    }

    private void setTypeArray(TypedArray typedArray) {

        int bg_resID = typedArray.getResourceId(R.styleable.CustomFanButton_fanBg, R.color.black);
        bg.setBackgroundResource(bg_resID);

        int image_resID = typedArray.getResourceId(R.styleable.CustomFanButton_fanButtonImage, R.drawable.btn_fan_selector);
        image.setImageResource(image_resID);

        int text_resID = typedArray.getResourceId(R.styleable.CustomFanButton_fanText, R.drawable.btn_fan_text_selector);
        fanText.setTextColor(ContextCompat.getColorStateList(this.getContext(), text_resID));
    }

    void setFanText(String fanText_string) {
        fanText.setText(fanText_string);
    }

    private void changeFanLevel(int fanLevel) {
        switch (fanLevel) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
        }
    }

    @Override
    public void onClick(View v) {
        mFanLevel++;
        if (mFanLevel > 8) {
            mFanLevel = 1;
        }
        setFanText(String.valueOf(mFanLevel));

        if (onFanLevelChangedListener != null) {
            onFanLevelChangedListener.fanLevelChanged(mFanLevel);
        }
    }

    @Override
    public void fanLevelChanged(int fanLevel) {
        changeFanLevel(fanLevel);
    }

    public void setFanLevelChangedListener(OnFanLevelChangedListener listener) {
        this.onFanLevelChangedListener = listener;
    }
}
