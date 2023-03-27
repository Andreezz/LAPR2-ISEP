package app.domain.model;

import java.util.List;

public class PancakeSort {

    static void flip(List<CenterData> store, int i)
    {
        int start = 0;
        while (start < i)
        {
            CenterData temp = store.get(start);
            store.set(start, store.get(i));
            store.set(i, temp);
            start++;
            i--;
        }
    }

    static int findMax(List<CenterData> store, int n, boolean arrival)
    {
        int max = 0, i;
        for (i = 0; i < n; ++i)
            if(arrival) {
                if (CompareInstance.compareDateTime.compare(store.get(i).getArr(), store.get(max).getArr()) > 0)
                    max = i;
            }
            else {
                if (CompareInstance.compareDateTime.compare(store.get(i).getLeave(), store.get(max).getLeave()) > 0)
                    max = i;
            }
        return max;
    }

    static int findMin(List<CenterData> store, int n, boolean arrival)
    {
        int max = 0, i;
        for (i = 0; i < n; ++i)
            if(arrival) {
                if (CompareInstance.compareDateTime.compare(store.get(i).getArr(), store.get(max).getArr()) < 0)
                    max = i;
            }
            else {
                if (CompareInstance.compareDateTime.compare(store.get(i).getLeave(), store.get(max).getLeave()) < 0)
                    max = i;
            }
        return max;
    }

    /** Sorts a list by descendig or ascending order, by arrival date/time or leaving date/time, with the algorithm Pancake Sort
     * @param store list to be sorted
     * @param arrival represents if the list should be sorted by arrival or not
     * @param ascending represents if the list should be sorted by ascending order or not
     */
    public static void sort(List<CenterData> store, boolean arrival, boolean ascending)
    {
        int n = store.size();
        for (int size = n; size > 1;--size)
        {
            int minOrMax;
            if(ascending)
                minOrMax = findMax(store, size, arrival);
            else
                minOrMax = findMin(store, size, arrival);
            if (minOrMax != size-1)
            {
                flip(store, minOrMax);
                flip(store, size-1);
            }
        }
    }
}
