package com.emcs.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;
import java.util.regex.Pattern;

public final class StringUtil {
    private static final String EmailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
    private static final transient Pattern emailPattern = Pattern.compile(EmailPattern);
    private static final String DEFAULT_PREFIX = "${";
    private static final String DEFAULT_SUFFIX = "}";
    static final String[] SBC = new String[]{"，", "。", "；", "“", "”", "？", "！", "（", "）", "：", "——", "、"};
    static final String[] DBC = new String[]{",", ".", ";", "\"", "\"", "?", "!", "(", ")", ":", "_", ","};
    private static final int PAD_LIMIT = 8192;
    private static final String[] PADDING = new String['\uffff'];
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * 判断两个是字符串是否相等，忽略大小写
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null?str2 == null:str1.equalsIgnoreCase(str2);
    }

    /**
     * 获取指定字符串的位置
     * @param str
     * @param searchChar
     * @return
     */
    public static int indexOf(String str, char searchChar) {

        return isEmpty(str)?-1:str.indexOf(searchChar);
    }


    public static boolean isNumEx(String str) {
        if(str != null && str.length() > 0) {
            char[] ch = str.toCharArray();
            int i = 0;

            for(int comcount = 0; i < str.length(); ++i) {
                if(!Character.isDigit(ch[i])) {
                    if(ch[i] != 46) {
                        return false;
                    }

                    if(i == 0 || i == str.length() - 1) {
                        return false;
                    }

                    ++comcount;
                    if(comcount > 1) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static Object getStringNumber(String str, int index) {
        if(str == null) {
            return null;
        } else {
            char[] ch = str.toCharArray();
            String tempStr = "";
            Vector returnNumber = new Vector();

            for(int i = 0; i < str.length(); ++i) {
                if(Character.isDigit(ch[i])) {
                    tempStr = tempStr + ch[i];
                } else {
                    if(!tempStr.equals("")) {
                        returnNumber.addElement(tempStr);
                    }

                    tempStr = "";
                }
            }

            if(!tempStr.equals("")) {
                returnNumber.addElement(tempStr);
            }

            if(!returnNumber.isEmpty() && index <= returnNumber.size()) {
                if(index <= 0) {
                    return returnNumber;
                } else {
                    return returnNumber.elementAt(index - 1);
                }
            } else {
                return null;
            }
        }
    }

    public static String[] sortByLength(String[] saSource, boolean bAsc) {
        if(saSource != null && saSource.length > 0) {
            int iLength = saSource.length;
            String[] saDest = new String[iLength];

            for(int sTemp = 0; sTemp < iLength; ++sTemp) {
                saDest[sTemp] = saSource[sTemp];
            }

            String var7 = "";
            boolean j = false;
            boolean k = false;

            for(int var8 = 0; var8 < iLength; ++var8) {
                for(int var9 = 0; var9 < iLength - var8 - 1; ++var9) {
                    if(saDest[var9].length() > saDest[var9 + 1].length() && bAsc) {
                        var7 = saDest[var9];
                        saDest[var9] = saDest[var9 + 1];
                        saDest[var9 + 1] = var7;
                    } else if(saDest[var9].length() < saDest[var9 + 1].length() && !bAsc) {
                        var7 = saDest[var9];
                        saDest[var9] = saDest[var9 + 1];
                        saDest[var9 + 1] = var7;
                    }
                }
            }

            return saDest;
        } else {
            return null;
        }
    }


    public static boolean isEmailAddress(String str) {

        return isEmpty(str)?false:emailPattern.matcher(str).matches();
    }

    public static String quoteNullString(String s) {
        return s == null?"Null":(s.trim().length() == 0?"Null":"\'" + s.trim() + "\'");
    }

    public static char getCharAtPosDefaultZero(String s, int pos) {
        return s == null?'0':(s.length() <= pos?'0':s.charAt(pos));
    }

    public static String setCharAtPosAppendZero(String s, int pos, char c) {
        if(s == null) {
            s = "";
        }

        while(pos > s.length() - 1) {
            s = s + '0';
        }

        String preString;
        if(pos == 0) {
            preString = "";
        } else {
            preString = s.substring(0, pos);
        }

        String afterString;
        if(pos == s.length() - 1) {
            afterString = "";
        } else {
            afterString = s.substring(pos + 1);
        }

        return preString + c + afterString;
    }

    public static String fillBlank(String s, int n, boolean isLeft) {
        return n < 0?s:(isEmpty(s)?rightPad("", n, " "):(s.length() >= n?s:(isLeft?leftPad(s, n, " "):rightPad(s, n, " "))));
    }

    public static int compareVersion(String version1, String version2) {
        StringTokenizer st1 = new StringTokenizer(version1, ".");
        StringTokenizer st2 = new StringTokenizer(version2, ".");
        ArrayList al1 = new ArrayList();
        ArrayList al2 = new ArrayList();

        while(st1.hasMoreTokens()) {
            al1.add(st1.nextToken());
        }

        while(st2.hasMoreTokens()) {
            al2.add(st2.nextToken());
        }

        int size1 = al1.size();
        int size2 = al2.size();

        for(int i = 0; i < size1 && i < size2; ++i) {
            int v1 = Integer.parseInt((String)al1.get(i));
            int v2 = Integer.parseInt((String)al2.get(i));
            if(v1 > v2) {
                return 1;
            }

            if(v1 < v2) {
                return -1;
            }
        }

        return size1 > size2?1:(size1 < size2?-1:0);
    }

    public static String deleteAny(String inString, String charsToDelete) {
        if(inString != null && charsToDelete != null) {
            StringBuffer out = new StringBuffer();

            for(int i = 0; i < inString.length(); ++i) {
                char c = inString.charAt(i);
                if(charsToDelete.indexOf(c) == -1) {
                    out.append(c);
                }
            }

            return out.toString();
        } else {
            return inString;
        }
    }

    public static String quote(String str) {
        return str != null?"\'" + str + "\'":null;
    }

    public static Object quoteIfString(Object obj) {
        return obj instanceof String?quote((String)obj):obj;
    }

    public static String unqualify(String qualifiedName) {
        return unqualify(qualifiedName, '.');
    }

    public static String unqualify(String qualifiedName, char separator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
    }

    public static Locale parseLocaleString(String localeString) {
        String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
        String language = parts.length > 0?parts[0]:"";
        String country = parts.length > 1?parts[1]:"";
        String variant = parts.length > 2?parts[2]:"";
        return language.length() > 0?new Locale(language, country, variant):null;
    }

    public static String[] removeDuplicateStrings(String[] array) {
        if(ArrayUtil.isEmpty(array)) {
            return array;
        } else {
            TreeSet set = new TreeSet();

            for(int i = 0; i < array.length; ++i) {
                set.add(array[i]);
            }

            return ArrayUtil.getStringArrayValues(set);
        }
    }

    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
        return splitArrayElementsIntoProperties(array, delimiter, (String)null);
    }

    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter, String charsToDelete) {
        if(array != null && array.length != 0) {
            Properties result = new Properties();

            for(int i = 0; i < array.length; ++i) {
                String element = array[i];
                if(charsToDelete != null) {
                    element = deleteAny(array[i], charsToDelete);
                }

                String[] splittedElement = split(element, delimiter);
                if(splittedElement != null) {
                    result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
                }
            }

            return result;
        } else {
            return null;
        }
    }

    public static String[] tokenizeToStringArray(String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {
        StringTokenizer st = new StringTokenizer(str, delimiters);
        ArrayList tokens = new ArrayList();

        while(true) {
            String token;
            do {
                if(!st.hasMoreTokens()) {
                    return ArrayUtil.getStringArrayValues(tokens);
                }

                token = st.nextToken();
                if(trimTokens) {
                    token = token.trim();
                }
            } while(ignoreEmptyTokens && token.length() <= 0);

            tokens.add(token);
        }
    }

    public static String[] commaDelimitedListToStringArray(String str) {
        return split(str, ",");
    }

    public static Set commaDelimitedListToSet(String str) {
        TreeSet set = new TreeSet();
        String[] tokens = commaDelimitedListToStringArray(str);

        for(int i = 0; i < tokens.length; ++i) {
            set.add(tokens[i]);
        }

        return set;
    }

    public static String arrayToDelimitedString(Object[] arr, String delim) {
        if(arr == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < arr.length; ++i) {
                if(i > 0) {
                    sb.append(delim);
                }

                sb.append(arr[i]);
            }

            return sb.toString();
        }
    }

    public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
        if(coll == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            Iterator it = coll.iterator();

            for(int i = 0; it.hasNext(); ++i) {
                if(i > 0) {
                    sb.append(delim);
                }

                sb.append(prefix).append(it.next()).append(suffix);
            }

            return sb.toString();
        }
    }

    public static String collectionToDelimitedString(Collection coll, String delim) {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    public static String arrayToCommaDelimitedString(Object[] arr) {
        return arrayToDelimitedString(arr, ",");
    }

    public static String collectionToCommaDelimitedString(Collection coll) {
        return collectionToDelimitedString(coll, ",");
    }

    public static String replace(String src, Map props) {
        return replace(src, "${", "}", props);
    }

    public static String replace(String src, String prefix, String suffix, Map props) {
        int len1 = prefix.length();
        int len2 = suffix.length();
        StringBuffer sb = new StringBuffer();

        for(int index1 = src.indexOf(prefix); index1 >= 0; index1 = src.indexOf(prefix)) {
            sb.append(src.substring(0, index1));
            src = src.substring(index1 + len1);
            if(src.startsWith(prefix)) {
                sb.append(prefix);
                break;
            }

            int index2 = src.indexOf(suffix);
            if(index2 < 0) {
                sb.append(prefix);
                break;
            }

            String t = src.substring(0, index2);
            Object o = props.get(t);
            String sp = o == null?"":o.toString();
            sb.append(sp);
            src = src.substring(index2 + len2);
        }

        sb.append(src);
        return new String(sb);
    }

    public static boolean isNotNullAndBlank(String str) {
        return !isNullOrBlank(str);
    }

    public static boolean isNullOrBlank(String str) {
        return isNull(str) || str.equals("") || str.equals("null");
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotNull(String str) {
        return str != null && str.trim().length() != 0?!str.trim().equalsIgnoreCase("null"):false;
    }

    public static String ifNullToBlank(String str) {
        return isNotNull(str) && !str.trim().equals("null")?str.trim():"";
    }

    public static String ifNullToBlank(Object obj) {
        String ret = "";
        String s = String.valueOf(obj);
        if(s != null && !"".equals(s) && !"null".equals(s) && !"NULL".equals(s)) {
            ret = s;
        } else {
            ret = "";
        }

        return ret;
    }

    public static boolean hasWildcards(String input) {
        return contains(input, "*") || contains(input, "?");
    }

    public static boolean isWildcardMatchOne(String r_Keyword, String[] r_WildcardMatcher, boolean r_CaseSensitive) {
        if(null == r_WildcardMatcher) {
            return true;
        } else {
            for(int i = 0; i < r_WildcardMatcher.length; ++i) {
                String t_WildCardMatcher = r_WildcardMatcher[i];
                if(isWildcardMatch(r_Keyword, t_WildCardMatcher, r_CaseSensitive)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isWildcardMatchAll(String r_Keyword, String[] r_WildcardMatcher, boolean r_CaseSensitive) {
        if(null == r_WildcardMatcher) {
            return true;
        } else {
            for(int i = 0; i < r_WildcardMatcher.length; ++i) {
                String t_WildCardMatcher = r_WildcardMatcher[i];
                if(!isWildcardMatch(r_Keyword, t_WildCardMatcher, r_CaseSensitive)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isWildcardMatch(String r_Keyword, String r_WildcardMatcher) {
        return isWildcardMatch(r_Keyword, r_WildcardMatcher, true);
    }

    public static boolean isWildcardMatch(String r_Keyword, String r_WildcardMatcher, boolean r_CaseSensitive) {
        if(r_Keyword == null && r_WildcardMatcher == null) {
            return true;
        } else if(r_Keyword != null && r_WildcardMatcher != null) {
            if(!r_CaseSensitive) {
                r_Keyword = r_Keyword.toLowerCase();
                r_WildcardMatcher = r_WildcardMatcher.toLowerCase();
            }

            String[] t_SplitValues = splitOnTokens(r_WildcardMatcher);
            boolean t_Chars = false;
            int t_Index = 0;
            int t_WildIndex = 0;
            Stack t_BackStack = new Stack();

            do {
                if(t_BackStack.size() > 0) {
                    int[] repeat = (int[])((int[])t_BackStack.pop());
                    t_WildIndex = repeat[0];
                    t_Index = repeat[1];
                    t_Chars = true;
                }

                for(; t_WildIndex < t_SplitValues.length; ++t_WildIndex) {
                    if(t_SplitValues[t_WildIndex].equals("?")) {
                        ++t_Index;
                        t_Chars = false;
                    } else if(t_SplitValues[t_WildIndex].equals("*")) {
                        t_Chars = true;
                        if(t_WildIndex == t_SplitValues.length - 1) {
                            t_Index = r_Keyword.length();
                        }
                    } else {
                        if(t_Chars) {
                            t_Index = r_Keyword.indexOf(t_SplitValues[t_WildIndex], t_Index);
                            if(t_Index == -1) {
                                break;
                            }

                            int var9 = r_Keyword.indexOf(t_SplitValues[t_WildIndex], t_Index + 1);
                            if(var9 >= 0) {
                                t_BackStack.push(new int[]{t_WildIndex, var9});
                            }
                        } else if(!r_Keyword.startsWith(t_SplitValues[t_WildIndex], t_Index)) {
                            break;
                        }

                        t_Index += t_SplitValues[t_WildIndex].length();
                        t_Chars = false;
                    }
                }

                if(t_WildIndex == t_SplitValues.length && t_Index == r_Keyword.length()) {
                    return true;
                }
            } while(t_BackStack.size() > 0);

            return false;
        } else {
            return false;
        }
    }

    private static String[] splitOnTokens(String r_Text) {
        if(r_Text.indexOf("?") == -1 && r_Text.indexOf("*") == -1) {
            return new String[]{r_Text};
        } else {
            char[] t_Array = r_Text.toCharArray();
            ArrayList t_List = new ArrayList();
            StringBuffer t_Buffer = new StringBuffer();

            for(int i = 0; i < t_Array.length; ++i) {
                if(t_Array[i] != 63 && t_Array[i] != 42) {
                    t_Buffer.append(t_Array[i]);
                } else {
                    if(t_Buffer.length() != 0) {
                        t_List.add(t_Buffer.toString());
                        t_Buffer.setLength(0);
                    }

                    if(t_Array[i] == 63) {
                        t_List.add("?");
                    } else if(t_List.size() == 0 || i > 0 && !t_List.get(t_List.size() - 1).equals("*")) {
                        t_List.add("*");
                    }
                }
            }

            if(t_Buffer.length() != 0) {
                t_List.add(t_Buffer.toString());
            }

            return (String[])((String[])t_List.toArray(new String[0]));
        }
    }

    public static boolean isIn(String r_Source, String[] r_Target, boolean r_CaseSensitive) {
        for(int i = 0; i < r_Target.length; ++i) {
            String t_Value = r_Target[i];
            if(r_CaseSensitive) {
                if(equals(r_Source, t_Value)) {
                    return true;
                }
            } else if(equalsIgnoreCase(r_Source, t_Value)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isIn(String r_Source, Collection r_Target) {
        Iterator t_Iterator = r_Target.iterator();

        String t_Value;
        do {
            if(!t_Iterator.hasNext()) {
                return false;
            }

            t_Value = (String)t_Iterator.next();
        } while(!equalsIgnoreCase(r_Source, t_Value));

        return true;
    }

    public static String targetEndStyle(String name) {
        return "</" + name + ">";
    }

    public static String valueToSetStyle(String value) {
        if(value == null) {
            value = "";
        }

        return "=\"" + value + "\"";
    }

    public static boolean equal(String s1, String s2) {
        if(s1 == s2) {
            return true;
        } else {
            if(s1 == null) {
                s1 = "";
            }

            if(s2 == null) {
                s2 = "";
            }

            s1 = s1.trim();
            s2 = s2.trim();
            return s1.equals(s2);
        }
    }

    public static String concat(Object... args) {
        StringBuffer buf = new StringBuffer();
        Object[] arr$ = args;
        int len$ = args.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object arg = arr$[i$];
            buf.append(arg);
        }

        return buf.toString();
    }

    public static String format(String s, Object[] params) {
        String message = s;
        if(s == null) {
            return "";
        } else {
            if(params != null && params.length > 0) {
                message = (new MessageFormat(s)).format(params);
            }

            return message;
        }
    }

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if(str != null && prefix != null) {
            if(str.startsWith(prefix)) {
                return true;
            } else if(str.length() < prefix.length()) {
                return false;
            } else {
                String lcStr = str.substring(0, prefix.length()).toLowerCase();
                String lcPrefix = prefix.toLowerCase();
                return lcStr.equals(lcPrefix);
            }
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static String substringAfter(String str, String separator) {
        if(isEmpty(str)) {
            return str;
        } else if(separator == null) {
            return "";
        } else {
            int pos = str.indexOf(separator);
            return pos == -1?"":str.substring(pos + separator.length());
        }
    }

    public static String substringBefore(String str, String separator) {
        if(!isEmpty(str) && separator != null) {
            if(separator.length() == 0) {
                return "";
            } else {
                int pos = str.indexOf(separator);
                return pos == -1?str:str.substring(0, pos);
            }
        } else {
            return str;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);
    }

    public static boolean contains(String str, String searchStr) {
        return str != null && searchStr != null?str.indexOf(searchStr) >= 0:false;
    }

    public static String deleteWhitespace(String str) {
        if(isEmpty(str)) {
            return str;
        } else {
            int sz = str.length();
            char[] chs = new char[sz];
            int count = 0;

            for(int i = 0; i < sz; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    chs[count++] = str.charAt(i);
                }
            }

            if(count == sz) {
                return str;
            } else {
                return new String(chs, 0, count);
            }
        }
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if(str == null) {
            return null;
        } else {
            int len = str.length();
            if(len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                ArrayList list = new ArrayList();
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;

                while(true) {
                    while(i < len) {
                        if(str.charAt(i) == separatorChar) {
                            if(match || preserveAllTokens) {
                                list.add(str.substring(start, i));
                                match = false;
                                lastMatch = true;
                            }

                            ++i;
                            start = i;
                        } else {
                            lastMatch = false;
                            match = true;
                            ++i;
                        }
                    }

                    if(match || preserveAllTokens && lastMatch) {
                        list.add(str.substring(start, i));
                    }

                    return (String[])((String[])list.toArray(new String[list.size()]));
                }
            }
        }
    }

    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    public static String replace(String text, String repl, String with, int max) {
        if(text != null && !isEmpty(repl) && with != null && max != 0) {
            StringBuffer buf = new StringBuffer(text.length());
            int start = 0;
            boolean end = false;

            int var7;
            while((var7 = text.indexOf(repl, start)) != -1) {
                buf.append(text.substring(start, var7)).append(with);
                start = var7 + repl.length();
                --max;
                if(max == 0) {
                    break;
                }
            }

            buf.append(text.substring(start));
            return buf.toString();
        } else {
            return text;
        }
    }

    public static String leftPad(String str, int size) {
        return leftPad(str, size, ' ');
    }

    public static String leftPad(String str, int size, char padChar) {
        if(str == null) {
            return null;
        } else {
            int pads = size - str.length();
            return pads <= 0?str:(pads > 8192?leftPad(str, size, String.valueOf(padChar)):padding(pads, padChar).concat(str));
        }
    }

    private static String padding(int repeat, char padChar) {
        String pad = PADDING[padChar];
        if(pad == null) {
            pad = String.valueOf(padChar);
        }

        while(pad.length() < repeat) {
            pad = pad.concat(pad);
        }

        PADDING[padChar] = pad;
        return pad.substring(0, repeat);
    }

    public static String leftPad(String str, int size, String padStr) {
        if(str == null) {
            return null;
        } else {
            if(isEmpty(padStr)) {
                padStr = " ";
            }

            int padLen = padStr.length();
            int strLen = str.length();
            int pads = size - strLen;
            if(pads <= 0) {
                return str;
            } else if(padLen == 1 && pads <= 8192) {
                return leftPad(str, size, padStr.charAt(0));
            } else if(pads == padLen) {
                return padStr.concat(str);
            } else if(pads < padLen) {
                return padStr.substring(0, pads).concat(str);
            } else {
                char[] padding = new char[pads];
                char[] padChars = padStr.toCharArray();

                for(int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLen];
                }

                return (new String(padding)).concat(str);
            }
        }
    }

    public static String rightPad(String str, int size) {
        return rightPad(str, size, ' ');
    }

    public static String rightPad(String str, int size, char padChar) {
        if(str == null) {
            return null;
        } else {
            int pads = size - str.length();
            return pads <= 0?str:(pads > 8192?rightPad(str, size, String.valueOf(padChar)):str.concat(padding(pads, padChar)));
        }
    }

    public static String rightPad(String str, int size, String padStr) {
        if(str == null) {
            return null;
        } else {
            if(isEmpty(padStr)) {
                padStr = " ";
            }

            int padLen = padStr.length();
            int strLen = str.length();
            int pads = size - strLen;
            if(pads <= 0) {
                return str;
            } else if(padLen == 1 && pads <= 8192) {
                return rightPad(str, size, padStr.charAt(0));
            } else if(pads == padLen) {
                return str.concat(padStr);
            } else if(pads < padLen) {
                return str.concat(padStr.substring(0, pads));
            } else {
                char[] padding = new char[pads];
                char[] padChars = padStr.toCharArray();

                for(int i = 0; i < pads; ++i) {
                    padding[i] = padChars[i % padLen];
                }

                return str.concat(new String(padding));
            }
        }
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null?str2 == null:str1.equals(str2);
    }

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if(str == null) {
            return null;
        } else {
            int len = str.length();
            if(len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                ArrayList list = new ArrayList();
                int sizePlus1 = 1;
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;
                if(separatorChars != null) {
                    if(separatorChars.length() != 1) {
                        label87:
                        while(true) {
                            while(true) {
                                if(i >= len) {
                                    break label87;
                                }

                                if(separatorChars.indexOf(str.charAt(i)) >= 0) {
                                    if(match || preserveAllTokens) {
                                        lastMatch = true;
                                        if(sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    } else {
                        char sep = separatorChars.charAt(0);

                        label71:
                        while(true) {
                            while(true) {
                                if(i >= len) {
                                    break label71;
                                }

                                if(str.charAt(i) == sep) {
                                    if(match || preserveAllTokens) {
                                        lastMatch = true;
                                        if(sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    }
                } else {
                    label103:
                    while(true) {
                        while(true) {
                            if(i >= len) {
                                break label103;
                            }

                            if(Character.isWhitespace(str.charAt(i))) {
                                if(match || preserveAllTokens) {
                                    lastMatch = true;
                                    if(sizePlus1++ == max) {
                                        i = len;
                                        lastMatch = false;
                                    }

                                    list.add(str.substring(start, i));
                                    match = false;
                                }

                                ++i;
                                start = i;
                            } else {
                                lastMatch = false;
                                match = true;
                                ++i;
                            }
                        }
                    }
                }

                if(match || preserveAllTokens && lastMatch) {
                    list.add(str.substring(start, i));
                }

                return (String[])((String[])list.toArray(new String[list.size()]));
            }
        }
    }

    public static String removeStart(String str, String remove) {
        return !isEmpty(str) && !isEmpty(remove)?(str.startsWith(remove)?str.substring(remove.length()):str):str;
    }

    public static String removeEnd(String str, String remove) {
        return !isEmpty(str) && !isEmpty(remove)?(str.endsWith(remove)?str.substring(0, str.length() - remove.length()):str):str;
    }

    public static String remove(String str, String remove) {
        return !isEmpty(str) && !isEmpty(remove)?replace(str, remove, "", -1):str;
    }

    public static String remove(String str, char remove) {
        if(!isEmpty(str) && str.indexOf(remove) != -1) {
            char[] chars = str.toCharArray();
            int pos = 0;

            for(int i = 0; i < chars.length; ++i) {
                if(chars[i] != remove) {
                    chars[pos++] = chars[i];
                }
            }

            return new String(chars, 0, pos);
        } else {
            return str;
        }
    }

    public static String htmlFilter(String value) {
        if(value != null && value.length() != 0) {
            StringBuilder result = null;
            String filtered = null;

            for(int i = 0; i < value.length(); ++i) {
                filtered = null;
                switch(value.charAt(i)) {
                    case '\"':
                        filtered = "&quot;";
                        break;
                    case '&':
                        filtered = "&amp;";
                        break;
                    case '\'':
                        filtered = "&#39;";
                        break;
                    case '<':
                        filtered = "&lt;";
                        break;
                    case '>':
                        filtered = "&gt;";
                }

                if(result == null) {
                    if(filtered != null) {
                        result = new StringBuilder(value.length() + 50);
                        if(i > 0) {
                            result.append(value.substring(0, i));
                        }

                        result.append(filtered);
                    }
                } else if(filtered == null) {
                    result.append(value.charAt(i));
                } else {
                    result.append(filtered);
                }
            }

            return result == null?value:result.toString();
        } else {
            return value;
        }
    }
}

