package com.IceCreamQAQ.bot.game.controller

import com.IceCreamQAQ.Yu.annotation.Action
import com.IceCreamQAQ.Yu.annotation.ActionBy
import com.IceCreamQAQ.Yu.annotation.PathBy
import com.IceCreamQAQ.Yu.md5
import com.IceCreamQAQ.Yu.toObject
import com.IceCreamQAQ.Yu.util.HeaderBuilder
import com.IceCreamQAQ.Yu.util.HeaderBuilder.to
import com.IceCreamQAQ.Yu.util.Web
import com.IceCreamQAQ.bot.game.entity.req.yuanShen.UserResp
import com.IceCreamQAQ.bot.game.println
import com.IceCreamQAQ.bot.game.service.YuanShenService
import com.icecreamqaq.yuq.annotation.GroupController
import com.icecreamqaq.yuq.annotation.PrivateController
import com.icecreamqaq.yuq.annotation.QMsg
import com.icecreamqaq.yuq.entity.Contact
import com.icecreamqaq.yuq.message.Message.Companion.toMessage
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@PathBy(PrefixCommand::class)
@PrivateController
@GroupController
class YuanShenInfoController : PrefixName {

    @Inject
    lateinit var service: YuanShenService

    private var Contact.uid: Int?
        get() = service.getUid(id)
        set(uid) = service.setUid(id, uid!!)

    @Inject
    private lateinit var web: Web

    @Action("查他 {it}")
    @QMsg(at = true, atNewLine = true)
    fun itInfo(it: Contact) = myInfo(it)

    @Action("我的信息")
    @QMsg(at = true, atNewLine = true)
    fun myInfo(qq: Contact): String {
        val mhyVersion = "2.1.0"
        val n = mhyVersion.md5
        val p = java.lang.String.valueOf(Date().time).substring(0, 10)
        val r = "1x7pr0"
        val c = "salt=$n&t=$p&r=$r".md5
        val ds = "$p,$r,$c"

        val uid = qq.uid ?: throw "您还没有绑定《原神》的用户ID，请先使用\"绑定ID uid\"指令绑定用户信息！".toMessage().toThrowable()
        val info = web.get("https://api-takumi.mihoyo.com/game_record/genshin/api/index?server=cn_gf01&role_id=$uid") {
            headerOf(
                    ua = "Mozilla/5.0 (Linux; Android 9; Unspecified Device) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/39.0.0.0 Mobile Safari/537.36 miHoYoBBS/2.2.",
                    referer = "https://webstatic.mihoyo.com/app/community-game-records/index.html?v=6",
                    "DS" to ds,
                    "x-rpc-app_version" to mhyVersion,
                    "x-rpc-client_type" to "4",
                    "X-Requested-With" to "com.mihoyo.hyperion"
            )
        }.println().toObject<UserResp>()

        if (info.retcode != 0) throw "获取失败，请稍后重试。".toMessage().toThrowable()

        return info.data?.run {
            var five = 1
            var four = 0

            val avatarListArray = arrayOf<ArrayList<String>>(
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
                    arrayListOf(),
            )

            var player: Triple<String, Int, String>? = null
            for (avatar in avatars) {
                if (avatar.id == 10000007) {
                    player = Triple("妹妹", avatar.level, avatar.element.toCY())
                    continue
                }
                if (avatar.id == 10000005) {
                    player = Triple("哥哥", avatar.level, avatar.element.toCY())
                    continue
                }
                with(avatar) {
                    if (rarity == 5) five++ else four++
                    avatarListArray[element.toCL()].add("$name ($level)")
                }
            }

            val head = with(stats) {
                player!!
                """
                    ${player.first} (${player.second}) ${player.third} ($uid)
                    --------------------
                    活跃天数：$activeDayNumber  成就：$achievementNumber  深境螺旋：$spiralAbyss
                    风神瞳：$anemoculusNumber ${if (anemoculusNumber == 66) "(满)" else ""}  岩神瞳：$geoculusNumber ${if (geoculusNumber == 131) "(满)" else ""}
                    传送点：$wayPointNumber ${if (wayPointNumber == 70) "(满)" else ""}  秘境：$domainNumber ${if (domainNumber == 24) "(满)" else ""}
                    华丽：$luxuriousChestNumber  珍贵：$preciousChestNumber  精致：$exquisiteChestNumber  普通：$commonChestNumber
                    --------------------
                    角色数量：$avatarNumber ($five/$four)
                """.trimIndent()
            }
            val messageStringBuilder = StringBuilder(head)
            with(messageStringBuilder) {
                for ((i, l) in avatarListArray.withIndex()) {
                    if (l.size > 0) {
                        append("\n")
                        append(i.toCY()).append("：").append(l[0])
                        for (j in 1 until l.size) append("，").append(l[j])
                    }
                }
                append("\n--------------------\n声望：")
                for (city in cityExplorations) {
                    append(with(city) { "\n$name：${level}级 (${explorationPercentage.toFloat() / 10}%)" })
                }
            }
            messageStringBuilder.toString()
        } ?: "遇到问题，解析失败！"
    }

    @Action("绑定ID {uid}")
    fun bindUid(qq: Contact, uid: Int): String {
//        if (qq.uid != null) return "您已经绑定过《原神》账号啦！无需再次绑定！"
        qq.uid = uid
        return "绑定成功！"
    }

    private fun String.toCY() = when (this.toLowerCase()) {
        "anemo" -> "风"
        "geo" -> "岩"
        "electro" -> "雷"
        "pyro" -> "火"
        "hydro" -> "水"
        "cryo" -> "冰"
        else -> "解析失败"
    }

    private fun String.toCL() = when (this.toLowerCase()) {
        "anemo" -> 0
        "geo" -> 1
        "electro" -> 2
        "pyro" -> 3
        "hydro" -> 4
        "cryo" -> 5
        else -> 6
    }

    private fun Int.toCY() = when (this) {
        0 -> "风"
        1 -> "岩"
        2 -> "雷"
        3 -> "火"
        4 -> "水"
        5 -> "冰"
        else -> "解析失败"
    }

    override val prefixName = "原神"
    override val prefixFlag = "yuanShen"
}