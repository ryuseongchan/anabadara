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

import kr.co.anabadara.DTO.BoardSellDTO;
import kr.co.anabadara.entity.BoardSell;
import kr.co.anabadara.repository.BoardSellRepository;
import kr.co.anabadara.repository.CommentRepository;

@Controller
@RequestMapping("/boardsell")
public class BoardSellController {

	@Autowired
	private BoardSellRepository boardRepo;

	@Autowired
	private CommentRepository commentRepo;

	// 게시판 글목록
	@RequestMapping
	public String goBoardSell(@RequestParam(required = false) String searchword, Model model, Pageable pageable) {
		int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1;
		PageRequest pageRequest = PageRequest.of(page, 3, Sort.Direction.DESC, "regdate");
		if (searchword == null) {
			Page<BoardSell> boardList = boardRepo.findAll(pageRequest);
			model.addAttribute("boardList", boardList);
		} else {
			Page<BoardSell> boardList = boardRepo.findByTitleContaining(searchword, pageRequest);
			model.addAttribute("searchword", searchword);
			model.addAttribute("boardList", boardList);
		}
		return "boardBuy";
	}

	// 글쓰기으로 이동
	@GetMapping("/write")
	public String goBoardSellWrite() {
		return "BoardSellWrite";
	}

	// 글수정으로 이동
	@GetMapping("/update/{sellNum}")
	public String goBoardSellUpdate(@PathVariable Long sellNum, Model model) {
		model.addAttribute("info", boardRepo.findById(sellNum).get());
		return "BoardSellUpdate";
	}

	// 글조회
	@GetMapping("/{sellNum}")
	public String getBoardSell(@PathVariable Long sellNum, Model model) {
		BoardSell bs = boardRepo.findById(sellNum).get();
		bs.setComments(commentRepo.findBySellNum(bs.getSellNum()));
		model.addAttribute("info", bs);
		return "BoardSellDetail";
	}

	// 글등록
	@PostMapping
	public String insertBoardSell(BoardSellDTO board) {
		boardRepo.save(board.toEntity());
		return "redirect:/boardsell";
	}

	// 글수정
	@PutMapping("/{sellNum}")
	@ResponseBody
	public void updateBoardSell(@PathVariable Long sellNum, @RequestBody BoardSellDTO board) {
		BoardSell boardToUpdate = boardRepo.findById(sellNum).get();
		boardToUpdate.setCategory(board.getCategory());
		boardToUpdate.setContent(board.getContent());
		boardToUpdate.setPlace(board.getPlace());
		boardToUpdate.setPrice(board.getPrice());
		boardToUpdate.setProduct(board.getProduct());
		boardToUpdate.setTitle(board.getTitle());
		boardRepo.save(boardToUpdate);
	}

	// 글삭제
	@DeleteMapping("/{sellNum}")
	@ResponseBody
	public String removeBoardSell(@PathVariable Long sellNum) {
		try {
			boardRepo.deleteById(sellNum);
		} catch (Exception e) {
			return "처리할 수 없습니다. 관리자에게 문의해주세요";
		}
		return "삭제되었습니다";
	}
}
