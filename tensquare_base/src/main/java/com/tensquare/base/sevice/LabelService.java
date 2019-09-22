package com.tensquare.base.sevice;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public boolean updateById(String labelId, Label label) {
        if (labelDao.existsById(labelId)) {
            label.setId(labelId);
            labelDao.save(label);
            return true;
        }
        return false;
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

    /**
     * 标签条件查询
     * @param map
     * @return
     */
    public List<Label> search(Map map) {
        //拼接查询条件

        return labelDao.findAll(getSpecification(map));
    }
    /**
     *
     * @param map
     * @return
     */
    public Page<Label> searchByPage(Map map,Integer page,Integer size) {

        //拼接分页条件（当前页码和每页显示记录数)
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(getSpecification(map), pageRequest);
    }

    //抽取一个公共的获得Specification方法
    public Specification<Label> getSpecification(Map map){
        //拼接查询条件
        return new Specification<Label>(){
            @Nullable
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                //map:标签名称 state 状态
                String labelname = (String) map.get("labelname");
                String state = (String) map.get("state");
                //将Predicate放入list中
                ArrayList<Predicate> objects = new ArrayList<>();
                if (!StringUtils.isEmpty(labelname)) {
                    Predicate p1 = criteriaBuilder.like(root.get("labelname").as(String.class),"%"+labelname+"%");
                    objects.add(p1);
                }
                if (!StringUtils.isEmpty(state)) {
                    Predicate p2 = criteriaBuilder.equal(root.get("state").as(String.class),state);
                    objects.add(p2);
                }
                if (objects == null || objects.size() == 0) {
                    return null;
                }
                //最终需要转成的数组对象
                Predicate[] predicates = new Predicate[objects.size()];
                //将list集合装成predicates数组
                Predicate[] predicatesArray = objects.toArray(predicates);
                return criteriaBuilder.and(predicatesArray);//条件1==XXX 条件2=yyy
            }
        };

    }

}
