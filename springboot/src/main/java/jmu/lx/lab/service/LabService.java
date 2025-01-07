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
 **/
@Service
public class LabService {

    @Resource
    private LabMapper labMapper;

    /**
     * 新增
     */
    public void add(Lab lab) {
        labMapper.insert(lab);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        labMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            labMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Lab lab) {
        labMapper.updateById(lab);
    }

    /**
     * 根据ID查询
     */
    public Lab selectById(Integer id) {
        return labMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Lab> selectAll(Lab lab) {
        return labMapper.selectAll(lab);
    }

    /**
     * 分页查询
     */
    public PageInfo<Lab> selectPage(Lab lab, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Lab> list = labMapper.selectAll(lab);
        return PageInfo.of(list);
    }

}