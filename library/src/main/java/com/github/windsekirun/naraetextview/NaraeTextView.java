package com.github.windsekirun.naraetextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * NaraeTextView
 * class: NaraeTextView
 * Created by WindSekirun on 15. 8. 5..
 */
public class NaraeTextView extends TextView {

    protected ArrayList<Clickable> list = new ArrayList<>();
    protected ClickableComplier complier;

    @SuppressLint("NewApi")
    public NaraeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public NaraeTextView(Context context) {
        super(context);
        init();
    }

    public NaraeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NaraeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        // setMovenentMethod to LinkMovementMethod, not null
        setMovementMethod(LinkMovementMethod.getInstance());
        complier = new ClickableComplier();
    }

    public NaraeTextView setText(String text) {
        complier.setText(text);
        return this;
    }

    public NaraeTextView addPattern(Clickable clickable) {
        list.add(clickable);
        complier.setLinks(list);
        return this;
    }

    public NaraeTextView addPatterns(ArrayList<Clickable> clickables) {
        list.addAll(clickables);
        complier.setLinks(list);
        return this;
    }

    public NaraeTextView build() {
        complier.build();
        setText(complier.getSpannable());
        return this;
    }
}
