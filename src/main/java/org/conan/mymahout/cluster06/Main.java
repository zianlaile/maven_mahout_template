package org.conan.mymahout.cluster06;

import java.io.IOException;

/**
 * Created by zhouzian on 2018/1/20.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //String file = "datafile/b_everyday_tables01.csv";
        String file = "datafile/t01.csv";
        int k = 4;
        String separator = ",";
        Kmeans.runKmeans(file,k,separator);
    }
}
