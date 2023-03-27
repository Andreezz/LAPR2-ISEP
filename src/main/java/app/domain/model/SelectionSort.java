package app.domain.model;

import java.util.List;

public class SelectionSort {

    /** Sorts a list by descendig or ascending order, by arrival date/time or leaving date/time, with the algorithm Selection Sort
     * @param store list to be sorted
     * @param arrival represents if the list should be sorted by arrival or not
     * @param ascending represents if the list should be sorted by ascending order or not
     */
    public static void sort(List<CenterData> store, boolean arrival, boolean ascending){
        int n = store.size();
        for (int i = 0; i < n-1; i++)
        {
            int minIndex = i;
            for (int j = minIndex+1; j < n; j++) {
                if(arrival) {
                    //if first ArrivalDateTime is minor than the second
                    if(ascending) {
                        if (CompareInstance.compareDateTime.compare(store.get(j).getArr(), store.get(minIndex).getArr()) < 0)
                            minIndex = j;
                    }
                    else{
                        if (CompareInstance.compareDateTime.compare(store.get(j).getArr(), store.get(minIndex).getArr()) > 0)
                            minIndex = j;
                    }
                }
                else{
                    //if first LeaveDateTime is minor than the second
                    if(ascending) {
                        if (CompareInstance.compareDateTime.compare(store.get(j).getLeave(), store.get(minIndex).getLeave()) < 0)
                            minIndex = j;
                    }
                    else{
                        if (CompareInstance.compareDateTime.compare(store.get(j).getLeave(), store.get(minIndex).getLeave()) > 0)
                            minIndex = j;
                    }
                }
            }

            CenterData temp = store.get(minIndex);
            store.set(minIndex, store.get(i));
            store.set(i, temp);
        }
    }
}
