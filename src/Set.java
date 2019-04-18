/**
 * @ClassName Set
 * @Description
 * @Author zhangzx
 * @Date 2019/4/18 11:06
 * Version 1.0
 **/
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty(E e);
}
