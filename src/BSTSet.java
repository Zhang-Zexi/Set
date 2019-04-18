/**
 * @ClassName BSTSet
 * @Description
 * @Author zhangzx
 * @Date 2019/4/18 11:10
 * Version 1.0
 **/
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contain(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty(E e) {
        return bst.isEmpty();
    }
}
