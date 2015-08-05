package com.github.windsekirun.naraetextview;

import com.github.windsekirun.naraetextview.util.OnLinkClickListener;

import java.util.regex.Pattern;

/**
 * NaraeTextView
 * class: Clickable
 * Created by WindSekirun on 15. 8. 5..
 */
public class Clickable {

    private String text;
    private Pattern pattern;
    private int textColor;

    private OnLinkClickListener linkClickListener;

    public Clickable(Clickable clickable) {
        text = clickable.text;
        pattern = clickable.pattern;
        linkClickListener = clickable.linkClickListener;
        textColor = clickable.textColor;
    }

    public Clickable(String text) {
        this.text = text;
        this.pattern = null;
        this.textColor = 0;
    }

    public Clickable(Pattern pattern) {
        this.pattern = pattern;
        this.text = null;
        this.textColor = 0;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Clickable setPattern(Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public Clickable setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
        return this;
    }

    public String getText() {
        return text;
    }

    public Clickable setText(String text) {
        this.text = text;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public Clickable setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public OnLinkClickListener getLinkClickListener() {
        return linkClickListener;
    }

    public Clickable setLinkClickListener(OnLinkClickListener linkClickListener) {
        this.linkClickListener = linkClickListener;
        return this;
    }
}
