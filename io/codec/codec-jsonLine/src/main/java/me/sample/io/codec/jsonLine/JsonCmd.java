package me.sample.io.codec.jsonLine;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Strings;
import me.java.library.io.common.cmd.CmdNode;
import me.java.library.io.common.cmd.CmdType;
import me.java.library.io.common.cmd.Terminal;
import me.java.library.io.common.cmd.TerminalNode;
import me.java.library.utils.base.JsonUtils;

/**
 * File Name             :  JsonCmd
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
public class JsonCmd extends CmdNode {
    private final static String CMD_ATTR = "cmdAttr";

    public JsonCmd() {
    }

    public JsonCmd(Terminal from, Terminal to) {
        super(from, to);
    }

    public JsonCmd(Terminal from, Terminal to, String code) {
        super(from, to, code);
    }

    public JsonCmd(Terminal from, Terminal to, String code, CmdType type) {
        super(from, to, code, type);
    }

    public <V> V getAttr(Class<V> attrClass) {
        String attrJson = super.getAttr(CMD_ATTR);
        if (!Strings.isNullOrEmpty(attrJson)) {
            JsonUtils.parseObject(attrJson, attrClass);
        }
        return null;
    }

    public void setAttr(Object attrValue) {
        super.setAttr(CMD_ATTR, attrValue);
    }

    @Override
    @JsonDeserialize(as = TerminalNode.class)
    public Terminal getFrom() {
        return super.getFrom();
    }

    @Override
    @JsonDeserialize(as = TerminalNode.class)
    public Terminal getTo() {
        return super.getTo();
    }
}
