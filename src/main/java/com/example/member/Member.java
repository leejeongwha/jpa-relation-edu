package com.example.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Member {

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	@OneToOne
	private Locker locker;

	// optional 로 notNull 제약조건을 추가할 수 있다.
	@ManyToOne()
	@JoinColumn(insertable = false, updatable = false)
	private Team team;

	public void setTeam(Team team) {
		if (this.team != null && this.team.getMembers().contains(this)) {
			this.team.getMembers().remove(this);
		}
		team.getMembers().add(this);

		this.team = team;
	}

	public void setLocker(Locker locker) {
		if (this.locker != null) {
			locker.setMember(null);
		}

		locker.setMember(this);
		this.locker = locker;
	}
}
