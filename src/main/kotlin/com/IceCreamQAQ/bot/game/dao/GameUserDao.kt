package com.IceCreamQAQ.bot.game.dao

import com.IceCreamQAQ.bot.game.entity.GameUser
import com.icecreamqaq.yudb.jpa.JPADao

interface GameUserDao : JPADao<GameUser, Int> {

    fun findByQqAndGame(qq: Long, game: String): GameUser?

}