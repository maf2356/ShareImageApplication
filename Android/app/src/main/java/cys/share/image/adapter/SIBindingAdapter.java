package cys.share.image.adapter;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import cys.share.image.R;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;

/**
 * Created by Administrator on 2016/10/28.
 */
public class SIBindingAdapter {

    @BindingAdapter({"imageUrl","width","height"})
    public static void loadImage(ImageView view, String url,int width,int height) {
        float dimension = view.getContext().getResources().getDimension(R.dimen.activity_horizontal_margin);
        int widthImg = (int) (ShareImageAuxiliaryTool.getWindowsWidth(view.getContext())-dimension);
//        ShareImageAuxiliaryTool.log(widthImg+"");
        if(width == 0 || height == 0){
            view.setImageResource(R.mipmap.img_default);
        }else {
            int heightImg = (int) (widthImg / (((float)width / height)));
            ShareImageAuxiliaryTool.log(widthImg+","+heightImg);
//            Picasso.with(view.getContext()).load(url).config(Bitmap.Config.RGB_565).resize(widthImg,heightImg).error(R.mipmap.img_default).into(view);
            Picasso.with(view.getContext()).load(url).config(Bitmap.Config.RGB_565).into(view);
        }
//        Glide.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter({"loadAvatar"})
    public static void loadAvatar(ImageView view, String url) {
            Picasso.with(view.getContext()).load(url).config(Bitmap.Config.RGB_565).error(R.drawable.ic_launcher).into(view);
    }

    @BindingAdapter({"loadImage"})
    public static void loadLargeViewImage(ImageView view, String url) {
            Picasso.with(view.getContext()).load(url).config(Bitmap.Config.ARGB_8888).into(view);
    }

}
