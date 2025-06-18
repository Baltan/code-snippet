package pattern.iterator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 15:21
 */
public interface Iterator<E> {
    boolean hasNext();

    E next();

    boolean hasPrevious();

    E previous();
}
