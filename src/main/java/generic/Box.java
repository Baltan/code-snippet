package generic;


/**
 * Description:
 *
 * @author Baltan
 * @date 2018/4/16 20:32
 */
class Box<T> {

    private T object;

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }

    public static void main(String[] args) {
        Box box = new Box();
        box.set(new Cat());
        Cat c = (Cat) box.get();
    }
}