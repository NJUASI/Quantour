package com.edu.nju.asi.optimization;

/**
 * Created by Harvey on 2017/6/13.
 */
public class GeneticAlgorithmTest extends GeneticAlgorithm {

    public static final int NUM = 1 << 24;

    public GeneticAlgorithmTest() {
        super(24);
    }

    @Override
    public double changeX(Chromosome chro) {
        // TODO Auto-generated method stub
        return ((1.0 * chro.getNum() / NUM) * 100) + 6;
    }

    @Override
    public double caculateY(double x) {
        // TODO Auto-generated method stub
        return 100 - Math.log(x);
    }

    public static void main(String[] args) {
        GeneticAlgorithmTest test = new GeneticAlgorithmTest();
        test.caculte();
    }
}
