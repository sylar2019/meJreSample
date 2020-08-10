package me.sample.io.appFrame.client;


import me.java.library.io.base.cmd.Cmd;
import me.sample.io.appFrame.AbstractEndpoint;

/**
 * File Name             :  TcpServerPipe
 *
 * @author :  sylar
 * Create                :  2019/12/18
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
public abstract class AbstractClient extends AbstractEndpoint implements IClient {

    protected void send(Cmd cmd) {
        if (pipe != null) {
            pipe.send(cmd);
        }
    }

}
