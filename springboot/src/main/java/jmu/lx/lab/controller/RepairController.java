package jmu.lx.lab.controller;

import jmu.lx.lab.common.Result;
import jmu.lx.lab.entity.Repair;
import jmu.lx.lab.service.RepairService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报修表前端操作接口
 **/
@RestController
@RequestMapping("/repair")
public class RepairController {

    @Resource
    private RepairService repairService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Repair repair) {
        repairService.add(repair);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        repairService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        repairService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Repair repair) {
        repairService.updateById(repair);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Repair repair = repairService.selectById(id);
        return Result.success(repair);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Repair repair ) {
        List<Repair> list = repairService.selectAll(repair);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Repair repair,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Repair> page = repairService.selectPage(repair, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 维修处理中
     */
    @PutMapping("/repairing")
    public Result repairing(@RequestBody Repair repair) {
        repairService.repairing(repair);
        return Result.success();
    }

    /**
     * 维修完成
     */
    @PutMapping("/repaired")
    public Result repaired(@RequestBody Repair repair) {
        repairService.repaired(repair);
        return Result.success();
    }

}