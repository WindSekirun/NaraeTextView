package com.github.windsekirun.naraetextview;

import com.github.windsekirun.naraetextview.util.OnLinkClickListener;

import java.util.regex.Pattern;

/**
 * NaraeTextView
 * class: Clickable
 * Created by WindSekirun on 15. 8. 5..
 */
public class Clickable {

    protected String text;
    protected Pattern pattern;

    protected OnLinkClickListener linkClickListener;

    public Clickable(Clickable clickable) {
        text = clickable.text;
        pattern = clickable.pattern;
        linkClickListener = clickable.linkClickListener;
    }

    public Clickable(String text) {
        this.text = text;
        this.pattern = null;
    }

    public Clickable(Pattern pattern) {
        this.pattern = pattern;
        this.text = null;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getText() {
        return text;
    }

    public Clickable setPattern(Pattern pattern) {
        this.pattern = pattern;
        return this;
    }

    public Clickable setText(String text) {
        this.text = text;
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
