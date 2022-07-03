package kr.co.anabadara;

import java.io.Serializable;

import kr.co.anabadara.entity.Member;

public class SessionMember implements Serializable {
	private Long id;
	private String name;
	private String email;
	private String image;

	public SessionMember(Member member) {
		this.id = member.getId();
		this.name = member.getName();
		this.email = member.getEmail();
		this.image = member.getImage();
	}

	public SessionMember() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
