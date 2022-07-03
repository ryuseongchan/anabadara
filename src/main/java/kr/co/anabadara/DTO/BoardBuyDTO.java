package kr.co.anabadara.DTO;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.anabadara.entity.BoardBuy;
import kr.co.anabadara.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardBuyDTO {
	
	private Long buyNum;
	private Long id;
	private String name;
	private String title;
	private String category;
	private String product;
	private String price;
	private String place;
	private String content;
	private LocalDateTime regdate;
	private List<Comment> comments;
	
	public BoardBuy toEntity() {
    	return BoardBuy.builder()
    			.buyNum(buyNum)
    			.id(id)
    			.name(name)
    			.title(title)
    			.category(category)
    			.product(product)
    			.price(price)
    			.place(place)
    			.content(content)
    			.build();
    }
}
