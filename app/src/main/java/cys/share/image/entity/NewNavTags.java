package cys.share.image.entity;

import java.util.List;

/**
 * Created by 陈尤帅 on 2016/11/22.
 */

public class NewNavTags {

    private List<String> datas;

    private long dataTimes;

    private int count;

    public List<String> getDatas() {
        return datas;
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
    }

    public long getDataTimes() {
        return dataTimes;
    }

    public void setDataTimes(long dataTimes) {
        this.dataTimes = dataTimes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
