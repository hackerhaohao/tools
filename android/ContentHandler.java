package com.hacker.haohao.http.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {
	
	private String nodeName = null;
	
	@Override
	public void startDocument() throws SAXException {
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		//记录当前节点名称
		nodeName = localName;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)throws SAXException {
		//根据节点名称判断之后处理数据ch
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)throws SAXException {
		
	}
	
	@Override
	public void endDocument() throws SAXException {
	}
}
