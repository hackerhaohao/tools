package com.hacker.haohao.http.utils;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * xml解析方式
 * @author ZhangHao
 * @version 1.0
 */
public class XmlUtils {
	public static void praseXmlWithPull(final String xml){
		try {
			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
			xmlPullParser.setInput(new StringReader(xml));
			int eventType = xmlPullParser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				String nodeName = xmlPullParser.getName();
				switch (eventType) {
				//开始解析某一个节点
				case XmlPullParser.START_TAG:
					//处理逻辑如果，目标节点名称
					String goalNodeName = "目标节点名称";
					if(goalNodeName.equals(nodeName)){
						//则使用xmlPullParser.nextText();方法就可以得到该nodeName的值
						goalNodeName = xmlPullParser.nextText();
					}
					break;	
				//结束解析某一个节点
				case XmlPullParser.END_TAG:
					//结束处理逻辑。
					break;
				default:
					break;
				}
				eventType = xmlPullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
