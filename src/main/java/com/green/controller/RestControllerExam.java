package com.green.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerExam {
    //  Method
	//  GET
	//  POST
	//  DELETE
	
	
	
	// Method : Get 호출되는 주소
	// data 조회 : SELECT
	@GetMapping("/exam")
	// @ResponseBody
    public String getExam() {
	    return "Get Data";	
	}
    
	// data 추가 : Insert
	@PostMapping("/exam")      
	// @ResponseBody
	public String postExam() {
	    return "Post Data";	
	}
   
	// 해당 data 삭제 : Delete
	@DeleteMapping("/exam")      
	// @ResponseBody
	public String deleteExam() {
	    return "Delete Data";	
	}
     
	// 일부 data 수정 : Update
	@PatchMapping("/exam")
	public String patchExam() {
	    return "patch Data";	
	}

	// 전체 data 수정 : Update
	@PutMapping("/exam")
	public String putExam() {
	    return "put Data";	
	}
	
}
