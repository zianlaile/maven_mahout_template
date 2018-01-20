package org.conan.mymahout.cluster06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.apache.mahout.clustering.kmeans.Cluster;
import org.apache.mahout.clustering.kmeans.KMeansClusterer;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.Vector;
import org.conan.mymahout.utils.Word2VecUtils;

public class Kmeans {

    public static void runKmeans(String path,int k,String sep) throws IOException {
        //List<Vector> sampleData = MathUtil.readFileToVector("datafile/randomData.csv");
        //List<Vector> sampleData = MathUtil.readFileToVector("datafile/b_everyday_tables02.csv");
        List<Vector> sampleData = MathUtil.readFileToVector(path,sep);

        //int k = 4;
        double threshold = 0.01;
        int col = sampleData.get(0).size();

        List<Vector> randomPoints = MathUtil.chooseRandomPoints(sampleData, k);
        for (Vector vector : randomPoints) {
            System.out.println("Init Point center: " + vector);
        }

        List<Cluster> clusters = new ArrayList<Cluster>();
        for (int i = 0; i < k; i++) {
            clusters.add(new Cluster(randomPoints.get(i), i, new EuclideanDistanceMeasure()));
        }

        List<double[]> douList = new ArrayList<double[]>();

        List<List<Cluster>> finalClusters = KMeansClusterer.clusterPoints(sampleData, clusters, new EuclideanDistanceMeasure(), k, threshold);
        for (Cluster cluster : finalClusters.get(finalClusters.size() - 1)) {
           // System.out.println("Cluster id: " + cluster.getId() + " center: " + cluster.getCenter().asFormatString());
            System.out.println("Cluster id: " + cluster.getId() + " center: " + cluster.getCenter().asFormatString());

            //去除左右大括号并按照逗号分隔开
            String[] s1 = cluster.getCenter().asFormatString().substring(1,cluster.getCenter().asFormatString().length()-1).split(",");

            //按冒号分开取后一个，如果缺少补零
            int len = 0;
            double[] d1 = new double[col];
            for (int i=0;i<s1.length;i++){
                //s2[len] = s1[1];
                String[] s2 = s1[i].split(":");
                if (Integer.valueOf(s2[0]) == len ){
                    d1[len] = Double.valueOf(s2[1]);
                }else {
                    d1[len] = 0;
                    i--;
                }
                len ++;
            }
            douList.add(d1);
        }

        //遍历list两两相互比较,输出归一的余弦距离和归一的欧氏距离
        for (int i=0;i<douList.size()-1;i++){
            for (int j=i+1;j<douList.size();j++){
                System.out.print("("+i+","+j+") "+"余弦："+Word2VecUtils.cosNormalization(Word2VecUtils.cosDistance(douList.get(i),douList.get(j))));
                System.out.println(", 欧式："+Word2VecUtils.euclideanNormalization(Word2VecUtils.euclideanDistance(douList.get(i),douList.get(j))));
            }
        }


    }
}
