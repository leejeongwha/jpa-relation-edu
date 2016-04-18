package com.example.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Team {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	// 초기화 하는 것을 일반적으로 권장 함(null 체크가 필요 없으므로)
	@OneToMany()
	@JoinColumn(name = "team_id")
	private List<Member> members = new ArrayList<Member>();

}
