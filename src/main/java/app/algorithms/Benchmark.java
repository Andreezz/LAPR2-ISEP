package app.algorithms;

import app.domain.model.PerformanceEvaluator;
import com.isep.mdis.Sum;

public class Benchmark implements PerformanceEvaluator {

    public int[] computeMaxSublist( int[] inputList ) {
        return Sum.Max(inputList);
    }

}


