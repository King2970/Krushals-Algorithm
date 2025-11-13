package util;

import java.util.LinkedList;

public class BinaryNode<E> {
    public E element;//data
    public BinaryNode<E> left;//left child
    public BinaryNode<E> right;//right child
    BinaryNode(E element){
        this(element, null, null);
    }
    BinaryNode(E element, BinaryNode<E> left, BinaryNode<E> right){
        this.left = left;
        this.right = right;
        this.element = element;
    }
    public int getHeight() {
        if(left == null && right == null)
            return 0;
        int height = 0;
        if(left != null)
            height = left.getHeight();
        if(right != null) {
            int temp = right.getHeight();
            if(height < temp)
                height = temp;
        }
        return height + 1;
    }
    public String toString2() {
        if(left == null && right == null)
            return element + "";
        return element + "(" + left + ", " + right + ")";
    }
    public String toString() {
        if(left == null && right == null && element == null)
            return "";
        LinkedList<BinaryNode<E>> list = new LinkedList<BinaryNode<E>>();
        String result = "";
        list.addFirst(this);
        list.addFirst(null);
        int h = getHeight();
        int level = (int) Math.pow(2,h);
        BinaryNode dummy = new BinaryNode(null);
        while(!list.isEmpty()) {
            boolean allDummies = true;
            for(BinaryNode<E> b: list)
                if(b != dummy && b != null) {
                    allDummies = false;
                    break;
                }
            BinaryNode<E> cur = list.removeLast();
            if(cur == null || allDummies)
                break;
            for(int i = 0; i < level;i++)
                result += '\t';
            if(cur != dummy)
                result += cur.element;
            for(int i = 0; i < level;i++)
                result += '\t';
            if(cur.left != null)
                list.addFirst(cur.left);
            else
                list.addFirst(dummy);
            if(cur.right != null)
                list.addFirst(cur.right);
            else
                list.addFirst(dummy);
            if(list.getLast() == null) {
                for(int i = 0; i < h;i++)
                    result += '\n';
                list.removeLast();
                list.addFirst(null);
                level/=2;
            }

        }
        return result + "\n";
    }
}