package cys.share.image.adapter;

import android.databinding.BaseObservable;

import java.util.Objects;

/**
 * Created by Administrator on 2016/11/6.
 */
public class BindableString extends BaseObservable{
    private String value;
    public String get() {
        return value != null ? value : "";
    }
    public void set(String value) {
        if (!Objects.equals(this.value, value)) {
            this.value = value;
            notifyChange();
        }
    }
    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}
