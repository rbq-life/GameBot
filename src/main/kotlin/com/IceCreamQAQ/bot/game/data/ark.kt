package com.IceCreamQAQ.bot.game.data

interface ArkNightsPool : CardPool {

    val sixProbability
        get() = 0.02
    val fiveProbability
        get() = 0.08
    val fourProbability
        get() = 0.5

    val upFloor: Double?
        get() = null

    val upSix: LevelPrizePool
    val upFive: LevelPrizePool
    val upFour: LevelPrizePool

    val normalSix: LevelPrizePool
    val normalFive: LevelPrizePool
    val normalFour: LevelPrizePool

    val rubbish
        get() = arrayOf("月见夜", "空爆", "芬", "香草", "翎羽", "玫兰莎", "卡缇", "米格鲁", "克洛斯", "炎熔", "芙蓉", "安赛尔", "史都华德", "梓兰", "斑点", "泡普卡")

    override operator fun invoke(result: CardSettle): String {
        val level = result.level
        val pp = when (level) {
            7 -> upSix
            6 -> normalSix
            5 -> upFive
            4 -> normalFive
            3 -> upFour
            2 -> normalFour
            else -> rubbish
        }()
        return when {
            level > 5 -> "--------------------\n" +
                    "|| 金: $pp (${if (result.isFloor) "保底" else result.count})${if (result.isUp) " (大保底)" else ""}\n" +
                    "--------------------"
            level > 3 -> "++ 紫: $pp (${if (result.isFloor) "保底" else result.count})${if (result.isUp) " (大保底)" else ""} "
            level > 1 -> "-- 蓝: $pp (${if (result.isFloor) "保底" else result.count})${if (result.isUp) " (大保底)" else ""} "
            else -> "^^白: $pp"
        }
    }

    object NormalPool : ArkNightsPool {

        override val name = "方舟"

        override val upSix = arrayOf("")
        override val upFive = arrayOf("")
        override val upFour = arrayOf("")


        override val normalSix = arrayOf("能天使", "推进之王", "伊芙利特", "艾雅法拉", "安洁丽娜", "闪灵", "夜莺", "星熊", "赛雷娅", "银灰", "斯卡蒂", "陈", "黑", "赫拉格", "麦哲伦", "莫斯提马", "煌", "阿", "刻俄柏", "风笛", "傀影", "温蒂", "早露", "铃兰", "棘刺", "森蚺", "史尔特尔", "瑕光")
        override val normalFive = arrayOf("白面鸮", "凛冬", "德克萨斯", "芙兰卡", "拉普兰德", "幽灵鲨", "蓝毒", "白金", "陨星", "天火", "梅尔", "赫默", "华法琳", "临光", "红", "雷蛇", "可颂", "普罗旺斯", "守林人", "崖心", "初雪", "真理", "空", "狮蝎", "食铁兽", "夜魔", "诗怀雅", "格劳克斯", "星极", "送葬人", "槐琥", "苇草", "布洛卡", "灰喉", "哞", "惊蛰", "慑砂", "巫恋", "极境", "石棉", "月禾", "莱恩哈特", "断崖", "蜜蜡", "贾维", "安哲拉", "燧石", "四月", "奥斯塔")
        override val normalFour = arrayOf("夜烟", "远山", "杰西卡", "流星", "白雪", "清道夫", "红豆", "杜宾", "缠丸", "霜叶", "慕斯", "砾", "暗锁", "末药", "调香师", "角峰", "蛇屠箱", "古米", "深海色", "地灵", "阿消", "猎蜂", "格雷伊", "苏苏洛", "桃金娘", "红云", "梅", "安比尔", "宴", "刻刀", "波登可", "卡达", "孑", "酸糖", "芳汀", "泡泡")

    }

    object TestUpPool : ArkNightsPool {

        override val upFloor: Double?
            get() = 0.5

        override val name = "搅动潮汐之剑"

        override val upSix = arrayOf("斯卡蒂")
        override val upFive = arrayOf("夜魔", "临光")
        override val upFour = arrayOf("猎蜂", "暗锁")


        override val normalSix = arrayOf("能天使", "推进之王", "伊芙利特", "艾雅法拉", "安洁丽娜", "闪灵", "夜莺", "星熊", "赛雷娅", "银灰", "陈", "黑", "赫拉格", "麦哲伦", "莫斯提马", "煌", "阿", "刻俄柏", "风笛", "傀影", "温蒂", "早露", "铃兰", "棘刺", "森蚺", "史尔特尔", "瑕光")
        override val normalFive = arrayOf("白面鸮", "凛冬", "德克萨斯", "芙兰卡", "拉普兰德", "幽灵鲨", "蓝毒", "白金", "陨星", "天火", "梅尔", "赫默", "华法琳", "红", "雷蛇", "可颂", "普罗旺斯", "守林人", "崖心", "初雪", "真理", "空", "狮蝎", "食铁兽", "诗怀雅", "格劳克斯", "星极", "送葬人", "槐琥", "苇草", "布洛卡", "灰喉", "哞", "惊蛰", "慑砂", "巫恋", "极境", "石棉", "月禾", "莱恩哈特", "断崖", "蜜蜡", "贾维", "安哲拉", "燧石", "四月", "奥斯塔")
        override val normalFour = arrayOf("夜烟", "远山", "杰西卡", "流星", "白雪", "清道夫", "红豆", "杜宾", "缠丸", "霜叶", "慕斯", "砾", "末药", "调香师", "角峰", "蛇屠箱", "古米", "深海色", "地灵", "阿消", "格雷伊", "苏苏洛", "桃金娘", "红云", "梅", "安比尔", "宴", "刻刀", "波登可", "卡达", "孑", "酸糖", "芳汀", "泡泡")

    }


}

object ArkPools : MutableMap<String, ArkNightsPool> by HashMap() {

    init {
        register(
                ArkNightsPool.NormalPool,
                ArkNightsPool.TestUpPool
        )
    }

    private fun register(vararg pools: ArkNightsPool) {
        for (pool in pools) {
            put(pool.name, pool)
        }
    }


}