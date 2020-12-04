package com.yzcx.ucar.framework.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yzcx.ucar.system.domain.SysDept;
import com.yzcx.ucar.system.service.ISysDeptService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author maozh
 *
 */
@Service("dept")
@Slf4j
public class DeptService {
	@Autowired private ISysDeptService sysDeptService;
	
	public List<SysDept> selectDeptList(){
		SysDept dept = new SysDept();
		List<SysDept> depts =  sysDeptService.selectDeptList(dept);
		log.info("所有部门信息 {}", JSON.toJSONString(depts));
		return depts;
    }
	
	public String selectDeptLabel(Long deptId){
		log.info("---查询 部门信息-- {}", deptId);
		SysDept dept = new SysDept();
		dept.setDeptId(deptId);
		List<SysDept> depts =  sysDeptService.selectDeptList(dept);
		log.info("部门信息 {}", JSON.toJSONString(depts));
		if(depts != null && depts.size()>0){
			return depts.get(0).getDeptName();
		}
		return "";
    }
	
	public String selectOrderOriginLabel(String deptId){
		if(deptId.equals("*")){
			return "不限";
		}
		if(deptId.equals("1")){
			return "易至";
		}
		log.info("---查询 部门信息-- {}", deptId);
		SysDept dept = new SysDept();
		dept.setDeptId(Long.parseLong(deptId));
		List<SysDept> depts =  sysDeptService.selectDeptList(dept);
		log.info("部门信息 {}", JSON.toJSONString(depts));
		if(depts != null && depts.size()>0){
			return depts.get(0).getDeptName();
		}
		return "";
    }
}
