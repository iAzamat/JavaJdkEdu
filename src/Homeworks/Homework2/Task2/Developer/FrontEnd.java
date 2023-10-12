package Homeworks.Homework2.Task2.Developer;

import Homeworks.Homework2.Task2.AbstractClass.Developer;
import Homeworks.Homework2.Task2.Interface.IFrontEndDev;

public class FrontEnd extends Developer implements IFrontEndDev {
    @Override
    public void programUIForms() {
        System.out.println("Write program UI Forms");
    }
}
