package com.company.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.dto.ScheduleDto;
import com.company.dto.ScreenDto;
import com.company.dto.TheaterDto;
import com.company.service.AIApiService;
import com.company.service.SchService;
import com.google.gson.Gson;
import org.springframework.core.io.ResourceLoader;

@Controller
public class ScheduleController {
	@Autowired
	SchService service;

	@Autowired
	ResourceLoader resourceLoader;

	// 관리자
	@GetMapping("/schedule-management.admin")
	public String adminPage(Model model) {
		model.addAttribute("theaterList", service.theaterList());

		return "schedule_management";
	}

	@RequestMapping(value = "/scheduleListAdmin.admin", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String scheduleListAdmin(@RequestParam("theaterNo") int no, @RequestParam("date") String date) {
		Map<String, Object> map = new HashMap<>();

		map.put("theaterNo", no);
		map.put("date", date);

		System.out.println();
		Gson gson = new Gson();
		String json = gson.toJson(service.scheduleListAdmin(map));

		return json;
	}

	@RequestMapping(value = "/deleteSchedule.admin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteSchedule(ScheduleDto dto) {
		Map<String, Object> result = new HashMap<>();
		result.put("result", service.deleteSchedule(dto));

		return result;
	}

	@RequestMapping(value = "/getMovieList.admin", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getMovieList(ScheduleDto dto) {

		Gson gson = new Gson();
		String json = gson.toJson(service.getMovieList());

		return json;
	}
	
	@RequestMapping(value = "/getScreenList.admin", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getScreenListByTheater(ScreenDto dto) {
		System.out.println(dto);

		Gson gson = new Gson();
		String json = gson.toJson(service.getScreenList(dto));
		System.out.println(json);

		return json;
	}
	
	@RequestMapping(value = "/getTheaterInfo.admin", method = RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String getTheaterHours(TheaterDto dto) {

		Gson gson = new Gson();
		String json = gson.toJson(service.getTheaterHours(dto));
		return json;
	}
	
	@RequestMapping(value = "/addSchedule.admin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSchedule(ScheduleDto dto) {
		System.out.println(".... 상영관 추가해주세요");
		System.out.println(dto);
		Map<String, Object> result = new HashMap<>();
		
		result.put("result", service.insertScheduleAction(dto));

		return result;
	}
	
}
