package com.yue.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yue.model.Page;
import com.yue.model.User;
import com.yue.service.UserService;

@Controller
public class MyBatisTest {
	@Autowired
	private UserService userService;
	User user = new User();
	Page page = new Page();
	List<User> userAll;
	
	@RequestMapping(value="findAllUser.do")
	public String findAllUser(Model model){
		System.out.println("------------------------------>findAllUser");
		this.page = new Page();
		List<User> users = userService.findAllUser();
    	userAll = users;
    	getPage(users,model);
		getSubList(users,model);
		return "main";
	}
	@RequestMapping(value="add")
	public String add(){
		System.out.println("------------------------------->add");
		return "add";
	}
	@RequestMapping(value="alter")
	public String alter(@RequestParam(value="id") int id,Model model){
		System.out.println("------------------------------->alter");
		for(int i=0;i<userAll.size();i++){
			User user = userAll.get(i);
			if(user.getId()==id){
				model.addAttribute("user", user);
			}
		}
		return "add";
	}
	@RequestMapping(value="mergeUser.do")
	public String mergeUser(@ModelAttribute("user") @Valid  User user,Model model){
		System.out.println("-------------------------------->mergeUser");
		userService.mergeUser(user);
		List<User> users = userService.findAllUser();
    	userAll = users;
		getPage(users,model);
    	getSubList(users,model);
		return "main";
	}
	@RequestMapping(value="deleteUser.do")
	public String deleteUser(@ModelAttribute("page") @Valid  Page page,@RequestParam(value="id") int id,Model model){
		System.out.println("-------------------------------->deleteUser");
		this.page=page;
		int flag = userService.deleteUser(id);
		System.out.println("***flag="+flag);
		List<User> users = userService.findAllUser();
		if((page.getCurrentPage()-1)*page.getRowsPerPage() == users.size()){
		   page.setCurrentPage(page.getCurrentPage()-1);
		}
    	userAll = users;
		getPage(users,model);
    	getSubList(users,model);
		return "main";
	}
	@RequestMapping(value="selectUser.do")
	public String selectUser(@ModelAttribute("page") @Valid  Page page,Model model){
		System.out.println("------------------------------->selectUser");
		this.page = page;
		List<User> users = userService.selectUser(page.getUsername_cx());
    	userAll = users;
        page.setRowsPerPage(3);
        page.setCurrentPage(1);
		getPage(users,model);
		getSubList(users,model);
		return "main";
	}
	@RequestMapping(value="turnPage.do")
	public String turnPage(@ModelAttribute("page") @Valid  Page page,Model model){
		System.out.println("------------------------------->turnPage");
		this.page = page;
		getPage(userAll,model);
		getSubList(userAll,model);
		return "main";
	}
	public void getSubList(List<User> users,Model model){
    	int rowsCount = users.size();
		int start = (page.getCurrentPage()-1)*page.getRowsPerPage() ;
		int end = page.getCurrentPage()*page.getRowsPerPage() ;
		int startIndex = start>rowsCount?1:start;
		int endIndex = end>rowsCount?rowsCount:end;
		List<User> userList = userAll.subList(startIndex,endIndex);
		model.addAttribute("userList",userList);
	}
	public void getPage(List<User> users,Model model){
    	int rowsCount = users.size();
    	int totalPage = rowsCount/page.getRowsPerPage()+(rowsCount%page.getRowsPerPage()>0?1:0);
    	page.setRowsCount(rowsCount);
    	page.setTotalPage(totalPage);
    	model.addAttribute("page",page );
	}

	
	
	
/*	
	@RequestMapping(value="insertUser.do")
	public String insertUser(@ModelAttribute("user") @Valid  User user){
		System.out.println("------------------------------->insertUser");
		int flag = userService.insertUser(user);
		System.out.println("****flag="+flag);
		return "hello";
	}
	@RequestMapping(value="updateUser")
	public String updateUser(){
		System.out.println("-------------------->updateUser");
		user.setId(10);
		user.setUsername("’‘‘¬10");
		int flag = userService.updateUser(user);
		System.out.println("***flag="+flag);
		return "hello";
	}*/
}






/*import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.yue.dao.UserDAO;
import com.yue.model.User;

public class MyBatisTest implements Controller{
	public ModelAndView handleRequest(HttpServletRequest arg0,HttpServletResponse arg1) throws Exception {
    	SqlSession sqlSession = getSessionFactory().openSession();
    	UserDAO userDao = sqlSession.getMapper(UserDAO.class);
    	List<User> users = userDao.findUser();
		System.out.println("------------------------------>selectUser");
    	for(int i=0;i<users.size();i++){
    		User user = users.get(i);
    		System.out.println("****username="+user.getUsername()+"****email="+user.getEmail());    	
    	}
		System.out.println("------------------------------>insertUser");
    	User userModel = new User();
    	userModel.setEmail("98923892@163.com");
    	userModel.setId(322);
    	userModel.setUsername("testyueyue");
    	userModel.setPassword("123456");
    	int user_insert = userDao.insertUser(userModel);
		System.out.println("insert_result="+user_insert);
		return new ModelAndView("/hello");
	}

	private static SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "MyBatis-config.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionFactory;
	}
}
*/