package cys.share.image.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2016/11/2.
 */
public class MyUploadImage {
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
