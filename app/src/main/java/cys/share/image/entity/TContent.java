package cys.share.image.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

import cys.share.image.BR;
import cys.share.image.entity.realm.BaseBean;
import cys.share.image.entity.realm.TagContentRealm;
import cys.share.image.entity.realm.imp.RealmTransaction;

/**
 * Created by Administrator on 2016/11/8.
 */
public class TContent extends BaseObservable implements RealmTransaction<TagContentRealm,TContent>,Serializable {

    private int id;

    private User user;

    private String shortContent;

    private Cover cover;

    private int imageCount;

    private String imageIds;

    private String tags;

    private int likeCount;

    private int commentCount;

    private long createTime;

    private boolean liked;

    private List<Cover> images;

    public List<Cover> getImages() {
        return images;
    }

    public void setImages(List<Cover> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getImageIds() {
        return imageIds;
    }

    public void setImageIds(String imageIds) {
        this.imageIds = imageIds;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getLikeCount() {
        return likeCount;
    }

    @Bindable
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
        this.notifyPropertyChanged(BR.likeCount);
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isLiked() {
        return liked;
    }

    @Bindable
    public void setLiked(boolean liked) {
        this.liked = liked;
        this.notifyPropertyChanged(BR.liked);
    }

    @Override
    public TContent toObject(TagContentRealm tagContentRealm) {
        Gson gson = new Gson();
        this.id = tagContentRealm.getId();
        this.commentCount = tagContentRealm.getCommentCount();
        this.cover = gson.fromJson(tagContentRealm.getCover(),Cover.class);
        this.createTime = tagContentRealm.getCreateTime();
        this.imageCount = tagContentRealm.getImageCount();
        this.imageIds = tagContentRealm.getImageIds();
        this.shortContent = tagContentRealm.getShortContent();
        this.likeCount = tagContentRealm.getLikeCount();
        this.tags = tagContentRealm.getTags();
        this.liked = tagContentRealm.isLiked();
        this.user = gson.fromJson(tagContentRealm.getUser(),User.class);
        return this;
    }

    @Override
    public TagContentRealm toRealmObject() {
        Gson gson = new Gson();
        TagContentRealm tagContentRealm = new TagContentRealm();
        tagContentRealm.setCommentCount(this.commentCount);
        tagContentRealm.setCover(gson.toJson(this.cover));
        tagContentRealm.setCreateTime(this.createTime);
        tagContentRealm.setId(this.id);
        tagContentRealm.setImageCount(this.imageCount);
        tagContentRealm.setImageIds(this.imageIds);
        tagContentRealm.setLikeCount(this.likeCount);
        tagContentRealm.setLiked(this.liked);
        tagContentRealm.setShortContent(this.shortContent);
        tagContentRealm.setTags(this.tags);
        tagContentRealm.setUser(gson.toJson(this.user));
        return tagContentRealm;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
