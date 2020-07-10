package me.sample.io.udp.unicast;

import me.java.library.io.Terminal;
import me.java.library.io.TerminalNode;

/**
 * File Name             :  Terminals
 *
 * @author :  sylar
 * Create                :  2019/12/20
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
public interface Terminals {

    Terminal Peer1 = new TerminalNode("peer1", "default");
    Terminal Peer2 = new TerminalNode("peer2", "default");
}
