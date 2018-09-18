package com.jcemaciel.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer extends Employee {

    private int tid;

    public Freelancer(int id, int tid, String name){
        super(id, name);
        this.tid = tid;
    }
}
