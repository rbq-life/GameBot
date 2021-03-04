package com.IceCreamQAQ.bot.game.data

typealias LevelPrizePool = Array<String>

operator fun LevelPrizePool.invoke() = get((Math.random() * size).toInt())

open class CardSettle(
    val level: Int,
    val count: Int,
    val pool: CardPool,
    val isFloor: Boolean,
    val isUp: Boolean,
) {
    lateinit var pp: String

    operator fun invoke() = this.apply { pp = pool.getResult(this) }

    override fun toString() = pool(this)
}

interface CardPool {
    val name: String

    fun getResult(cardSettle: CardSettle): String

    operator fun invoke(result: CardSettle): String
}