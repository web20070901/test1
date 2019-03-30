package com.wangyulong.fxmall.goods.controller;

import com.wangyulong.fxmall.goods.bean.WxbGood;
import com.wangyulong.fxmall.goods.service.IGoodsService;
import com.wangyulong.fxmall.goods.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * master
 */
public class GoodsServlet extends HttpServlet {
    private IGoodsService goodsService = new GoodsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        pageStr = pageStr == null?"1":pageStr;
        List<WxbGood> goodList = goodsService.queryGoodsByPage(Integer.parseInt(pageStr));
        req.setAttribute("goodList",goodList);
        req.getRequestDispatcher("goods_list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
