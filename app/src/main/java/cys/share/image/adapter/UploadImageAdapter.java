package cys.share.image.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cys.share.image.R;
import cys.share.image.databinding.ItemUploadBinding;

/**
 * Created by Administrator on 2016/11/3.
 */

public class UploadImageAdapter extends BaseAdapter{

    private List<UploadImageBean> mList = new ArrayList<>();

    private Context context;


    public UploadImageAdapter(Context context,List<String> data){
        this.context = context;
        for (int i=0;i<data.size();i++) {
            UploadImageBean bean = new UploadImageBean();
            bean.path = data.get(i);
            bean.flag = i;
            mList.add(bean);
        }
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public UploadImageBean getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemUploadBinding dataBinding = null;
        if(view == null){
            dataBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_upload, viewGroup, false);
        }else{
            dataBinding = DataBindingUtil.getBinding(view);
        }
//
//        Glide.with(context)
//                .load(getItem(i))
//                .placeholder(R.mipmap.default_error)
//                .error(R.mipmap.default_error)
//                .centerCrop()
//                .crossFade()
//                .into(dataBinding.img);
        UploadImageBean bean = getItem(i);
        dataBinding.shareImageProgressBar.setImage(bean.path);
        dataBinding.shareImageProgressBar.setWidth(4);
        dataBinding.shareImageProgressBar.setColor("#FF0FDF0F");
        dataBinding.shareImageProgressBar.setProgress(bean.progress);
        dataBinding.shareImageProgressBar.setOpacity(true);
        dataBinding.shareImageProgressBar.showProgress(true);
        return dataBinding.getRoot();
    }


    public void notifyUploadProgressSetChanged(int  flag,int progress) {
        //
        mList.get(flag).progress = progress;
        this.notifyDataSetChanged();
    }

    class UploadImageBean{

        public String path;

        public int progress;

        public int flag;
    }
}
