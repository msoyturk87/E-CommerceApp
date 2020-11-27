package com.cybertek.ecommerce.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDao<T,ID> {


    protected Map<ID,T> map = new HashMap<>();

    List<T> realAll(){
        return new ArrayList<>(map.values());
    }

    T readById(ID id){
        return map.get(id);
    }

    void create(T object){
    }


    void delete(ID id){

        map.entrySet().removeIf(entry -> entry.getValue().equals(id));
    }

    void update(T object,ID id){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
        map.put(id,object);
    }























}
