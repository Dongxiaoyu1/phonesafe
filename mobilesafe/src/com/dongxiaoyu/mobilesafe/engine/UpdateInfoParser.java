package com.dongxiaoyu.mobilesafe.engine;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.dongxiaoyu.mobilesafe.domain.UpdateInfo;

/**
 * 更新信息的解析器
 * @author Administrator
 *
 */
public class UpdateInfoParser 
{
	/**
	 * 解析服务器返回的输入流
	 * @param in
	 * @return
	 */
	public static UpdateInfo getUpdateInfo(InputStream in)
	{
		XmlPullParser  parser = Xml.newPullParser();
		try 
		{
			parser.setInput(in, "utf-8");
			int type = parser.getEventType();
			UpdateInfo info = new UpdateInfo();
			while(type != XmlPullParser.END_DOCUMENT)
			{
				switch(type)
				{
				case XmlPullParser.START_TAG:
					if("version".equals(parser.getName()))
					{
						String version = parser.nextText();
						info.setVersion(version);
					}else if("description".equals(parser.getName()))
					{
						String desc = parser.nextText();
						info.setDescription(desc);
					}else if("path".equals(parser.getName()))
					{
						String path = parser.nextText();
						info.setApkurl(path);
					}
					break;
				}
				type = parser.next();
			}
			return info;
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

}
