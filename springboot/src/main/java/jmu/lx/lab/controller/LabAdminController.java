package jmu.lx.lab.controller;

import jmu.lx.lab.common.Result;
import jmu.lx.lab.entity.LabAdmin;
import jmu.lx.lab.service.LabAdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实验员前端操作接口
 **/
@RestController
@RequestMapping("/labAdmin")
public class LabAdminController {

    @Resource
    private LabAdminService labAdminService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody LabAdmin labAdmin) {
        labAdminService.add(labAdmin);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        labAdminService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        labAdminService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody LabAdmin labAdmin) {
        labAdminService.updateById(labAdmin);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        LabAdmin labAdmin = labAdminService.selectById(id);
        return Result.success(labAdmin);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(LabAdmin labAdmin ) {
        List<LabAdmin> list = labAdminService.selectAll(labAdmin);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(LabAdmin labAdmin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<LabAdmin> page = labAdminService.selectPage(labAdmin, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 重置密码
     */
    @PutMapping("/resetPassword/{id}")
    public Result resetPassword(@PathVariable Integer id) {
        labAdminService.resetPassword(id);
        return Result.success();
    }

}