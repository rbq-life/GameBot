package com.IceCreamQAQ.bot.game.controller

import com.IceCreamQAQ.Yu.annotation.Action
import com.IceCreamQAQ.Yu.annotation.Before
import com.IceCreamQAQ.Yu.annotation.Config
import com.IceCreamQAQ.bot.game.data.YuanShenPool
import com.IceCreamQAQ.bot.game.data.YuanShenPools
import com.IceCreamQAQ.bot.game.entity.UserRecord
import com.IceCreamQAQ.bot.game.service.YuanShenService
import com.icecreamqaq.yuq.annotation.GroupController
import com.icecreamqaq.yuq.annotation.PrivateController
import com.icecreamqaq.yuq.annotation.QMsg
import com.icecreamqaq.yuq.entity.Contact
import com.icecreamqaq.yuq.error.SkipMe
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

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

    val czWpD = hashMapOf(
        "琴" to 7,
        "刻晴" to 7,
        "莫娜" to 7,
        "七七" to 7,
        "迪卢克" to 7,
        "阿莫斯之弓" to 5,
        "天空之翼" to 5,
        "四风原典" to 5,
        "天空之卷" to 5,
        "和璞鸢" to 5,
        "天空之脊" to 5,
        "狼的末路" to 5,
        "天空之傲" to 5,
        "天空之刃" to 5,
        "风鹰剑" to 5,
    )

    @Action("常驻{role}满{c}")
    @QMsg(at = true, atNewLine = true)
    fun cardRole(qq: Contact, role: String, c: String): String {
//        val cx = when (c) {
//            "命" -> 7
//            "精" -> 5
//            else -> throw SkipMe()
//        }
        if (!(c == "命" || c == "精")) throw SkipMe()
        val p = YuanShenPool.NormalPool
//        if (p.upFive.size > 1) throw SkipMe()
        val fiveMap = HashMap<String, MutableList<Triple<Int, Int, Int>>>()
//        var have = false
        var cx = 0
        for ((s, f) in czWpD) {
            if (s == role) cx = f
            fiveMap[s] = ArrayList()
        }
        if (cx == 0) return "您欲计算的目标不在常驻池中！"

        return try {
            var i = 1
            var s = 0
            var w = 0
            var f = 0
            var wx = 0
            while (s < cx) {
                val r = qq(p)(1)[0]
                r.apply {
                    if (level > 3) {
                        wx++
                        if (pp == role) s++
                        else w++
                        fiveMap[pp]!!.add(Triple(wx, count, i))
                    } else if (level > 1) f++
                }
                i++
            }
            i--
            val sb = StringBuilder()
            for ((k, v) in fiveMap) {
                if (v.size == 0) continue
                sb.append("\n                $k(${v.size}):")
                for ((i, c, a) in v) sb.append(" ($i,$c,$a)")
            }

            val rd = i / 50 + if (i % 50 == 0) 0 else 1
            """
                抽卡“$role”满命结果：
                总计抽卡次数: $i
                产出五星个数: ${s + w}
                歪非目标个数: $w
                产出四星个数: $f
                平均五星次数: ${((i * 1.0) / (s + w) * 100).toInt() / 100.0}
                总计花费金额: ${rd * 648} ($rd * 648)
                总计抽卡结果: $sb
            """.trimIndent()
        } catch (ex: Exception) {
            ex.printStackTrace()
            "系统异常！"
        }
    }

    operator fun Contact.invoke(pool: YuanShenPool) = service.getUserRecord(this.id, pool)

    operator fun UserRecord.invoke(num: Int) = service.card(this, num)

}