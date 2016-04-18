package com.example.school;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Teacher {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "teachers")
	private List<Student> students = new ArrayList<Student>();

	public void addStudent(Student student) {
		if (!this.students.contains(student)) {
			this.students.add(student);
		}
		if (!student.getTeachers().contains(this)) {
			student.getTeachers().add(this);
		}
	}

}
