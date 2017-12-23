package mes.dbase;

import java.util.Vector;
import java.io.Serializable;
import java.util.Hashtable;
import java.sql.ResultSet;


public class DataCollection extends Object implements Serializable{
	
    Vector data = null;
    
    public  DataCollection(){
        data = new Vector();
    }
    
    
    public void add(Object d){
        data.addElement(d);
    }
    
    public void add(Object d, int index){
        data.insertElementAt (d, index);
    }
    public Object get(int i ){
        return data.elementAt(i);
    }
    
    public int size(){
        return data.size();
    }
    
    public void remove(String  primarykey, String value){
        try{
            for(int i = 0; i < size(); i++){
                Hashtable t = (Hashtable)get(i);
                if(t.get("primarykey").equals(value) ){
                    data.remove(i);
                }
            }
        }catch(Exception e){
            System.out.println("Exception while trying to remove a data on DataCollection : "+value);
        }
    }
    
    public Hashtable getHashtable(Object key, Object value){
        for(int i = 0; i < data.size(); i++){
            Hashtable h  = (Hashtable)data.get(i);
            if(h.get(key).toString().equals(value)){
                return h;
            }
        }
        return null;
    }
    
    public void remove(int index){
        data.removeElementAt(index);
    }
    
    public void remove(Object ob){
        data.removeElement(ob);
    }
    public void removeAllElements(){
        data.removeAllElements();
    }
    
    public void setSize(int s){
        data.setSize(s);
    }
   
    
    
    
}