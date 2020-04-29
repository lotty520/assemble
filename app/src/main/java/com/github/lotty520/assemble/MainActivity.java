package com.github.lotty520.assemble;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

  private WebView mWebView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageView iv = findViewById(R.id.iv);

    mWebView = findViewById(R.id.wv);
    mWebView.setOnTouchListener(this);
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
    drawable.setCircular(true);
    iv.setImageDrawable(drawable);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    Log.e("wh", "x:" + event.getX() + ",y:" + event.getY());
    return false;
  }
}