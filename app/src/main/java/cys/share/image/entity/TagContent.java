package cys.share.image.entity;

import com.google.gson.Gson;

import cys.share.image.entity.realm.BaseBean;
import cys.share.image.entity.realm.TagContentRealm;
import cys.share.image.entity.realm.imp.RealmTransaction;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/10/27.
 */
public class TagContent extends BaseBean implements RealmTransaction<TagContentRealm,TagContent>{

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

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
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

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public TagContent toObject(TagContentRealm tagContentRealm) {
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
}
