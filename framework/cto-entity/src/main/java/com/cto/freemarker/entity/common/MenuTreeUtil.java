package com.cto.freemarker.entity.common;

import java.util.ArrayList;
import java.util.List;

import com.cto.freemarker.entity.Menu;

/**
 * 菜单构建
 * @author Bigger-Xu
 * @mail 562123314@qq.com
 * @date 2017年5月5日 上午10:20:13
 */
public class MenuTreeUtil {
    
    private final static String MENU_ID = "menu_";
    private final static String BTN_ID = "btn_";
    
    List<Menu> rootMenus;
    List<Menu> childMenus;

    public MenuTreeUtil(List<Menu> rootMenus, List<Menu> childMenus){
        this.rootMenus = rootMenus;
        this.childMenus = childMenus;
    }

    public List<TreeNode> getTreeNode(){
        return getRootNodes();
    }
    
    private TreeNode menuToNode(Menu menu){
        if(menu == null){
            return null;
        }
        TreeNode node = new TreeNode();
        node.setId(MENU_ID + menu.getId());
        node.setDataId(menu.getId());
        node.setName(menu.getName());
        node.setUrl(menu.getUrl());
        node.setDescription(menu.getPermission());
        node.setParentId(menu.getParentId());
        node.getAttributes().put("type", "0");
        node.getAttributes().put("id", menu.getId());
        return node;
    }


    private void addChlidNodes(TreeNode rootNode){
        List<TreeNode> childNodes = new ArrayList<TreeNode>();  
        for(Menu menu : childMenus){
            if(rootNode.getDataId().equals(menu.getParentId())){
                TreeNode node = menuToNode(menu);
                childNodes.add(node);
            }
        }
        rootNode.setChildren(childNodes);
    }
    
    private List<TreeNode> getRootNodes(){
        List<TreeNode> rootNodes = new ArrayList<TreeNode>();
        for(Menu menu : rootMenus){
            TreeNode node = menuToNode(menu);
            if(node != null){
                addChlidNodes(node);
                rootNodes.add(node);
            }
        }
        return rootNodes;
    }

    /**
     * 构建树形数据
     * @return
     */
    public static List<TreeNode> treeMenu(List<Menu> rootMenus,List<Menu> childMenus){
        MenuTreeUtil util = new MenuTreeUtil(rootMenus,childMenus);
        List<TreeNode> nodeList = util.getTreeNode();
        for (TreeNode node : nodeList) {
            if ("消息中心".equals(node.getName())) {
                node.setIcon("icon-envelope");
            } else if ("日志管理".equals(node.getName().trim())) {
                node.setIcon("icon-dashboard");
            } else if ("消息管理".equals(node.getName().trim())) {
                node.setIcon("icon-glass");
            } else if ("公告管理".equals(node.getName().trim())) {
                node.setIcon("icon-plane");
            } else if ("课程管理".equals(node.getName().trim())) {
                node.setIcon("icon-pause");
            } else if ("题库管理".equals(node.getName().trim())) {
                node.setIcon("icon-print");
            } else if ("教务管理".equals(node.getName().trim())) {
                node.setIcon("icon-user");
            } else if ("资源管理".equals(node.getName().trim())) {
                node.setIcon("icon-align-left");
            } else if ("系统管理".equals(node.getName().trim())) {
                node.setIcon("icon-wrench");
            } else if ("调课中心".equals(node.getName().trim())) {
                node.setIcon("icon-shopping-cart");
            } else if ("实验管理".equals(node.getName().trim())) {
                node.setIcon("icon-fire");
            } else if ("下载中心".equals(node.getName().trim())) {
                node.setIcon("icon-print");
            } else if ("考试中心".equals(node.getName().trim())) {
                node.setIcon("icon-print");
            } else{
                node.setIcon("icon-envelope");
            }
        }
        return nodeList;
    }
}
