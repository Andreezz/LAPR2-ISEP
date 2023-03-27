package app.controller;

import app.domain.model.Company;
import app.domain.model.Hours;
import app.domain.shared.Constants;
import app.properties.PropertiesCache;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private AuthFacade authFacade;



    private App()
    {

        try {
            PropertiesCache props = new PropertiesCache();

        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();

        String[] aux = props.getProperty("hour").split(":");
        this.company.setTime(new Hours(Integer.parseInt(aux[0]), Integer.parseInt(aux[1])));

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = formatter.format(date);
            this.company.getArrivalStore().executeTask(app.domain.model.Date.getCurrentDate(), new Hours(Integer.parseInt(aux[0]), Integer.parseInt(aux[1])));
        }catch(Exception e){
            e.printStackTrace();
        }

        bootstrap();
    }

    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }


    private void bootstrap()
    {
        final String pass = "123456";

        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_NURSE, Constants.ROLE_NURSE);
        this.authFacade.addUserRole(Constants.ROLE_COORDINATOR,  Constants.ROLE_COORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_SNSUSER, Constants.ROLE_SNSUSER);

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", pass,Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Recepcionist", "recep@lei.sem2.pt", pass,Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("SNS User", "snsuser@lei.sem2.pt", pass, Constants.ROLE_SNSUSER);
        this.authFacade.addUserWithRole("Coordinator", "cord@lei.sem2.pt", pass, Constants.ROLE_COORDINATOR);
        this.authFacade.addUserWithRole("Nurse", "nurse@lei.sem2.pt", pass,Constants.ROLE_NURSE);




    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }
}

