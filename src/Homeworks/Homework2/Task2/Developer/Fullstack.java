package Homeworks.Homework2.Task2.Developer;

import Homeworks.Homework2.Task2.AbstractClass.Developer;
import Homeworks.Homework2.Task2.Interface.IBackEndDev;
import Homeworks.Homework2.Task2.Interface.IFrontEndDev;

public class Fullstack extends Developer implements IBackEndDev, IFrontEndDev {
    @Override
    public void writeServerCode() {
        System.out.println("Write server code");
    }

    @Override
    public void programUIForms() {
        System.out.println("Write program UI Forms");
    }
}
