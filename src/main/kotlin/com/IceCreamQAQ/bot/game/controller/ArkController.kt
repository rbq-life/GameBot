package com.IceCreamQAQ.bot.game.controller

import com.IceCreamQAQ.Yu.annotation.Action
import com.IceCreamQAQ.Yu.annotation.Path
import com.IceCreamQAQ.bot.game.data.ArkNightsPool
import com.IceCreamQAQ.bot.game.data.ArkPools
import com.IceCreamQAQ.bot.game.data.YuanShenPool
import com.IceCreamQAQ.bot.game.data.YuanShenPools
import com.IceCreamQAQ.bot.game.entity.UserRecord
import com.IceCreamQAQ.bot.game.service.ArkService
import com.icecreamqaq.yuq.annotation.GroupController
import com.icecreamqaq.yuq.annotation.PrivateController
import com.icecreamqaq.yuq.annotation.QMsg
import com.icecreamqaq.yuq.entity.Contact
import com.icecreamqaq.yuq.error.SkipMe
import javax.inject.Inject

@PrivateController
@GroupController
class ArkController {


    @Inject
    lateinit var ark: ArkService

    @Action("{pool}十连")
    @QMsg(at = true, atNewLine = true)
    fun cardTen(qq: Contact, pool: String): String {
        val p = ArkPools[pool] ?: throw SkipMe()
        return try {
            val l = qq(p)(10)
            val sb = StringBuilder("您的十连抽卡结果为：")
            for (s in l) {
                sb.append("\n").append(s)
            }
            sb.toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
            "系统异常！"
        }
    }

    @Action("{pool}单抽")
    @QMsg(at = true, atNewLine = true)
    fun cardOne(qq: Contact, pool: String): String {
        val p = ArkPools[pool] ?: throw SkipMe()
        return try {
            val l = qq(p)(1)
            val sb = StringBuilder("您的单抽抽卡结果为：")
            for (s in l) {
                sb.append("\n").append(s)
            }
            sb.toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
            "系统异常！"
        }
    }

    operator fun Contact.invoke(pool: ArkNightsPool) = ark.getUserRecord(this.id, pool)

    operator fun UserRecord.invoke(num: Int) = ark.card(this, num)

}