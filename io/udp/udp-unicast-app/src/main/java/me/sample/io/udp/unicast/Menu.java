package me.sample.io.udp.unicast;

/**
 * File Name             :  me.sample.io.udp.unicast.Menu
 *
 * @author :  sylar
 * Create                :  2019/12/9
 * Description           :
 * Reviewed By           :
 * Reviewed On           :
 * Version History       :
 * Modified By           :
 * Modified Date         :
 * Comments              :
 * CopyRight             : COPYRIGHT(c) allthings.vip  All Rights Reserved
 * *******************************************************************************************
 */
public enum Menu {

    //@formatter:off
    Start       ('1',"启动单播"),
    Stop        ('2',"停止单播"),
    Send        ('3',"发送指令"),
    Exit        ('0',"退出"),
     //@formatter:on
    ;

    private char menuKey;
    private String menuName;

    Menu(char menuKey, String menuName) {
        this.menuKey = menuKey;
        this.menuName = menuName;
    }

    public char getMenuKey() {
        return menuKey;
    }

    public String getMenuName() {
        return menuName;
    }

    public static void show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t请选择你要执行的操作:").append("\r\n");
        for (Menu menu : Menu.values()) {
            stringBuilder.append("\t").append(menu.getMenuKey()).append(" - ").append(menu.getMenuName()).append("\r\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static Menu from(char ch) {
        for (Menu menu : Menu.values()) {
            if (menu.getMenuKey() == ch) {
                return menu;
            }
        }
        return null;
    }
}
