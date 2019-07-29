package com.gaodun.storm.study.common;

import com.gaodun.storm.study.common.util.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

public abstract class BaseDTO<T> {
    public void fromEntity(T entity) {
        if (Objects.isNull(entity)) {
            return;
        }
        BeanUtils.copy(entity, this);
    }

    public T toEntity() {
        try {
            T entity = ((Class<T>) ((ParameterizedType) this.getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            BeanUtils.copy(this, entity);
            return entity;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
