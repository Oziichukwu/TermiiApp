package termiiapi.priorityQueueQuestion.Question5;

import java.util.Comparator;

class StudentComparator implements Comparator<Student> {


    @Override
    public int compare(Student s1, Student s2) {

        if(s1.getCgpa() > s2.getCgpa()){
            return -1;
        }
        if (s1.getCgpa() < s2.getCgpa()) {
            return 1;
        }
        if (s1.getName().compareTo(s2.getName()) < 0) {
            return -1;
        }
        if (s1.getName().compareTo(s2.getName()) > 0) {
            return 1;
        }
        if (s1.getId() < s2.getId()) {
            return -1;
        }
        if (s1.getId() > s2.getId()) {
            return 1;
        }
        return 0;
    }
}
