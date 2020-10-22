package com.IceCreamQAQ.bot.yuanShen.service

import com.IceCreamQAQ.bot.yuanShen.dao.UserRecordDao
import com.IceCreamQAQ.bot.yuanShen.data.YuanShenPool
import com.IceCreamQAQ.bot.yuanShen.data.YuanShenPools
import com.IceCreamQAQ.bot.yuanShen.data.YuanShenSettle
import com.IceCreamQAQ.bot.yuanShen.data.invoke
import com.IceCreamQAQ.bot.yuanShen.entity.UserRecord
import com.IceCreamQAQ.bot.yuanShen.entity.getPool
import com.icecreamqaq.yudb.jpa.annotation.Transactional
import javax.inject.Inject

class CardService {

    @Inject
    private lateinit var ud: UserRecordDao

    @Transactional
    fun getUserRecord(qq: Long, pool: YuanShenPool) = ud.findByQqAndPool(qq, pool.name) ?: {
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

    operator fun UserRecord.invoke(): YuanShenSettle {
        val pool = getPool()!!
        fiveFloor++
        if (fiveFloor == pool.fiveFloor) {
            fiveFloor = 0
            return if (pool.upFloor != null) {
                if (upFive) {
                    upFive = false
                    YuanShenSettle(5, fiveFloor, pool, isFloor = true, isUp = true)
                } else {
                    val r = Math.random()
                    if (r > pool.upFloor!!) {
                        upFive = true
                        YuanShenSettle(4, fiveFloor, pool, isFloor = true, isUp = false)
                    } else YuanShenSettle(5, fiveFloor, pool, isFloor = true, isUp = false)
                }
            } else YuanShenSettle(4, fiveFloor, pool, true, isUp = false)
        }
        fourFloor++
        if (fourFloor == pool.fourFloor) {
            fourFloor = 0
            return if (pool.upFloor != null) {
                if (upFour) {
                    upFour = false
                    YuanShenSettle(3, fourFloor, pool, isFloor = true, isUp = true)
                } else {
                    val r = Math.random()
                    if (r > pool.upFloor!!) {
                        upFour = true
                        YuanShenSettle(2, fourFloor, pool, isFloor = true, isUp = false)
                    } else YuanShenSettle(3, fourFloor, pool, isFloor = true, isUp = false)
                }
            } else YuanShenSettle(2, fourFloor, pool, isFloor = true, isUp = false)
        }
        val r = Math.random()
        return if (r <= 0.006) {
            val c = fiveFloor
            fiveFloor = 0
            if (pool.upFloor == null) YuanShenSettle(4, c, pool, false, isUp = false)
            else if (upFive) {
                upFive = false
                YuanShenSettle(5, c, pool, isFloor = false, isUp = true)
            } else {
                val rr = Math.random()
                if (rr > pool.upFloor!!) {
                    upFive = true
                    YuanShenSettle(4, c, pool, false, isUp = false)
                } else YuanShenSettle(5, c, pool, isFloor = false, isUp = false)
            }
        } else if (r <= 0.051) {
            val c = fourFloor
            fourFloor = 0
            if (pool.upFloor == null) YuanShenSettle(2, c, pool, false, isUp = false)
            else if (upFour) {
                upFour = false
                YuanShenSettle(3, c, pool, isFloor = false, isUp = true)
            } else {
                val rr = Math.random()
                if (rr > pool.upFloor!!) {
                    upFour = true
                    YuanShenSettle(2, c, pool, isFloor = false, isUp = false)
                } else YuanShenSettle(3, c, pool, isFloor = false, isUp = false)
            }
        } else YuanShenSettle(1, 0, pool, false, isUp = false)
    }

}