package cys.share.image.entity.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/11/2.
 *
 * {"id":54,
 * "path":"timeline/20161102/5713c6eddba2112b.png",
 * "name":"未命名.png",
 * "previewUrl":"http://cdn.tu42.com/timeline/20161102/5713c6eddba2112b.png!small?auth_key=1478957433-0-0-12bab07a57b1f6ddcb2e294a8ec7fe7e",
 * "small250_440Url":"http://cdn.tu42.com/timeline/20161102/5713c6eddba2112b.png!250_440?auth_key=1478957433-0-0-2c2116b61a3b1e80a7db46ba5fbc8433",
 * "smallUrl":"http://cdn.tu42.com/timeline/20161102/5713c6eddba2112b.png!w400?auth_key=1478957433-0-0-5f5d17a9d39531d53eeabf743bc2ae8e",
 * "middleUrl":"http://cdn.tu42.com/timeline/20161102/5713c6eddba2112b.png!w750?auth_key=1478957433-0-0-37c0fcf195140e6f435a7b5b60ec7521",
 * "largeUrl":"http://cdn.tu42.com/timeline/20161102/5713c6eddba2112b.png!w1920?auth_key=1478957433-0-0-055ca8cfeb0e6a8272435a59ecc0db11",
 * "width":500,"height":300
 * ,"md5":"5713c6eddba2112b"
 * }
 */
public class MyUploadImageRealm extends RealmObject{

    @PrimaryKey
    private int id;

    private String path;

    private String name;

    private String previewUrl;

    private String samll250_440Url;

    private String smallUrl;

    private String middleUrl;

    private String largeUrl;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getSamll250_440Url() {
        return samll250_440Url;
    }

    public void setSamll250_440Url(String samll250_440Url) {
        this.samll250_440Url = samll250_440Url;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getMiddleUrl() {
        return middleUrl;
    }

    public void setMiddleUrl(String middleUrl) {
        this.middleUrl = middleUrl;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
