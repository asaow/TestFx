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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Loki
 */

public class Question implements Serializable {
    
    private int id;
    private String question;
    private int courseId;
    //private String answer;
    //private String wrong1;
    //private String wrong2;
    //private String wrong3;

    private Collection<Answer>answers= new ArrayList<Answer>();

    public Collection<Answer> getAnswer() {
        return answers;
    }

    public void setAnswer(Collection<Answer> answer) {
        this.answers = answer;
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

//    public Question(int courseId, int id, String question, String answer,String wrong1, String wrong2, String wrong3 ){
//        this.courseId = courseId;
//        this.id = id;
//        this.question = question;
//        this.answer = answer;
//        this.wrong1 = wrong1;
//        this.wrong2 = wrong2;
//        this.wrong3 = wrong3;
//        
//    }



//    public String getWrong1() {
//        return wrong1;
//    }
//
//    public void setWrong1(String wrong1) {
//        this.wrong1 = wrong1;
//    }
//
//    public String getWrong2() {
//        return wrong2;
//    }
//
//    public void setWrong2(String wrong2) {
//        this.wrong2 = wrong2;
//    }
//
//    public String getWrong3() {
//        return wrong3;
//    }
//
//    public void setWrong3(String wrong3) {
//        this.wrong3 = wrong3;
//    }
//   
//        
//    public String getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(String answer) {
//        this.answer = answer;
//    }
    

}
