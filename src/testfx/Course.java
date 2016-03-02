/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testfx;

/**
 *
 * @author Loki
 */
public class Course {
    private int id;
    private String name;
    private int teacherId;

    
    public Course(int id, String name, int teacherId){
       this.id = id;
       this.name = name;
       this.teacherId = teacherId;
    }
    
    public Course(){
        
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
    
    

