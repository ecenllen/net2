package com.yydd.net.net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: liaohaiping Time: 2019-04-09 Description:
 */
public class Linq<T> {

    private List<T> list;

    public static <E> Linq<E> of(Iterable<E> list) {
        return new Linq<>(list);
    }

    public static <E> Linq<E> of(E[] list) {
        return new Linq<>(list);
    }

    private Linq(Iterable<T> in) {
        this.list = new ArrayList<>();
        if (in != null) {
            for (T t : in) {
                list.add(t);
            }
        }
    }

    private Linq(T[] in) {
        this.list = new ArrayList<>();
        if (in != null) {
            for (T t : in) {
                list.add(t);
            }
        }
    }

    public Linq<T> where(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return of(result);
    }

    public int count(Predicate<T> predicate) {
        int count = 0;
        for (T t : list) {
            if (predicate.test(t)) {
                count++;
            }
        }
        return count;
    }

    public Linq<T> sort(Comparator<T> comparator) {
        Collections.sort(list, comparator);
        return this;
    }

    /**
     * 在list1中查找与list2中一样的数据
     */
    public Linq<T> leftJoin(List<T> list2, Equaler<T> equaler) {
        List<T> result = new ArrayList<>();
        for (T t1 : list) {
            for (T t2 : list2) {
                if (equaler.equals(t1, t2)) {
                    result.add(t1);
                    break;
                }
            }
        }
        return of(result);
    }

    /**
     * 在list1中查找与list2中一样的数据
     */
    public <T2> Linq<T> leftJoinOtherType(List<T2> list2, KindEqualer<T, T2> equaler) {
        List<T> result = new ArrayList<>();
        for (T t1 : list) {
            for (T2 t2 : list2) {
                if (equaler.equals(t1, t2)) {
                    result.add(t1);
                    break;
                }
            }
        }
        return of(result);
    }


    public <TField> Map<TField, List<T>> groupBy(Converter<T, TField> fieldSelector) {
        Map<TField, List<T>> groups = new HashMap<>();
        for (T t : list) {
            TField f = fieldSelector.convert(t);
            List<T> val = groups.get(f);
            if (groups.containsKey(f)) {
                groups.get(f).add(t);
            } else {
                groups.put(f, Arrays.asList(t));
            }
        }
        return groups;
    }

    /**
     * 转换
     */
    public <TResult> Linq<TResult> map(Converter<T, TResult> converter) {
        List<TResult> result = new ArrayList<>();
        for (T t : list) {
            TResult r = converter.convert(t);
            result.add(r);
        }
        return of(result);
    }

    /**
     * 转换
     */
    public <TResult> Linq<TResult> flatMap(Converter<T, List<TResult>> converter) {
        List<TResult> result = new ArrayList<>();
        for (T t : list) {
            List<TResult> r = converter.convert(t);
            result.addAll(r);
        }
        return of(result);
    }

    public int sumInt() {
        int sum = 0;
        for (T t : list) {
            if (t instanceof Integer) {
                sum += ((Integer) t).intValue();
            } else if (t instanceof Float) {
                sum += ((Float) t).intValue();
            } else if (t instanceof Double) {
                sum += ((Double) t).intValue();
            }
        }
        return sum;
    }

    public float sumFloat() {
        float sum = 0f;
        for (T t : list) {
            if (t instanceof Integer) {
                sum += ((Integer) t).floatValue();
            } else if (t instanceof Float) {
                sum += ((Float) t).floatValue();
            } else if (t instanceof Double) {
                sum += ((Double) t).floatValue();
            }
        }
        return sum;
    }

    public double sumDouble() {
        float sum = 0f;
        for (T t : list) {
            if (t instanceof Integer) {
                sum += ((Integer) t).doubleValue();
            } else if (t instanceof Float) {
                sum += ((Float) t).doubleValue();
            } else if (t instanceof Double) {
                sum += ((Double) t).doubleValue();
            }
        }
        return sum;
    }


    public String stringJoin(String delimiter, Converter<T, String> converter) {
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            sb.append(delimiter + converter.convert(t));
        }
        return sb.substring(delimiter.length());
    }


    public T first() {
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public T first(Predicate<T> predicate) {
        for (T t : list) {
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    public T last() {
        if (list.size() > 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    public T last(Predicate<T> predicate) {
        for (int i = list.size() - 1; i >= 0; i--) {
            T t = list.get(i);
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }

    public Linq<T> forEach(Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
        return this;
    }


    /**
     * 方法用于把数组中的所有元素放入一个字符串。
     *
     * @param converter
     * @return
     */
    public String join(Converter<T, String> converter, String spliter) {
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            String r = converter.convert(t);
            if (sb.length() > 0) {
                sb.append(spliter);
            }
            sb.append(r);
        }
        return sb.toString();
    }

    /**
     * 方法用于把数组中的所有元素放入一个字符串。
     *
     * @return
     */
    public String join(String spliter) {
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            if (sb.length() > 0) {
                sb.append(spliter);
            }
            if (t != null) {
                sb.append(t.toString());
            }
        }
        return sb.toString();
    }


    public List<T> toList() {
        return this.list;
    }


    public static interface Predicate<E> {

        boolean test(E e);
    }

    public static interface Converter<E, ER> {

        ER convert(E e);
    }

    public static interface Equaler<E> {

        boolean equals(E e1, E e2);
    }

    public static interface KindEqualer<T1, T2> {

        boolean equals(T1 e1, T2 e2);
    }

    public static interface Consumer<T> {
        void accept(T t);
    }

    public static final Equaler<String> STRING_EQUALER = new Equaler<String>() {
        @Override
        public boolean equals(String e1, String e2) {
            if (e1 == null || e2 == null) {
                return false;
            }
            return e1.equals(e2);
        }
    };
    public static final Equaler<Integer> INTEGER_EQUALER = new Equaler<Integer>() {
        @Override
        public boolean equals(Integer e1, Integer e2) {
            if (e1 == null || e2 == null) {
                return false;
            }
            return e1.equals(e2);
        }
    };
    public static final Equaler<Boolean> BOOLEAN_EQUALER = new Equaler<Boolean>() {
        @Override
        public boolean equals(Boolean e1, Boolean e2) {
            if (e1 == null || e2 == null) {
                return false;
            }
            return e1.equals(e2);
        }
    };

}
