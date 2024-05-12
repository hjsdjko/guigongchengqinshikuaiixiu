
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 在线报修
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/guzhangshangbao")
public class GuzhangshangbaoController {
    private static final Logger logger = LoggerFactory.getLogger(GuzhangshangbaoController.class);

    private static final String TABLE_NAME = "guzhangshangbao";

    @Autowired
    private GuzhangshangbaoService guzhangshangbaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private NewsService newsService;//公告
    @Autowired
    private WeixiuyuanService weixiuyuanService;//维修人员
    @Autowired
    private WeixiuyuanCommentbackService weixiuyuanCommentbackService;//维修人员评价
    @Autowired
    private YonghuService yonghuService;//学生
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("维修人员".equals(role))
            params.put("weixiuyuanId",request.getSession().getAttribute("userId"));
        params.put("guzhangshangbaoDeleteStart",1);params.put("guzhangshangbaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = guzhangshangbaoService.queryPage(params);

        //字典表数据转换
        List<GuzhangshangbaoView> list =(List<GuzhangshangbaoView>)page.getList();
        for(GuzhangshangbaoView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuzhangshangbaoEntity guzhangshangbao = guzhangshangbaoService.selectById(id);
        if(guzhangshangbao !=null){
            //entity转view
            GuzhangshangbaoView view = new GuzhangshangbaoView();
            BeanUtils.copyProperties( guzhangshangbao , view );//把实体数据重构到view中
            //级联表 维修人员
            //级联表
            WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectById(guzhangshangbao.getWeixiuyuanId());
            if(weixiuyuan != null){
            BeanUtils.copyProperties( weixiuyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "weixiuyuanId"
, "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setWeixiuyuanId(weixiuyuan.getId());
            }
            //级联表 学生
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(guzhangshangbao.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "weixiuyuanId"
, "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody GuzhangshangbaoEntity guzhangshangbao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,guzhangshangbao:{}",this.getClass().getName(),guzhangshangbao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("维修人员".equals(role))
            guzhangshangbao.setWeixiuyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("学生".equals(role))
            guzhangshangbao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<GuzhangshangbaoEntity> queryWrapper = new EntityWrapper<GuzhangshangbaoEntity>()
            .eq("yonghu_id", guzhangshangbao.getYonghuId())
            .eq("quyu_types", guzhangshangbao.getQuyuTypes())
            .eq("guzhangshangbao_address", guzhangshangbao.getGuzhangshangbaoAddress())
            .eq("guzhangshangbao_guzhang", guzhangshangbao.getGuzhangshangbaoGuzhang())
            .eq("guzhangshangbao_types", guzhangshangbao.getGuzhangshangbaoTypes())
            .eq("weixiuyuan_id", guzhangshangbao.getWeixiuyuanId())
            .in("guzhangshangbao_yesno_types", new Integer[]{1,2})
            .eq("guzhangshangbao_delete", guzhangshangbao.getGuzhangshangbaoDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuzhangshangbaoEntity guzhangshangbaoEntity = guzhangshangbaoService.selectOne(queryWrapper);
        if(guzhangshangbaoEntity==null){
            guzhangshangbao.setGuzhangshangbaoYesnoTypes(1);
            guzhangshangbao.setGuzhangshangbaoDelete(1);
            guzhangshangbao.setCreateTime(new Date());
            guzhangshangbaoService.insert(guzhangshangbao);
            return R.ok();
        }else {
            if(guzhangshangbaoEntity.getGuzhangshangbaoYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(guzhangshangbaoEntity.getGuzhangshangbaoYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GuzhangshangbaoEntity guzhangshangbao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,guzhangshangbao:{}",this.getClass().getName(),guzhangshangbao.toString());
        GuzhangshangbaoEntity oldGuzhangshangbaoEntity = guzhangshangbaoService.selectById(guzhangshangbao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));

            if(oldGuzhangshangbaoEntity.getGuzhangshangbaoYesnoTypes() == 3 || guzhangshangbao.getGuzhangshangbaoYesnoTypes() == 3){
                guzhangshangbao.setGuzhangshangbaoYesnoTypes(1);
            }
            if(guzhangshangbao.getGuzhangshangbaoYesnoTypes() == 2 && guzhangshangbao.getWeixiuyuanId() !=null){
                guzhangshangbao.setGuzhangshangbaoYesnoTypes(4);
            }

            guzhangshangbaoService.updateById(guzhangshangbao);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody GuzhangshangbaoEntity guzhangshangbaoEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,guzhangshangbaoEntity:{}",this.getClass().getName(),guzhangshangbaoEntity.toString());

        GuzhangshangbaoEntity oldGuzhangshangbao = guzhangshangbaoService.selectById(guzhangshangbaoEntity.getId());//查询原先数据

//        if(guzhangshangbaoEntity.getGuzhangshangbaoYesnoTypes() == 2){//通过
//            guzhangshangbaoEntity.setGuzhangshangbaoTypes();
//        }else if(guzhangshangbaoEntity.getGuzhangshangbaoYesnoTypes() == 3){//拒绝
//            guzhangshangbaoEntity.setGuzhangshangbaoTypes();
//        }
        guzhangshangbaoService.updateById(guzhangshangbaoEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<GuzhangshangbaoEntity> oldGuzhangshangbaoList =guzhangshangbaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<GuzhangshangbaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            GuzhangshangbaoEntity guzhangshangbaoEntity = new GuzhangshangbaoEntity();
            guzhangshangbaoEntity.setId(id);
            guzhangshangbaoEntity.setGuzhangshangbaoDelete(2);
            list.add(guzhangshangbaoEntity);
        }
        if(list != null && list.size() >0){
            guzhangshangbaoService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<GuzhangshangbaoEntity> guzhangshangbaoList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            GuzhangshangbaoEntity guzhangshangbaoEntity = new GuzhangshangbaoEntity();
//                            guzhangshangbaoEntity.setYonghuId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoUuidNumber(data.get(0));                    //报修编号 要改的
//                            guzhangshangbaoEntity.setQuyuTypes(Integer.valueOf(data.get(0)));   //区域 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoAddress(data.get(0));                    //详细地址 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoGuzhang(data.get(0));                    //故障信息 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoTypes(Integer.valueOf(data.get(0)));   //故障类型 要改的
//                            guzhangshangbaoEntity.setWeixiuyuanId(Integer.valueOf(data.get(0)));   //维修人员 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoYesnoTypes(Integer.valueOf(data.get(0)));   //处理结果 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoYesnoText(data.get(0));                    //处理回复 要改的
//                            guzhangshangbaoEntity.setGuzhangshangbaoDelete(1);//逻辑删除字段
//                            guzhangshangbaoEntity.setCreateTime(date);//时间
                            guzhangshangbaoList.add(guzhangshangbaoEntity);


                            //把要查询是否重复的字段放入map中
                                //报修编号
                                if(seachFields.containsKey("guzhangshangbaoUuidNumber")){
                                    List<String> guzhangshangbaoUuidNumber = seachFields.get("guzhangshangbaoUuidNumber");
                                    guzhangshangbaoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> guzhangshangbaoUuidNumber = new ArrayList<>();
                                    guzhangshangbaoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("guzhangshangbaoUuidNumber",guzhangshangbaoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //报修编号
                        List<GuzhangshangbaoEntity> guzhangshangbaoEntities_guzhangshangbaoUuidNumber = guzhangshangbaoService.selectList(new EntityWrapper<GuzhangshangbaoEntity>().in("guzhangshangbao_uuid_number", seachFields.get("guzhangshangbaoUuidNumber")).eq("guzhangshangbao_delete", 1));
                        if(guzhangshangbaoEntities_guzhangshangbaoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GuzhangshangbaoEntity s:guzhangshangbaoEntities_guzhangshangbaoUuidNumber){
                                repeatFields.add(s.getGuzhangshangbaoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [报修编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        guzhangshangbaoService.insertBatch(guzhangshangbaoList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = guzhangshangbaoService.queryPage(params);

        //字典表数据转换
        List<GuzhangshangbaoView> list =(List<GuzhangshangbaoView>)page.getList();
        for(GuzhangshangbaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        GuzhangshangbaoEntity guzhangshangbao = guzhangshangbaoService.selectById(id);
            if(guzhangshangbao !=null){


                //entity转view
                GuzhangshangbaoView view = new GuzhangshangbaoView();
                BeanUtils.copyProperties( guzhangshangbao , view );//把实体数据重构到view中

                //级联表
                    WeixiuyuanEntity weixiuyuan = weixiuyuanService.selectById(guzhangshangbao.getWeixiuyuanId());
                if(weixiuyuan != null){
                    BeanUtils.copyProperties( weixiuyuan , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWeixiuyuanId(weixiuyuan.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(guzhangshangbao.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody GuzhangshangbaoEntity guzhangshangbao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,guzhangshangbao:{}",this.getClass().getName(),guzhangshangbao.toString());
        Wrapper<GuzhangshangbaoEntity> queryWrapper = new EntityWrapper<GuzhangshangbaoEntity>()
            .eq("yonghu_id", guzhangshangbao.getYonghuId())
            .eq("guzhangshangbao_uuid_number", guzhangshangbao.getGuzhangshangbaoUuidNumber())
            .eq("quyu_types", guzhangshangbao.getQuyuTypes())
            .eq("guzhangshangbao_address", guzhangshangbao.getGuzhangshangbaoAddress())
            .eq("guzhangshangbao_guzhang", guzhangshangbao.getGuzhangshangbaoGuzhang())
            .eq("guzhangshangbao_types", guzhangshangbao.getGuzhangshangbaoTypes())
            .eq("weixiuyuan_id", guzhangshangbao.getWeixiuyuanId())
            .in("guzhangshangbao_yesno_types", new Integer[]{1,2})
            .eq("guzhangshangbao_yesno_text", guzhangshangbao.getGuzhangshangbaoYesnoText())
            .eq("guzhangshangbao_delete", guzhangshangbao.getGuzhangshangbaoDelete())
//            .notIn("guzhangshangbao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GuzhangshangbaoEntity guzhangshangbaoEntity = guzhangshangbaoService.selectOne(queryWrapper);
        if(guzhangshangbaoEntity==null){
            guzhangshangbao.setGuzhangshangbaoYesnoTypes(1);
            guzhangshangbao.setGuzhangshangbaoDelete(1);
            guzhangshangbao.setCreateTime(new Date());
        guzhangshangbaoService.insert(guzhangshangbao);

            return R.ok();
        }else {
            if(guzhangshangbaoEntity.getGuzhangshangbaoYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(guzhangshangbaoEntity.getGuzhangshangbaoYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

