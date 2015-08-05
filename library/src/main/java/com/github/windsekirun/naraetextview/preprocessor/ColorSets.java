package com.github.windsekirun.naraetextview.preprocessor;

/**
 * NaraeTextView
 * Class: ColorSets
 * Created by WindSekirun on 15. 8. 6..
 */
public class ColorSets {

    private int hashtagColor;
    private int mentionColor;
    private int urlColor;

    public int getHashtagColor() {
        return hashtagColor;
    }

    public int getMentionColor() {
        return mentionColor;
    }

    public int getUrlColor() {
        return urlColor;
    }

    public ColorSets setHashtagColor(int hashtagColor) {
        this.hashtagColor = hashtagColor;
        return this;
    }

    public ColorSets setMentionColor(int mentionColor) {
        this.mentionColor = mentionColor;
        return this;
    }

    public ColorSets setUrlColor(int urlColor) {
        this.urlColor = urlColor;
        return this;
    }
}
