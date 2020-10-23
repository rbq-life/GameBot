package com.IceCreamQAQ.bot.game.service

import com.IceCreamQAQ.bot.game.dao.UserRecordDao
import com.IceCreamQAQ.bot.game.data.CardPool
import com.IceCreamQAQ.bot.game.data.CardSettle
import com.IceCreamQAQ.bot.game.data.YuanShenPool
import com.IceCreamQAQ.bot.game.data.YuanShenPools
import com.IceCreamQAQ.bot.game.entity.UserRecord
import com.icecreamqaq.yudb.jpa.annotation.Transactional
import javax.inject.Inject

abstract class BaseCardService {

    protected abstract fun UserRecord.getPool(): CardPool?
    protected abstract operator fun UserRecord.invoke(): CardSettle

    @Inject
    private lateinit var ud: UserRecordDao

    @Transactional
    fun getUserRecord(qq: Long, pool: CardPool) = ud.findByQqAndPool(qq, pool.name) ?: {
        val r = UserRecord(
                qq = qq,
                pool = pool.name
        )
        ud.save(r)
        r
    }()

    @Transactional
    fun card(userRecord: UserRecord, num: Int): ArrayList<String> {
        val pool = userRecord.getPool()!!
        val list = ArrayList<String>(10)
        for (i in 0 until num) list.add(pool(userRecord()))
        ud.update(userRecord)
        return list
    }

}