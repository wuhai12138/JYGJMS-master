package com.summ.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.baomidou.mybatisplus.toolkit.StringUtils.isNotEmpty;

/**
 * Author: D.Yang
 * Email: koyangslash@gmail.com
 * Date: 16/8/31
 * Time: 下午5:42
 * Describe: String工具类
 */
public class StringUtil {



    /**
     * 26英文字母字符串
     */
    public static String[] ENGLIST_LETTER = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 判断字符串是否为空或者空字符串 如果字符串是空或空字符串则返回true，否则返回false。也可以使用Android自带的TextUtil
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null || "".equals(str) || "null".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     *判断数组中是否有重复值
     */
    public static boolean isRepeat(List<String> list){
        //List转String
        String[] array = list.toArray(new String[list.size()]);

        Set<String> set = new HashSet<String>();
        for(String str : array){
            set.add(str);
        }
        if(set.size() == 1){
            return true;//所有元素都一样
        }else{
            return false;//有元素不一样
        }
    }

    /**
     * 判断是否是中文
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }



    /**
     * 将double转换为字符串，保留小数点位数
     *
     * @param doubleNum 需要解析的double
     * @param digitNum  小数点位数，小于0则默认0
     * @return
     */
    public static String DoubleToAmountString(Double doubleNum, int digitNum) {
        if (digitNum < 0)
            digitNum = 0;
        StringBuilder strBuilder = new StringBuilder("#");
        for (int i = 0; i < digitNum; i++) {
            if (i == 0)
                strBuilder.append(".#");
            else
                strBuilder.append("#");
        }
        DecimalFormat df = new DecimalFormat(strBuilder.toString());
        return df.format(doubleNum);
    }

    /**
     * 将int型用0补全为定长度的字符串
     *
     * @param a 需要解析的double
     * @param length  返回的字符串长度
     * @return
     */
    public static String changeIntegerLenght(Integer a, Integer length) {
        String str = a.toString();
        StringBuilder stringBuilder = new StringBuilder(str);
        for (int i=0;i<length-str.length();i++){
            stringBuilder.insert(0,"0");
        }
        return stringBuilder.toString();
    }


    /**
     * 去除String中的某一个字符
     *
     * @param orgStr
     * @param splitStr 要去除的字符串
     * @return
     */
    public static String removeAllChar(String orgStr, String splitStr) {
        String[] strArray = orgStr.split(splitStr);
        String res = "";
        for (String tmp : strArray) {
            res += tmp;
        }
        return res;
    }



    /**
     * MD5加密 32位小写
     *
     * @param sSecret
     * @return
     */
    public static String getMd5Value(String sSecret) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update((sSecret).getBytes("UTF-8"));
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * string转int
     * @param str
     * @return
     */
    public static int parseInt(String str){
        int i=0;
        if (StringUtil.isBlank(str)){
            return 0;
        }
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return i;
    }


    /**
     * string转double
     * @param str
     * @return
     */
    public static double parseDouble(String str){
        double i=0.0;
        if (StringUtil.isBlank(str)){
            return 0.0;
        }
        try {
            i = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return i;
    }

    // 加密
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        return s;
    }

    // 解密
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 定义下划线
    private static final char UNDERLINE = '_';



    /**
     * 驼峰转下划线工具
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String camelToUnderline(String param) {
        if (isNotEmpty(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);

            for (int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (Character.isUpperCase(c)) {
                    sb.append(UNDERLINE);
                    sb.append(Character.toLowerCase(c));
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 下划线转驼峰工具
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String underlineToCamel(String param) {
        if (isNotEmpty(param)) {
            int len = param.length();
            StringBuilder sb = new StringBuilder(len);

            for (int i = 0; i < len; ++i) {
                char c = param.charAt(i);
                if (c == 95) {
                    ++i;
                    if (i < len) {
                        sb.append(Character.toUpperCase(param.charAt(i)));
                    }
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        } else {
            return "";
        }
    }


    /**
     * 在字符串两周添加''
     *
     * @param param 字符串
     * @return 处理后的字符串
     */
    public static String addSingleQuotes(String param) {
        return "\'" + param + "\'";
    }

    /**
     * @param imgStr base64编码字符串
     * @param path   图片路径-具体到文件
     */
    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
