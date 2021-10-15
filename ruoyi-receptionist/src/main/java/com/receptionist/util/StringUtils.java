package com.receptionist.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 *
 * @author dongyangyang
 * @Date 2016/12/28 23:12
 * @Version 1.0
 */
@Slf4j
public class StringUtils {

    /**
     * 首字母变小写
     *
     * @param str
     * @return
     */
    public static String firstCharToLowerCase( String str ) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[ 0 ] += ( 'a' - 'A' );
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     *
     * @param str
     * @return
     */
    public static String firstCharToUpperCase( String str ) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[ 0 ] -= ( 'a' - 'A' );
            return new String(arr);
        }
        return str;
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty( final String str ) {
        return ( str == null ) || ( str.trim().length() == 0 );
    }

    /**
     * 判断是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty( final String str ) {
        return !isEmpty(str);
    }

    /**
     * 判断是否空白
     *
     * @param str
     * @return
     */
    public static boolean isBlank( final String str ) {

        if (( str == null ) || ( ( str.trim().length() ) == 0 )) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否不是空白
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank( final String str ) {
        return !isBlank(str);
    }

    /**
     * 判断多个字符串全部是否为空
     *
     * @param strings
     * @return
     */
    public static boolean isAllEmpty( String... strings ) {
        if (strings == null) {
            return true;
        }
        for (String str : strings) {
            if (isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断多个字符串其中任意一个是否为空
     *
     * @param strings
     * @return
     */
    public static boolean isHasEmpty( String... strings ) {
        if (strings == null) {
            return true;
        }
        for (String str : strings) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkValue为 null 或者为 "" 时返回 defaultValue
     *
     * @param checkValue
     * @param defaultValue
     * @return
     */
    public static String isEmpty( String checkValue, String defaultValue ) {
        return isEmpty(checkValue) ? defaultValue : checkValue;
    }

    /**
     * 字符串不为 null 而且不为 "" 并且等于other
     *
     * @param str
     * @param other
     * @return
     */
    public static boolean isNotEmptyAndEquelsOther( String str, String other ) {
        if (isEmpty(str)) {
            return false;
        }
        return str.equals(other);
    }

    /**
     * 字符串不为 null 而且不为 "" 并且不等于other
     *
     * @param str
     * @param other
     * @return
     */
    public static boolean isNotEmptyAndNotEquelsOther( String str, String... other ) {
        if (isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < other.length; i++) {
            if (str.equals(other[ i ])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串不等于other
     *
     * @param str
     * @param other
     * @return
     */
    public static boolean isNotEquelsOther( String str, String... other ) {
        for (int i = 0; i < other.length; i++) {
            if (other[ i ].equals(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串不为空
     *
     * @param strings
     * @return
     */
    public static boolean isNotEmpty( String... strings ) {
        if (strings == null) {
            return false;
        }
        for (String str : strings) {
            if (str == null || "".equals(str.trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 比较字符相等
     *
     * @param value
     * @param equals
     * @return
     * @Updator 丁祺群
     */
    public static boolean equals( String value, String equals ) {
        if (isAllEmpty(value, equals)) {
            return true;
        }
        if (value == null) {
            return false;
        }
        return value.equals(equals);
    }

    /**
     * 比较字符串不相等
     *
     * @param value
     * @param equals
     * @return
     */
    public static boolean isNotEquals( String value, String equals ) {
        return !equals(value, equals);
    }

    public static String[] split( String content, String separatorChars ) {
        return splitWorker(content, separatorChars, -1, false);
    }

    public static String[] split( String str, String separatorChars, int max ) {
        return splitWorker(str, separatorChars, max, false);
    }

    public static final String[] EMPTY_STRING_ARRAY = new String[ 0 ];

    private static String[] splitWorker( String str, String separatorChars, int max, boolean preserveAllTokens ) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len == 0) {
            return EMPTY_STRING_ARRAY;
        }
        List<String> list = new ArrayList<String>();
        int sizePlus1 = 1;
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        if (separatorChars == null) {
            while (i < len) {
                if (Character.isWhitespace(str.charAt(i))) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else if (separatorChars.length() == 1) {
            char sep = separatorChars.charAt(0);
            while (i < len) {
                if (str.charAt(i) == sep) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        } else {
            while (i < len) {
                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                    if (match || preserveAllTokens) {
                        lastMatch = true;
                        if (sizePlus1++ == max) {
                            i = len;
                            lastMatch = false;
                        }
                        list.add(str.substring(start, i));
                        match = false;
                    }
                    start = ++i;
                    continue;
                }
                lastMatch = false;
                match = true;
                i++;
            }
        }
        if (match || ( preserveAllTokens && lastMatch )) {
            list.add(str.substring(start, i));
        }
        return (String[]) list.toArray(EMPTY_STRING_ARRAY);
    }

    /**
     * 消除转义字符
     *
     * @param str
     * @return
     */
    public static String escapeXML( String str ) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            switch (c) {
                case '\u00FF':
                case '\u0024':
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '\"':
                    sb.append("&quot;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                default:
                    if (c >= '\u0000' && c <= '\u001F') {
                        break;
                    }
                    if (c >= '\uE000' && c <= '\uF8FF') {
                        break;
                    }
                    if (c >= '\uFFF0' && c <= '\uFFFF') {
                        break;
                    }
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串中特定模式的字符转换成map中对应的值
     *
     * @param s   需要转换的字符串
     * @param map 转换所需的键值对集合
     * @return 转换后的字符串
     */
    public static String replace( String s, Map<String, Object> map ) {
        StringBuilder ret = new StringBuilder((int) ( s.length() * 1.5 ));
        int cursor = 0;
        for (int start, end; ( start = s.indexOf("${", cursor) ) != -1 && ( end = s.indexOf("}", start) ) != -1; ) {
            ret.append(s.substring(cursor, start)).append(map.get(s.substring(start + 2, end)));
            cursor = end + 1;
        }
        ret.append(s.substring(cursor, s.length()));
        return ret.toString();
    }

    public static String replace( String s, Object... objs ) {
        if (objs == null || objs.length == 0) {
            return s;
        }
        if (s.indexOf("{}") == -1) {
            return s;
        }
        StringBuilder ret = new StringBuilder((int) ( s.length() * 1.5 ));
        int cursor = 0;
        int index = 0;
        for (int start; ( start = s.indexOf("{}", cursor) ) != -1; ) {
            ret.append(s.substring(cursor, start));
            if (index < objs.length) {
                ret.append(objs[ index ]);
            } else {
                ret.append("{}");
            }
            cursor = start + 2;
            index++;
        }
        ret.append(s.substring(cursor, s.length()));
        return ret.toString();
    }

    /**
     * 字符串格式化工具,参数必须以{0}之类的样式标示出来.大括号中的数字从0开始。
     *
     * @param source 源字符串
     * @param params 需要替换的参数列表,写入时会调用每个参数的toString().
     * @return 替换完成的字符串。如果原始字符串为空或者参数为空那么将直接返回原始字符串。
     */
    public static String replaceArgs( String source, Object... params ) {
        if (params == null || params.length == 0 || source == null || source.isEmpty()) {
            return source;
        }
        StringBuilder buff = new StringBuilder(source);
        StringBuilder temp = new StringBuilder();
        int startIndex = 0;
        int endIndex = 0;
        String param = null;
        for (int count = 0; count < params.length; count++) {
            if (params[ count ] == null) {
                param = null;
            } else {
                param = params[ count ].toString();
            }

            temp.delete(0, temp.length());
            temp.append("{");
            temp.append(count);
            temp.append("}");
            while (true) {
                startIndex = buff.indexOf(temp.toString(), endIndex);
                if (startIndex == -1) {
                    break;
                }
                endIndex = startIndex + temp.length();

                buff.replace(startIndex, endIndex, param == null ? "" : param);
            }
            startIndex = 0;
            endIndex = 0;
        }
        return buff.toString();
    }

    public static String substringBefore( final String s, final String separator ) {
        if (isEmpty(s) || separator == null) {
            return s;
        }
        if (separator.isEmpty()) {
            return "";
        }
        final int pos = s.indexOf(separator);
        if (pos < 0) {
            return s;
        }
        return s.substring(0, pos);
    }

    public static String substringBetween( final String str, final String open, final String close ) {
        if (str == null || open == null || close == null) {
            return null;
        }
        final int start = str.indexOf(open);
        if (start != -1) {
            final int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

    public static String substringAfter( final String str, final String separator ) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return "";
        }
        final int pos = str.indexOf(separator);
        if (pos == -1) {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    public static String nullToString( Object object ) {
        return object == null ? "" : object.toString();
    }

    public static Double nullTodouble( Object object ) {
        return object == null ? 0 : Double.valueOf(nullToString(object));
    }

    /*用户参数校验,空串则返回空,去掉两端空白字符*/
    public static String paramCheckString( String string ) {
        return ( null == string || "".equals(string.trim()) ) ? null : string.trim();
    }

    /**
     * 判断两个字符串首部是否相等
     *
     * @param parent 父编码：短
     * @param son    子编码：长
     * @return 结果
     */
    public static boolean isSonCode( String parent, String son ) {
        return son.length() >= parent.length() && son.substring(0, parent.length()).equals(parent);
    }

    /**
     * 判断子编码是否属于父编码列表下的
     *
     * @param son  子编码
     * @param list 父编码列表
     */
    public static boolean isSonOfCodeList( String son, List<String> list ) {
        if (StringUtils.isEmpty(son)) {
            return false;
        }
        if (list == null) {
            return false;
        }
        for (String s : list) {
            if (isSonCode(s, son)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断编码是否为列表中的某个编码的父编码
     *
     * @param str  编码
     * @param list 编码列表
     */
    public static boolean listIsSonOfCode( String str, List<String> list ) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        if (list == null) {
            return false;
        }
        for (String s : list) {
            if (isSonCode(str, s)) {
                return true;
            }
        }
        return false;
    }


    public static List<String> getProvinceCodeByList( List<String> list ) {
        Set<String> pList = new HashSet<String>();
        for (String s : list) {
            if (s.length() >= 2) {
                pList.add(s.substring(0, 2));
            } else {
                pList.add(s);
            }
        }
        return new ArrayList<String>(pList);
    }

    private static Pattern isNumeric = Pattern.compile("[0-9]*");

    //判断字符串是否为数字
    public static boolean isNumeric( String str ) {
        if (isEmpty(str)) {
            return false;
        }

        return isNumeric.matcher(str).matches();
    }

    public static Integer getNumber( String str ) {
        if (isNumeric(str)) {
            return Integer.valueOf(str);
        } else {
            return null;
        }
    }

    public static String getRandomPassword() {
        return String.valueOf((int) ( ( Math.random() * 9 + 1 ) * 10000 ));
    }


    /**
     * 把（参数）对象的所有字符串的前后空白字符都去掉
     * @param o
     * @param <T>
     */
    public static <T> void trimAllFields(T o) {
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object f = field.get(o);
                if (f instanceof String) {
                    field.set(o, ((String) f).trim());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 编号生成（协议编号、合同编号等）
     *
     * @param prefix 前缀，如 XXX
     * @param count 保留位数，如 4
     * @param value 要进行拼接的值，如时间戳 1617694100791
     * @return 字符串如：XXX201707070791
     */
    public static String createAgreementNumber(String prefix, int count, long value) {
        if (value < 0) {
            return null;
        }
        // 20170707
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String day = format.format(new Date());
        ++ value;
        NumberFormat nf = NumberFormat.getInstance();
        //设置是否使用分组(取消 1,000 的逗号分隔符)
        nf.setGroupingUsed(false);
        //设置最大整数位数
        nf.setMaximumIntegerDigits(count);
        //设置最小整数位数
        nf.setMinimumIntegerDigits(count);

        return prefix + day + nf.format(value);
    }

    /**
     * 获取短码UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 生成随机数字或字母
     * @param length 长度
     * @param type 1为字母,2为数字
     * @return
     */
    public static String getStringRandom(int length,String type) {
        StringBuffer val = new StringBuffer();
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if("2".equals(type)){
                charOrNum = "num";
            }
            if("1".equals(type)){
                charOrNum = "char";
            }
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                val.append((char)(random.nextInt(26) + 65));
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }

    /**
     * 要求外部订单号必须唯一。
     * @return {String}
     */
    public  static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        key = key + System.currentTimeMillis();
        key = key.substring(0, 15);
        return key;
    }

    public static void main(String[] args) {
        System.out.println(getStringRandom(6,"2"));
    }
}