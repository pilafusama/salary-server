package com.tenpay.wxwork.salary.service;

import redis.clients.jedis.*;

import java.io.Closeable;

/**
 * Created by yaogangli on 2018/3/12.
 */
public interface CkvPlusClient extends JedisCommands, MultiKeyCommands, BasicCommands, BinaryJedisCommands,
        MultiKeyBinaryCommands, Closeable {
}
