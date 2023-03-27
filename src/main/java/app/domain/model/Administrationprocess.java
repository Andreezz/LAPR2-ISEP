package app.domain.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Administrationprocess implements Serializable {

    private static final long serialVersionUID = 12L;
    /**
     *It represents  The Administration Process of a vaccine.
     */
        private List<AgeGroup> group;

        /**
         * Contructor of Administration Process.
         */
        public Administrationprocess() {
            this.group = new ArrayList<AgeGroup>();
        }

        /**
         *  method to save the Age Group in the List of Administration Process.
         * @param age
         * @return
         */
        public boolean addAgeGroup(AgeGroup age) {
            if (!validateAdministrationprocess(age))
                return false;
            return this.group.add(age);

        }

        /** method to validate the Age Group
         * @param age
         * @return
         */
        public boolean validateAdministrationprocess(AgeGroup age) {
            if (age == null)
                return false;

            return !this.group.contains(age);
        }

        /**
         * Method to register and Create  a new Age Group and doses.
         *
         * @param ageA
         * @param ageB
         * @param VaccineDosage
         * @param TimeIntervalDose
         * @return
         */

        public boolean CreateAdministrationProcess(int ageA, int ageB, List<Float> VaccineDosage, List<Integer> TimeIntervalDose) {
            AgeGroup ageSave = new AgeGroup(ageA, ageB);

            for (int i = 0; i < VaccineDosage.size(); i++) {
                ageSave.addDoses(new Dose(VaccineDosage.get(i), TimeIntervalDose.get(i)));
            }

            return addAgeGroup(ageSave);
        }


        public List<AgeGroup> getGroup() {
            return group;
        }

        /**
         * Obtains in text format the information needed of all Administration Process.
         * @return
         */
        @Override
        public String toString() {
            String string = "";

            string += group.toString() + "\n";

            return string;
        }
}
