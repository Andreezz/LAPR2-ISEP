package app.domain.model;

 //import app.algorithms.Benchmark;
import app.algorithms.BruteForce;
import app.controller.PerformanceAnalysisController;
import app.mappers.ArrivalMapper;
import app.mappers.CenterDataMapper;
import app.mappers.dto.ArrivalDTO;
import app.mappers.dto.CenterDataDTO;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PerformanceAnalysisTest {

    private PerformanceAnalysis perfAnalysis = new PerformanceAnalysis();
    private PerformanceAnalysisController perfController = new PerformanceAnalysisController();
    private CenterDataMapper mapper = new CenterDataMapper();
    private ArrivalMapper mapperArrival = new ArrivalMapper();

    @Test
    void difArrivalLeaving() throws ParseException {
        CenterData cd1 = new CenterData("5/30/2022" , ConvertTimeFormat.convert("8:24") , "5/30/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "13:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        CenterData cd2 = new CenterData("5/30/2022" , ConvertTimeFormat.convert("9:24") , "5/30/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "14:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        CenterData cd3 = new CenterData("5/30/2022" , "10:24" , "5/30/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "15:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        CenterData cd4 = new CenterData("5/30/2022" , "11:24" , "5/30/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "16:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        CenterData cd5 = new CenterData("5/30/2022" , "12:24" , "5/30/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "17:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        List<CenterData> lstData = new ArrayList<>();
        Set<Arrival> lstArrival = new HashSet<>();
        lstData.add(cd1);
        lstData.add(cd2);
        lstData.add(cd3);
        lstData.add(cd4);
        lstData.add(cd5);
        List<CenterDataDTO> lst = mapper.toDTO(lstData);
        List<ArrivalDTO> lstArrivals = mapperArrival.toDTO(lstArrival);
        int[] expected = {1,1,1,1,1,-1,-1,-1,-1,-1,0,0};
        int[] inputList = perfAnalysis.difArrivalLeaving("5/30/2022" , 60 , lst , lstArrivals );
        for (Integer i : inputList) {
            System.out.println(i);
        }
        assertArrayEquals(expected,inputList);

    }

   /* @Test
    void computeMaxSublist() {
        BruteForce tf = new BruteForce();
        Benchmark bm = new Benchmark();
        int[] inputList = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
        int[] maxSubList = perfAnalysis.computeMaxSublist(inputList , bm);
        int[] expected = {51, -9, 44, 74, 4};
        assertArrayEquals(expected,maxSubList );
    }
*/
    @Test
    void getSublistSum() {
        int[] inputList = new int[]{29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
        int expected = 122;
        int result = perfAnalysis.getSublistSum(inputList);
        assertEquals(expected,result);
    }

    @Test
    void getSublistTimeInterval() throws ParseException {
        CenterData cd1 = new CenterData("5/30/2022" , "10:30" , "30/05/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "13:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        CenterData cd2 = new CenterData("5/30/2022" , "10:30" , "30/05/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "14:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        CenterData cd3 = new CenterData("5/30/2022" , "10:30" , "5/30/2022" ,"8:00", "30/05/2022" ,"9:11"
                , "5/30/2022" , "15:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        CenterData cd4 = new CenterData("5/30/2022" , "11:30" , "5/30/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "16:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        CenterData cd5 = new CenterData("5/30/2022" , "12:10" , "5/30/2022" ,"8:00", "5/30/2022" ,"9:11"
                , "5/30/2022" , "08:43","161593120","21C16-05",	"Spikevax",	"Primeira");

        List<CenterData> lstData = new ArrayList<>();
        Set<Arrival> lstArrival = new HashSet<>();
        lstData.add(cd1);
        lstData.add(cd2);
        lstData.add(cd3);
        lstData.add(cd4);
        lstData.add(cd5);
        List<CenterDataDTO> lst = mapper.toDTO(lstData);
        List<ArrivalDTO> lstArrivals = mapperArrival.toDTO(lstArrival);
        BruteForce tf = new BruteForce();
        int[] inputList = perfAnalysis.difArrivalLeaving("5/30/2022" , 144 , lst , lstArrivals);
        int[] maxSub = perfAnalysis.computeMaxSublist(inputList,tf);
        System.out.println(Arrays.toString(inputList));
        String expected = "[10:24 ; 12:48]";
        for(String i : perfAnalysis.getTimes() ) {
            System.out.println(i);
        }

        String result = perfAnalysis.getSublistTimeInterval(tf);
        System.out.println(result);
        assertTrue(checkIfEquals(expected,result));
    }

    public boolean checkIfEquals(String expected , String result) {
        return expected.equals(result);
    }
}