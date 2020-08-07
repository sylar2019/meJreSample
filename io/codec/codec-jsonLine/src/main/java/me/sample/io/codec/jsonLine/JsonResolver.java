package me.sample.io.codec.jsonLine;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import me.java.library.io.base.cmd.Cmd;
import me.java.library.io.core.codec.SimpleCmdResolver;
import me.java.library.utils.base.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * File Name             :  JsonResolver
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
public class JsonResolver implements SimpleCmdResolver {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Cmd> bufToCmd(ChannelHandlerContext ctx, ByteBuf buf) {
        List<Cmd> list = Lists.newArrayList();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String json = new String(bytes, CharsetUtil.UTF_8);
        json = json.trim();

        logger.debug("### bufToCmd:\n" + json);
        if (!Strings.isNullOrEmpty(json)) {
            JsonCmd cmd = JsonUtils.parseObject(json, JsonCmd.class);
            if (cmd != null) {
                list.add(cmd);
            }
        }

        return list;
    }

    @Override
    public ByteBuf cmdToBuf(Cmd cmd) {
        ByteBuf buf = Unpooled.buffer();
        String json = JsonUtils.toJSONString(cmd) + "\r\n";

        logger.debug("### bufToCmd:\n" + json);
        buf.writeBytes(json.getBytes(CharsetUtil.UTF_8));
        return buf;
    }
}
