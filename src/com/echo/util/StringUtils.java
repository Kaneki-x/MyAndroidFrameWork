package com.echo.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 字符串处理工具类
 * @author ziyuzhang
 *
 */
public class StringUtils {

	public StringUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 得到字符串中某个字符的个数
	 * @param str  字符串
	 * @param c 字符
	 * @return
	 */
	public static final int getCharnum(String str,char c){
		int num = 0;
		char[] chars = str.toCharArray();
		for(int i = 0; i < chars.length; i++)
		{
			if(c == chars[i])
			{
				num++;
			}
		} 

		return num;
	}


	/**
	 * 返回yyyymm
	 * @param aDate
	 * @return
	 */
	public static final String getYear_Month(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat("yyyyMM");
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}
	
	/**
	 * hxw 返回当前年
	 * 
	 * @return
	 */
	public static String getYear() {
		Calendar calendar = Calendar.getInstance();
		return String.valueOf(calendar.get(1));
	}

	/**
	 * hxw 返回当前月
	 * 
	 * @return
	 */
	public static String getMonth() {
		Calendar calendar = Calendar.getInstance();
		String temp = String.valueOf(calendar.get(2) + 1);
		if (temp.length() < 2)
			temp = "0" + temp;
		return temp;
	}


	/**
	 * 按长度分割字符串
	 * 
	 * @param content
	 * @param len
	 * @return
	 */
	public static String[] split(String content, int len) {
		if (content == null || content.equals("")) {
			return null;
		}
		int len2 = content.length();
		if (len2 <= len) {
			return new String[] { content };
		} else {
			int i = len2 / len + 1;
			System.out.println("i:" + i);
			String[] strA = new String[i];
			int j = 0;
			int begin = 0;
			int end = 0;
			while (j < i) {
				begin = j * len;
				end = (j + 1) * len;
				if (end > len2)
					end = len2;
				strA[j] = content.substring(begin, end);
				// System.out.println(strA[j]+"<br/>");
				j = j + 1;
			}
			return strA;
		}
	}

	/**
	 * 验证是不是EMAIL
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		boolean retval = false;
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";   
		Pattern regex = Pattern.compile(check);   
		Matcher matcher = regex.matcher(email);   
		retval = matcher.matches();  
		return retval;
	}

	/**
	 * 验证汉字为true 
	 * @param s
	 * @return
	 */
	public static boolean isLetterorDigit(String s) {
		if (s.equals("") || s == null) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				// if (!Character.isLetter(s.charAt(i))){
				return false;
			}
		}
		// Character.isJavaLetter()
		return true;
	}
	/**
	 * 判断某字符串是否为null，如果长度大于256，则返回256长度的子字符串，反之返回原串
	 * @param str
	 * @return
	 */
	public static String checkStr(String str){
		if(str==null){
			return "";
		}else if(str.length()>256){
			return str.substring(256);
		}else{
			return str;
		}
	}

	/**
	 * 验证是不是Int
	 * validate a int string   
	 * @param str
	 * the Integer string.
	 * @return true if the str is invalid otherwise false.
	 */
	public static boolean validateInt(String str) {
		if (str == null || str.trim().equals(""))
			return false;

		char c;
		for (int i = 0, l = str.length(); i < l; i++) {
			c = str.charAt(i);
			if (!((c >= '0') && (c <= '9')))
				return false;
		}

		return true;
	}
	/**
	 * 字节码转换成16进制字符串 内部调试使用
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	/**
	 * 字节码转换成自定义字符串 内部调试使用
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2string(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	public static byte[] string2byte(String hs) {
		byte[] b = new byte[hs.length() / 2];
		for (int i = 0; i < hs.length(); i = i + 2) {
			String sub = hs.substring(i, i + 2);
			byte bb = new Integer(Integer.parseInt(sub, 16)).byteValue();
			b[i / 2] = bb;
		}
		return b;
	}

	/**
	 * 验证字符串是否为空
	 * @param param
	 * @return
	 */
	public static boolean empty(String param) {
		return param == null || param.trim().length() < 1;
	}

	// 验证英文字母或数据
	public static boolean isLetterOrDigit(String str) {
		java.util.regex.Pattern p = null; // 正则表达式
		java.util.regex.Matcher m = null; // 操作的字符串
		boolean value = true;
		try {
			p = java.util.regex.Pattern.compile("[^0-9A-Za-z]");
			m = p.matcher(str);
			if (m.find()) {

				value = false;
			}
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 验证是否是小写字符串
	 */
	@SuppressWarnings("unused")
	private static boolean isLowerLetter(String str) {
		java.util.regex.Pattern p = null; // 正则表达式
		java.util.regex.Matcher m = null; // 操作的字符串
		boolean value = true;
		try {
			p = java.util.regex.Pattern.compile("[^a-z]");
			m = p.matcher(str);
			if (m.find()) {
				value = false;
			}
		} catch (Exception e) {
		}
		return value;
	}


	/**
	 * 判断一个字符串是否都为数字
	 * @param strNum
	 * @return
	 */
	public static boolean isDigit(String strNum) {
		return strNum.matches("[0-9]{1,}");
	}

	/**
	 * 判断一个字符串是否都为数字
	 * @param strNum
	 * @return
	 */
	public static boolean isDigit2(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher((CharSequence) strNum);
		return matcher.matches();
	}

	/**
	 * 截取数字
	 * @param content
	 * @return
	 */
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	/**
	 * 截取非数字
	 * @param content
	 * @return
	 */
	public static String splitNotNumber(String content) {
		Pattern pattern = Pattern.compile("\\D+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	public static String encode(String str, String code) {
		try {
			return URLEncoder.encode(str, code);
		} catch (Exception ex) {
			ex.fillInStackTrace();
			return "";
		}
	}

	public static String decode(String str, String code) {
		try {
			return URLDecoder.decode(str, code);
		} catch (Exception ex) {
			ex.fillInStackTrace();
			return "";
		}
	}

	public static String nvl(String param) {
		return param != null ? param.trim() : "";
	}

	public static int parseInt(String param, int d) {
		int i = d;
		try {
			i = Integer.parseInt(param);
		} catch (Exception e) {
		}
		return i;
	}

	public static int parseInt(String param) {
		return parseInt(param, 0);
	}

	public static long parseLong(String param) {
		long l = 0L;
		try {
			l = Long.parseLong(param);
		} catch (Exception e) {
		}
		return l;
	}

	public static double parseDouble(String param) {
		return parseDouble(param, 1.0);
	}

	public static double parseDouble(String param, double t) {
		double tmp = 0.0;
		try {
			tmp = Double.parseDouble(param.trim());
		} catch (Exception e) {
			tmp = t;
		}
		return tmp;
	}

	public static boolean parseBoolean(String param) {
		if (empty(param))
			return false;
		switch (param.charAt(0)) {
		case 49: // '1'
		case 84: // 'T'
		case 89: // 'Y'
		case 116: // 't'
		case 121: // 'y'
			return true;

		}
		return false;
	}

	/**
	 * public static String replace(String mainString, String oldString, String
	 * newString) { if(mainString == null) return null; int i =
	 * mainString.lastIndexOf(oldString); if(i < 0) return mainString;
	 * StringBuffer mainSb = new StringBuffer(mainString); for(; i >= 0; i =
	 * mainString.lastIndexOf(oldString, i - 1)) mainSb.replace(i, i +
	 * oldString.length(), newString);
	 * 
	 * return mainSb.toString(); }
	 * 
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final String[] split(String str, String delims) {
		StringTokenizer st = new StringTokenizer(str, delims);
		ArrayList list = new ArrayList();
		for (; st.hasMoreTokens(); list.add(st.nextToken()))
			;
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String substring(String str, int length) {
		if (str == null)
			return null;

		if (str.length() > length)
			return (str.substring(0, length - 2) + "...");
		else
			return str;
	}

	public static boolean validateDouble(String str) throws RuntimeException {
		if (str == null)
			// throw new RuntimeException("null input");
			return false;
		char c;
		int k = 0;
		for (int i = 0, l = str.length(); i < l; i++) {
			c = str.charAt(i);
			if (!((c >= '0') && (c <= '9')))
				if (!(i == 0 && (c == '-' || c == '+')))
					if (!(c == '.' && i < l - 1 && k < 1))
						// throw new RuntimeException("invalid number");
						return false;
					else
						k++;
		}
		return true;
	}

	public static boolean validateMobile(String str, boolean includeUnicom) {
		if (str == null || str.trim().equals(""))
			return false;
		str = str.trim();

		if (str.length() != 11 || !validateInt(str))
			return false;

		if (includeUnicom
				&& (str.startsWith("130") || str.startsWith("133") || str.startsWith("131")))
			return true;

		if (!(str.startsWith("139") || str.startsWith("138") || str.startsWith("137")
				|| str.startsWith("136") || str.startsWith("135")))
			return false;
		return true;
	}

	public static boolean validateMobile(String str) {
		return validateMobile(str, false);
	}

	public static String gbToIso(String s) throws UnsupportedEncodingException {
		return covertCode(s, "GB2312", "ISO8859-1");
	}

	public static String covertCode(String s, String code1, String code2)
			throws UnsupportedEncodingException {
		if (s == null)
			return null;
		else if (s.trim().equals(""))
			return "";
		else
			return new String(s.getBytes(code1), code2);
	}

	public static String transCode(String s0) throws UnsupportedEncodingException {
		if (s0 == null || s0.trim().equals(""))
			return null;
		else {
			s0 = s0.trim();
			return new String(s0.getBytes("GBK"), "ISO8859-1");
		}
	}
	/**
	 * 编码转换
	 * @param s
	 * @return
	 */
	public static String GBToUTF8(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2) {
				out.append("\\u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				out.append(str);
				String str1 = Integer.toHexString(bytes[i] & 0xff);
				for (int j = str1.length(); j < 2; j++) {
					out.append("0");
				}

				out.append(str1);
			}
			return out.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unused")
	public static final String[] replaceAll(String[] obj, String oldString, String newString) {
		if (obj == null) {
			return null;
		}
		int length = obj.length;
		String[] returnStr = new String[length];
		String str = null;
		for (int i = 0; i < length; i++) {
			returnStr[i] = replaceAll(obj[i], oldString, newString);
		}
		return returnStr;
	}
	
	/**
	 * 字符串全文替换
	 * @param s0
	 * @param oldstr
	 * @param newstr
	 * @return
	 */
	public static String replaceAll(String s0, String oldstr, String newstr) {
		if (s0 == null || s0.trim().equals(""))
			return null;
		StringBuffer sb = new StringBuffer(s0);
		int begin = 0;
		// int from = 0;
		begin = s0.indexOf(oldstr);
		while (begin > -1) {
			sb = sb.replace(begin, begin + oldstr.length(), newstr);
			s0 = sb.toString();
			begin = s0.indexOf(oldstr, begin + newstr.length());
		}
		return s0;
	}

	public static String toHtml(String str) {
		String html = str;
		if (str == null || str.length() == 0) {
			return str;
		}
		html = replaceAll(html, "&", "&amp;");
		html = replaceAll(html, "<", "&lt;");
		html = replaceAll(html, ">", "&gt;");
		html = replaceAll(html, "\r\n", "\n");
		html = replaceAll(html, "\n", "<br>\n");
		html = replaceAll(html, "\t", "         ");
		html = replaceAll(html, " ", "&nbsp;");
		return html;
	}

	/**
	 * 字符串替换
	 * @param line
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static final String replace(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static final String replaceIgnoreCase(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 标签转义
	 * @param input
	 * @return
	 */
	public static final String escapeHTMLTags(String input) {
		// Check if the string is null or zero length -- if so, return
		// what was sent in.
		if (input == null || input.length() == 0) {
			return input;
		}
		// Use a StringBuffer in lieu of String concatenation -- it is
		// much more efficient this way.
		StringBuffer buf = new StringBuffer(input.length());
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '<') {
				buf.append("&lt;");
			} else if (ch == '>') {
				buf.append("&gt;");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	/**
	 * Returns a random String of numbers and letters of the specified length.
	 * The method uses the Random class that is built-in to Java which is
	 * suitable for low to medium grade security uses. This means that the
	 * output is only pseudo random, i.e., each number is mathematically
	 * generated so is not truly random.
	 * <p>
	 * 
	 * For every character in the returned String, there is an equal chance that
	 * it will be a letter or number. If a letter, there is an equal chance that
	 * it will be lower or upper case.
	 * <p>
	 * 
	 * The specified length must be at least one. If not, the method will return
	 * null.
	 * 
	 * @param length
	 *            the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */

	private static Random randGen = null;
	private static Object initLock = new Object();
	private static char[] numbersAndLetters = null;

	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Init of pseudo random number generator.
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					// Also initialize the numbersAndLetters array
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
				}
			}
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * 固定长度字符串截取
	 * @param content
	 * @param i
	 * @return
	 */
	public static String getSubstring(String content, int i) {
		int varsize = 10;
		String strContent = content;
		if (strContent.length() < varsize + 1) {
			return strContent;
		} else {
			int max = (int) Math.ceil((double) strContent.length() / varsize);
			if (i < max - 1) {
				return strContent.substring(i * varsize, (i + 1) * varsize);
			} else {
				return strContent.substring(i * varsize);
			}
		}
	}

	/**
	 * 日期转String
	 * @param pattern
	 * @return
	 */
	public static String now(String pattern) {
		return dateToString(new Date(), pattern);
	}

	public static String dateToString(Date date, String pattern) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	public static synchronized String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

	/**
	 * String转Date
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date stringToDate(String strDate, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse(strDate);
		long lngTime = date.getTime();
		return new java.sql.Date(lngTime);
	}

	public static java.util.Date stringToUtilDate(String strDate, String pattern)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(strDate);
	}

	public static String formatHTMLOutput(String s) {
		if (s == null)
			return null;

		if (s.trim().equals(""))
			return "";

		String formatStr;
		formatStr = replaceAll(s, " ", "&nbsp;");
		formatStr = replaceAll(formatStr, "\n", "<br />");

		return formatStr;
	}

	/*
	 * 4舍5入 @param num 要调整的数 @param x 要保留的小数位
	 */
	public static double myround(double num, int x) {
		BigDecimal b = new BigDecimal(num);
		return b.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/*
	 * public static String getSubstring(String content,int i){ int varsize=10;
	 * String strContent=content; if(strContent.length()<varsize+1){ return
	 * strContent; }else{ int
	 * max=(int)Math.ceil((double)strContent.length()/varsize); if(i<max-1){
	 * return strContent.substring(i*varsize,(i+1)*varsize); }else{ return
	 * strContent.substring(i*varsize); } } }
	 */

	/**
	 * liuqs
	 * 
	 * @param param
	 * @param d
	 * @return
	 */
	public static int parseLongInt(Long param, int d) {
		int i = d;
		try {
			i = Integer.parseInt(String.valueOf(param));
		} catch (Exception e) {
		}
		return i;
	}

	public static int parseLongInt(Long param) {
		return parseLongInt(param, 0);
	}

	public static String returnString(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 修改敏感字符编码
	 * @param value
	 * @return
	 */
	public static String htmlEncode(String value){
		String re[][] = {{"<","&lt;"},
				{">","&gt;"},
				{"\"","&quot;"},
				{"\\′","&acute;"},
				{"&","&amp;"}
		};

		for(int i=0; i<4; i++){
			value = value.replaceAll(re[i][0], re[i][1]);
		}
		return value;
	}
	
	/**
	 * 防SQL注入
	 * 
	 * @param str
	 * @return
	 */
	public static boolean sql_inj(String str) {
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String inj_stra[] = inj_str.split("|");
		for (int i=0 ; i < inj_stra.length ; i++ )
		{
			if (str.indexOf(inj_stra[i])>=0)
			{
				return true;
			}
		}
		return false;
	}


}

