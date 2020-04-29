package com.ckkj.router;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ckkj.router.utils.PackageUtils;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tongdun.android.shell.FMAgent;
import cn.tongdun.android.shell.inter.FMCallback;

public class ContactActivity extends AppCompatActivity {

    public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    public final static String ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;

    private final static Uri PHONE_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;


    private ListView mListView;

    private ListAdapter mAdapter;

    public static void writeInputSreamToFile(@NonNull InputStream is, @NonNull File file) throws IOException {
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
        Mango.init(new MangoConfig.Builder().baseUrl("http://localhost:15611/").initDownloader(true).openLog(true).build());

    }

//    public void fetch(View view) {

//        StringClient.get("http://10.57.242.166:15611/xapp/action/download.json", new Callback<String>() {
//            @Override
//            public void onError(Throwable t, String msg) {
//                Log.e("wh", msg);
//            }
//
//            @Override
//            public void onSuccess(String result) {
//                Log.e("wh", "成功.");
//                if (!TextUtils.isEmpty(result)) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(result);
//                        String data = jsonObject.getString("data");
//                        byte[] decode = Base64.decode(data, Base64.DEFAULT);
//                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "xapp.apk";
//                        File file = new File(path);
//                        if (!file.exists()) {
//                            file.createNewFile();
//                        }
//                        FileOutputStream fileOutputStream = new FileOutputStream(file);
//                        fileOutputStream.write(decode);
//                        fileOutputStream.flush();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

//        FileClient.get("http://10.57.242.166:15611/xapp/action/download.json", new Callback<InputStream>() {
//            @Override
//            public void onError(Throwable t, String msg) {
//                Log.e("wh", msg);
//            }
//
//            @Override
//            public void onSuccess(InputStream result) {
//                Log.e("wh", "请求成功");
//                File targetFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "xapp.apk");
//                if (!targetFile.exists()) {
//                    try {
//                        targetFile.createNewFile();
//                        writeInputSreamToFile(result, targetFile);
//                        Log.e("wh", "保存成功");
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

//    }

    public void fetch(View view) {
        long l = System.currentTimeMillis();
        FMAgent.initWithCallback(this, FMAgent.ENV_SANDBOX, new FMCallback() {
            @Override
            public void onEvent(String blackbox) {
                // 注意这里不是主线程 请不要在这个函数里进行ui操作，否则可能会出现崩溃
                Log.e("wh", "finish");
            }
        });
        Log.e("wh", "耗时:" + (System.currentTimeMillis() - l));

//        List<InstalledPackageInfo> installedPackageInfos = InstalledPackageInfo.collectInfo(this);
//        JSONArray jsonArray = new JSONArray();
//        for (int i = 0; i < installedPackageInfos.size(); i++) {
//            jsonArray.put(installedPackageInfos.get(i).toJson());
//        }
//        Log.e("wh","length:" + jsonArray.length());
//        Map<String,Object> pa = new HashMap<>(1);
//        pa.put("pkgList",jsonArray.toString());
//        StringClient.post("http://10.57.243.75:8080/app/list",pa,null);

        Map<String, String> installedPackages = PackageUtils.installedPackages(this);
        installedPackages.size();


//        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)) {
//            List<ContactInfo> contacts = fetchContacts(this);
//            mAdapter.add(contacts);
//        } else {
//            //grant permissions
//        }
    }

    @RequiresPermission(Manifest.permission.READ_CONTACTS)
    public List<ContactInfo> fetchContacts(Context context) {
        // 联系人信息
        List<ContactInfo> phoneList = new ArrayList<>();
        // 根据ID去重
        Set<Integer> idSet = new HashSet<>();

        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(PHONE_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(NAME));
            //取得联系人ID
            int contactId = cursor.getInt(cursor.getColumnIndex(ID));
            if (!idSet.contains(contactId)) {
                Cursor phoneCursor = cr.query(PHONE_URI, null, ID + " = ?", new String[]{String.valueOf(contactId)}, null);
                String phoneNumber = "";
                while (phoneCursor.moveToNext()) {
                    String phone = phoneCursor.getString(phoneCursor.getColumnIndex(NUM));
                    //格式化手机号
                    phone = phone.replace(" ", "");
                    //多个手机号用逗号分隔
                    phoneNumber = phoneNumber + "," + phone;
                }
                if (!TextUtils.isDigitsOnly(phoneNumber)) {
                    phoneNumber = phoneNumber.substring(1);
                }
                ContactInfo phoneDto = new ContactInfo(name, phoneNumber);
                phoneList.add(phoneDto);
                idSet.add(contactId);
            }

        }
        return phoneList;

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
