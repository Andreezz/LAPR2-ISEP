package app.domain.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

    /**
     *It represents an age group that knows one or more doses.
     */
    public class AgeGroup implements Serializable {

        private static final long serialVersionUID = 13L;

        private static final int ZERO_DOSES = 0;

        private int ageA;
        private int ageB;

        private List<Dose> doses;

        /**
         * Constructor of AgeGroup
         *
         * @param ageA
         * @param ageB
         */
        public AgeGroup(int ageA, int ageB) {
            this.ageA = ageA;
            this.ageB = ageB;
            doses = new ArrayList<Dose>();
        }

        /**
         * Gets age
         *
         * @return
         */
        public int getAgeA() {
            return ageA;
        }

        /**
         * Sets age
         *
         * @param ageA
         */
        public void setAgeA(int ageA) {
            this.ageA = ageA;
        }

        /**
         * Gets Age
         *
         * @return
         */
        public int getAgeB() {
            return ageB;
        }

        /**
         * Sets Age2
         *
         * @param ageB
         */
        public void setAgeB(int ageB) {
            this.ageB = ageB;
        }

        /**
         * Sets a dose in the list.
         *
         * @param doses
         * @return
         */

        public boolean addDoses(Dose doses) {
            return this.doses.add(doses);
        }

        /**
         * Gets the value  of Vaccine dose.
         *
         * @return
         */
        public int getVaccineDose() {

            return doses.size();
        }

        /**
         * Gets of number of Vaccine dose
         *
         * @param index
         * @return
         */

        public Dose getDose(int index) {
            return doses.get(index);
        }


        /**
         * Obtains in text format the information needed.
         *
         * @return
         */
        @Override
        public String toString() {
            String string;

            if (ageA >= 0) {
                string = "Greater than " + ageB + " years old.\n ";
            } else if (ageB >= 0) {
                string = "Minor than " + ageB + " years old.\n ";
            } else {
                string = "Age from" + ageB + " to " + ageB + ".\n";
            }


            if (doses.size() == ZERO_DOSES) {
                return string + "0 doses registered";
            } else {
                for (int i = 0; i < doses.size(); i++) {
                    string += "dose nº" + (i + 1) + ":" + doses.get(i).toString() + "\n";

                }
                return string;
            }
        }
    }



