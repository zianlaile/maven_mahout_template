package org.conan.mymahout.cluster06;

import org.apache.mahout.utils.clustering.ClusterDumper;

import java.io.IOException;
import org.apache.hadoop.fs.Path;

/**
 * Created by zhouzian on 2018/1/20.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        //String file = "datafile/b_everyday_tables01.csv";
        String file = "datafile/t01.csv";
        int k = 3;
        String separator = ",";
       // Kmeans.runKmeans(file,k,separator);

        Path seqFileDir = new Path("datafile/t01.csv");

        Path pointsDir = new Path("datafile/rel.csv");

        ClusterDumper clusterdumper = new ClusterDumper(seqFileDir, pointsDir);

        clusterdumper.printClusters(null);

    }
}
