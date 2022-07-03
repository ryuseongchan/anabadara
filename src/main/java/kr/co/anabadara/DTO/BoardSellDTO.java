package kr.co.anabadara.DTO;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.anabadara.entity.BoardSell;
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
public class BoardSellDTO {
	private Long sellNum;
	private Long id;
	private String name;
	private String title;
	private String category;
	private String product;
	private String price;
	private String place;
	private String image;
	private String content;
	private LocalDateTime regdate;
	private List<CommentDTO> comments;

	public BoardSell toEntity() {
    	return BoardSell.builder()
    			.sellNum(sellNum)
    			.category(category)
    			.content(content)
    			.id(id)
    			.image(image)
    			.name(name)
    			.place(place)
    			.price(price)
    			.product(product)
    			.title(title)
    			.build();
    }
}
