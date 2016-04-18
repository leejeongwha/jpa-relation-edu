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
public class Student {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToMany
	private List<Teacher> teachers = new ArrayList<Teacher>();

	public void addTeacher(Teacher teacher) {
		if (!this.teachers.contains(teacher)) {
			this.teachers.add(teacher);
		}
		if (!teacher.getStudents().contains(this)) {
			teacher.getStudents().add(this);
		}
	}
}
