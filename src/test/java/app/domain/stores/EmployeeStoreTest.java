package app.domain.stores;

import app.domain.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeStoreTest {

    @Test
    void employeeExists() {
        EmployeeStore store = new EmployeeStore();
        Employee employeeA =new Employee("rua A","aaaa", "351912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        Employee employeeB =new Employee("rua A","aaaa", "351912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        Employee employeeC = new Employee("rua B","aaaa", "351912345600", "00001","coisa@coisa.pt","39849069-4-ZV8");
        //employees are equal
        try{
            store.saveEmployee(employeeA);
            store.employeeExists(employeeB);
        }
        catch (Exception e){
            Assertions.assertEquals("The employee already exists!Please note that employee attributes must be unique for each employee!", e.getMessage());
        }
        //employees have the same name
        try{
            store.saveEmployee(employeeA);
            store.employeeExists(employeeC);
        }
        catch (Exception e){
            Assertions.assertEquals("The employee already exists!Please note that employee attributes must be unique for each employee!", e.getMessage());
        }
    }

    @Test
    void clearEmployeeList() {
        EmployeeStore store = new EmployeeStore();
        Employee employeeA =new Employee("rua A","aaaa", "912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        store.saveEmployee(employeeA);
        store.clearEmployeeList();
        String expected = "";
        Assertions.assertEquals(expected, store.getEmployeesList());
    }

    @Test
    void validateEmployee(){
        EmployeeStore store = new EmployeeStore();
        Employee employeeA =new Employee("rua A","aaaa", "912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        //value should be true because values are valid
        Assertions.assertTrue(store.validateEmployee(employeeA));
    }

    @Test
    public void isEmptyTest() {
        EmployeeStore store = new EmployeeStore();
        Assertions.assertTrue(store.isEmpty());
    }

   @Test
     public void filteringList() {
        EmployeeStore list = new EmployeeStore();
        Employee employeeA = new Employee("rua B","Anya", "912345678", "00000","coisa@isep.pt","39849069-4-ZV7");
        Employee employeeB = new Employee("rua A","Anta", "912345644", "02200","cois1a@isep.pt","39859069-4-ZV5");
        list.saveEmployee(employeeA);
        list.saveEmployee(employeeB);

        List<String> ids = new ArrayList<>();
        ids.add("coisa@isep.pt");
        ids.add("coisa1@isep.pt");
        ids.add("coisa2@isep.pt");
        ids.add("coisa3@isep.pt");

        EmployeeStore actual = list.filterList(ids);
        EmployeeStore expected = new EmployeeStore();
        expected.saveEmployee(employeeA);

        Assertions.assertEquals(actual.getEmployeesList(), expected.getEmployeesList());
     }

}