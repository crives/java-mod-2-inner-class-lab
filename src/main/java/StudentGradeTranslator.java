public class StudentGradeTranslator {

    GradeCalculator gradeCalculator;

    public StudentGradeTranslator() {
        this.gradeCalculator = new FlatCurveGradeCalculator();
    }

    public StudentGradeTranslator(String gradingMethod) {
        if (gradingMethod == null) {
            this.gradeCalculator = new FlatCurveGradeCalculator();
        } else if (gradingMethod.equals("FLAT")) {
            this.gradeCalculator = new FlatCurveGradeCalculator();
        } else if (gradingMethod.equals("SLIGHT")) {
            this.gradeCalculator = new SlightCurveGradeCalculator();
        } else if (gradingMethod.equals("HEAVY")) {
            this.gradeCalculator = new HeavyCurveGradeCalculator();
        }
    }

    public String getLetterGrade(int numberGrade) {
        return gradeCalculator.getLetterGrade(numberGrade);
    }

    public boolean isPassingGrade(int numberGrade) {
        return gradeCalculator.isPassingGrade(numberGrade);
    }

    interface GradeCalculator {
        public String getLetterGrade(int numberGrade);
        public boolean isPassingGrade(int numberGrade);
        public int howManyForNextLetterGrade(int numberGrade);
    }

    class FlatCurveGradeCalculator implements GradeCalculator {
        public String getLetterGrade(int numberGrade) {
            if (numberGrade < 60) {
                return "F";
            }
            if (numberGrade < 70) {
                return "D";
            }
            if (numberGrade < 80) {
                return "C";
            }
            if (numberGrade < 90) {
                return "B";
            }
            return "A";
        }
        
        public boolean isPassingGrade(int numberGrade) {
            if (numberGrade >= 60) return true;
            return false;
        }

        public int howManyForNextLetterGrade(int numberGrade) {
            int remainder = 0;

            if (numberGrade >= 60  && numberGrade < 90) {
                remainder = (int)(Math.ceil(numberGrade * .10) * 10) - numberGrade;
            } else if (numberGrade < 60) {
                remainder = 70 - numberGrade;
            }
            return remainder;
        }
    }

    class SlightCurveGradeCalculator implements GradeCalculator {
        @Override
        public String getLetterGrade(int numberGrade) {
            if (numberGrade < 55) {
                return "F";
            }
            if (numberGrade < 65) {
                return "D";
            }
            if (numberGrade < 75) {
                return "C";
            }
            if (numberGrade < 85) {
                return "B";
            }
            return "A";
        }

        public boolean isPassingGrade(int numberGrade) {
            if (numberGrade >= 55) return true;
            return false;
        }

        public int howManyForNextLetterGrade(int numberGrade) {
            int remainder = 0;
            if (getLetterGrade(numberGrade) == "B") {
                remainder = 85 % numberGrade;
            } 
            if (getLetterGrade(numberGrade) == "C") {
                remainder = 75 % numberGrade;
            } 
            if (getLetterGrade(numberGrade) == "D") {
                remainder = 65 % numberGrade;
            }
            if (getLetterGrade(numberGrade) == "F") {
                remainder = 65 - numberGrade;
            }
            return remainder;
        }
    }

    class HeavyCurveGradeCalculator implements GradeCalculator {
        public String getLetterGrade(int numberGrade) {
            if (numberGrade < 50) {
                return "F";
            }
            if (numberGrade < 60) {
                return "D";
            }
            if (numberGrade < 70) {
                return "C";
            }
            if (numberGrade < 80) {
                return "B";
            }
            return "A";
        }

        public boolean isPassingGrade(int numberGrade) {
            if (numberGrade >= 50) return true;
            return false;
        }
        
        public int howManyForNextLetterGrade(int numberGrade) {
            int remainder = 0;
            if (numberGrade >= 50 && numberGrade < 80) {
                remainder = (int)(Math.ceil(numberGrade * .10) * 10) - numberGrade;
            } else if (numberGrade < 50) {
                remainder = 60 - numberGrade;
            }
            return remainder;
        }
    }
}