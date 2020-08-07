package me.sample.io.codec.jsonLine;


import me.java.library.io.base.cmd.Terminal;

/**
 * File Name             :  JsonCmdUtils
 *
 * @author :  sylar
 * Create                :  2019/12/17
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
public class JsonCmdUtils {
    public static JsonCmd clientToServer(String cmdCode, Object cmdAttr) {
        JsonCmd cmd = clientToServer(cmdCode);
        cmd.setAttr(cmdAttr);
        return cmd;
    }

    public static JsonCmd clientToServer(String cmdCode) {
        return new JsonCmd(Terminal.CLIENT, Terminal.SERVER, cmdCode);
    }

    public static JsonCmd serverToClient(String cmdCode, Object cmdAttr) {
        JsonCmd cmd = serverToClient(cmdCode);
        cmd.setAttr(cmdAttr);
        return cmd;
    }

    public static JsonCmd serverToClient(String cmdCode) {
        return new JsonCmd(Terminal.SERVER, Terminal.CLIENT, cmdCode);
    }


}
