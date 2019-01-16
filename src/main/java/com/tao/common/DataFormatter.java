package com.tao.common;


/**
 * Created by huangtao8 on 2019/1/15.
 */
public interface DataFormatter<T, E> {
    E format(T data);

    class DefaultFormatter<T> implements DataFormatter<T, T> {
        @Override
        public T format(T data) {
            return data;
        }
    }
}
