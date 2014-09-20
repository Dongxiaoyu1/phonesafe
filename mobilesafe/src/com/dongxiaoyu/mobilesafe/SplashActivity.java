package com.dongxiaoyu.mobilesafe;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.anan.mobilesafe.R;
import com.dongxiaoyu.mobilesafe.domain.UpdateInfo;
import com.dongxiaoyu.mobilesafe.engine.UpdateInfoParser;

public class SplashActivity extends Activity 
{
	private TextView tv_version_textview;
	private UpdateInfo  info;
	
	//��Ϣ������
	private Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg) 
		{
			switch(msg.what)
			{
			case 0:
				showMainUI();
				break;
			case 1:
				Toast.makeText(SplashActivity.this, "���°汾", 0).show();
				//��ʾ�Ի���
				showDownLoad();
				break;
			case 2:
				Toast.makeText(SplashActivity.this, "no xml", 0).show();
				break;
			case 3:
				break;
			
			
			}
		}

		
		
		
		
	};
	
	private void showDownLoad() 
	{
		AlertDialog.Builder  builer = new Builder(this);
		builer.setTitle("�����°汾");
		builer.setMessage(info.getDescription());
		builer.setPositiveButton("����", new OnClickListener() 
		{
			
			@Override
			public void onClick(DialogInterface dialog, int which) 
			{
				// TODO Auto-generated method stub
				//����
				
			}
		}).setNegativeButton("ȡ��", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				showMainUI();
				
			}
		});
		builer.create().show();	
	}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv_version_textview = (TextView) this.findViewById(R.id.splash_version);
        tv_version_textview.setText(getVersion());
        System.out.println(getVersion());
        
        Thread  t = new Thread(new CheckVersionTask());
        t.start();
       
        

    }
    
    //���ӷ�����.�õ�����������
    private class CheckVersionTask implements Runnable
    {

		@Override
		public void run() 
		{
			//�õ���Ϣ����
			Message msg = Message.obtain();
			try 
			{
				URL url = new URL("http://10.0.2.2:8080/version.xml");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				int code = conn.getResponseCode();
				if(code == 200)
				{
					InputStream in = conn.getInputStream();
					info = UpdateInfoParser.getUpdateInfo(in);
					if(info != null)
					{
						if(getVersion().equals(info.getVersion()))
						{
							//�汾��ͬ������Ӧ�ó����������
							msg.what = 0;
						}else
						{
							msg.what =1;
							//��ʾ�û�����
						}
						
					}else
					{
						msg.what=2;
					}
				}else
				{
					//����������
					msg.what = 3;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			handler.sendMessage(msg);
			
		}
    	
    }
    
    //�õ��汾��
    public String getVersion()
    {
    	PackageManager  pm = getPackageManager();
    	String versionname="";
    	try
    	{
    		//�õ������嵥�ļ��Ķ���
			PackageInfo  info = pm.getPackageInfo(getPackageName(), 0);
			versionname= info.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return versionname;
    }
    
    private void showMainUI()
    {
    	Intent intent = new Intent(this,MainActivity.class);
    	this.startActivity(intent);
    	this.finish();
    }

  

}
