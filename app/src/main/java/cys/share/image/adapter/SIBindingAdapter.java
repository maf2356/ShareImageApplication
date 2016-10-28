package cys.share.image.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import cys.share.image.auxiliary.ShareImageAuxiliaryTool;

/**
 * Created by Administrator on 2016/10/28.
 */
public class SIBindingAdapter {

    @BindingAdapter({"imageUrl","width","height"})
    public static void loadImage(ImageView view, String url,int width,int height) {
        int widthImg = ShareImageAuxiliaryTool.getWindowsWidth(view.getContext());
        if(width == 0 || height == 0){
            Picasso.with(view.getContext()).load(url).config(Bitmap.Config.ARGB_8888).into(view);
        }else {
            int heightImg = (int) (widthImg / ((float) (width / height)));
            Picasso.with(view.getContext()).load(url).config(Bitmap.Config.ARGB_8888).resize(widthImg, heightImg).into(view);
        }
//        Glide.with(view.getContext()).load(url).into(view);
    }

}
