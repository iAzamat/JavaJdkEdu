package Lections.Lection3;

public class GBox<T> {
    private T value;
    public GBox(T value) {this.value = value;}

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void showType() {
        System.out.printf("Type is %s, with value (%s)\n",
                value.getClass().getName(), getValue());
    }
}
