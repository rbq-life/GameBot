package com.IceCreamQAQ.bot.game.entity

import javax.persistence.*

@Entity
@Table(name = "gameUser")
data class GameUser(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
        @Column
        var qq: Long = 0,
        @Column
        var game: String = "",
        @Column
        var uid: Int = 0
)