package org.hurelhuyag.mongolscriptkeyboard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import java.util.List;

/**
 * Created by hurlee on 4/3/16
 */
public class CandidateView extends HorizontalScrollView {

    public CandidateView(Context context) {
        super(context);
    }

    public CandidateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CandidateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CandidateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSuggestions(List<String> suggestions, boolean completions, boolean typedWordValid) {

    }

    public void clear() {
    }
}
