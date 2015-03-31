package com.saintdan.util;


import org.junit.Assert;
import org.junit.Test;

/**
 * @author LiaoYifan
 * @date 3/31/15
 * @since JDK1.8
 */
public class TestArrayUtil {

    @Test
    public void testIsEmpty() throws Exception {
        Assert.assertTrue(ArrayUtil.isEmpty(new byte[]{}));
        Assert.assertTrue(ArrayUtil.isEmpty(null));
        Assert.assertFalse(ArrayUtil.isEmpty(new byte[]{3}));
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(0, ArrayUtil.size(new byte[]{}));
        Assert.assertEquals(0,ArrayUtil.size(null));
        Assert.assertEquals(3,ArrayUtil.size(new byte[]{1, 2, 3}));
    }

    @Test
    public void testSafeClone() throws Exception {
        // Test null
        Assert.assertNull(ArrayUtil.shallowClone(null));

        // Test String
        String[] strings = new String[]{"a", "b", "c"};
        String[] cloneStrings = ArrayUtil.shallowClone(strings);
        Assert.assertNotSame(strings, cloneStrings);
        Assert.assertArrayEquals(strings, cloneStrings);
        int i = 0;
        for (String string: strings) {
            Assert.assertSame(string, cloneStrings[i++]);
        }

        // Test Integer
        Integer[] integers = new Integer[]{1, 1, 2, 3, 5, 8};
        Integer[] cloneIntegers = ArrayUtil.shallowClone(integers);
        Assert.assertNotSame(integers, cloneIntegers);
        Assert.assertArrayEquals(integers, cloneIntegers);
        int j = 0;
        for (Integer integer: integers) {
            Assert.assertEquals(integer, cloneIntegers[j++]);
        }
    }

    @Test(expected=ArrayStoreException.class)
    public void testAdd() throws Exception {
        // Test a + null
        Double[] doubles1 = new Double[] {1.1, 2.2, 3.3};
        Object[] result1 = ArrayUtil.add(doubles1, null);
        Assert.assertNotSame(doubles1, result1);
        int i = 0;
        for (Double d: doubles1) {
            Assert.assertEquals(d, result1[i++]);
        }

        // Test null + b
        Double[] doubles2 = new Double[]{11.11, 22.22, 33.33};
        Object[] result2 = ArrayUtil.add(null, doubles2);
        Assert.assertNotSame(doubles2, result2);
        int j = 0;
        for (Double d: doubles2) {
            Assert.assertEquals(d, result2[j++]);
        }

        // Test a + b
        Object[] result3 = ArrayUtil.add(doubles1, doubles2);
        Assert.assertNotSame(doubles1, result3);
        Assert.assertNotSame(doubles2, result3);
        for (int k=0;k<doubles1.length;k++) {
            Assert.assertEquals(doubles1[k], result3[k]);
        }
        for (int k=0;k<doubles2.length;k++) {
            Assert.assertEquals(doubles2[k], result3[doubles1.length + k]);
        }

        // Test a + b different type
        String[] strings = new String[]{"a", "b"};
        Object[] result4 = ArrayUtil.add(doubles1, strings);
        for (int k=0;k<doubles1.length;k++) {
            Assert.assertEquals(doubles1[k], result4[k]);
        }
        for (int k=0;k<strings.length;k++) {
            Assert.assertSame(strings[k], result4[doubles1.length + k]);
        }
    }

    @Test
    public void testAppend() throws Exception {
        String[] strings = new String[]{"a", "b"};
        String lastItem = "c";
        Object[] result = ArrayUtil.append(strings, lastItem);
        for (int i=0;i<strings.length;i++) {
            Assert.assertSame(strings[i], result[i]);
        }
        Assert.assertSame(lastItem, result[result.length - 1]);
    }

    @Test
    public void testPop() throws Exception {
        String[] strings = new String[]{"a", "b", "c"};
        String[] string02 = new String[]{"a", "c"};
        Object[] result1 = ArrayUtil.pop(strings, 1);
        for (int i=0;i<string02.length;i++) {
            Assert.assertSame(result1[i], string02[i]);
        }

        String[] string01 = new String[]{"a","b"};
        Object[] result2 = ArrayUtil.pop(strings);
        for (int i=0;i<string01.length;i++) {
            Assert.assertSame(result2[i], string01[i]);
        }
    }

    @Test
    public void testSlice() throws Exception {
        String[] toast = new String[]{"one piece", "piece2", "piece3", "piece4"};
        String[] pieces = ArrayUtil.slice(toast, 1, 3);
        String[] piece = ArrayUtil.slice(toast, 0, 1);

        Assert.assertSame(toast[1], pieces[0]);
        Assert.assertSame(toast[2], pieces[1]);

        Assert.assertSame(toast[0], piece[0]);
        System.out.println("Monkey.D.Luffy: \"" + piece[0].toUpperCase() + " must be mine! I will be the king of pirates!\"");
    }
}
