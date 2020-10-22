package com.IceCreamQAQ.bot.yuanShen.controller

import com.IceCreamQAQ.Yu.annotation.Action
import com.IceCreamQAQ.bot.yuanShen.data.YuanShenPool
import com.IceCreamQAQ.bot.yuanShen.data.YuanShenPools
import com.IceCreamQAQ.bot.yuanShen.entity.UserRecord
import com.IceCreamQAQ.bot.yuanShen.service.CardService
import com.icecreamqaq.yuq.annotation.GroupController
import com.icecreamqaq.yuq.annotation.PrivateController
import com.icecreamqaq.yuq.annotation.QMsg
import com.icecreamqaq.yuq.entity.Contact
import javax.inject.Inject

@PrivateController
@GroupController
class CardController {


    @Inject
    lateinit var cardService: CardService

    @Action("{pool}十连")
    @QMsg(at = true, atNewLine = true)
    fun cardTen(qq: Contact, pool: String): String {
        val p = YuanShenPools[pool] ?: return "没有发现目标名为：$pool 的卡池。"
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
        val p = YuanShenPools[pool] ?: return "没有发现目标名为：$pool 的卡池。"
        return try {
            val l = qq(p)(1)
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

    operator fun Contact.invoke(pool: YuanShenPool) = cardService.getUserRecord(this.id, pool)

    operator fun UserRecord.invoke(num: Int) = cardService.card(this, num)

}