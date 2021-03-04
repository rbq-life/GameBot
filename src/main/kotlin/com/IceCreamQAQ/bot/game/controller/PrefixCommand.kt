package com.IceCreamQAQ.bot.game.controller

import com.IceCreamQAQ.Yu.annotation.Config
import com.IceCreamQAQ.Yu.controller.PathManager
import com.alibaba.fastjson.JSONObject
import javax.inject.Inject

class PrefixCommand : PathManager {

    var only = false
    var onlyFlag = ""

    @Config("com.IceCreamQAQ.GameBot.game")
    private lateinit var flags: JSONObject

    @Inject
    fun init() {
        var c = false
        for (key in flags.keys) {
            val a = flags.getBoolean(key)!!
            if (a && c) {
                c = false
                break
            }
            if (a && !c) {
                c = true
                onlyFlag = key
            }
        }
        if (c) only = true
    }

    override fun getPath(clazz: Class<*>, instance: Any): String? {
        return if (only && (instance as PrefixName).prefixFlag == onlyFlag) null
        else (instance as PrefixName).prefixName
    }
}