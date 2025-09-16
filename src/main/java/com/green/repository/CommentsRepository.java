package com.green.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.green.dto.Comments;

// JpaRepository : CrudRepository + PagingAndSortingRepostitory

@Repository
public interface CommentsRepository 
	extends  JpaRepository<Comments, Long>  {
       
	
	// @Query : findByArticleId() 함수를 호출하면
	// Jpa 기능이 아닌 @Query 안의 sql 안의 sql( JPQL )을 실행한다
	// nativeQuery = true   : oracle용 sql 문을 사용
	// nativeQuery = false  : JPQL 문법의 sql 이 작동한다
	
	
	@Query(value="SELECT * FROM COMMENTS WHERE ARTICLE_ID=:article_id" 
	, nativeQuery = true)

	// native query xml 
	// src/main/resources/META-INF/orm.xml // 폴더와 파일을 생성행야 한다
	// orm.xml 에 sql 을 저장해서 함수호출
	List<Comments> findByNickname(String nickname);

	List<Comments> findByArticleId(Long articleId);



	
	// 기본 findById() 를 직접 JPQL로 재정의
	// @Query("select a from Article a wher a.id =:id")
	// Optional<Aricle> findById(@Param("id") Long id);
	
}


