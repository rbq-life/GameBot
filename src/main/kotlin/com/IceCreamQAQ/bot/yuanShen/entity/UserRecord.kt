package com.IceCreamQAQ.bot.yuanShen.entity

import com.IceCreamQAQ.bot.yuanShen.data.YuanShenPools
import javax.persistence.*

@Entity
@Table(
        name = "userRecord",
        uniqueConstraints = arrayOf(
                UniqueConstraint(columnNames = ["qq", "pool"])
        )
)
data class UserRecord(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        @Column
        var qq: Long = 0,
        @Column
        var pool: String = "",
        @Column
        var fourFloor: Int = 0,
        @Column
        var fiveFloor: Int = 0,
        @Column
        var upFive: Boolean = false,
        @Column
        var upFour: Boolean = false
)

fun UserRecord.getPool() = YuanShenPools[this.pool]