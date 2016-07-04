package com.wonders.cmhealth.utils;


public class StringHelper {
	
	/**
	 * Replaces all occurrences of an old String with a new String in the given
	 * String.
	 */
	public static String substituteAll(String s, String o, String n) {
		if (s == null)
			return null;
		while (s.indexOf(o) != -1)
			s = substituteOne(s, o, n);
		return s;
	}
	
	/**
	 * Replaces one occurrence of an old String with a new String in the given
	 * String.
	 */
	public static String substituteOne(String s, String o, String n) {
		if (s == null)
			return null;
		int begin = s.indexOf(o);
		if (begin == -1)
			return s;
		int end = begin + o.length();
		return (new StringBuffer(s)).replace(begin, end, n).toString();
	}
	
	/**
	 * 在指定位置删除字符串中的特定字符
	 * @param source 源字符串
	 * @param posFlag -1在头，0所有位置，1在尾
	 * @param str 要删除的字符
	 * @return
	 */
	public static String removeTheStr(String source,int posFlag,String str){
		String result = null;
		if(posFlag == 0){
			result = source.replaceAll(str, "");	
		}
		StringBuffer sb = new StringBuffer(source);
		if(posFlag == -1){
			while(sb.indexOf(str) == 0){
				sb.delete(0, str.length());
			}
			result = sb.toString();
			
		}
		
		if(posFlag == 1){
			String[] arr = source.split(str);
			if(arr[0] != null){
				result = arr[0];
			}
			
		}if(posFlag == 1){
			sb = sb.reverse();
			StringBuffer strb = new StringBuffer(str);
			strb = strb.reverse();
			while(sb.indexOf(strb.toString()) == 0){
				sb.delete(0, strb.length());
			}
			
			sb.reverse();
			result = sb.toString();
			
		}
		
		return result;
	}
	
	/**
	 * 在结尾删除特定字符
	 * @param source
	 * @param str
	 * @return
	 */
	public static String removeTailStr(String source,String str){
		return removeTheStr(source,1,str);
	}
	
	/**
	 * 删除特定字符
	 * @param source
	 * @param str
	 * @return
	 */
	public static String removeAllStr(String source,String str){
		return removeTheStr(source,0,str);
	}
	
	/**
	 * 在开头删除特定字符
	 * @param source
	 * @param str
	 * @return
	 */
	public static String removeTopStr(String source,String str){
		return removeTheStr(source,-1,str);
	}
	
	/**
	 * 对指定字符串补上特定字符
	 * @param source 源字符串
	 * @param fillstr 增补的字符
	 * @param len 总长度
	 * @return
	 */
	public static String fillStr(String source,String fillstr,int len){
		int p = len -source.length();
		StringBuffer sb = new StringBuffer(len);
		for(int i=0;i<p;i++){
			sb.append(fillstr);
		}
		sb.append(source);
		return sb.toString();
	}
	
	public static String generateRegionLevel(String source){
		StringBuffer sb = new StringBuffer();
		sb.append(source.substring(0,2));
		sb.append("0000000000");
		if(!source.substring(2).equals("0000000000")){
			sb.append("->");
			sb.append(source.substring(0,4));
			sb.append("00000000");
			if(!source.substring(4).equals("00000000")){
				sb.append("->");
				sb.append(source.substring(0,6));
				sb.append("000000");
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		
		System.out.println(removeTopStr("000300010000","0"));
		System.out.println(removeAllStr("000300010000","0"));
		System.out.println(removeTailStr("000300010000","0"));
		
		long a = 1;
		String str = a+"";
		System.out.println(fillStr(str,"0",10));
		System.out.println(generateRegionLevel("310107101000"));
	}

}
