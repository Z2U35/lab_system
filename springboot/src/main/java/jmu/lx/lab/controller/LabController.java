package jmu.lx.lab.controller;

import jmu.lx.lab.common.Result;
import jmu.lx.lab.entity.Lab;
import jmu.lx.lab.service.LabService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实验室表前端操作接口
 **/
@RestController
@RequestMapping("/lab")
public class LabController {

    @Resource
    private LabService labService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Lab lab) {
        labService.add(lab);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        labService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        labService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Lab lab) {
        labService.updateById(lab);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Lab lab = labService.selectById(id);
        return Result.success(lab);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Lab lab ) {
        List<Lab> list = labService.selectAll(lab);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Lab lab,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Lab> page = labService.selectPage(lab, pageNum, pageSize);
        return Result.success(page);
    }

}