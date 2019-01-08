/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.outfittery.util;

import java.util.Date;
import java.util.List;

/**
 *
 * @author sarkhanrasullu
 */
public class ListUtil{
  
    
    public boolean removeDate(Date date, List<Date> list){
        for(Date d: list){
            if(d.equals(date)){
                return list.remove(d);
            }
        }
        
        return false;
    }
}
