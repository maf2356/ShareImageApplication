package cys.share.image.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/12.
 */
public class CommentDatas {

    private List<Comment> datas;

    private long dataTime;

    private int count;

    public List<Comment> getDatas() {
        return datas;
    }

    public void setDatas(List<Comment> datas) {
        this.datas = datas;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
