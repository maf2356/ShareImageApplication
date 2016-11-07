package cys.share.image.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

import cys.share.image.Constant;
import cys.share.image.R;
import cys.share.image.ShareImageApplication;
import cys.share.image.api.ShareImageApi;
import cys.share.image.auxiliary.ShareImageAuxiliaryTool;
import cys.share.image.database.ShareImageRealm;
import cys.share.image.databinding.ActivityLoginBinding;
import cys.share.image.entity.ResponseMessage;
import cys.share.image.entity.User;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/10/29.
 */
public class LoginActivity extends AppCompatActivity{

    ActivityLoginBinding mBinding;
    View mLoginView;
    View mRegisterView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mLoginView = mBinding.loginLayout;
        mRegisterView = mBinding.registerLayout;

    }

    public void login(View view){
        final ProgressDialog registerDialog = ShareImageAuxiliaryTool.createProgressDialog(this,"请稍等","正在登陆中...");
        String account = mBinding.loginAccount.getText().toString();
        String password = mBinding.loginPassword.getText().toString();
        ShareImageApi.login(account,password, new Subscriber<User>() {
            @Override
            public void onCompleted() {
                ((ShareImageApplication)LoginActivity.this.getApplication()).setLogin(true);
                registerDialog.dismiss();
                sendBroadcast(new Intent(Constant.REFRESH_ACTION));
                LoginActivity.this.finish();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                registerDialog.dismiss();
                ShareImageAuxiliaryTool.log(e.getMessage());
            }

            @Override
            public void onNext(User responseMessage) {
                //register succ
                if(responseMessage!=null){
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    ShareImageRealm.getInstance(LoginActivity.this).saveUserInfo(responseMessage);
                    ShareImageAuxiliaryTool.saveToken(LoginActivity.this,responseMessage.getToken());

                }
            }
        });
    }

    public void register(View view){
        final ProgressDialog registerDialog = ShareImageAuxiliaryTool.createProgressDialog(this,"请稍等","正在注册中...");
        String account = mBinding.registerAccount.getText().toString();
        String nickName = mBinding.registerAccount.getText().toString();
        String password = mBinding.registerPassword.getText().toString();
        ShareImageApi.register(account,nickName,password, new Subscriber<User>() {
            @Override
            public void onCompleted() {
                ((ShareImageApplication)LoginActivity.this.getApplication()).setLogin(true);
                registerDialog.dismiss();
                LoginActivity.this.finish();
            }

            @Override
            public void onError(Throwable e) {
                ShareImageAuxiliaryTool.log(e.getMessage());
            }

            @Override
            public void onNext(User responseMessage) {
                //register success
                if(responseMessage!=null){
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    ShareImageRealm.getInstance(LoginActivity.this).saveUserInfo(responseMessage);
                    ShareImageAuxiliaryTool.saveToken(LoginActivity.this,responseMessage.getToken());

                }
            }
        });
    }
    public void _loginOther(View view){

    }
    public void _go2register(View view){
        YoYo.with(Techniques.ZoomOutDown)
                .duration(750)
                .interpolate(new AccelerateDecelerateInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mRegisterView.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.ZoomInDown)
                                .duration(900)
                                .interpolate(new AccelerateDecelerateInterpolator())
                                .playOn(mRegisterView);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        mLoginView.setVisibility(View.GONE);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(mLoginView);
    }
}
