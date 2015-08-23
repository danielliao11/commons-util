package com.saintdan.util.commons.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Productivity utilities for Java arrays.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 3/31/15
 * @since JDK1.8
 */
public final class ArrayUtil {

    private ArrayUtil() {
    }

    /**
     * Null-safe isEmpty check.
     * @param array which to be checked
     * @return true or false
     */
    public static boolean isEmpty(final Object array) {
        return array == null || Array.getLength(array) == 0;
    }

    /**
     * Null-safe size check.
     * @param array which to be size checked
     * @return array.length
     */
    public static int size(final Object array) {
        return array == null ? 0 : Array.getLength(array);
    }

    /**
     * Null-safe shallow clone. Only clones the object, not the reference of that object.
     * @param array which to be cloned
     * @param <T> type T
     * @return <T> cloned array
     */
    public static <T> T[] shallowClone(final T[] array) {
        if (array == null) {
            return null;
        }
        T[] cloneArrary = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length);
        System.arraycopy(array, 0, cloneArrary, 0, array.length);
        return cloneArrary;
    }

    /**
     * Array add. Maybe likes Python, and just "like". :)
     * @param a left
     * @param b right
     * @param <T> type T
     * @return <T> a + b
     * null + null = null
     * a + null = a
     * null + b = b
     * a + b = a + b
     */
    public static <T> T[] add(final T[] a, final T[] b) {
        if (a == null) {
            return shallowClone(b);
        } else if (b == null) {
            return shallowClone(a);
        }
        final T[] newArray = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length + b.length);
        System.arraycopy(a, 0, newArray, 0, a.length);
        System.arraycopy(b, 0, newArray, a.length, b.length);
        return newArray;
    }

    /**
     * Imitate Python's append.
     * @param array source array
     * @param item  the last item
     * @param <T>   type T
     * @return <T>  new array with the item in the last.
     */
    public static <T> T[] append(final T[] array, T item) {
        if (item == null) {
            return array;
        }
        T[] lastItem = (T[]) Array.newInstance(array.getClass().getComponentType(), 1);
        lastItem[0] = item;
        return add(array, lastItem);
    }

    /**
     * Imitate Python's Pop. Pop the last item. bear -> koala bear
     * @param array "Don't cut my tail! ToT"
     * @param <T>   type T
     * @return <T>  koala bear
     */
    public static <T> T[] pop(final T[] array) {
        return pop(array, size(array)-1);
    }

    /**
     * Array remove item. Likes Python.
     * @param array    who wants to be slim. :)
     * @param position where to remove
     * @param <T>      type T
     * @return <T>     slim one who just lose weight at that "position"
     */
    public static <T> T[] pop(T[] array, int position) {
        if (isEmpty(array)) {
            return array;
        }
        int length = Array.getLength(array);
        T[] slimOne = (T[]) Array.newInstance(array.getClass().getComponentType(), length - 1);

        System.arraycopy(array, 0, slimOne, 0, position);
        System.arraycopy(array, position + 1, slimOne, position, length - position - 1);
        return slimOne;
    }

    /**
     * Imitate Python's slice. Some toast, sir?
     * @param array Source array
     * @param start start position
     * @param end   end position
     * @param <T>   type T
     * @return <T>  New Array
     */
    public static <T> T[] slice(T[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end);
    }

}
