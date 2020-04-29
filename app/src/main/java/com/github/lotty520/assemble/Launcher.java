package com.github.lotty520.assemble;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author lotty
 */
public class Launcher extends AppCompatActivity {

  private static final int DELAY_TIME = 1500;

  private static final int RC_PERMISSION = 245;
  private static final String[] PERMISSIONS =
      {
          Manifest.permission.WRITE_EXTERNAL_STORAGE,
          Manifest.permission.READ_CONTACTS,
          Manifest.permission.READ_PHONE_STATE,
          Manifest.permission.BLUETOOTH,
          Manifest.permission.ACCESS_FINE_LOCATION
      };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_launcher);
  }

  private void jump() {
    startActivity(new Intent(Launcher.this, ContactActivity.class));
    Launcher.this.finish();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  protected void onResume() {
    super.onResume();
    //fix sometime statusBar and navigationBar toggle in
    autoFullScreen();
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
  public void onRequestPermissionsResult(int requestCode, String[] permissions,
      int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }
}
