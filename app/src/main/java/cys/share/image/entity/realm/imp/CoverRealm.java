package cys.share.image.entity.realm.imp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/10/28.
 */

public class CoverRealm extends RealmObject {

    @PrimaryKey
    private int id;

    private String path;

    private String nama;

    private String previewUrl;

    private String small250_440Url;

    private String samllUrl;

    private String middlerUrl;

    private String largeUrl;

    private int width;

    private int height;

    private String md5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getSmall250_440Url() {
        return small250_440Url;
    }

    public void setSmall250_440Url(String small250_440Url) {
        this.small250_440Url = small250_440Url;
    }

    public String getSamllUrl() {
        return samllUrl;
    }

    public void setSamllUrl(String samllUrl) {
        this.samllUrl = samllUrl;
    }

    public String getMiddlerUrl() {
        return middlerUrl;
    }

    public void setMiddlerUrl(String middlerUrl) {
        this.middlerUrl = middlerUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
