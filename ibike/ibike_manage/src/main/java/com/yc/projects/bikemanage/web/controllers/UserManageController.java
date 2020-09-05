package com.yc.projects.bikemanage.web.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.projects.bikemanage.bean.User;
import com.yc.projects.bikemanage.service.UserManageService;
import com.yc.projects.bikemanage.web.model.JsonModel;

@Controller
public class UserManageController {
	@Autowired
	private UserManageService userManageService;

	@RequestMapping("/back/addUser")
	@ResponseBody
	public JsonModel addUser(JsonModel jm, User user) {
		try {
			if (user.getOpenId() != null && !"".equals(user.getOpenId())) {
				userManageService.addUser(user);
				jm.setCode(1);
			} else {
				jm.setCode(0);
				jm.setMsg("openId为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/searchUser")
	@ResponseBody
	public JsonModel searchUser(JsonModel jm, User user, Integer pageNum, Integer pageSize) {
		try {
			if (pageNum == null) {
				pageNum = 1;
			}
			if (pageSize == null) {
				pageSize = 10;
			}
			Map<String, Object> map = userManageService.searchUser(user, pageNum, pageSize);
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

	@RequestMapping("/back/updateUser")
	@ResponseBody
	public JsonModel searchUser(JsonModel jm, User user) {
		try {
			boolean flag = userManageService.updateUser(user);
			if (flag) {
				jm.setCode(1);
			} else {
				jm.setCode(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jm.setCode(0);
			jm.setMsg(e.getMessage());
		}
		return jm;
	}

	@RequestMapping("/back/deleteUser")
	@ResponseBody
	public JsonModel deleteUser(JsonModel jm, User user) {
		System.out.println(user);
		try {
			boolean flag = userManageService.deleteUser(user);
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

}
