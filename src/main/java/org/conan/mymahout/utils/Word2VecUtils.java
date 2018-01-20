package org.conan.mymahout.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
//import org.deeplearning4j.models.word2vec.Word2Vec;

/**
 * 词向量相关工具
 * @author Bill
 *
 */
public class Word2VecUtils {
	//private Word2Vec word2vec;
	private String modelPath;

	/**
	 * 设置w2v工具基本参数
	 * @param modelPath 模型路径
	 */
	public Word2VecUtils(String modelPath) {
		this.modelPath = modelPath;
	}

	/**
	 * 初始化工具：加载模型
	 * @return 成功返回true，失败返回false
	 */
/*	public boolean initUtils() {
		try {
			System.out.println("初始化词向量模型...");
			word2vec = WordVectorSerializer.loadFullModel(modelPath);
			System.out.println("词向量模型初始化完成！");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}*/

	/**
     * 过滤出向量库中存在的标签
     * @param mWord 待分析词语
     * @return 存在向量则返回true，否则返回false
     */
/*    public boolean hasWord(String mWord) {
        boolean hasWord = false;
        try {
        	hasWord = word2vec.hasWord(mWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasWord;
    }*/

    /**
     * 获取两个词之间的相似度，即两个词的余弦距离
     * @param w1 词一
     * @param w2 词二
     * @return 距离值
     */
//    public double getWordDistance(String w1, String w2) {
//    	return word2vec.similarity(w1, w2);
//    }

    /**
     * 获取词向量值
     * @param word 待取的词
     * @return 词向量值
     */
/*    public double[] getWordVector(String word) {
    	return word2vec.getWordVector(word);
    }*/

    /**
     * 获取目标词最相似的n个词向量
     * @param word 目标词
     * @param num 相似个数
     * @return
     */
/*    public Map<String, Double> getSimWordsInfo(String word, int num) {
    	Map<String, Double> wordsInfo = new HashMap<String, Double>();
    	try {
    		Collection<String> words = word2vec.wordsNearest(word, num);
    		for(String w : words) {
    			double sim = word2vec.similarity(word, w);
    			wordsInfo.put(w, sim);
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return wordsInfo;
    }*/

	/**
	 * 求两个向量之间的余弦距离
	 * @param v1 向量1
     * @param v2 向量2
	 */
	public static double cosDistance(double[] v1, double[] v2) {
		double costh = 0.0;
		try {
			// 计算v1和v2向量的余弦距离
			double fz = 0.0;
			double fm = 0.0;
			double sumTag1 = 0.0;
			double sumTag2 = 0.0;
			int dim = v1.length;
			for(int i = 0; i < dim; i++) {
				// 计算分子
				fz = fz + v1[i] * v2[i];
				// 计算分母
				sumTag1 = sumTag1 + (v1[i] * v1[i]);
				sumTag2 = sumTag2 + (v2[i] * v2[i]);
			}
			fm = Math.sqrt(sumTag1) * Math.sqrt(sumTag2);
			costh = fz / fm;

			return costh;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return costh;
	}

	/**
	 * 计算每一维度的均值，然后每一维度减去这个均值，得到余弦的调整距离
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static List<double[]> cosAdjust(double[] v1, double[] v2) {
		int dim = v1.length;
		double[] adjVec1 = new double[dim];
		double[] adjVec2 = new double[dim];
		List<double[]> adjList = new ArrayList<double[]>();
		double average = 0.0;
		double sum = 0.0;
		try {
			for(int i = 0; i < dim; i++) {
				sum = sum + v1[i] + v2[i];
			}
			average = sum / (dim * 2);
			for(int i = 0; i < dim; i++) {
				adjVec1[i] = v1[i] - average;
				adjVec2[i] = v2[i] - average;
			}
			adjList.add(adjVec1);
			adjList.add(adjVec2);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return adjList;
	}

	/**
	 * 求两个向量之间的欧式距离
	 * @param v1 向量1
	 * @param v2 向量2
	 * @return 欧式距离
	 */
	public static double euclideanDistance(double[] v1, double[] v2) {
		double euc = 1.0;
		int dim = v1.length;
		try {
			// 计算v1和v2向量空间中的欧式距离
			double sum = 0.0;
			for(int i = 0; i < dim; i++) {
				sum = sum + ((v1[i] - v2[i]) * (v1[i] - v2[i]));
			}
			euc = Math.sqrt(sum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return euc;
	}

	/**
	 * 余弦距离归一化
	 * @param cosdis 余弦距离
	 * @return
	 */
	public static double cosNormalization(double cosdis) {
		return 0.5 + 0.5 * cosdis;
	}

	/**
	 * 欧式距离归一化
	 * @param eudis
	 * @return
	 */
	public static double euclideanNormalization(double eudis) {
		return 1 / (1 + eudis);
	}

	/**
	 * 返回两个n维向量相加的结果
	 * @param v1 向量一
	 * @param v2 向量二
	 * @return 向量一加上向量二的结果
	 */
	public static double [] sumVectors(double [] v1, double [] v2) {
		int v1len = v1.length,v2len = v2.length;
		if(v1len != v2len)
			return null;
		double [] sum = new double[v1len];

		for(int i = 0; i < v1len; i++) {
			sum[i] = v1[i] + v2[i];
		}

		return sum;
	}
}
