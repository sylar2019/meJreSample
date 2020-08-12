package me.sample.io.appFrame.client;

/**
 * File Name             :  Menu
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
public enum ClientMenu {

    //@formatter:off
    Start       ('1',"启动客户端"),
    Stop        ('2',"停止客户端"),
    Send        ('3',"发送测试指令"),
    Exit        ('0',"退出"),
     //@formatter:on
    ;

    private char menuKey;
    private String menuName;

    ClientMenu(char menuKey, String menuName) {
        this.menuKey = menuKey;
        this.menuName = menuName;
    }

    public static void show() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t请选择你要执行的操作:").append("\r\n");
        for (ClientMenu menu : ClientMenu.values()) {
            stringBuilder.append("\t").append(menu.getMenuKey()).append(" - ").append(menu.getMenuName()).append("\r\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static ClientMenu from(char ch) {
        for (ClientMenu menu : ClientMenu.values()) {
            if (menu.getMenuKey() == ch) {
                return menu;
            }
        }
        return null;
    }

    public char getMenuKey() {
        return menuKey;
    }

    public String getMenuName() {
        return menuName;
    }
}
