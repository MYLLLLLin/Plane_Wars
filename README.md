# Plane_Wars
Java实现简单的飞机大战
# 注
此次使用Java语言实现一个简易的2D小游戏飞机大战。
实现大概功能：创建一个页面，存在一个玩家飞机，可以射击子弹，当子弹打到敌机、奖赏类（Bee与Bigbee）时，获得相应的积分或是奖励。boss在达到固定积分时会出现，并且会射击子弹。
由于Bee和Bigbee属性类似，只有图片及奖赏不同，因此可继承于同一个接口；而Enemyplane和Boss在笔者写的过程中由于Boss添加了射击的属性，因此在生成时需要进行判断是否为Boss，若生成的是boss则生成boss的子弹，在代码中使用了if (one instanceof Enemy2)进行判断，因此写了两个接口，读者可自行优化使代码简洁；其余问题见源码注释。
注意：在此不建议在Game_Background中的生成飞行物使用普通数组，因为使用普通的数组，在增删飞行物时，过程复杂，对空间压力较大，不利于操作，生成飞行物过多时容易爆存，因此建议使用ArrayList进行操作。
缺点：设置了两个Enemy接口，当玩家子弹碰撞boss子弹时，双方子弹不会消失，模式太少。
在源码中注释了判断两个任意飞行物是否相撞的函数，可自行添加修改。
