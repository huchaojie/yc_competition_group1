package com.yc.projects.bikemanage.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.projects.bikemanage.bean.Admin;
import com.yc.projects.bikemanage.service.AdminManageService;
import com.yc.projects.bikemanage.web.model.JsonModel;

@Controller
public class AdminManageController {
	@Autowired
	private AdminManageService adminManageService;

	@RequestMapping("/back/addAdmin")
	@ResponseBody
	public JsonModel addUser(JsonModel jm, Admin admin) {
		try {
			if (admin.getName() != null && !"".equals(admin.getName()) && admin.getPassword() != null
					&& !"".equals(admin.getPassword()) && admin.getSex() != null && !"".equals(admin.getSex())
					&& admin.getType() != null && !"".equals(admin.getType())) {
				adminManageService.addAdmin(admin);
				jm.setCode(1);
			} else {
				jm.setCode(0);
				jm.setMsg("所有信息都不能为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/searchAdmin")
	@ResponseBody
	public JsonModel searchUser(JsonModel jm, Admin admin, Integer pageNum, Integer pageSize) {
		try {
			if (pageNum == null) {
				pageNum = 1;
			}
			if (pageSize == null) {
				pageSize = 10;
			}
			Map<String, Object> map = adminManageService.searchAdmin(admin, pageNum, pageSize);
			jm.setCode(1);
			jm.setObj(map.get("list"));
			jm.setMsg(map.get("total") + "");
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/updateAdmin")
	@ResponseBody
	public JsonModel searchUser(JsonModel jm, Admin admin) {
		try {
			boolean flag = adminManageService.updateAdmin(admin);
			if (flag) {
				jm.setCode(1);
			} else {
				jm.setCode(0);
				jm.setMsg("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/deleteAdmin")
	@ResponseBody
	public JsonModel deleteUser(JsonModel jm, Admin admin) {
		try {
			boolean flag = adminManageService.deleteAdmin(admin);
			if (flag) {
				jm.setCode(1);
			} else {
				jm.setCode(0);
				jm.setMsg("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/login")
	@ResponseBody
	public JsonModel login(JsonModel jm, Admin admin) {
		try {
			if (admin.getName() != null && !"".equals(admin.getName()) && admin.getPassword() != null
					&& !"".equals(admin.getPassword())) {
				boolean login = adminManageService.login(admin);
				if (login) {
					jm.setCode(1);
				} else {
					jm.setCode(0);
					jm.setMsg("用户名或密码错误");
				}
			} else {
				jm.setCode(0);
				jm.setMsg("用户名或密码不能为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/checkLogin")
	@ResponseBody
	public JsonModel checkLogin(JsonModel jm, Admin admin) {
		try {
			boolean flag = adminManageService.checkLogin(admin);
			if (flag) {
				jm.setCode(1);
			} else {
				jm.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
		}
		return jm;
	}

	@RequestMapping("/back/logout")
	@ResponseBody
	public JsonModel logout(JsonModel jm, Admin admin) {
		try {
			boolean flag = adminManageService.logout(admin);
			if (flag) {
				jm.setCode(1);
			} else {
				jm.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
		}
		return jm;
	}

}
