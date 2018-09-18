package com.jcemaciel.reactive;

import com.jcemaciel.reactive.model.Employee;
import com.jcemaciel.reactive.model.Freelancer;
import com.jcemaciel.reactive.processor.MyProcessor;
import com.jcemaciel.reactive.subscriber.SubscriberEmployee;
import com.jcemaciel.reactive.subscriber.SubscriberFreelancer;
import com.jcemaciel.reactive.utils.EmployeeUtils;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class MyReactiveAppWithProcessor {

    public static void main(String[] args) throws InterruptedException {

        // Create end Publisher
        SubmissionPublisher<Employee> publisher = new SubmissionPublisher<>();

        //Create Processor
        MyProcessor transformProcessor = new MyProcessor(s -> {
            return new Freelancer(s.getId(), s.getId() +100, s.getName());
        });

        //Create End Subscriber
        SubscriberFreelancer subs = new SubscriberFreelancer();

        //Create chain of publisher, processor and subscriber
        publisher.subscribe(transformProcessor); //publisher to a processor
        transformProcessor.subscribe(subs);

        //Publish Items
        System.out.println("Publishing items to subscriber");
        List<Employee> funcionarios = EmployeeUtils.getEmployees();
        funcionarios.stream().forEach(i-> publisher.submit(i));

        //Logic to wait for messages processing to finish
        while(funcionarios.size() != subs.getCounter()){
            Thread.sleep(10);
        }

        //Closing publisher
        publisher.close();
        transformProcessor.close();

        System.out.println("Exiting the App");

    }

}
