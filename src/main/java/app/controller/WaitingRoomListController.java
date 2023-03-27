package app.controller;

import app.domain.model.Company;
import app.domain.stores.ArrivalStore;


public class WaitingRoomListController {
    private Company company;
    private ArrivalStore allArrivals;

    public WaitingRoomListController() {
        this.company = App.getInstance().getCompany();
        this.allArrivals = this.company.getArrivalStore();
    }

    public ArrivalStore getArrivals(String vc){
        ArrivalStore store = this.allArrivals.filterByVaccCentre(vc);
        return store.orderByArrival();
    }

    public void waitingRoomListing(ArrivalStore list){
         list.print();
    }

}

