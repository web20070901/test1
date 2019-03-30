package com.wangyulong.fxmall.goods.dao.impl;

import com.wangyulong.fxmall.commons.info.SystemConstantsUtils;
import com.wangyulong.fxmall.commons.mybatis.MybatisSessionFactoryUtils;
import com.wangyulong.fxmall.goods.bean.WxbGood;
import com.wangyulong.fxmall.goods.dao.IGoodDAO;
import com.wangyulong.fxmall.goods.mapper.GoodsMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * mybatis的商品数据访问层
 * 注意 异常在dao层不要捕获
 */

public class GoodsDAOImpl implements IGoodDAO {

    @Override
    public List<WxbGood> queryGoodsByPage(Integer page) {
        GoodsMapper goodsMapper = MybatisSessionFactoryUtils.getSession().getMapper(GoodsMapper.class);
        List<WxbGood> wxbGoods = goodsMapper.queryGoodsByPage(page, SystemConstantsUtils.Page.PAGE_SIZE);
        return wxbGoods;
    }

    @Override
    public void inGoods(WxbGood wxbGood) {
        SqlSession session = MybatisSessionFactoryUtils.getSession();
        GoodsMapper goodsMapper = session.getMapper(GoodsMapper.class);
        goodsMapper.inGoods(wxbGood);
        session.commit();
    }
}
