package Lections.Lection3;

public class KVBox <K, V> {
    private K key;
    private V value;

    public KVBox(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue() {
        return value;
    }
    public K getKey() {
        return key;
    }
    public void showType() {
        System.out.printf("Type of key is %s, key = %s, " +
                "type of value is %s, value = %s\n",
                key.getClass().getName(), getKey(),
                value.getClass().getName(), getValue());
    }
}
