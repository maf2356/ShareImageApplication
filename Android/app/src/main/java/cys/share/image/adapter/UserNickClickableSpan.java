package cys.share.image.adapter;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import java.util.ArrayList;

import cys.share.image.auxiliary.ShareImageAuxiliaryTool;

/**
 * Created by Administrator on 2016/11/14.
 */

public  class UserNickClickableSpan extends ClickableSpan {

    public UserNickClickableSpan() {
        super();
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(0xFF1C81E6);
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(View widget) {
    }

}
