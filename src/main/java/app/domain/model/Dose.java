package app.domain.model;

import java.io.Serializable;

public class Dose implements Serializable {
    private static final long serialVersionUID = 14L;
    /**
     * the dose class represents interval time of a vaccine and your vaccine  dosage
     */

        private float VaccineDosage;
        private int TimeIntervalDose;

        /**
         *  Doses Construtor.
         * @param VaccineDosage
         * @param TimeIntervalDose
         */
        public Dose(float VaccineDosage, int TimeIntervalDose) {
            this.VaccineDosage = VaccineDosage;
            this.TimeIntervalDose = TimeIntervalDose;

        }

        /**
         * Gets the value  of Vaccine dosage.
         * @return
         */
        public float getVaccineDosage() {
            return VaccineDosage;
        }

        /**
         *Gets the value  of time Interval.
         * @return
         */
        public int getTimeIntervalDose() {
            return TimeIntervalDose;
        }

        /**
         *Set the value  of Vaccine Dosage.
         * @return
         */
        public void setVaccineDosage(float VaccineDosage) {
            this.VaccineDosage = this.VaccineDosage;
        }
        /**
         *sets the value  of time Interval.
         * @return
         */
        public void setTimeIntervalDose(int TimeIntervalDose) {
            this.TimeIntervalDose = TimeIntervalDose;
        }

        /**
         * Obtains in text format the information needed for a Dose.
         * @return
         */
        public String toString(){
            return "Dosage:"+ VaccineDosage +"ml, "+"interval time:"+TimeIntervalDose+" day.";
        }
    }
