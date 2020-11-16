package com.IceCreamQAQ.bot.game.entity.req.yuanShen

import com.alibaba.fastjson.annotation.JSONField

class UserResp {
    /**
     * retcode : 0
     * message : OK
     * data : {"role":null,"avatars":[{"id":10000003,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Qin.png","name":"琴","element":"Anemo","fetter":3,"level":60,"rarity":5},{"id":10000023,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Xiangling.png","name":"香菱","element":"Pyro","fetter":2,"level":53,"rarity":4},{"id":10000007,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_PlayerGirl.png","name":"旅行者","element":"Anemo","fetter":0,"level":52,"rarity":5},{"id":10000021,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Ambor.png","name":"安柏","element":"Pyro","fetter":4,"level":51,"rarity":4},{"id":10000014,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Barbara.png","name":"芭芭拉","element":"Hydro","fetter":2,"level":50,"rarity":4},{"id":10000020,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Razor.png","name":"雷泽","element":"Electro","fetter":3,"level":50,"rarity":4},{"id":10000015,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Kaeya.png","name":"凯亚","element":"Cryo","fetter":1,"level":34,"rarity":4},{"id":10000031,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Fischl.png","name":"菲谢尔","element":"Electro","fetter":1,"level":34,"rarity":4},{"id":10000034,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Noel.png","name":"诺艾尔","element":"Geo","fetter":1,"level":28,"rarity":4},{"id":10000006,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Lisa.png","name":"丽莎","element":"Electro","fetter":1,"level":20,"rarity":4},{"id":10000024,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Beidou.png","name":"北斗","element":"Electro","fetter":1,"level":2,"rarity":4},{"id":10000025,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Xingqiu.png","name":"行秋","element":"Hydro","fetter":1,"level":1,"rarity":4},{"id":10000027,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Ningguang.png","name":"凝光","element":"Geo","fetter":1,"level":1,"rarity":4},{"id":10000039,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Diona.png","name":"迪奥娜","element":"Cryo","fetter":1,"level":1,"rarity":4},{"id":10000043,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Sucrose.png","name":"砂糖","element":"Anemo","fetter":1,"level":1,"rarity":4}],"stats":{"active_day_number":55,"achievement_number":92,"win_rate":0,"anemoculus_number":56,"geoculus_number":89,"avatar_number":15,"way_point_number":70,"domain_number":20,"spiral_abyss":"3-3","precious_chest_number":74,"luxurious_chest_number":32,"exquisite_chest_number":272,"common_chest_number":224},"city_explorations":[{"id":1,"level":3,"exploration_percentage":626,"icon":"https://upload-bbs.mihoyo.com/game_record/genshin/city_icon/UI_ChapterIcon_Mengde.png","name":"蒙德"},{"id":2,"level":3,"exploration_percentage":443,"icon":"https://upload-bbs.mihoyo.com/game_record/genshin/city_icon/UI_ChapterIcon_Liyue.png","name":"璃月"}]}
     */
    @JSONField(name = "retcode")
    var retcode: Int? = null

    @JSONField(name = "message")
    var message: String? = null

    @JSONField(name = "data")
    var data: DataDTO? = null

    class DataDTO {
        /**
         * role : null
         * avatars : [{"id":10000003,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Qin.png","name":"琴","element":"Anemo","fetter":3,"level":60,"rarity":5},{"id":10000023,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Xiangling.png","name":"香菱","element":"Pyro","fetter":2,"level":53,"rarity":4},{"id":10000007,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_PlayerGirl.png","name":"旅行者","element":"Anemo","fetter":0,"level":52,"rarity":5},{"id":10000021,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Ambor.png","name":"安柏","element":"Pyro","fetter":4,"level":51,"rarity":4},{"id":10000014,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Barbara.png","name":"芭芭拉","element":"Hydro","fetter":2,"level":50,"rarity":4},{"id":10000020,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Razor.png","name":"雷泽","element":"Electro","fetter":3,"level":50,"rarity":4},{"id":10000015,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Kaeya.png","name":"凯亚","element":"Cryo","fetter":1,"level":34,"rarity":4},{"id":10000031,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Fischl.png","name":"菲谢尔","element":"Electro","fetter":1,"level":34,"rarity":4},{"id":10000034,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Noel.png","name":"诺艾尔","element":"Geo","fetter":1,"level":28,"rarity":4},{"id":10000006,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Lisa.png","name":"丽莎","element":"Electro","fetter":1,"level":20,"rarity":4},{"id":10000024,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Beidou.png","name":"北斗","element":"Electro","fetter":1,"level":2,"rarity":4},{"id":10000025,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Xingqiu.png","name":"行秋","element":"Hydro","fetter":1,"level":1,"rarity":4},{"id":10000027,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Ningguang.png","name":"凝光","element":"Geo","fetter":1,"level":1,"rarity":4},{"id":10000039,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Diona.png","name":"迪奥娜","element":"Cryo","fetter":1,"level":1,"rarity":4},{"id":10000043,"image":"https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Sucrose.png","name":"砂糖","element":"Anemo","fetter":1,"level":1,"rarity":4}]
         * stats : {"active_day_number":55,"achievement_number":92,"win_rate":0,"anemoculus_number":56,"geoculus_number":89,"avatar_number":15,"way_point_number":70,"domain_number":20,"spiral_abyss":"3-3","precious_chest_number":74,"luxurious_chest_number":32,"exquisite_chest_number":272,"common_chest_number":224}
         * city_explorations : [{"id":1,"level":3,"exploration_percentage":626,"icon":"https://upload-bbs.mihoyo.com/game_record/genshin/city_icon/UI_ChapterIcon_Mengde.png","name":"蒙德"},{"id":2,"level":3,"exploration_percentage":443,"icon":"https://upload-bbs.mihoyo.com/game_record/genshin/city_icon/UI_ChapterIcon_Liyue.png","name":"璃月"}]
         */
        @JSONField(name = "stats")
        lateinit var stats: StatsDTO

        @JSONField(name = "avatars")
        lateinit var avatars: List<AvatarsDTO>

        @JSONField(name = "city_explorations")
        lateinit var cityExplorations: List<CityExplorationsDTO>

        class StatsDTO {
            /**
             * active_day_number : 55
             * achievement_number : 92
             * win_rate : 0
             * anemoculus_number : 56
             * geoculus_number : 89
             * avatar_number : 15
             * way_point_number : 70
             * domain_number : 20
             * spiral_abyss : 3-3
             * precious_chest_number : 74
             * luxurious_chest_number : 32
             * exquisite_chest_number : 272
             * common_chest_number : 224
             */
            @JSONField(name = "active_day_number")
            var activeDayNumber: Int? = null

            @JSONField(name = "achievement_number")
            var achievementNumber: Int? = null

            @JSONField(name = "win_rate")
            var winRate: Int? = null

            @JSONField(name = "anemoculus_number")
            var anemoculusNumber: Int? = null

            @JSONField(name = "geoculus_number")
            var geoculusNumber: Int? = null

            @JSONField(name = "avatar_number")
            var avatarNumber: Int? = null

            @JSONField(name = "way_point_number")
            var wayPointNumber: Int? = null

            @JSONField(name = "domain_number")
            var domainNumber: Int? = null

            @JSONField(name = "spiral_abyss")
            var spiralAbyss: String? = null

            @JSONField(name = "precious_chest_number")
            var preciousChestNumber: Int? = null

            @JSONField(name = "luxurious_chest_number")
            var luxuriousChestNumber: Int? = null

            @JSONField(name = "exquisite_chest_number")
            var exquisiteChestNumber: Int? = null

            @JSONField(name = "common_chest_number")
            var commonChestNumber: Int? = null
        }

        class AvatarsDTO {
            /**
             * id : 10000003
             * image : https://upload-bbs.mihoyo.com/game_record/genshin/character_icon/UI_AvatarIcon_Qin.png
             * name : 琴
             * element : Anemo
             * fetter : 3
             * level : 60
             * rarity : 5
             */
            @JSONField(name = "id")
            var id: Int = 0

            @JSONField(name = "image")
            lateinit var image: String

            @JSONField(name = "name")
            lateinit var name: String

            @JSONField(name = "element")
            lateinit var element: String

            @JSONField(name = "fetter")
            var fetter: Int = 0

            @JSONField(name = "level")
            var level: Int = 0

            @JSONField(name = "rarity")
            var rarity: Int = 0
        }

        class CityExplorationsDTO {
            /**
             * id : 1
             * level : 3
             * exploration_percentage : 626
             * icon : https://upload-bbs.mihoyo.com/game_record/genshin/city_icon/UI_ChapterIcon_Mengde.png
             * name : 蒙德
             */
            @JSONField(name = "id")
            var id: Int = 0

            @JSONField(name = "level")
            var level: Int = 0

            @JSONField(name = "exploration_percentage")
            var explorationPercentage: Int = 0

            @JSONField(name = "icon")
            lateinit var icon: String

            @JSONField(name = "name")
            lateinit var name: String
        }
    }
}