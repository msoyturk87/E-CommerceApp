package com.cybertek.ecommerce.service;

import java.util.List;

public interface CrudService<T,ID>{

    List<T> readAll();

    T readById(ID id);

    void create(T object);

    void delete(ID id);


    void update(T object,ID id);
}
