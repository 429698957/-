package com.tensquare.base.sevice;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

/**
 * 标签的业务层
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 增加标签
     * @param label
     */
    public void save(Label label) {
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 根据ID来删除标签
     * @param labelId
     */
    public void deleteById(String labelId) {
       labelDao.deleteById(labelId);
    }

    /**
     * 根据Id更新数据
     * @param labelId
     * @param label
     */
    public void updateById(String labelId, Label label) {

        label.setId(labelId);
        labelDao.save(label);
    }

    /**
     * 查询所有标签数据
     * @return
     */
    public List<Label> findAll() {
            return labelDao.findAll();
    }

    /**
     * 根据单个ID查询标签数据
     * @return
     */
    public Label findById(String lableId) {
        return labelDao.findById(lableId).get();
    }
}
