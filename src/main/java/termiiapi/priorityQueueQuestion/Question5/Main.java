package termiiapi.priorityQueueQuestion.Question5;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalEvents = Integer.parseInt(input.nextLine());
        PriorityQueue<Student> pq = new PriorityQueue(totalEvents, new StudentComparator());
        while(totalEvents > 0){
            String event = input.next();
            if (event.equals("ENTER")) {
                String name = input.next();
                double cgpa = input.nextDouble();
                int id = input.nextInt();
                Student s = new Student(id, name, cgpa);
                pq.add(s);
            }
            else {
                pq.poll();
            }

            totalEvents--;
        }
        if (pq.isEmpty()) {
            System.out.println("EMPTY");
        }
        while (!pq.isEmpty()) {
            System.out.println(pq.poll().getName());
        }
    }
}
