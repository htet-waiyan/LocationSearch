/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locationsearch.exception;

import javax.ejb.ApplicationException;

/**
 *
 * @author ht3t
 */
@ApplicationException
public class PlaceNotFoundException extends Exception{

    public PlaceNotFoundException(String message) {
        super(message);
    }
    
}
