/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;

import java.util.List;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author rakib
 */
public class Utilities {

    public static String toString(Object obj) {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(obj.getClass().getSimpleName());
        result.append("[");
        result.append(newLine);

        List<Field> fields = getAllModelFields(obj.getClass());
        for(Field field:fields){
            result.append(" ");
            try{
                result.append(field.getName());
                result.append(": ");
                field.setAccessible(true);
                result.append(field.get(obj));
            }catch(IllegalAccessException ex){
                
            }
            result.append(newLine);
        
        }
        result.append("]");
        result.append(newLine);
        return result.toString();
    }

    private static List<Field> getAllModelFields(Class<?> aClass) {

        List<Field> fields = new ArrayList<>();
        do {
            //Collections.addAll(fields, aClass.getDeclaredField());
            Collections.addAll(fields, aClass.getDeclaredFields());
            aClass = aClass.getSuperclass();
        } while (aClass != null);
        
        return fields;
    }

}
