package com.jcemaciel.reactive;

import com.jcemaciel.reactive.model.Employee;
import com.jcemaciel.reactive.subscriber.SubscriberEmployee;
import com.jcemaciel.reactive.utils.EmployeeUtils;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class MyReactiveApp {

    public static void main(String[] args) throws InterruptedException {

        //Create a publisher
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

        //Register a subscriber
        SubscriberEmployee subs = new SubscriberEmployee();
        publisher.subscribe(subs);

        List<Employee> Employees = EmployeeUtils.getEmployees();

        // Publish items
        System.out.println("Publishing Items to Subscriber");
        Employees.parallelStream().forEach(i -> publisher.submit(i));

        // logic to wait till processing of all messages are over
        while (Employees.size() != subs.getCounter()) {
            Thread.sleep(10);
        }
        // close the Publisher
        publisher.close();

        System.out.println("Exiting the app");

    }

}
