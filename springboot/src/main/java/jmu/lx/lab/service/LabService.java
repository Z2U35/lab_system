package jmu.lx.lab.service;

import jmu.lx.lab.entity.Lab;
import jmu.lx.lab.mapper.LabMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实验室表业务处理
 * 该类负责处理与实验室相关的业务逻辑，包括增删改查等操作。
 */
@Service
public class LabService {

    // 注入 LabMapper，用于访问数据库
    @Resource
    private LabMapper labMapper;

    /**
     * 新增实验室
     * @param lab  需要新增的实验室实体对象
     */
    public void add(Lab lab) {
        // 调用Mapper层的insert方法将实验室信息插入数据库
        labMapper.insert(lab);
    }

    /**
     * 根据ID删除实验室
     * @param id  要删除的实验室ID
     */
    public void deleteById(Integer id) {
        // 调用Mapper层的deleteById方法根据ID删除实验室
        labMapper.deleteById(id);
    }

    /**
     * 批量删除实验室
     * @param ids  要删除的实验室ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 循环调用Mapper层的deleteById方法删除多个实验室
            labMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改实验室信息
     * @param lab  包含要修改的实验室信息的实体对象，必须包含ID
     */
    public void updateById(Lab lab) {
        // 调用Mapper层的updateById方法更新实验室信息
        labMapper.updateById(lab);
    }

    /**
     * 根据ID查询实验室信息
     * @param id  要查询的实验室ID
     * @return  查询到的实验室实体对象，如果不存在则返回null
     */
    public Lab selectById(Integer id) {
        // 调用Mapper层的selectById方法根据ID查询实验室
        return labMapper.selectById(id);
    }

    /**
     * 查询所有实验室
     * @param lab  查询条件，可以为空，表示查询所有
     * @return  查询到的实验室列表
     */
    public List<Lab> selectAll(Lab lab) {
        // 调用Mapper层的selectAll方法查询所有实验室
        return labMapper.selectAll(lab);
    }

    /**
     * 分页查询实验室
     * @param lab  查询条件，可以为空，表示查询所有
     * @param pageNum  当前页码，从1开始
     * @param pageSize  每页显示的数量
     * @return  包含分页信息的PageInfo对象
     */
    public PageInfo<Lab> selectPage(Lab lab, Integer pageNum, Integer pageSize) {
        // 使用PageHelper设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询当前页的实验室数据
        List<Lab> list = labMapper.selectAll(lab);
        // 将查询结果封装成PageInfo对象返回
        return PageInfo.of(list);
    }

}