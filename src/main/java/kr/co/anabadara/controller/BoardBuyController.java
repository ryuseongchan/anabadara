package kr.co.anabadara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.anabadara.DTO.BoardBuyDTO;
import kr.co.anabadara.entity.BoardBuy;
import kr.co.anabadara.repository.BoardBuyRepository;
import kr.co.anabadara.repository.CommentRepository;

@Controller
@RequestMapping("/boardbuy")
public class BoardBuyController {

	@Autowired
	private BoardBuyRepository boardRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	//게시판 글목록
	@RequestMapping
	public String goBoardBuy(@RequestParam(required = false) String searchword, Model model, Pageable pageable) {
		int page = pageable.getPageNumber()==0 ? 0 : pageable.getPageNumber() -1;
		PageRequest pageRequest = PageRequest.of(page, 3, Sort.Direction.DESC, "regdate");
		if(searchword==null) {
			Page<BoardBuy> boardList = boardRepo.findAll(pageRequest);
			model.addAttribute("boardList", boardList);
		}else {
		Page<BoardBuy> boardList = boardRepo.findByTitleContaining(searchword, pageRequest);
			model.addAttribute("searchword", searchword);
			model.addAttribute("boardList", boardList);
		}
		return "boardBuy";
	}
	
	//글쓰기으로 이동
	@GetMapping("/write")
	public String goBoardBuyWrite() {
		return "BoardBuyWrite";
	}

	//글수정으로 이동
	@GetMapping("/update/{buyNum}")
	public String goBoardBuyUpdate(@PathVariable Long buyNum, Model model) {
		model.addAttribute("info", boardRepo.findById(buyNum).get());
		return "BoardBuyUpdate";
	}
	
	//글조회
	@GetMapping("/{buyNum}")
	public String getBoardBuy(@PathVariable Long buyNum, Model model) {
		BoardBuy bb = boardRepo.findById(buyNum).get();
		bb.setComments(commentRepo.findByBuyNum(bb.getBuyNum()));
		model.addAttribute("info", bb);
		return "BoardBuyDetail";
	}
	
	//글등록
	@PostMapping
	public String insertBoardBuy(BoardBuyDTO board) {
		boardRepo.save(board.toEntity());
		return "redirect:/boardbuy";
	}

	//글수정
	@PutMapping("/{buyNum}")
	@ResponseBody
	public void updateBoardBuy(@PathVariable Long buyNum, @RequestBody BoardBuyDTO board) {
		BoardBuy boardBuyToUpdate = boardRepo.findById(buyNum).get();
		boardBuyToUpdate.setCategory(board.getCategory());
		boardBuyToUpdate.setContent(board.getContent());
		boardBuyToUpdate.setPlace(board.getPlace());
		boardBuyToUpdate.setPrice(board.getPrice());
		boardBuyToUpdate.setProduct(board.getProduct());
		boardBuyToUpdate.setTitle(board.getTitle());
		boardRepo.save(boardBuyToUpdate);
	}

	//글삭제
	@DeleteMapping("/{buyNum}")
	@ResponseBody
	public String removeBoardBuy(@PathVariable Long buyNum) {
		try {
		boardRepo.deleteById(buyNum);
		}catch(Exception e){
			return "처리할 수 없습니다. 관리자에게 문의해주세요";
		}
		return "삭제되었습니다";
	}

}
