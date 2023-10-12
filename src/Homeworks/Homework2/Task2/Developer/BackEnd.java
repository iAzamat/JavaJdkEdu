package Homeworks.Homework2.Task2.Developer;

import Homeworks.Homework2.Task2.AbstractClass.Developer;
import Homeworks.Homework2.Task2.Interface.IBackEndDev;

public class BackEnd extends Developer implements IBackEndDev {

    @Override
    public void writeServerCode() {
        System.out.println("Write server code");
    }
}
