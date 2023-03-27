package app.controller;

import app.algorithms.BruteForce;
import app.domain.model.*;
import app.domain.stores.ArrivalStore;
import app.domain.stores.CenterDataStore;
import app.mappers.ArrivalMapper;
import app.mappers.CenterDataMapper;
import app.mappers.dto.ArrivalDTO;
import app.mappers.dto.CenterDataDTO;
import app.properties.PropertiesCache;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Controller UserStory [016]
 * @author Pedro Ferreira -> 1210825@isep.ipp.pt
 */

public class PerformanceAnalysisController {

    private App app;
    private PerformanceAnalysis perfAnalysis;
    private CenterDataMapper mapperCenterData = new CenterDataMapper();
    private ArrivalMapper mapperArrival = new ArrivalMapper();

    public PerformanceAnalysisController() {
        this.app = App.getInstance();
        this.perfAnalysis = new PerformanceAnalysis();
    }

    public List<CenterDataDTO> getCenterDataDTO() {
        CenterDataStore store = app.getCompany().getCenterDataStore();
        List<CenterData> lst = store.getAll();
        return mapperCenterData.toDTO(lst);
    }

    public List<ArrivalDTO> getArrivalsDTO() {
        ArrivalStore store = app.getCompany().getArrivalStore();
        Set<Arrival> lst = store.getAll();
        return mapperArrival.toDTO(lst);
    }

    public int[] createList( String date, int time_interval, List<CenterDataDTO> cdDTO, List<ArrivalDTO> arrivalsDTO ) throws ParseException {
        return this.perfAnalysis.difArrivalLeaving( date , time_interval , cdDTO , arrivalsDTO );
    }

    public int[] computeMaxSublist( int[] inputLst , PerformanceEvaluator pe ) throws Exception {
        return this.perfAnalysis.computeMaxSublist(inputLst,pe);
    }

    public int getSublistSum( int[] maxSublist ) {
        return this.perfAnalysis.getSublistSum(maxSublist);
    }

    public String getSublistTimeInterval(BruteForce bf) throws Exception {
        return this.perfAnalysis.getSublistTimeInterval(bf);
    }

    public PerformanceEvaluator getPerformanceEvaluator() throws Exception {

            PropertiesCache props = new PropertiesCache();
            String x = props.getProperty("Company.PerformanceAnalysis.Class");
            Class<?> oClass = Class.forName(x);
            return (PerformanceEvaluator) oClass.getDeclaredConstructor().newInstance();

    }
}
