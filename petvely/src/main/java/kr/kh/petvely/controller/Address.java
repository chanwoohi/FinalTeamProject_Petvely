package kr.kh.petvely.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class Address {

	@GetMapping("/address/insert")
	public String AddreccInsert() {
		return "address/insert";
	}
}
