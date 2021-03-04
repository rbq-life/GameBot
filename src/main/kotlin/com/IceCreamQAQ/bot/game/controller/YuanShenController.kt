package com.IceCreamQAQ.bot.game.controller

import com.IceCreamQAQ.Yu.annotation.Action
import com.IceCreamQAQ.Yu.annotation.Before
import com.IceCreamQAQ.Yu.annotation.Config
import com.IceCreamQAQ.Yu.md5
import com.IceCreamQAQ.Yu.toObject
import com.IceCreamQAQ.Yu.util.Web
import com.IceCreamQAQ.bot.game.data.YuanShenPool
import com.IceCreamQAQ.bot.game.data.YuanShenPools
import com.IceCreamQAQ.bot.game.entity.UserRecord
import com.IceCreamQAQ.bot.game.entity.req.yuanShen.UserResp
import com.IceCreamQAQ.bot.game.service.YuanShenService
import com.icecreamqaq.yuq.annotation.GroupController
import com.icecreamqaq.yuq.annotation.PrivateController
import com.icecreamqaq.yuq.annotation.QMsg
import com.icecreamqaq.yuq.entity.Contact
import com.icecreamqaq.yuq.error.SkipMe
import com.icecreamqaq.yuq.message.Message.Companion.toMessage
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@PrivateController
@GroupController
class YuanShenController {


    @Inject
    lateinit var service: YuanShenService

    @Config("com.IceCreamQAQ.GameBot.game.yuanShen")
    private lateinit var yuanShen: String

    @Before
    fun i() {
        if (yuanShen == "false") throw SkipMe()
    }

    @Action("{pool}十连")
    @QMsg(at = true, atNewLine = true)
    fun cardTen(qq: Contact, pool: String): String {
        val p = YuanShenPools[pool] ?: throw SkipMe()
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
        val p = YuanShenPools[pool] ?: throw SkipMe()
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

    @Action("{pool}满命")
    @QMsg(at = true, atNewLine = true)
    fun cardAll(qq: Contact, pool: String): String {
        val p = YuanShenPools[pool] ?: throw SkipMe()
        if (p.upFive.size > 1) throw SkipMe()
        val o = p.upFive[0]
        return try {
            var i = 1
            var s = 0
            var w = 0
            var f = 0
            val sb = StringBuilder()
            while (s < 7) {
                val r = qq(p)(1)[0]
                r.apply {
                    if (level > 3) {
                        if (level == 5) s++
                        else w++
                        sb.append("\n                $pp ($i) (${if (isFloor) "保底" else count})${if (isUp) " (大保底)" else ""}")
                    } else if (level > 1) f++
                }
                i++
            }
//            val sb = StringBuilder("您的单抽抽卡结果为：")
//            for (s in l) {
//                sb.append("\n").append(s)
//            }
//            sb.toString()
            """
                抽卡“$pool”满命结果：
                总计抽卡次数：${i - 1}
                产出五星个数：${s + w}
                歪出非UP个数：$w
                产出四星个数：$f
                总计抽卡结果：$sb
            """.trimIndent()
        } catch (ex: Exception) {
            ex.printStackTrace()
            "系统异常！"
        }
    }

    operator fun Contact.invoke(pool: YuanShenPool) = service.getUserRecord(this.id, pool)

    operator fun UserRecord.invoke(num: Int) = service.card(this, num)

}