package Lections.Lection3;

import java.lang.reflect.GenericDeclaration;

import static Lections.Lection3.BBox.setIfNull;

public class Main {
    public static void main(String[] args) {
        Box b1 = new Box(20);
        Box b2 = new Box(30);
        System.out.println((Integer) b1.getObj() + (Integer) b2.getObj());

        Box b3 = new Box("Hello, ");
        Box b4 = new Box("World!");
        System.out.println((String) b3.getObj() + (String) b4.getObj());

        GBox<String> stringBox = new GBox<>("Hello");
        stringBox.showType();
        GBox<Integer> integerBox = new GBox<>(12);
        integerBox.showType();
        GBox<Boolean> newBox = new GBox<>(true);
        newBox.showType();

        KVBox<Integer, String> kvb0 = new KVBox<>(1, "Hello");
        KVBox<String, GBox<String>> kvb1 = new KVBox<>("World", new GBox<>("Java"));
        kvb0.showType();
        kvb1.showType();
        BBox<Integer> integerBBox = new BBox<>();
        integerBBox.showType();
        setIfNull(integerBBox, 4);
        integerBBox.showType();
    }
}
