package com.IceCreamQAQ.bot.game

import com.icecreamqaq.yuq.mirai.YuQMiraiStart

fun main(args: Array<String>) {
    YuQMiraiStart.start(args)
}

fun String.println():String{
    println(this)
    return this
}