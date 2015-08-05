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
    protected ArrayList<Clickable> lists = new ArrayList<>();
    protected ArrayList<Clickable> foundList = new ArrayList<>();

    protected String mText;
    protected Spannable mSpannable;
    public ArrayList<Clickable> getLinks() {
        return lists;
    }

    public void setLinks(ArrayList<Clickable> mLinks) {
        this.lists = mLinks;
    }

    public ArrayList<Clickable> getFoundLinks() {
        return foundList;
    }

    public void setFoundLinks(ArrayList<Clickable> mFoundLinks) {
        this.foundList = mFoundLinks;
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
        foundList.clear();
        foundList.addAll(lists);

        int size = foundList.size();
        int i = 0;
        while (i < size) {
            if (foundList.get(i).getPattern() != null) {
                addLinksFromPattern(foundList.get(i));

                foundList.remove(i);
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
            foundList.add(clickable);
        }
    }

    public void build() {
        mSpannable = null;

        convertPatternsToLinks();

        for (Clickable clickable : foundList) {
            addLinkToSpan(clickable);
        }
    }

}
