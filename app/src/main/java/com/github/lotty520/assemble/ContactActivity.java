package com.github.lotty520.assemble;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.lotty520.mango.Mango;
import com.lotty520.mango.MangoConfig;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

  public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
  public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
  public final static String ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;

  private final static Uri PHONE_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

  private ListView mListView;

  private ListAdapter mAdapter;

  public static void writeInputSreamToFile(@NonNull InputStream is, @NonNull File file)
      throws IOException {
    if (!file.exists()) {
      throw new FileNotFoundException("File does not exist");
    }
    InputStream inputStream = null;
    OutputStream outputStream = null;
    byte[] buffer = new byte[1024];
    inputStream = new BufferedInputStream(is);
    outputStream = new BufferedOutputStream(new FileOutputStream(file));

    int read;
    while ((read = inputStream.read(buffer)) != -1) {
      outputStream.write(buffer, 0, read);
    }
    outputStream.flush();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact);
    mListView = findViewById(R.id.contactList);
    mAdapter = new ListAdapter(this);
    mListView.setAdapter(mAdapter);
    TextView viewById = findViewById(R.id.tv);
    viewById.setText(Build.FINGERPRINT);
    Mango.init(new MangoConfig.Builder().baseUrl("http://localhost:15611/")
        .initDownloader(true)
        .openLog(true)
        .build());
  }

  public static class ContactInfo {
    String name;
    String phone;

    public ContactInfo(String name, String phone) {
      this.name = name;
      this.phone = phone;
    }
  }

  public static class ListAdapter extends BaseAdapter {
    List<ContactInfo> mContacts = new ArrayList<>();
    Context mContext;

    public ListAdapter(Context context) {
      this.mContext = context;
    }

    public void add(List<ContactInfo> list) {
      mContacts.addAll(list);
      notifyDataSetChanged();
    }

    @Override
    public int getCount() {
      return mContacts.size();
    }

    @Override
    public Object getItem(int position) {
      return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
      return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.contact_item, null, false);
      ContactInfo contactInfo = mContacts.get(position);
      TextView name = view.findViewById(R.id.name);
      TextView phone = view.findViewById(R.id.phone);
      name.setText(contactInfo.name);
      phone.setText(contactInfo.phone);
      return view;
    }
  }
}
