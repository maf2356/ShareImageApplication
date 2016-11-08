package cys.share.image.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;

import cys.share.image.Constant;
import cys.share.image.R;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.databinding.ActivityModifyBinding;
import cys.share.image.entity.ResponseMessage;
import cys.share.image.entity.User;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ShareImageUserModifyActivity extends BaseActivity {

    private ActivityModifyBinding mBinding;

    private User user;

    private int flag;

    private String mModifyContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_modify);
        user = getUser();
        mBinding.setUser(user);
        mBinding.toolbar.inflateMenu(R.menu.menu_user_modity);
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.action_save){
                    submitUserInfo();
                }
                return false;
            }
        });

        flag = getIntent().getIntExtra(Constant.MODIFY,0);
        switch (flag){
            case Constant.MODIFY_NICKNAME:
                mModifyContent = user.getNickName();
                mBinding.etModify.setFloatingLabelText(getResources().getText(R.string.modify_user_nick_text));
                mBinding.etModify.setHint(R.string.modify_user_nick_text);
                mBinding.toolbar.setTitle(R.string.modify_user_nick_title);
                break;
            case Constant.MODIFY_PASSWORD:
                mBinding.etModify.setFloatingLabelText(getResources().getText(R.string.modify_user_password_text));
                mBinding.toolbar.setTitle(R.string.modify_user_password_title);
                mBinding.etModify.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mBinding.etModify.setHint(R.string.modify_user_password_text);
                break;
        }
        mBinding.etModify.setText(mModifyContent);


    }

    private void submitUserInfo(){
        if(flag == Constant.MODIFY_NICKNAME){
            modifyUserNick();
        }else if(flag == Constant.MODIFY_PASSWORD){
            modifyUserPassword();
        }
    }

    private void modifyUserNick(){
        String nickName = mBinding.etModify.getText().toString();
        if(TextUtils.isEmpty(nickName)){
            mBinding.etModify.setError(getResources().getString(R.string.modify_user_nick_error_null));
            return;
        }
        if(nickName.equals(user.getNickName())){
            return;
        }
        final ProgressDialog dialog = ShareImageAuxiliaryTool.createProgressDialog(this,"请稍等","正在提交...");
        ShareImageApi.modifyUserNick(user.getToken(), nickName, new Subscriber<User>() {
            @Override
            public void onCompleted() {
                dialog.dismiss();
                setResult(Constant.MODIFY_NICKNAME);
                ShareImageUserModifyActivity.this.finish();
            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
            }

            @Override
            public void onNext(User user) {
                ShareImageRealm.getInstance(ShareImageUserModifyActivity.this).saveUserInfo(user);
            }
        });
    }


    private void modifyUserPassword(){
        String password = mBinding.etModify.getText().toString();
        if(TextUtils.isEmpty(password)){
            mBinding.etModify.setError(getResources().getString(R.string.modify_user_nick_error_null));
            return;
        }
        if(password.equals(user.getNickName())){
            return;
        }
        final ProgressDialog dialog = ShareImageAuxiliaryTool.createProgressDialog(this,"请稍等","正在提交...");
        ShareImageApi.modifyUserPassword(user.getToken(), password, new Subscriber<ResponseMessage>() {
            @Override
            public void onCompleted() {
                dialog.dismiss();
                setResult(Constant.MODIFY_PASSWORD);
                ShareImageUserModifyActivity.this.finish();
            }

            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
            }

            @Override
            public void onNext(ResponseMessage msg) {
                ShareImageAuxiliaryTool.log(msg.getMessage());
            }
        });
    }
}
