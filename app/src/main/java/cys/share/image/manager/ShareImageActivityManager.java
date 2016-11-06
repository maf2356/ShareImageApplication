package cys.share.image.manager;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by Administrator on 2016/11/6.
 */
public class ShareImageActivityManager {

        private static Stack<Activity> activityStack;
        private static ShareImageActivityManager instance;

        private ShareImageActivityManager(){}
        public static ShareImageActivityManager getAppManager(){
            if(instance==null){
                instance=new ShareImageActivityManager();
            }
            return instance;
        }
        public void addActivity(Activity activity){
            if(activityStack==null){
                activityStack=new Stack<Activity>();
            }
            activityStack.add(activity);
        }
        /**
         * 获取当前Activity（堆栈中最后一个压入的）
         */
        public Activity currentActivity(){
            Activity activity=activityStack.lastElement();
            return activity;
        }
        /**
         * 结束当前Activity（堆栈中最后一个压入的）
         */
        public void finishActivity(){
            Activity activity=activityStack.lastElement();
            finishActivity(activity);
        }
        /**
         * 结束指定的Activity
         */
        public void finishActivity(Activity activity){
            if(activity!=null){
                activity.finish();
                activityStack.remove(activity);
                activity=null;
            }
        }
        /**
         * 结束指定类名的Activity
         */
        public void finishActivity(Class<?> cls){
            for (Activity activity : activityStack) {
                if(activity.getClass().equals(cls) ){
                    finishActivity(activity);
                }
            }
        }
        /**
         * 结束所有Activity
         */
        public void finishAllActivity(){
            for (int i = 0, size = activityStack.size(); i < size; i++){
                if (null != activityStack.get(i)){
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }
        public void AppExit(Context context) {
            try {
                finishAllActivity();
                ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                activityMgr.restartPackage(context.getPackageName());
                System.exit(0);
            } catch (Exception e) { }
        }
}
