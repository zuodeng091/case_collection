package com.casecollection.backend.system;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单表
 * 
 * @Create_by Ranger
 * @Create_Date 2015年5月21日上午1:41:22
 */
public class Menu{

	// 菜单名称
	private String menuName;
	// url
	private String url;
    // 图表css class
    private String iconClass;
	// 父菜单id
	private Long parentId;
	// 菜单级别
	private int level;
	//权限级别
	private int rightLevel;
	// 子菜单
	private List<Menu> childMenu = new ArrayList<Menu>();
	
	private Integer power;

	public Menu(String menuName, String url, String iconClass, Long parentId, int level, Integer power) {
        super();
        this.menuName = menuName;
        this.url = url;
        this.iconClass = iconClass;
        this.parentId = parentId;
        this.level = level;
        this.power = power;
        this.rightLevel = rightLevel;
    }

    public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<Menu> getChildMenu() {
		return childMenu;
	}

	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public int getRightLevel() {
        return rightLevel;
    }

    public void setRightLevel(int rightLevel) {
        this.rightLevel = rightLevel;
    }
}
