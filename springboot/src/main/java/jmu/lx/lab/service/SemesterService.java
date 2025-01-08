package jmu.lx.lab.service;

import jmu.lx.lab.entity.Semester;
import jmu.lx.lab.mapper.SemesterMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学期信息表业务处理
 * 该类负责处理与学期信息相关的业务逻辑，包括增删改查、查询当前学期等操作。
 */
@Service
public class SemesterService {

    // 注入 SemesterMapper，用于访问数据库
    @Resource
    private SemesterMapper semesterMapper;

    /**
     * 新增学期信息
     * @param semester  需要新增的学期实体对象
     */
    public void add(Semester semester) {
        // 调用Mapper层的insert方法将学期信息插入数据库
        semesterMapper.insert(semester);
    }

    /**
     * 根据ID删除学期信息
     * @param id  要删除的学期ID
     */
    public void deleteById(Integer id) {
        // 调用Mapper层的deleteById方法根据ID删除学期信息
        semesterMapper.deleteById(id);
    }

    /**
     * 批量删除学期信息
     * @param ids  要删除的学期ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 循环调用Mapper层的deleteById方法删除多个学期信息
            semesterMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改学期信息
     * @param semester  包含要修改的学期信息的实体对象，必须包含ID
     */
    public void updateById(Semester semester) {
        // 调用Mapper层的updateById方法更新学期信息
        semesterMapper.updateById(semester);
    }

    /**
     * 根据ID查询学期信息
     * @param id  要查询的学期ID
     * @return  查询到的学期实体对象，如果不存在则返回null
     */
    public Semester selectById(Integer id) {
        // 调用Mapper层的selectById方法根据ID查询学期信息
        return semesterMapper.selectById(id);
    }

    /**
     * 查询所有学期信息
     * @param semester  查询条件，可以为空，表示查询所有
     * @return  查询到的学期列表
     */
    public List<Semester> selectAll(Semester semester) {
        // 调用Mapper层的selectAll方法查询所有学期信息
        return semesterMapper.selectAll(semester);
    }

    /**
     * 分页查询学期信息
     * @param semester  查询条件，可以为空，表示查询所有
     * @param pageNum  当前页码，从1开始
     * @param pageSize  每页显示的数量
     * @return 包含分页信息的PageInfo对象
     */
    public PageInfo<Semester> selectPage(Semester semester, Integer pageNum, Integer pageSize) {
        // 使用PageHelper设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询当前页的学期数据
        List<Semester> list = semesterMapper.selectAll(semester);
        // 将查询结果封装成PageInfo对象返回
        return PageInfo.of(list);
    }

    /**
     * 查询当前学期信息
     * @param semester  查询条件，可以为空，这里主要用于传递一些额外的查询参数
     * @return  查询到的当前学期实体对象，如果不存在则返回null
     */
    public Semester selectCurSemester(Semester semester) {
        // 调用Mapper层的selectCurSemester方法查询当前学期信息
        return semesterMapper.selectCurSemester(semester);
    }

    /**
     * 修改当前学期信息
     * @param semester  包含要修改的当前学期信息的实体对象，必须包含ID
     */
    public void updateCurSemester(Semester semester) {
        // 调用Mapper层的updateCurSemester方法更新当前学期信息
        semesterMapper.updateCurSemester(semester);
    }

}