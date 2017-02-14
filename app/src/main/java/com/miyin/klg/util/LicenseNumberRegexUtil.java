package com.miyin.klg.util;

/**
 * Created by en on 2017/2/14.
 */

public class LicenseNumberRegexUtil {
    /**
     * 营业执照注册号校验正确返回码
     */
    private static String error_Businesslicense_Empty = "请输入营业执照注册号";
    public static String error_Businesslicense = "您输入的营业执照注册号有误，请核对后再输!";


    public static String test = "110108000000016";// 营业执照号


    public static void main(String[] args) {
        isLicense_18(test);
    }


    /**
     * 校验 营业执照注册号
     *
     * @param businesslicense
     * @return
     */
    public static boolean isLicense_15(String businesslicense) {


        if ("".equals(businesslicense) || " ".equals(businesslicense)) {
            return false;
        } else if (businesslicense.length() != 15) {
            return false;
        }
        String businesslicensePrex14 = businesslicense.substring(0, 14);// 获取营业执照注册号前14位数字用来计算校验码
        String businesslicense15 = businesslicense.substring(14, businesslicense.length());// 获取营业执照号的校验码
        char[] chars = businesslicensePrex14.toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = Integer.parseInt(String.valueOf(chars[i]));
        }
        getCheckCode(ints);
        if (!businesslicense15.equals(getCheckCode(ints) + "")) {// 比较填写的营业执照注册号的校验码和计算的校验码是否一致
            return false;
        }
        return true;
    }


    /**
     * 获取 营业执照注册号的校验码
     *
     * @param ints
     * @return
     */
    private static int getCheckCode(int[] ints) {
        if (null != ints && ints.length > 1) {
            int ti = 0;
            int si = 0;// pi|11+ti
            int cj = 0;// （si||10==0？10：si||10）*2
            int pj = 10;// pj=cj|11==0?10:cj|11
            for (int i = 0; i < ints.length; i++) {
                ti = ints[i];
                pj = (cj % 11) == 0 ? 10 : (cj % 11);
                si = pj + ti;
                cj = (0 == si % 10 ? 10 : si % 10) * 2;
                if (i == ints.length - 1) {
                    pj = (cj % 11) == 0 ? 10 : (cj % 11);
                    return pj == 1 ? 1 : 11 - pj;
                }
            }
        }
        return -1;


    }


    public static boolean isLicense_18(String code){
        if (code.length() != 18) {
            return false;
        }
        String regex = "^([159Y]{1})([1239]{1})([0-9ABCDEFGHJKLMNPQRTUWXY]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-90-9ABCDEFGHJKLMNPQRTUWXY])$";
        if (!code.matches(regex)) {
            return false;
        }
        String str = "0123456789ABCDEFGHJKLMNPQRTUWXY";
        int[] ws = { 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28 };
        String[] codes = new String[2];
        codes[0] = code.substring(0, code.length() - 1);
        codes[1] = code.substring(code.length() - 1, code.length());
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += str.indexOf(codes[0].charAt(i)) * ws[i];
        }
        int c18 = 31 - (sum % 31);
        if (c18 == 31) {
            c18 = 'Y';
        } else if (c18 == 30) {
            c18 = '0';
        }
        if (str.charAt(c18) != codes[1].charAt(0)) {
            return false;
        }
        return true;
    }

}