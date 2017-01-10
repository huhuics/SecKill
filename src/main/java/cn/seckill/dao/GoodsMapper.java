package cn.seckill.dao;

import cn.seckill.domain.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    /**
     * 加锁查询
     * @param id 主键
     * @return   Goods
     */
    Goods selectForUpdate(Long id);

    /**
     * 修改(乐观锁)
     * @param goods
     * @return
     */
    int updateWithCAS(Goods goods);
}