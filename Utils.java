package com.blankj.utilcode.utils;

import android.os.Build;
import android.text.Html;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/8/7
 *     desc  : ���������ع�����
 * </pre>
 */
public class EncodeUtils {

    private EncodeUtils() {
        throw new UnsupportedOperationException("u can't fuck me...");
    }

    /**
     * URL����
     * <p>�����Լ�ָ���ַ���,����ʹ��{@link #urlEncode(String input, String charset)}����</p>
     *
     * @param input Ҫ������ַ�
     * @return ����ΪUTF-8���ַ���
     */
    public static String urlEncode(String input) {
        return urlEncode(input, "UTF-8");
    }

    /**
     * URL����
     * <p>��ϵͳ��֧��ָ���ı����ַ���,��ֱ�ӽ�inputԭ������</p>
     *
     * @param input   Ҫ������ַ�
     * @param charset �ַ���
     * @return ����Ϊ�ַ������ַ���
     */
    public static String urlEncode(String input, String charset) {
        try {
            return URLEncoder.encode(input, charset);
        } catch (UnsupportedEncodingException e) {
            return input;
        }
    }

    /**
     * URL����
     * <p>�����Լ�ָ���ַ���,����ʹ�� {@link #urlDecode(String input, String charset)}����</p>
     *
     * @param input Ҫ������ַ���
     * @return URL�������ַ���
     */
    public static String urlDecode(String input) {
        return urlDecode(input, "UTF-8");
    }

    /**
     * URL����
     * <p>��ϵͳ��֧��ָ���Ľ����ַ���,��ֱ�ӽ�inputԭ������</p>
     *
     * @param input   Ҫ������ַ���
     * @param charset �ַ���
     * @return URL����Ϊָ���ַ������ַ���
     */
    public static String urlDecode(String input, String charset) {
        try {
            return URLDecoder.decode(input, charset);
        } catch (UnsupportedEncodingException e) {
            return input;
        }
    }

    /**
     * Base64����
     *
     * @param input Ҫ������ַ���
     * @return Base64�������ַ���
     */
    public static byte[] base64Encode(String input) {
        return base64Encode(input.getBytes());
    }

    /**
     * Base64����
     *
     * @param input Ҫ������ֽ�����
     * @return Base64�������ַ���
     */
    public static byte[] base64Encode(byte[] input) {
        return Base64.encode(input, Base64.NO_WRAP);
    }

    /**
     * Base64����
     *
     * @param input Ҫ������ֽ�����
     * @return Base64�������ַ���
     */
    public static String base64Encode2String(byte[] input) {
        return Base64.encodeToString(input, Base64.NO_WRAP);
    }

    /**
     * Base64����
     *
     * @param input Ҫ������ַ���
     * @return Base64�������ַ���
     */
    public static byte[] base64Decode(String input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64����
     *
     * @param input Ҫ������ַ���
     * @return Base64�������ַ���
     */
    public static byte[] base64Decode(byte[] input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }

    /**
     * Base64URL��ȫ����
     * <p>��Base64�е�URL�Ƿ��ַ�??,/=תΪ�����ַ�, ��RFC3548</p>
     *
     * @param input ҪBase64URL��ȫ������ַ���
     * @return Base64URL��ȫ�������ַ���
     */
    public static byte[] base64UrlSafeEncode(String input) {
        return Base64.encode(input.getBytes(), Base64.URL_SAFE);
    }

    /**
     * Html����
     *
     * @param input ҪHtml������ַ���
     * @return Html�������ַ���
     */
    public static String htmlEncode(String input) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return Html.escapeHtml(input);
        } else {
            // ����Html.escapeHtml()�д���
            StringBuilder out = new StringBuilder();
            for (int i = 0, len = input.length(); i < len; i++) {
                char c = input.charAt(i);
                if (c == '<') {
                    out.append("&lt;");
                } else if (c == '>') {
                    out.append("&gt;");
                } else if (c == '&') {
                    out.append("&amp;");
                } else if (c >= 0xD800 && c <= 0xDFFF) {
                    if (c < 0xDC00 && i + 1 < len) {
                        char d = input.charAt(i + 1);
                        if (d >= 0xDC00 && d <= 0xDFFF) {
                            i++;
                            int codepoint = 0x010000 | (int) c - 0xD800 << 10 | (int) d - 0xDC00;
                            out.append("&#").append(codepoint).append(";");
                        }
                    }
                } else if (c > 0x7E || c < ' ') {
                    out.append("&#").append((int) c).append(";");
                } else if (c == ' ') {
                    while (i + 1 < len && input.charAt(i + 1) == ' ') {
                        out.append("&nbsp;");
                        i++;
                    }
                    out.append(' ');
                } else {
                    out.append(c);
                }
            }
            return out.toString();
        }
    }

    /**
     * Html����
     *
     * @param input ��������ַ���
     * @return Html�������ַ���
     */
    public static String htmlDecode(String input) {
        return Html.fromHtml(input).toString();
    }
}