package com.tabletype.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.restime.model.ResTimeVO;
import com.tabletype.model.TableTypeService;
import com.tabletype.model.TableTypeVO;

@Controller
@RequestMapping("/tabletype")
public class TableIdController {
	@Autowired
	TableTypeService TableTypeSvc;
	
	@GetMapping("addTableType")
	public String addTableType(ModelMap model) {
		TableTypeVO tableVO = new TableTypeVO();
		model.addAttribute("tableVO", tableVO);
		return "back-end/tabletype/addTableType";
	}
//	@GetMapping("insert")
	@PostMapping("insert")
	public String insert(@Valid TableTypeVO tableTypeVO, BindingResult result, ModelMap model) throws IOException{
		/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ************************/
		
		/*************************** 2.�}�l�s�W��� *****************************************/
		// EmpService empSvc = new EmpService();
		TableTypeSvc.addTableType(tableTypeVO);
		/*************************** 3.�s�W����,�ǳ����(Send the Success view) **************/
		List<TableTypeVO> list = TableTypeSvc.getAll();
		model.addAttribute("tableTypeListData", list);
		model.addAttribute("success", "- (�s�W���\)");
		return "redirect:/tabletype/listAllTableType"; // �s�W���\�᭫�ɦ�IndexController_inSpringBoot.java����50��@GetMapping("/emp/listAllEmp")
		
		
		
	}
	
	
	
	
}
