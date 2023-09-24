package com.ch.kafka.producer;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ChenHao
 * @date 2023/9/25 0:05
 */
public class A {
//    public static void main(String[] args) {
//        List<String> l1 = Arrays.asList("A1", "B1");
//        List<String> l2 = Arrays.asList("A2", "B2");
//        List<String> l3 = Arrays.asList("A3", "B3");
//
//        ArrayList<String> strings = new ArrayList<>();
//
//
//        List<List<String>> lists = Lists.cartesianProduct(l1, l2, l3);
//        // 格式化输出，便于观察
//        for (int i = 0, len = l1.size() * l2.size() * l3.size(); i < len; i++) {
//            System.out.print(lists.get(i) + "\t");
//            if ((i + 1) % l3.size() == 0) {
//                System.out.println();
//            }
//        }
//    }

    public static void main(String[] args) {
        List<List<String>> listData = new ArrayList<>();
        listData.add(Arrays.asList("A1", "B1"));
        listData.add(Arrays.asList("A2", "B2"));
        listData.add(Arrays.asList("A3", "B3"));
//        listData.add(Arrays.asList("A4", "B4"));
        List<List<String>> lisReturn = getDescartes(listData);
        System.out.println(lisReturn);
        System.out.println(lisReturn.size());

        for (List<String> list : lisReturn) {

        }
    }

    private static <T> List<List<T>> getDescartes(List<List<T>> list) {
        List<List<T>> returnList = new ArrayList<>();
        descartesRecursive(list, 0, returnList, new ArrayList<T>());
        return returnList;
    }

    /**
     * 递归实现
     * 原理：从原始list的0开始依次遍历到最后
     *
     * @param originalList 原始list
     * @param position     当前递归在原始list的position
     * @param returnList   返回结果
     * @param cacheList    临时保存的list
     */
    private static <T> void descartesRecursive(List<List<T>> originalList, int position, List<List<T>> returnList, List<T> cacheList) {
        List<T> originalItemList = originalList.get(position);
        for (int i = 0; i < originalItemList.size(); i++) {
            //最后一个复用cacheList，节省内存
            List<T> childCacheList = (i == originalItemList.size() - 1) ? cacheList : new ArrayList<>(cacheList);
            childCacheList.add(originalItemList.get(i));
            if (position == originalList.size() - 1) {
                //遍历到最后退出递归
                returnList.add(childCacheList);
                continue;
            }
            descartesRecursive(originalList, position + 1, returnList, childCacheList);
        }
    }
}