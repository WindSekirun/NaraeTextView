package com.github.windsekirun.naraetextview.preprocessor;

import com.github.windsekirun.naraetextview.Clickable;
import com.github.windsekirun.naraetextview.NaraeTextView;
import com.github.windsekirun.naraetextview.bulitin.BuildClickable;
import com.github.windsekirun.naraetextview.util.OnLinkClickListener;
import com.github.windsekirun.naraetextview.util.PatternKind;

import java.util.ArrayList;

/**
 * NaraeTextView
 * class: TwitterLinkPreprocessor
 * Created by WindSekirun on 15. 8. 5..
 */
public class TwitterLinkPreprocessor {

    public static void process(NaraeTextView narae, String text, ColorSets colorset, final OnProcessListener listener) {
        final ArrayList<Clickable> list = new ArrayList<>();

        Clickable hashTagClickable = BuildClickable.getClickableObject(PatternKind.HASHTAG, colorset.getHashtagColor(), new OnLinkClickListener() {
            @Override
            public void onClick(String text) {
                listener.onHashTag(text);
            }
        });

        Clickable mentionClickable = BuildClickable.getClickableObject(PatternKind.USERNAME, colorset.getMentionColor(), new OnLinkClickListener() {
            @Override
            public void onClick(String text) {
                listener.onMention(text);
            }
        });

        Clickable urlClickable = BuildClickable.getClickableObject(PatternKind.HTTP, colorset.getUrlColor(), new OnLinkClickListener() {
            @Override
            public void onClick(String text) {
                listener.onURL(text);
            }
        });

        list.add(hashTagClickable);
        list.add(mentionClickable);
        list.add(urlClickable);

        narae.setText(text).addPatterns(list).build();
    }
}
