/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pegasus.petsoft.dal;

import java.util.ArrayList;

/**
 *
 * @author luis.moraes
 * @param <T> seu tipo de objeto
 */
public interface IRepository<T> {
    boolean insert(T t);
    T retrieve(int id);
    ArrayList<T> retrieveAll();
    boolean update(T t);
    boolean delete(int id);
}
