package com.ckkj.router.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ckkj.router.aidl.IMobile;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lotty
 */
public class BinderService extends Service {

    private CopyOnWriteArrayList<String> mMobiles = new CopyOnWriteArrayList<>();

    private IBinder binder = new IMobile.Stub() {
        @Override
        public List<String> fetchMobileList() throws RemoteException {
            Log.e("wh", "server::fetchMobileList called");
            return mMobiles;
        }

        @Override
        public void createMobile(String mobile) throws RemoteException {
            Log.e("wh", "server::createMobile called");
            mMobiles.add(mobile);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
//            checkCallingOrSelfPermission("");  //权限检查
//            getPackageManager().getPackagesForUid(getCallingUid()); // 包名检查
            return super.onTransact(code, data, reply, flags);
        }
    };

    /**
     * 通过start或者bind方式启动都会调用该回调
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mMobiles.add("小米");
        mMobiles.add("华为");
        mMobiles.add("OPPO");
        mMobiles.add("VIVO");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
