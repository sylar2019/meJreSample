package me.sample.io.codec.jsonLine;

import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * File Name             :  CustomFrameDecoder
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
public class JsonFrameDecoder extends LineBasedFrameDecoder {
    private final static int MAX_LENGTH = 1024 * 10;

    public JsonFrameDecoder() {
        super(MAX_LENGTH);
    }
}
