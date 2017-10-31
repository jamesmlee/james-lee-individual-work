/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao {
// member field of type Student – this represents the one and only Student in the DAO   
    private Student onlyStudent;
// member field of type List<Student> - this is a List containing the one and only Student    
    private List<Student> studentList = new ArrayList<>();
// Constructor – create and initialize the one and only Student and add the Student to our List   
    public ClassRosterDaoStubImpl() {
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("John");
        onlyStudent.setLastName("Doe");
        onlyStudent.setCohort("Java-Jan-2015");
       
        studentList.add(onlyStudent);
    }
/* if the Id of the Student being added equals that of the one and only Student in the DAO 
, it returns a reference to the one and only Student in the DAO. Otherwise, it returns null.  
Note that the incoming Student is never added to the DAO or persisted in any way */
    @Override
    public Student addStudent(String studentId, Student student) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }
    }
// returns the List containing the one and only Student 
    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return studentList;
    }
// If the Id being requested equals that of the one and only Student then it 
// returns a reference to the one and only Student.  Otherwise, it returns null. 
    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }       
    }
// If the Id being requested equals that of the one and only Student then it 
// returns a reference to the one and only Student.  Otherwise, it returns null.     
    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        if (studentId.equals(onlyStudent.getStudentId())) {
            return onlyStudent;
        } else {
            return null;
        }
    }   
}
