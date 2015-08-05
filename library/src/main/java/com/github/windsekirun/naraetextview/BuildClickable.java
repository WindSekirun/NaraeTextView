package com.github.windsekirun.naraetextview;

import com.github.windsekirun.naraetextview.util.OnLinkClickListener;
import com.github.windsekirun.naraetextview.util.PatternKind;

import java.util.regex.Pattern;

/**
 * NaraeTextView
 * class: BuildClickable
 * Created by IrenSekirun on 15. 8. 5..
 */
public class BuildClickable {

    public static Clickable getClickableObject(PatternKind pk, OnLinkClickListener listener) {
        Pattern pattern = BuildPattern.getPattern(pk);
        return new Clickable(pattern).setLinkClickListener(listener);
    }
}
