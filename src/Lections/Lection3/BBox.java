package Lections.Lection3;

public class BBox <V extends Number> {
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    private V value;

    public static <T extends Number> void setIfNull (BBox<T> box, T t) {
        if (box.getValue() == null) {
            box.setValue(t);
        }
    }
    public void showType() {
        if (getValue() == null) {
            System.out.println("Value of your item is Null");
        } else {
            System.out.printf("Type is %s, with value (%s)\n",
                    value.getClass().getName(), getValue());
        }

    }
}
