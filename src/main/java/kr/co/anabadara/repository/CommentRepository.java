package kr.co.anabadara.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.anabadara.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	List<Comment> findByBuyNum(Long buyNum);
	List<Comment> findBySellNum(Long sellNum);
}
