package com.IceCreamQAQ.bot.game.service

import com.IceCreamQAQ.bot.game.dao.GameUserDao
import com.IceCreamQAQ.bot.game.data.YuanShenPools
import com.IceCreamQAQ.bot.game.data.CardSettle
import com.IceCreamQAQ.bot.game.entity.GameUser
import com.IceCreamQAQ.bot.game.entity.UserRecord
import com.icecreamqaq.yudb.jpa.annotation.Transactional
import javax.inject.Inject

class YuanShenService : BaseCardService() {

    @Inject
    private lateinit var gameUserDao: GameUserDao

    @Transactional
    fun getUid(qq: Long) = gameUserDao.findByQqAndGame(qq, "YuanShen")?.uid

    @Transactional
    fun setUid(qq: Long, uid: Int) {
        gameUserDao.findByQqAndGame(qq, "YuanShen")?.let {
            it.uid = uid
            gameUserDao.update(it)
        } ?: gameUserDao.save(
            GameUser(
                qq = qq,
                game = "YuanShen",
                uid = uid
            )
        )
    }

    override fun UserRecord.getPool() = YuanShenPools[this.pool]

    override fun UserRecord.invoke(): CardSettle {
        val pool = getPool()!!
        fiveFloor++
        fourFloor++
        if (fiveFloor >= pool.fiveFloor) {
            fiveFloor = 0
            return if (pool.upFloor != null) {
                if (upFive) {
                    upFive = false
                    CardSettle(5, fiveFloor, pool, isFloor = true, isUp = true)
                } else {
                    val r = Math.random()
                    if (r > pool.upFloor!!) {
                        upFive = true
                        CardSettle(4, fiveFloor, pool, isFloor = true, isUp = false)
                    } else CardSettle(5, fiveFloor, pool, isFloor = true, isUp = false)
                }
            } else CardSettle(4, fiveFloor, pool, true, isUp = false)
        }
        if (fourFloor >= pool.fourFloor) {
            fourFloor = 0
            return if (pool.upFloor != null) {
                if (upFour) {
                    upFour = false
                    CardSettle(3, fourFloor, pool, isFloor = true, isUp = true)
                } else {
                    val r = Math.random()
                    if (r > pool.upFloor!!) {
                        upFour = true
                        CardSettle(2, fourFloor, pool, isFloor = true, isUp = false)
                    } else CardSettle(3, fourFloor, pool, isFloor = true, isUp = false)
                }
            } else CardSettle(2, fourFloor, pool, isFloor = true, isUp = false)
        }
        val r = Math.random()

        return if (r <= (pool.fiveProbability + (fiveFloor - 73).let { if (it > 0) it * 0.053 else 0.0 })) {
            val c = fiveFloor
            fiveFloor = 0
            if (pool.upFloor == null) CardSettle(4, c, pool, false, isUp = false)
            else if (upFive) {
                upFive = false
                CardSettle(5, c, pool, isFloor = false, isUp = true)
            } else {
                val rr = Math.random()
                if (rr > pool.upFloor!!) {
                    upFive = true
                    CardSettle(4, c, pool, false, isUp = false)
                } else CardSettle(5, c, pool, isFloor = false, isUp = false)
            }
        } else if (r <= pool.fourProbability + if (fourFloor == 9) 0.51 else 0.0) {
            val c = fourFloor
            fourFloor = 0
            if (pool.upFloor == null) CardSettle(2, c, pool, false, isUp = false)
            else if (upFour) {
                upFour = false
                CardSettle(3, c, pool, isFloor = false, isUp = true)
            } else {
                val rr = Math.random()
                if (rr > pool.upFloor!!) {
                    upFour = true
                    CardSettle(2, c, pool, isFloor = false, isUp = false)
                } else CardSettle(3, c, pool, isFloor = false, isUp = false)
            }
        } else CardSettle(1, 0, pool, false, isUp = false)
    }

}