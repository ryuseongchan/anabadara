package kr.co.anabadara.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor 
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String image;
	@Column
	private String mobile;
	@Column
	private String role = "ROLE_USER";
	
	@OneToMany(mappedBy = "member")
	List<BoardBuy> boardBuyList = new ArrayList<BoardBuy>();
	
	@OneToMany(mappedBy = "member")
	List<BoardSell> boardSellList = new ArrayList<BoardSell>();
	
	@OneToMany(mappedBy = "member")
	List<Comment> comments = new ArrayList<Comment>();
	
	
	public Member(String name, String email, String image, String mobile) {
		this.name = name;
		this.email = email;
		this.image = image;
		this.mobile = mobile;
	}
	
	public Member update(String name, String image, String mobile) {
		this.name = name;
		this.image = image;
		this.mobile = mobile;
		return this;
	}
	

}
