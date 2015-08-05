package com.github.windsekirun.naraetextview;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * NaraeTextView
 * class: ClickableBuilder
 * Created by WindSekirun on 15. 8. 5..
 */
public class ClickableComplier {

    public enum ViewType {
        TEXT_VIEW, EDIT_TEXT
    }

    protected ArrayList<Clickable> mLinks = new ArrayList<>();
    protected ArrayList<Clickable> mFoundLinks = new ArrayList<>();

    protected String mText;
    protected Spannable mSpannable;

    private ViewType mViewType;

    public ClickableComplier(ViewType viewType) {
        this.mViewType = viewType;
    }

    public ArrayList<Clickable> getLinks() {
        return mLinks;
    }

    public void setLinks(ArrayList<Clickable> mLinks) {
        this.mLinks = mLinks;
    }

    public ArrayList<Clickable> getFoundLinks() {
        return mFoundLinks;
    }

    public void setFoundLinks(ArrayList<Clickable> mFoundLinks) {
        this.mFoundLinks = mFoundLinks;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public Spannable getSpannable() {
        return mSpannable;
    }

    public void setSpannable(Spannable mSpannable) {
        this.mSpannable = mSpannable;
    }

    public void addLinkToSpan(Clickable link) {
        if (mSpannable == null) {
            mSpannable = SpannableString.valueOf(mText);
        }

        addLinkToSpan(mSpannable, link);
    }

    private void addLinkToSpan(Spannable s, Clickable link) {
        Pattern pattern = Pattern.compile(Pattern.quote(link.getText()));
        Matcher matcher = pattern.matcher(mText);

        while (matcher.find()) {

            int start = matcher.start();

            if (start >= 0) {
                int end = start + link.getText().length();

                applyLink(link, new Range(start, end), s);
            }
        }
    }

    private void applyLink(final Clickable link, final Range range, Spannable text) {
        ClickableLinkSpan linkSpan = new ClickableLinkSpan(link, range);
        text.setSpan(linkSpan, range.start, range.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public void removePreviousSpans() {
        ClickableSpan[] toRemoveSpans = mSpannable.getSpans(0, mSpannable.length(), ClickableSpan.class);
        for (ClickableSpan toRemoveSpan : toRemoveSpans) {
            mSpannable.removeSpan(toRemoveSpan);
        }
    }

    public void convertPatternsToLinks() {
        mFoundLinks.clear();
        mFoundLinks.addAll(mLinks);

        int size = mFoundLinks.size();
        int i = 0;
        while (i < size) {
            if (mFoundLinks.get(i).getPattern() != null) {
                addLinksFromPattern(mFoundLinks.get(i));

                mFoundLinks.remove(i);
                size--;
            } else {
                i++;
            }
        }
    }

    private void addLinksFromPattern(Clickable clickableWithPattern) {
        Pattern pattern = clickableWithPattern.getPattern();
        Matcher m = pattern.matcher(mText);

        while (m.find()) {
            Clickable clickable = new Clickable(clickableWithPattern).setText(m.group());
            mFoundLinks.add(clickable);
        }
    }

    public void build() {
        if (mViewType == ViewType.EDIT_TEXT) {
            mText = mSpannable.toString();
            removePreviousSpans();
        } else {
            mSpannable = null;
        }

        convertPatternsToLinks();

        for (Clickable clickable : mFoundLinks) {
            addLinkToSpan(clickable);
        }
    }

}
