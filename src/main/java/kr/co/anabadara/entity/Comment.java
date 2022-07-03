package kr.co.anabadara.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentNum;

	@Column
	private Long id;

	@Column
	private String name;

	@Column
	private String content;

	@Column
	private Long buyNum;

	@Column
	private Long sellNum;

	@ManyToOne(targetEntity = BoardBuy.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "buyNum", insertable = false, updatable = false)
	@JsonIgnore
	private BoardBuy boardBuy;

	@ManyToOne(targetEntity = BoardSell.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "sellNum", insertable = false, updatable = false)
	@JsonIgnore
	private BoardSell boardSell;

	@ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "id", insertable = false, updatable = false)
	@JsonIgnore
	private Member member;

	@Builder
	public Comment(Long commentNum, Long id, String name, String content, Long buyNum, Long sellNum) {
		super();
		this.commentNum = commentNum;
		this.id = id;
		this.name = name;
		this.content = content;
		this.buyNum = buyNum;
		this.sellNum = sellNum;
	}

}
