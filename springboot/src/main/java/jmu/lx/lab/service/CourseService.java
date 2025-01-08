package jmu.lx.lab.service;

import jmu.lx.lab.entity.Course;
import jmu.lx.lab.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程表业务处理
 * 该类负责处理与课程相关的业务逻辑，包括增删改查等操作。
 */
@Service
public class CourseService {

    // 注入 CourseMapper，用于访问数据库
    @Resource
    private CourseMapper courseMapper;

    /**
     * 新增课程
     * @param course  需要新增的课程实体对象
     */
    public void add(Course course) {
        courseMapper.insert(course); // 调用Mapper层的insert方法将课程信息插入数据库
    }

    /**
     * 根据ID删除课程
     * @param id  要删除的课程ID
     */
    public void deleteById(Integer id) {
        courseMapper.deleteById(id); // 调用Mapper层的deleteById方法根据ID删除课程
    }

    /**
     * 批量删除课程
     * @param ids  要删除的课程ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            courseMapper.deleteById(id); // 循环调用Mapper层的deleteById方法删除多个课程
        }
    }

    /**
     * 根据ID修改课程信息
     * @param course  包含要修改的课程信息的实体对象，必须包含ID
     */
    public void updateById(Course course) {
        courseMapper.updateById(course); // 调用Mapper层的updateById方法更新课程信息
    }

    /**
     * 根据ID查询课程信息
     * @param id  要查询的课程ID
     * @return  查询到的课程实体对象，如果不存在则返回null
     */
    public Course selectById(Integer id) {
        return courseMapper.selectById(id); // 调用Mapper层的selectById方法根据ID查询课程
    }

    /**
     * 查询所有课程
     * @param course  查询条件，可以为空，表示查询所有
     * @return  查询到的课程列表
     */
    public List<Course> selectAll(Course course) {
        return courseMapper.selectAll(course); // 调用Mapper层的selectAll方法查询所有课程
    }

    /**
     * 分页查询课程
     * @param course  查询条件，可以为空，表示查询所有
     * @param pageNum  当前页码，从1开始
     * @param pageSize  每页显示的数量
     * @return  包含分页信息的PageInfo对象
     */
    public PageInfo<Course> selectPage(Course course, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize); // 使用PageHelper设置分页参数
        List<Course> list = courseMapper.selectAll(course); // 查询当前页的课程数据
        return PageInfo.of(list); // 将查询结果封装成PageInfo对象返回
    }

}