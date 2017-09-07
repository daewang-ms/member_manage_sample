package com.example.demo.controller;

import com.example.demo.domain.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class HomeController {
	Logger logger = LoggerFactory.getLogger(HomeController.class);

	public static ArrayList<Member> list = new ArrayList<>();

	static {
		list.add(new Member("김용직", 20));
	}

	/**
	 * Home
	 *
	 * @return
	 */
	@GetMapping("")
	public String getHome() {
		return "index";
	}

	/**
	 * 등록화면
	 *
	 * @return
	 */
	@GetMapping("reg")
	public String getReg() {
		return "write";
	}

	/**
	 * 등록 프로세스
	 *
	 * @param member
	 * @return
	 */
	@PostMapping("reg")
	public String postReg(Member member) {
		logger.debug("{}", member);

		list.add(member);
		return "redirect:/list";
	}

	/**
	 * 전체회원 조회
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("list")
	public String getList(Model model) {
		model.addAttribute("student", list);
		return "list";
	}

	/**
	 * 학생 랜덤 선출
	 *
	 * @return
	 */
	@GetMapping("/random")
	public ResponseEntity getRandom() {
		Member student = list.get((int) (Math.random() * list.size()));
		return ResponseEntity.ok(student);
	}
}
