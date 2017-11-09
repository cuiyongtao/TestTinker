package com.test.TestTinker;

import android.app.Application;

import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

/**
 * 作者： Victory
 * 创建时间：  2017/11/8
 * 邮箱：cuiyongtao520@163.com
 * QQ：949021037
 * 说明：
 */

public class MyApplication extends Application {
    private ApplicationLike applicationLike;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.TINKER_ENABLE) {
            // 我们可以从这里获得Tinker加载过程的信息
            applicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();
            // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
            TinkerPatch.init(applicationLike)
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true);
            // 每隔3个小时（通过setFetchPatchIntervalByHours设置）去访问后台时候有更新,通过handler实现轮训的效果
            TinkerPatch.with().setFetchPatchIntervalByHours(3).fetchPatchUpdateAndPollWithInterval();
        }
    }
}
