package com.github.windsekirun.naraetextview.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.github.windsekirun.naraetextview.NaraeTextView;
import com.github.windsekirun.naraetextview.preprocessor.ColorSets;
import com.github.windsekirun.naraetextview.preprocessor.OnProcessListener;
import com.github.windsekirun.naraetextview.preprocessor.TwitterLinkPreprocessor;

import org.apache.commons.lang3.StringUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * NaraeTextView Sample
 * class: MainActivity
 * Created by WindSekirun on 15. 8. 5..
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.editText)
    EditText edit;

    @Bind(R.id.button)
    Button button;

    @Bind(R.id.textView)
    NaraeTextView textView;

    @Bind(R.id.root)
    RelativeLayout root;

    ColorSets colorSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        colorSets.setHashtagColor(0xffE91E63).setMentionColor(0xff9C27B0).setUrlColor(0xffF44336);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TwitterLinkPreprocessor.process(textView, edit.getText().toString(), colorSets, new OnProcessListener() {
                    @Override
                    public void onHashTag(String text) {
                        Uri uri = Uri.parse("http://www.twitter.com/search?q=" + text);
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }

                    @Override
                    public void onMention(String text) {
                        Uri uri = Uri.parse("http://www.twitter.com/search?q=" + text);
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }

                    @Override
                    public void onURL(String text) {
                        Uri uri = Uri.parse(text);
                        Intent it = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(it);
                    }
                });
            }
        });

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("#tag @tag http://github.com/windsekirun");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
