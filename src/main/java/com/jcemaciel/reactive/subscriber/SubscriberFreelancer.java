package com.jcemaciel.reactive.subscriber;

import com.jcemaciel.reactive.model.Freelancer;
import lombok.Getter;

import java.util.concurrent.Flow;

@Getter
public class SubscriberFreelancer implements Flow.Subscriber<Freelancer> {

    private Flow.Subscription subscription;
    private int counter = 0;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Subscribed for Freelancer");
        this.subscription = subscription;
        this.subscription.request(1); //requesting data from publisher
        System.out.println("onSubscribe requested 1 item");
    }

    @Override
    public void onNext(Freelancer item) {
        System.out.println("Processing Freelancer " + item);
        counter++;
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("Some error happened on SubscriberFreelancer");
        e.printStackTrace();

    }

    @Override
    public void onComplete() {
        System.out.println("All Processing Done SubscriberFreelancer");
    }


}
