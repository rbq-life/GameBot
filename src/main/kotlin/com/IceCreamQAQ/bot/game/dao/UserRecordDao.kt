package com.IceCreamQAQ.bot.game.dao

import com.IceCreamQAQ.bot.game.entity.UserRecord
import com.icecreamqaq.yudb.YuDao
import com.icecreamqaq.yudb.jpa.JPADao
import com.icecreamqaq.yudb.jpa.annotation.Dao
import com.icecreamqaq.yudb.jpa.hibernate.HibernateDao

interface UserRecordDao : JPADao<UserRecord, Int> {

    fun findByQqAndPool(qq: Long, pool: String): UserRecord

}