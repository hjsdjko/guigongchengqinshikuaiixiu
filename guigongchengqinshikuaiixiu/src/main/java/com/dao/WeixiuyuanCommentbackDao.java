package com.dao;

import com.entity.WeixiuyuanCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WeixiuyuanCommentbackView;

/**
 * 维修人员评价 Dao 接口
 *
 * @author 
 */
public interface WeixiuyuanCommentbackDao extends BaseMapper<WeixiuyuanCommentbackEntity> {

   List<WeixiuyuanCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
