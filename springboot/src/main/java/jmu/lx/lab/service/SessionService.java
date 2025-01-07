package jmu.lx.lab.service;

import jmu.lx.lab.entity.Session;
import jmu.lx.lab.mapper.SessionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 节次室表业务处理
 **/
@Service
public class SessionService {

    @Resource
    private SessionMapper sessionMapper;

    /**
     * 新增
     */
    public void add(Session session) {
        sessionMapper.insert(session);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        sessionMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            sessionMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Session session) {
        sessionMapper.updateById(session);
    }

    /**
     * 根据ID查询
     */
    public Session selectById(Integer id) {
        return sessionMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Session> selectAll(Session session) {
        return sessionMapper.selectAll(session);
    }

    /**
     * 分页查询
     */
    public PageInfo<Session> selectPage(Session session, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Session> list = sessionMapper.selectAll(session);
        return PageInfo.of(list);
    }

}