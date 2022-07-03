package kr.co.anabadara.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor 
public class BoardBuy extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long buyNum;
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String title;
	@Column
	private String category;
	@Column
	private String product;
	@Column
	private String price;
	@Column
	private String place;
	@Column
	private String content;
	
	@ManyToOne(targetEntity = Member.class, fetch=FetchType.LAZY)
	@JoinColumn(name="id", insertable=false, updatable=false)
	@JsonIgnore
	private Member member;
	
	@OneToMany(mappedBy = "boardBuy", cascade = CascadeType.REMOVE)
	@JsonIgnore
	List<Comment> comments = new ArrayList<Comment>();

	@Builder
	public BoardBuy(Long buyNum, Long id, String name, String title, String category, String product, String price,
			String place, String content) {
		this.buyNum = buyNum;
		this.id = id;
		this.name = name;
		this.title = title;
		this.category = category;
		this.product = product;
		this.price = price;
		this.place = place;
		this.content = content;
	}
	
	

}
