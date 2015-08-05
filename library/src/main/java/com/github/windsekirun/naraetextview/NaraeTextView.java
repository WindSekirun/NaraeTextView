package com.github.windsekirun.naraetextview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
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

    @SuppressWarnings("NullableProblems")
    public boolean onTouchEvent(MotionEvent event) {
        TextView widget = this;
        Object text = widget.getText();
        if (text instanceof Spanned) {
            Spannable buffer = new SpannableString(widget.getText());

            int action = event.getAction();

            if (action == MotionEvent.ACTION_UP
                    || action == MotionEvent.ACTION_DOWN) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                x -= widget.getTotalPaddingLeft();
                y -= widget.getTotalPaddingTop();

                x += widget.getScrollX();
                y += widget.getScrollY();

                Layout layout = widget.getLayout();
                int line = layout.getLineForVertical(y);
                int off = layout.getOffsetForHorizontal(line, x);

                ClickableSpan[] link = buffer.getSpans(off, off,
                        ClickableSpan.class);

                if (link.length != 0) {
                    if (action == MotionEvent.ACTION_UP) {
                        link[0].onClick(widget);
                    } else if (action == MotionEvent.ACTION_DOWN) {
                        Selection.setSelection(buffer, buffer.getSpanStart(link[0]), buffer.getSpanEnd(link[0]));
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
