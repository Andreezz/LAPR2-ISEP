package app.domain.model;


/**
 * Id Generator for the UserStory: Register an Employee
 * @author Lourenço Mayol -> 1211206@isep.ipp.pt
 */

public class IdGenerator {
    /**
     * @param counter represents the number of employees that were created
     * the id is a five digit String, an employee's id will be the amount of created employees including him
     * for example (the first employee will have the id 00001, the fifth employee will have the id 00005)
     * @return a String that contains an employee's id
     */
    public static String getId(int counter){
        StringBuilder id = new StringBuilder();
        //counter não pode ser 0, fórmula não funciona com 0
        counter++;
        int counterNumberDigits =  (int)(Math.log10(counter) + 1);
        id.append(counter);
        for(int i = 0; i < 5 - counterNumberDigits;i++){
            id.insert(0, "0");
        }
        return String.valueOf(id);
    }
}
