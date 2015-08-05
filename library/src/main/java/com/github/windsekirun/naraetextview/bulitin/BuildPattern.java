package com.github.windsekirun.naraetextview.bulitin;

import com.github.windsekirun.naraetextview.util.PatternKind;

import java.util.regex.Pattern;

/**
 * NaraeTextView
 * class: BuildPattern
 * Created by IrenSekirun on 15. 8. 5..
 */
public class BuildPattern {
    
    public static Pattern getPattern(PatternKind pk) {
        Pattern toBuild = null;
        switch (pk) {
            case HASHTAG:
                toBuild = Pattern.compile("(https?://)?([\\da-zA-Z\\.-]+)\\.([a-zA-Z])([/\\w\\.-_]*)*/?");
                break;
            case USERNAME:
                toBuild = Pattern.compile("@([0-9a-zA-Z_])+");
                break;
            case HTTP:
                toBuild = Pattern.compile("#([0-9a-zA-Z가-힣_])+");
                break;
        }
        return toBuild;
    }
}
