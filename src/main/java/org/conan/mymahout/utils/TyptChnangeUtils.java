package org.conan.mymahout.utils;

/**
 * Created by zhouzian on 2018/1/20.
 */
public class TyptChnangeUtils {
    /**
     * String[] 转 double[]
     * @param s1
     * @return
     */
    public static double[] StrngToDouble(String[] s1){
        double[] d1 = new double[s1.length];
        for (int i=0;i<s1.length;i++){
            d1[i] = Double.parseDouble(s1[i]);
        }
        return d1;
    }

    /**
     * double[] 转 String[]
     * @param d1
     * @return
     */
    public static String[] DoubleToString(double[] d1){
        String[] s1 = new String[d1.length];
        for (int i=0;i<d1.length;i++){
            s1[i] = String.valueOf(d1[i]);
        }
        return s1;
    }

}
