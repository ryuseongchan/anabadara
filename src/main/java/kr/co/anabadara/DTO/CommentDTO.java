package kr.co.anabadara.DTO;

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
public class CommentDTO {
	
	private Long commentNum;
	private Long id;
	private String name;
	private String content;
	private Long buyNum;
	private Long sellNum;
	
	public Comment toEntity() {
		
    	return Comment.builder()
    			.commentNum(commentNum)
    			.buyNum(buyNum)
    			.sellNum(sellNum)
    			.id(id)
    			.name(name)
    			.content(content)
    			.build();
    }
}
