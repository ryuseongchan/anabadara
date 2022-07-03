package kr.co.anabadara.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.anabadara.entity.BoardBuy;

@Repository
public interface BoardBuyRepository extends JpaRepository<BoardBuy, Long>{
	Page<BoardBuy> findByTitleContaining(String searchword, PageRequest pageRequest);
}
