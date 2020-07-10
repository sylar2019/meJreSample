package me.sample.monolith;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * File Name             :  RoomAlgorithm
 *
 * @author :  sylar
 * Create                :  2020/6/3
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
public class RoomAlgorithm {

    class Channel {
        public LinkedList<Line> lines = Lists.newLinkedList();

        public Channel append(Line line) {
            lines.add(line);
            return this;
        }

        /**
         * 求一个通道上最大的s
         *
         * @param lines
         * @return
         */
        int max(List<Line> lines) {
            return lines.stream().max(Comparator.comparingInt(o -> o.s)).get().s;
        }
    }

    class Line {
        public int x;
        public int y;
        public int s;
    }


    List<Integer> parallelCount(List<Channel> channels) {
        channels.forEach(channel -> {

        });
        return null;
    }

    List<Channel> createChannels(List<Line> lines) {
        //先对线段排序
        lines = lines.stream().sorted(Comparator.comparingInt(l -> l.x)).collect(Collectors.toList());
        List<Channel> channels = Lists.newArrayList();
        while (true) {
            Channel channel = createChannel(lines);
            if (channel != null) {
                channels.add(channel);
            } else {
                break;
            }
        }
        return channels;
    }

    Channel createChannel(List<Line> lines) {
        if (lines == null || lines.size() == 0) {
            return null;
        }

        Channel channel = new Channel();
        Line first = lines.remove(0);
        channel.append(first);

        int min = first.y;
        while (true) {
            Line line = findFollowing(min, lines);
            if (line != null) {
                channel.append(line);
                min = line.y;
                lines.remove(line);
            } else {
                break;
            }
        }
        return channel;
    }

    Line findFollowing(int min, List<Line> lines) {
        for (Line line : lines) {
            if (line.x >= min) {
                return line;
            }
        }
        return null;
    }

}
