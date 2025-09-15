package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.Article;
import com.green.dto.ArticleDto;
import com.green.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ArticleApiController {

	@Autowired
	private ArticleService  articleService;
	
	// 결과 data 로 출력 : JSON 기본값
	// build.gradle 에 jackson dataformat xml 라이브러리를 추가하면 기본 data 로 출력
	// 라이브러리를 추가한 후 json을 출력할려면 produces = "application/json;charset=UTF-8"
	@GetMapping(value="/api/articles",
			produces = "application/json;charset=UTF-8" )               // content type   <-서버에서 클라이언트로
	        // produces = MediaType.APPLICATION_JSON_VALUE )      
	public  List<Article> list() {
		
		List<Article>  list = articleService.getList();
		log.info("list", list );
		System.out.println(list);
		return list;
	
		/*
		 XMlMapper xmlMapper  = new XmlMapper();
		 List<Article> list = articleService.getList();
		 return xmlMapper.writeValueAsString(list);
		 */
			
	}
		
	// ResponsEntity<Article>
	//  Artcile  : DATA
	//  +상태 코드  ResponseEntity.status( HttpStatus.ok  ) 
	//             또는 ResponseEntity.status( Htt1pStatus.BAD_REQUEST  ) 
	
	// 한개 조회                 
	@GetMapping(value="/api/articles/{id}") // xml 로 출력                                 
	public ResponseEntity<Article> getOne(
	@PathVariable ("id") Long id
			) {
		
		Article article = articleService.getOne( id );
	    log.info("article:" + article );
		ResponseEntity<Article>  result =
		( article != null)
		  ? ResponseEntity.status(HttpStatus.OK).body( article )
		  : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();		  
		return result;
	}
	
	// 한줄 추가 
	@PostMapping("/api/articles")
	public ResponseEntity<Article> create( 
		@RequestBody	ArticleDto  articleDto   ) {  
		   //  @RequestBody : 넘어오는 정보는 json 문자열
		
		Article  created  =  articleService.create( articleDto  );
		// saved : 저장된 id, title. content  를 돌려받늗다
		
		ResponseEntity<Article>  result = 
		  ( created != null )	
		     ? ResponseEntity.status(HttpStatus.OK).body( created )   // 200(ok) , 201(created)	  
			 : ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400	 (Error)	
		
		return  result;
	}
	
	// 
	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Article> delete(
			@PathVariable("id") Long id
			) {
		
		Article deleted = articleService.delete(id);
		
		ResponseEntity<Article>  result = 
				  ( deleted != null )	
				     ? ResponseEntity.status(HttpStatus.OK).body( deleted )   // 200(ok) , 201(created)	  
					 : ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400	 (Error)	
				
				return  result;
		
	}
    
	// 수정
	// Put          : 전체 데이터를 수정 -> 모든 데이터가 필요
	// Patch        : 전체, 일부 데이터를 수정 -> 모든 데이터가 필요
	// @RequestBody : 넘어오는 정보는 json 구조이다
	@PatchMapping("/api/articles")
	public ResponseEntity<Article> update(
	   @RequestBody Article article                                           // <- JSON.string
			) {
		
		Article updated = articleService.update( article );
		ResponseEntity<Article> result =
		  (updated != null) 
		? ResponseEntity.status(HttpStatus.OK).body( updated )
		: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();	          //  400	  
		
	return result;
	}
	
	// 3줄의 data
	/*
	[
	   { "title" : "시간예약", "content"  : "1420"},
	   { "title" : "영화지정", "content"  : "캐대헌"},
	   { "title" : "자리 지정", "content" : "A2"}
	]
	*/
	
	// /api/transaction-test1
    // 3개의 데이터를 받아서 서비스에 잠  저장결과를 받는다

	@PostMapping("/api/transaction-test1")
    public ResponseEntity<List<Article>> transaction1(
    	@RequestBody	List<ArticleDto> dtos
    		) {
		
		List<Article> createdList = articleService.createArticleList( dtos );
		
		ResponseEntity<List<Article>> result = 
		  ( createdList != null )                                                   // null이 아니면
		  ? ResponseEntity.status(HttpStatus.OK).body( createdList )
		  : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return result;
	}
   
	@PostMapping("/api/transaction-test2")
    public ResponseEntity<List<Article>> transaction2(
    	@RequestBody	List<ArticleDto> dtos
    		) {
		
		List<Article> createdList = articleService.createArticleList( dtos );
		
		ResponseEntity<List<Article>> result = 
		  ( createdList != null )                                                   // null이 아니면
		  ? ResponseEntity.status(HttpStatus.OK).body( createdList )
		  : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return result;
	}
}







