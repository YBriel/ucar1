package com.yzcx.ucar.web.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzcx.ucar.common.annotation.Log;
import com.yzcx.ucar.common.constant.UserConstants;
import com.yzcx.ucar.common.core.controller.BaseController;
import com.yzcx.ucar.common.core.domain.AjaxResult;
import com.yzcx.ucar.common.core.domain.Ztree;
import com.yzcx.ucar.common.enums.BusinessType;
import com.yzcx.ucar.common.utils.StringUtils;
import com.yzcx.ucar.framework.util.ShiroUtils;
import com.yzcx.ucar.system.domain.SysDept;
import com.yzcx.ucar.system.domain.SysRole;
import com.yzcx.ucar.system.service.ISysDeptService;

/**
 * 部门信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController
{
    private String prefix = "system/dept";

    @Autowired
    private ISysDeptService deptService;

    @RequiresPermissions("system:dept:view")
    @GetMapping()
    public String dept()
    {
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SysDept> list(SysDept dept)
    {
        List<SysDept> deptList = deptService.selectDeptList(dept);
        return deptList;
    }

    /**
     * 新增部门
     */
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(parentId));
        return prefix + "/add";
    }

    /**
     * 新增保存部门
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return error("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        dept.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.insertDept(dept));
    }

    /**
     * 修改
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        SysDept dept = deptService.selectDeptById(deptId);
        if (StringUtils.isNotNull(dept) && 100L == deptId)
        {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    /**
     * 保存
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDept dept)
    {
        if (UserConstants.DEPT_NAME_NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept)))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        else if (dept.getParentId().equals(dept.getDeptId()))
        {
            return error("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        dept.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(deptService.updateDept(dept));
    }

    /**
     * 删除
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if (deptService.selectDeptCount(deptId) > 0)
        {
            return AjaxResult.warn("存在下级部门,不允许删除");
        }
        if (deptService.checkDeptExistUser(deptId))
        {
            return AjaxResult.warn("部门存在用户,不允许删除");
        }
        return toAjax(deptService.deleteDeptById(deptId));
    }

    /**
     * 校验部门名称
     */
    @PostMapping("/checkDeptNameUnique")
    @ResponseBody
    public String checkDeptNameUnique(SysDept dept)
    {
        return deptService.checkDeptNameUnique(dept);
    }

    /**
     * 选择部门树
     */
    @GetMapping("/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/tree";
    }

    /**
     * 选择酒店行业部门树
     */
    @GetMapping("/selectHotelDeptTree/{deptId}")
    public String selectHotelDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/hotelDeptTree";
    }
    
    /**
     * 供查询的酒店行业部门数
     */
    @GetMapping("/selectHotelDeptTreeForQuery/{deptId}")
    public String getHotelDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/hotelDeptTreeForQuery";
    }
    
    /**
     * 渠道平台树构造
     */
    @GetMapping("/selectCooperTree/{deptId}")
    public String selectCooperatorCompanyTree(ModelMap mmap)
    {
        return prefix + "/cooperDeptTree";
    }
    
    /**
     * 订单来源树
     */
    @GetMapping("/selectOriginTree")
    public String selectOriginTree(ModelMap mmap){
        return prefix + "/originTree";
    }
    
    /**
     * 加载渠道列表树
     */
    @GetMapping("/cooperTree")
    @ResponseBody
    public List<Ztree> cooperTree(){
    	SysDept dept = new SysDept();
    	dept.setParentId(207l);
        List<Ztree> ztrees = deptService.selectDeptTree(dept);
        
        dept.setParentId(208l);
        List<Ztree> ztree = deptService.selectDeptTree(dept);
        ztrees.addAll(ztree);
        return ztrees;
    }
    
    /**
     * 加载订单来源列表树
     */
    @GetMapping("/orderOriginTree")
    @ResponseBody
    public List<Ztree> orderOriginTree(){
    	 List<Ztree> other = new ArrayList<Ztree>();
    	 Ztree root = new Ztree();
    	 root.setId(-2l);
    	 root.setName("其他");
    	 root.setpId(0l);
    	 root.setTitle("包括自有平台和不限制");
    	 other.add(root);
    	 
         Ztree all = new Ztree();
         all.setId(-1l);
         all.setName("不限");
         all.setpId(-2l);
         all.setTitle("不限制来源");
         other.add(all);
      
         Ztree platform = new Ztree();
         platform.setId(1l);
         platform.setName("易至平台");
         platform.setpId(-2l);
         platform.setTitle("易至自有订单");
         other.add(platform);
         
    	SysDept dept = new SysDept();
    	dept.setParentId(207l);
        List<Ztree> ztrees = deptService.selectDeptTree(dept);
        other.addAll(ztrees);
        dept.setParentId(208l);
        List<Ztree> ztree = deptService.selectDeptTree(dept);
        other.addAll(ztree);
       
        return other;
    }
    
    /**
     * 加载部门列表树
     */
    @GetMapping("/hotelDeptTreeData")
    @ResponseBody
    public List<Ztree> hotelDeptTreeData()
    {
    	SysDept dept = new SysDept();
    	dept.setParentId(208l);
        List<Ztree> ztrees = deptService.selectDeptTree(dept);
        return ztrees;
    }
    
    
    
    /**
     * 加载部门列表树
     */
    @GetMapping("/hotelDeptTreeDataForQuery")
    @ResponseBody
    public List<Ztree> hotelDeptTreeDataForQuery()
    {
    	SysDept dept = new SysDept();
    	dept.setAncestors("208");
        List<Ztree> ztrees = deptService.selectDeptTree(dept);
        return ztrees;
    }
    
    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
    	SysDept dept = new SysDept();
        List<Ztree> ztrees = deptService.selectDeptTree(dept);
        return ztrees;
    }

    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData(SysRole role)
    {
        List<Ztree> ztrees = deptService.roleDeptTreeData(role);
        return ztrees;
    }
}
