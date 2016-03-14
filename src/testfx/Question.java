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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author Grupp 2
 */

public class Question implements Serializable {
    
    private int id;
    private String question;
    private int courseId = 1;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static final String RADIO_TYPE = "ETT SVAR";
    public static final String CHECKBOX_TYPE = "FLERA SVAR";


    public static String getRADIO_TYPE() {
        return RADIO_TYPE;
    }

    public static String getCHECKBOX_TYPE() {
        return CHECKBOX_TYPE;
    }


    private Collection<Answer>answers= new ArrayList<Answer>();

    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }
    
        
    public Question(){
        
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
