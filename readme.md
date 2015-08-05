#NaraeTextView

특정한 정규식을 이용해 텍스트뷰에서 정규식과 일치하는 부분에 리스너를 달 수 있는 간단한 라이브러리 입니다.

## 샘플
[alt text](http://i.imgur.com/nOpJuMgm.png)

[이 곳](https://github.com/WindSekirun/NaraeTextView/releases) 에서 확인이 가능합니다.

## 사용법
**1. XML 선언**
```XML
<com.github.windsekirun.naraetextview.NaraeTextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />
```

**2. Clickable 객체 생성**

```JAVA
Clickable clickable = new Clickable("Regex").setTextColor(ColorValue).setOnLinkClickListener(new OnLinkClickListener() {
            @Override
            public void onClick(String text) {
                Log.d("NaraeTextView", text);
            }
        });
```

**3. NaraeTextView 링크 부착**

```JAVA
NaraeTextView narae = (NaraeTextView) findViewById(R.id.textView);
narae.setText("");
narae.addPattern(clickable)
//narae.addPatterns(clickableList) ArrayList<Clickable>
narae.build();
```

또는 

```JAVA
NaraeTextView narae = (NaraeTextView) findViewById(R.id.textView).setText("").addPattern(clickable).build();
```

## 빠른 사용
이 라이브러리는 사용 편의를 위해 내장 기능을 제공하고 있습니다.

### 내장 Clickable
**해시태그 Clickable**

```JAVA
Clickable clickable = BuildClickable.getClickableObject(PatternKind.HASHTAG, colorvalue, new OnLinkClickListener() {
            @Override
            public void onClick(String text) {
                //SOMETHING TODO
            }
        });
```

**@Username Clickable**
```JAVA
Clickable clickable = BuildClickable.getClickableObject(PatternKind.USERNAME, colorvalue, new OnLinkClickListener() {
            @Override
            public void onClick(String text) {
                //SOMETHING TODO
            }
        });
```

**URL Clickable**
```JAVA
Clickable clickable = BuildClickable.getClickableObject(PatternKind.HTTP, colorvalue, new OnLinkClickListener() {
            @Override
            public void onClick(String text) {
                //SOMETHING TODO
            }
        });
```

### 트위터 링크 전처리기

```JAVA
NaraeTextView textView = (NaraeTextView) findViewById(R.id.textView);
ColorSets colorset = new ColorSets();
colorSets.setHashtagColor(0xffE91E63).setMentionColor(0xff9C27B0).setUrlColor(0xffF44336);
TwitterLinkPreprocessor.process(textView, "", colorset, new OnProcessListener() {
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
```

## 라이센스
이 라이브러리는 [MIT LICENSE](https://github.com/WindSekirun/NaraeTextView/blob/master/LICENSE.MD)를 사용하고 있습니다.

샘플 앱은 아래와 같은 라이브러리를 사용합니다.
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [Apache Common Lang](https://commons.apache.org/proper/commons-lang)

## 리버전

**v 1.0.1 (2015. 08. 05)**
* setPattern(String patten) 추가

**v 1.0.0 (2015. 08. 05)**
* 첫 릴리즈
* 샘플 앱 배포

## 기여 안내
기여는 언제든지 환영합니다! [Issue Tracker](https://github.com/WindSekirun/NaraeTextView/issues) [Pull Request](https://github.com/WindSekirun/NaraeTextView/pulls) 전부 환영합니다!
