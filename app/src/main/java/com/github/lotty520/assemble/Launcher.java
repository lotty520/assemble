package com.ckkj.router;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ckkj.router.utils.PermissionUtil;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author lotty
 */
public class Launcher extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {

    private static final int DELAY_TIME = 1500;


    private static final int RC_PERMISSION = 245;
    private static final String[] PERMISSIONS =
            {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (PermissionUtil.hasPermission(this, PERMISSIONS)) {
            jump();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).setTitle(R.string.permission_ask_title).setRationale(R.string.permission_ask_again).build().show();
        }
    }

    private void jump() {
        startActivity(new Intent(Launcher.this, ContactActivity.class));
        Launcher.this.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            requestAllPermission();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestAllPermission();
        //fix sometime statusBar and navigationBar toggle in
        autoFullScreen();
        if (PermissionUtil.hasPermission(this, PERMISSIONS)) {
            new Handler(getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    jump();
                }
            }, DELAY_TIME);
        }
    }

    private void autoFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_PERMISSION)
    public void requestAllPermission() {
        if (!PermissionUtil.hasPermission(this, PERMISSIONS)) {
            EasyPermissions.requestPermissions(
                    this, getString(R.string.permission_ask), RC_PERMISSION, PERMISSIONS);
        }
    }
}
