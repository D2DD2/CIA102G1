package com.tabletype.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reservationcontrol.model.ResCVO;
import com.restime.model.ResTimeVO;
import com.tabletype.model.TableTypeService;
import com.tabletype.model.TableTypeVO;

@Controller
@RequestMapping("/tabletype")
public class TableTypeController {
	@Autowired
	TableTypeService TableTypeSvc;
	
	@PostMapping("getOne_For_Display")
	public String getOne_For_Display(
		/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
		
		@RequestParam("tableId") String tableId,
		ModelMap model) {
		
		/***************************2.�}�l�d�߸��*********************************************/
//		EmpService empSvc = new EmpService();
		TableTypeVO tableTypeVO = TableTypeSvc.getOneTableType(Integer.valueOf(tableId));
		
		List<TableTypeVO> list = TableTypeSvc.getAll();
		model.addAttribute("tableTypeListData", list); // for select_page.html ��97 109���
		
		if (tableTypeVO == null) {
			model.addAttribute("errorMessage", "�d�L���");
			return "back-end/tabletype/select_page";
		}
		
		/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*****************/
		model.addAttribute("tableTypeVO", tableTypeVO);
		model.addAttribute("getOne_For_Display", "true"); // �X��getOne_For_Display��select_page.html����126�� -->
		
//		
		return "back-end/tabletype/select_page"; // �d�ߧ��������select_page.html�Ѩ��128��insert listOneEmp.html����th:fragment="listOneEmp-div
	}
	
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("tableId") Integer tableId, ModelMap model) {
		/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ************************/
		/*************************** 2.�}�l�d�߸�� *****************************************/
		// EmpService empSvc = new EmpService(); //autowired
		TableTypeVO tableTypeVO = TableTypeSvc.getOneTableType(Integer.valueOf(tableId));

		/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) **************/
		model.addAttribute("tableTypeVO", tableTypeVO);
		return "back-end/tabletype/update_tabletype_input"; // �d�ߧ��������update_emp_input.html
	}
	
	@PostMapping("update")
	public String update(@Valid TableTypeVO tableTypeVO, BindingResult result, ModelMap model
//			,@RequestParam("upFiles") MultipartFile[] parts
			) throws IOException {

		/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ************************/
		// �h��BindingResult��upFiles��쪺FieldError���� --> ����172��
//		result = removeFieldError(resVO, result, "upFiles");
//
//		if (parts[0].isEmpty()) { // �ϥΪ̥���ܭn�W�Ǫ��s�Ϥ���
//			// EmpService empSvc = new EmpService();
//			byte[] upFiles = empSvc.getOneEmp(empVO.getEmpno()).getUpFiles();
//			empVO.setUpFiles(upFiles);
//		} else {
//			for (MultipartFile multipartFile : parts) {
//				byte[] upFiles = multipartFile.getBytes();
//				empVO.setUpFiles(upFiles);
//			}
//		}
//		if (result.hasErrors()) {
//			return "back-end/emp/update_emp_input";
//		}
		/*************************** 2.�}�l�ק��� *****************************************/
		// EmpService empSvc = new EmpService();
		TableTypeSvc.updateTableType(tableTypeVO);

		/*************************** 3.�ק粒��,�ǳ����(Send the Success view) **************/
		model.addAttribute("success", "- (�ק令�\)");
		tableTypeVO = TableTypeSvc.getOneTableType(Integer.valueOf(tableTypeVO.getTableId()));
		model.addAttribute("tableTypeVO", tableTypeVO);
		return "back-end/tabletype/listOneTableType"; // �ק令�\�����listOneEmp.html
	}
	@ModelAttribute("tableTypeListData")
	protected List<TableTypeVO> referenceListData() {
		// DeptService deptSvc = new DeptService();
		List<TableTypeVO> list = TableTypeSvc.getAll();
		return list;
	}
	
	
}
