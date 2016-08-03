package com.casecollection.backend.constants;

import java.util.ArrayList;
import java.util.List;

import com.casecollection.backend.system.Menu;

public class MenuConstants {

    private static List<Menu> schoolMenus = new ArrayList<>();
    private static List<Menu> adminMenus = new ArrayList<>();
    
    static {
        //一级菜单
        int schoolLevel1Count = 0;
        int adminLevel1Count = 0;
        adminMenus.add(new Menu("系统管理", null, "fa fa-wrench", 0L, 1, adminLevel1Count++));
        schoolMenus.add(new Menu("系统管理", null, "fa fa-wrench", 0L, 1, schoolLevel1Count++));
        schoolMenus.add(new Menu("信息管理", null, "fa fa-list", 0L, 1, schoolLevel1Count++));
        schoolMenus.add(new Menu("答疑管理", null, "fa fa-question", 0L, 1, schoolLevel1Count++));
        schoolMenus.add(new Menu("专业管理", null, "glyphicon glyphicon-book", 0L, 1, schoolLevel1Count++));
        schoolMenus.add(new Menu("录取数据管理", null, "fa fa-database", 0L, 1, schoolLevel1Count++));
        schoolMenus.add(new Menu("招录政策", null, "glyphicon glyphicon-info-sign", 0L, 1, schoolLevel1Count++));
        schoolMenus.add(new Menu("用户数据", null, "glyphicon glyphicon-user", 0L, 1, schoolLevel1Count++));
        schoolMenus.add(new Menu("辅助插件", null, "fa fa-plus", 0L, 1, schoolLevel1Count++));

        //二级菜单
        schoolLevel1Count = 0;
        adminLevel1Count = 0;
        int schoolLevel2Count = 0;
        int adminLevel2Count = 0;
        adminMenus.get(adminLevel1Count).getChildMenu().add(new Menu("帮助文档管理", "/helpDoc/toList", "", 0L, 2, adminLevel2Count++));
        adminMenus.get(adminLevel1Count).getChildMenu().add(new Menu("数据字典管理", "/dictionary/toList", "", 0L, 2, adminLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("基础配置管理", "/school/manager", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("数据字典管理", "/dictionary/toList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("用户管理", "/user/toList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("个人信息", "/user/manager", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("修改密码", "/user/toResetPassword", "", 0L, 2, schoolLevel2Count++));

        schoolLevel2Count = 0;
        schoolLevel1Count++;
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("师资学生管理", "/schoolExt/toList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("联系我们", "/contactWay/toContactWayList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("信息分类管理", "/infoCat/page", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("信息发布管理", "/infoContent/page", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("信息发布审核", "/infoContent/verifyList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("校园风采管理", "/schoolScenery/page", "", 0L, 2, schoolLevel2Count++));

        schoolLevel2Count = 0;
        schoolLevel1Count++;
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("待认领问题", "/answerlog/toList?type=3", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("待处理问题", "/answerlog/toList?type=1", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("待审核问题", "/answerlog/toList?type=2", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("已解决问题", "/answerlog/toList?type=0", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("预置话术", "/preTalk/toList", "", 0L, 2, schoolLevel2Count++));

        schoolLevel2Count = 0;
        schoolLevel1Count++;
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("院系信息管理", "/department/toDepartList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("专业信息管理", "/major/toMajorList", "", 0L, 2, schoolLevel2Count++));

        schoolLevel2Count = 0;
        schoolLevel1Count++;
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("专业录取数据管理", "/enroll/majorScore", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("录取线级省线管理", "/enroll/schoolScore", "", 0L, 2, schoolLevel2Count++));

        schoolLevel2Count = 0;
        schoolLevel1Count++;
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("规则分类", "/enrollRule/toList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("规则详细", "/enrollRuleItem/toList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("学校招生计划", "/enrollPlan/toTotalList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("专业招生计划", "/enrollPlan/toList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("预测分数线", "/enrollPlan/toPreList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("预测模型管理", "/preManager/toList", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("预测分数开关", "/switch/toList", "", 0L, 2, schoolLevel2Count++));

        schoolLevel2Count = 0;
        schoolLevel1Count++;
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("用户建议查询", "/userAdvice/page", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("用户访问统计", "/report/userView", "", 0L, 2, schoolLevel2Count++));

        schoolLevel2Count = 0;
        schoolLevel1Count++;
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("分数预测短信模板", "/plugin/createPreSms", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("录取进度短信模板", "/plugin/enrollNumSms", "", 0L, 2, schoolLevel2Count++));
        schoolMenus.get(schoolLevel1Count).getChildMenu().add(new Menu("考生录取短信模板", "/plugin/enrollStatusSms", "", 0L, 2, schoolLevel2Count++));


    }

    public static List<Menu> getSchoolMenus() {
        return schoolMenus;
    }

    public static List<Menu> getAdminMenus() {
        return adminMenus;
    }
    
    public static List<Menu> getMenu1List() {
        List<Menu> list = new ArrayList<>();
        for(Menu m : schoolMenus) {
            if(m.getLevel() == 1) {
                list.add(m);
            }
        }
        return list;
    }
    
    public static List<Menu> getMenu2List(String menu1Name) {
        for(Menu m : schoolMenus) {
            if(m.getLevel() == 1 && menu1Name.equals(m.getMenuName())) {
                return m.getChildMenu();
            }
        }
        return new ArrayList<>();
    }
}
