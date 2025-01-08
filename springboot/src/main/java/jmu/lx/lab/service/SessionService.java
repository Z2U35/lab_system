package jmu.lx.lab.service;

import jmu.lx.lab.entity.Session;
import jmu.lx.lab.mapper.SessionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 节次表业务处理
 * 该类负责处理与节次信息相关的业务逻辑，包括增删改查等操作。
 */
@Service
public class SessionService {

    // 注入 SessionMapper，用于访问数据库
    @Resource
    private SessionMapper sessionMapper;

    /**
     * 新增节次信息
     * @param session 需要新增的节次实体对象
     */
    public void add(Session session) {
        // 调用Mapper层的insert方法将节次信息插入数据库
        sessionMapper.insert(session);
    }

    /**
     * 根据ID删除节次信息
     * @param id  要删除的节次ID
     */
    public void deleteById(Integer id) {
        // 调用Mapper层的deleteById方法根据ID删除节次信息
        sessionMapper.deleteById(id);
    }

    /**
     * 批量删除节次信息
     * @param ids  要删除的节次ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            // 循环调用Mapper层的deleteById方法删除多个节次信息
            sessionMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改节次信息
     * @param session  包含要修改的节次信息的实体对象，必须包含ID
     */
    public void updateById(Session session) {
        // 调用Mapper层的updateById方法更新节次信息
        sessionMapper.updateById(session);
    }

    /**
     * 根据ID查询节次信息
     * @param id  要查询的节次ID
     * @return  查询到的节次实体对象，如果不存在则返回null
     */
    public Session selectById(Integer id) {
        // 调用Mapper层的selectById方法根据ID查询节次信息
        return sessionMapper.selectById(id);
    }

    /**
     * 查询所有节次信息
     * @param session  查询条件，可以为空，表示查询所有
     * @return  查询到的节次列表
     */
    public List<Session> selectAll(Session session) {
        // 调用Mapper层的selectAll方法查询所有节次信息
        return sessionMapper.selectAll(session);
    }

    /**
     * 分页查询节次信息
     * @param session  查询条件，可以为空，表示查询所有
     * @param pageNum  当前页码，从1开始
     * @param pageSize  每页显示的数量
     * @return 包含分页信息的PageInfo对象
     */
    public PageInfo<Session> selectPage(Session session, Integer pageNum, Integer pageSize) {
        // 使用PageHelper设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 查询当前页的节次数据
        List<Session> list = sessionMapper.selectAll(session);
        // 将查询结果封装成PageInfo对象返回
        return PageInfo.of(list);
    }

}