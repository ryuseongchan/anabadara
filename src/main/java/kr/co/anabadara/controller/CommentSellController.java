package kr.co.anabadara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.anabadara.DTO.CommentDTO;
import kr.co.anabadara.entity.Comment;
import kr.co.anabadara.repository.CommentRepository;

@Controller
@RequestMapping("/commentsell")
public class CommentSellController {

	@Autowired
	private CommentRepository commentRepo;
	
		//댓글등록
		@PostMapping
		public String insertComment(@RequestBody CommentDTO comment, Model model) {
			System.out.println("1");
			commentRepo.save(comment.toEntity());
			System.out.println("2");
			model.addAttribute("comments", commentRepo.findBySellNum(comment.getSellNum()));
			System.out.println("3");
			return "CommentSell";
		}

		//댓글수정
		@PutMapping("/{commentNum}")
		public String updateComment(@PathVariable Long commentNum, @RequestBody CommentDTO commentDTO, Model model) {
			Comment comment = commentRepo.findById(commentNum).get();
			comment.setContent(commentDTO.getContent());
			commentRepo.save(comment);
			model.addAttribute("comments", commentRepo.findBySellNum(comment.getSellNum()));	
			return "CommentSell";
		}

		//댓글삭제
		@DeleteMapping("/{commentNo}/{sellNum}")
		public String deleteComment(@PathVariable Long commentNo, @PathVariable Long sellNum, Model model) {
			commentRepo.deleteById(commentNo);
			model.addAttribute("comments", commentRepo.findBySellNum(sellNum));
			return "CommentSell";
		}

}
