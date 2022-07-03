package kr.co.anabadara.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.anabadara.entity.BoardSell;

@Repository
public interface BoardSellRepository extends JpaRepository<BoardSell, Long> {
	Page<BoardSell> findByTitleContaining(String searchword, PageRequest pageRequest);
}
