/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

import com.sg.classroster.dto.Student;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class ClassRosterDaoTest {
// get an instance for our dao and initialize it    

    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    public ClassRosterDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

// put dao in a known good state. our known good state is empty
// if you already have students, adding another determines total     
    @Before
    public void setUp() throws Exception {
// ask dao for all students        
        List<Student> studentList = dao.getAllStudents();
// iterate through and remove all of them, leaving us with an empty dao
        for (Student student : studentList) {
            dao.removeStudent(student.getStudentId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addStudent method, of class ClassRosterDao.
     */
    @Test
    public void testAddGetStudent() throws Exception {
// set up some students add student in dao, get it back out and make sure they're equal
// Arrange
        Student student = new Student("0001");
        student.setFirstName("Joe");
        student.setLastName("Smith");
        student.setCohort("Java-May-2000");
// Act
        dao.addStudent(student.getStudentId(), student);
// declare another student object; the one associated with id 0001        
        Student fromDao = dao.getStudent(student.getStudentId());
// Assert that our objects are equal        
        assertEquals(student, fromDao);
    }

    /**
     * Test of getAllStudents method, of class ClassRosterDao.
     */
    @Test
    public void testGetAllStudents() throws Exception {
// Act: create and add 2 student objects to dao 
        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");

        dao.addStudent(student1.getStudentId(), student1);

        Student student2 = new Student("0002");
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET-May-2000");

        dao.addStudent(student2.getStudentId(), student2);
// Act: get all the student objects from dao
// Assert: check to see the dao returned 2 objects
// both done in 1 line below
        assertEquals(2, dao.getAllStudents().size());
    }

    /**
     * Test of removeStudent method, of class ClassRosterDao.
     */
    @Test
    public void testRemoveStudent() throws Exception {
// Arrange: add 2 student objects to the dao
        Student student1 = new Student("0001");
        student1.setFirstName("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");

        dao.addStudent(student1.getStudentId(), student1);

        Student student2 = new Student("0002");
        student2.setFirstName("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET-May-2000");

        dao.addStudent(student2.getStudentId(), student2);
// Act: remove 1 of the students
        dao.removeStudent(student1.getStudentId());
// Assert: check to see there's only 1 student left        
        assertEquals(1, dao.getAllStudents().size());
// Assert: check to see the DAO returns null if we try to retrieve the removed Student        
        assertNull(dao.getStudent(student1.getStudentId()));
// Act: remove the other Student from the DAO
        dao.removeStudent(student2.getStudentId());
// Assert: check to see that there are no Students in the DOA        
        assertEquals(0, dao.getAllStudents().size());
// Assert: check to see that the DAO returns null if we try to retrieve the removed Student         
        assertNull(dao.getStudent(student2.getStudentId()));
    }

}
