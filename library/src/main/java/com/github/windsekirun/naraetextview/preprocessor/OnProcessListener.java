package com.github.windsekirun.naraetextview.preprocessor;

/**
 * NaraeTextView
 * class: OnProcessListener
 * Created by WindSekirun on 15. 8. 5..
 */
public interface OnProcessListener {

    void onHashTag(String text);

    void onMention(String text);

    void onURL(String text);
}
