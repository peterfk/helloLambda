/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hellolambda;

/**
 *
 * @author pkipping
 */
//new java 8 interface
public interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        //return square root
        return Math.sqrt(a);
    }

}
