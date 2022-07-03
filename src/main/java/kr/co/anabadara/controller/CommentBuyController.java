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
@RequestMapping("/commentbuy")
public class CommentBuyController {

	@Autowired
	private CommentRepository commentRepo;
	
		//댓글등록
		@PostMapping
		public String insertComment(@RequestBody CommentDTO comment, Model model) {
			commentRepo.save(comment.toEntity());
			model.addAttribute("comments", commentRepo.findByBuyNum(comment.getBuyNum()));
			return "CommentBuy";
		}

		//댓글수정
		@PutMapping("/{commentNum}")
		public String updateComment(@PathVariable Long commentNum, @RequestBody CommentDTO commentDTO, Model model) {
			Comment comment = commentRepo.findById(commentNum).get();
			comment.setContent(commentDTO.getContent());
			commentRepo.save(comment);
			model.addAttribute("comments", commentRepo.findByBuyNum(comment.getBuyNum()));	
			return "CommentBuy";
		}

		//댓글삭제
		@DeleteMapping("/{commentNo}/{buyNum}")
		public String deleteComment(@PathVariable Long commentNo, @PathVariable Long buyNum, Model model) {
			commentRepo.deleteById(commentNo);
			model.addAttribute("comments", commentRepo.findByBuyNum(buyNum));
			return "CommentBuy";
		}

}
