package com.battleshipics4u.game;

public class Player {

    protected String name; //creates string accessable in same subclasses and packages.

    public Player(String name){
        this.name = name; //used in order to avoid mixing up the string with something that shares the same name
    }

    public static Player create(String name){
        return new Player(name); //returns new name if called upon
    }

    public String getName(){
        return name; //rturns name
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
