package com.miyin.klg.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.miyin.klg.R;
import com.miyin.klg.base.BaseActivity;
import com.miyin.klg.util.Constants;
import com.miyin.klg.util.ConstantsURL;
import com.miyin.klg.util.HttpUtil;
import com.miyin.klg.util.SpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/*
*引导页
 */
public class SplashActivity extends BaseActivity {
    private final String TAG= getClass().getSimpleName();
    RelativeLayout activitySplash;
    /**
     * 更新新版本的状态码
     */
    protected static final int UPDATE_VERSION = 100;
    /**
     * 进入应用程序主界面状态码
     */
    protected static final int ENTER_HOME = 101;

    /**
     * url地址出错状态码
     */
    protected static final int URL_ERROR = 102;
    protected static final int IO_ERROR = 103;
    protected static final int JSON_ERROR = 104;

    private int mLocalVersionCode;
    private String downloadUrl;
    private String versionName;
    private String versionCode;
    private String versionDes;
    @Override
    public int setLayout() {

        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        activitySplash= $(R.id.activity_splash);

    }

    @Override
    public void initDate() {
        //版本检查及更新
        mLocalVersionCode = getVersionCode();


        RotateAnimation animRotate=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animRotate.setDuration(1000);//动画时间
        animRotate.setFillAfter(true);//保持动画结束的状态

        //缩放动画
        ScaleAnimation animScale=new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);//保持动画结束的状态

        //渐变动画
        AlphaAnimation animAlpha=new AlphaAnimation(0,1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        //动画集合
        AnimationSet animationSet=new AnimationSet(true);
        animationSet.addAnimation(animRotate);
        animationSet.addAnimation(animScale);
        animationSet.addAnimation(animAlpha);


        activitySplash.startAnimation(animationSet);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
               // Log.i(TAG,"动画开始onAnimationStart");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
               // Log.i(TAG,"动画重置onAnimationRepeat");
            }
            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {
                // 获得版本号
                checkVersion();
            }
        });
    }



    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_VERSION:
                    // 弹出对话框,提示用户更新
                    showUpdateDialog();
                    break;

                case URL_ERROR:
                    showToast("url异常");
                    enterHome();

                    break;

                case IO_ERROR:
                    showToast("读取异常");
                    enterHome();

                    break;

                case JSON_ERROR:
                    showToast( "JSON解析异常");

                    enterHome();

                    break;

                case ENTER_HOME:
                    enterHome();
                    break;

                default:
                    break;
            }
        }
    };


    protected void showUpdateDialog() {
        // 对话框,是依赖于activity存在的
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置左上角图标
        builder.setIcon(R.mipmap.ico);
        builder.setTitle("版本更新");
        // 设置描述内容
        builder.setMessage(versionDes);

        // 积极按钮,立即更新
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 下载apk,apk链接地址,downloadUrl
                downloadApk();
            }
        });

        builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 取消对话框,进入主界面
                enterHome();
            }
        });

        // 点击取消事件监听
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // 即使用户点击取消,也需要让其进入应用程序主界面
                enterHome();
                dialog.dismiss();
            }
        });

        builder.show();
    }

    protected void downloadApk() {
        // apk下载链接地址,放置apk的所在路径

        // 1,判断sd卡是否可用,是否挂在上
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            // 2,获取sd路径
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                    + "clg.apk";
            // 3,发送请求,获取apk,并且放置到指定路径
            HttpUtils httpUtils = new HttpUtils();
            // 4,发送请求,传递参数(下载地址,下载应用放置位置)
            httpUtils.download(downloadUrl, path, new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    // 下载成功(下载过后的放置在sd卡中apk)
                    Log.i(TAG, "下载成功");
                    File file = responseInfo.result;
                    // 提示用户安装
                    installApk(file);
                }

                @Override
                public void onFailure(HttpException arg0, String arg1) {
                    Log.i(TAG, "下载失败");
                    // 下载失败
                }

                // 刚刚开始下载方法
                @Override
                public void onStart() {
                    Log.i(TAG, "刚刚开始下载");
                    super.onStart();
                }

                // 下载过程中的方法(下载apk总大小,当前的下载位置,是否正在下载)
                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    Log.i(TAG, "下载中........");
                    Log.i(TAG, "total = " + total);
                    Log.i(TAG, "current = " + current);
                    super.onLoading(total, current, isUploading);
                }
            });

        }
    }

    protected void installApk(File file) {
        // 系统应用界面,源码,安装apk入口
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
		/*
		 * //文件作为数据源 intent.setData(Uri.fromFile(file)); //设置安装的类型
		 * intent.setType("application/vnd.android.package-archive");
		 */
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
       // ACTION_PACKAGE_REPLACED(ACTION_PACKAGE_REPLACED);
    }

    protected void enterHome() {
        // 动画结束,跳转页面
        // 如果是第一次进入, 跳新手引导
        // 否则跳主页面
        boolean isFirstEnter = SpUtil.getBoolean(SplashActivity.this, Constants.IS_FIRST_ENTER, true);
        Bundle bundle = new Bundle();
        if (isFirstEnter){
            // 新手引导
//                    intent = new Intent(getApplicationContext(),
//                            LoginActivity.class);

            bundle.putInt("type", 1);

        }
        else {
            // 主页面
//                    intent = new Intent(getApplicationContext(),
//                            LoginActivity.class);
            bundle.putInt("type", 1);
        }
        openActivity(HomeActivity.class, bundle);
        finish();// 结束当前页面
    }


    private void checkVersion() {
        new Thread() {
            public void run() {
                // 发送请求获取数据,参数则为请求json的链接地址
                Message msg = Message.obtain();
                try {
                       String json=  HttpUtil.post(ConstantsURL.USER_VERSION_URL,new String[]{"isAndroid"},new String[]{"1"});
                        if (!TextUtils.isEmpty(json)&&json.indexOf("status")!=-1) {
                            try {
                                // json解析
                                JSONObject jsondata = new JSONObject(json);
                                JSONObject jsonObject=jsondata.getJSONObject("data");
                                downloadUrl = jsonObject.getString("downLoad");
                                versionName = jsonObject.getString("version");
                                versionDes = jsonObject.getString("versionText");
                                versionCode = jsonObject.getString("build");
                                if (Integer.parseInt(versionCode) > mLocalVersionCode)
                                    msg.what = UPDATE_VERSION;
                                else // 版本一致进入页面
                                    msg.what = ENTER_HOME;
                            } catch (JSONException e) {
                                e.printStackTrace();
                                msg.what = JSON_ERROR;
                            }

                        }
                    else
                            msg.what = ENTER_HOME;

                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = URL_ERROR;
                } finally {

                    mHandler.sendMessage(msg);
                }
            };

        }.start();
    }

    /**
     * 返回版本号
     *
     * @return 非-1 则代表获取成功
     */
    private int getVersionCode() {
        // 包管理者对象packageManager
        PackageManager pm = getPackageManager();
        // 从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            // 获取版本名称
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取版本名称:清单文件中
     *
     * @return 应用版本名称 返回null代表异常
     */
    private String getVersionName() {
        // 包管理者对象packageManager
        PackageManager pm = getPackageManager();
        // 从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            // 获取版本名称
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
