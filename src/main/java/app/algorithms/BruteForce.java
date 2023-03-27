package app.algorithms;

import app.domain.model.PerformanceEvaluator;

import java.util.List;

public class BruteForce implements PerformanceEvaluator {

    private int start;
    private int end;

    public BruteForce() {}

    public int[] computeMaxSublist(int[] inputList ) {

        int n = inputList.length;
        int maxSum = Integer.MIN_VALUE;
        start = 0;
        end = 0;

        for ( int i = 0 ; i < n ; i++ ) {
            int sum = 0;

            for ( int j = i ; j < n ; j++ ) {
                sum += inputList[j];

                if (sum > maxSum) {
                    maxSum = sum;
                    this.start = i;
                    this.end = j;
                }
            }
        }
        int[] maxSubList = new int[(end-start)+1];

        int count = start;
        for ( int i = 0 ; i < maxSubList.length ; i++ ) {
            maxSubList[i] = inputList[count];
            count++;
        }

        return maxSubList;
    }

    public String getSublistTimeInterval(List<String> times) {
        String x = times.get(start);
        String y = times.get(end+1);
        return "[" + x + " ; " + y + "]";
    }
}
