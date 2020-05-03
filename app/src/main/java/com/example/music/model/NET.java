package com.example.music.model;

import java.util.List;

//获取到的json数据放进这个类里
public class NET {

    /**
     * result : {"songs":[{"id":347230,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34209,"name":"海阔天空","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":747504000000,"size":10,"copyrightId":1003,"status":1,"picId":102254581395219,"mark":0},"duration":326000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":376199,"fee":1,"rUrl":null,"mark":8192},{"id":346089,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34110,"name":"25周年精选","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1205164800000,"size":48,"copyrightId":5003,"status":1,"picId":82463372084291,"alia":["Beyond 25th Anniversary"],"mark":0},"duration":322000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5501497,"fee":8,"rUrl":null,"mark":0},{"id":27515047,"name":"海阔天空","artists":[{"id":9881,"name":"汪小敏","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2623243,"name":"一声所爱 大地飞歌（第九期）","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1375200000007,"size":12,"copyrightId":0,"status":1,"picId":5630599045913351,"mark":0},"duration":178677,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":255660,"name":"海阔天空 (Live)","artists":[{"id":8336,"name":"林忆莲","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":25533,"name":"Sandy Lam Concert MMXI 演唱会","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1338825600000,"size":24,"copyrightId":7003,"status":3,"picId":109951163062452320,"mark":0},"duration":258000,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":8192},{"id":29017036,"name":"海阔天空","artists":[{"id":6211,"name":"幼稚园杀手","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2965013,"name":"热门华语236","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1388505600004,"size":46,"copyrightId":0,"status":2,"picId":3297435373429925,"mark":0},"duration":224000,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":524288},{"id":387668,"name":"海阔天空","artists":[{"id":13283,"name":"信乐团","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":38400,"name":"挑信","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1101830400000,"size":28,"copyrightId":14025,"status":3,"picId":119846767442613,"mark":0},"duration":279667,"copyrightId":14025,"status":0,"alias":["偶像剧《死了都要爱-信乐团的故事》片头曲"],"rtype":0,"ftype":0,"mvid":5343895,"fee":8,"rUrl":null,"mark":0},{"id":387717,"name":"海阔天空","artists":[{"id":13283,"name":"信乐团","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":38408,"name":"海阔天空","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1075564800000,"size":10,"copyrightId":-1,"status":1,"picId":109951164094157630,"mark":0},"duration":276626,"copyrightId":1416096,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5343895,"fee":1,"rUrl":null,"mark":8192},{"id":31877402,"name":"海阔天空 (Live)","artists":[{"id":7891,"name":"韩红","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":3142050,"name":"我是歌手第三季 第5期","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1422547200007,"size":6,"copyrightId":424010,"status":-1,"picId":2917004349702834,"mark":0},"duration":310000,"copyrightId":404023,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":65536},{"id":25780157,"name":"海阔天空","artists":[{"id":9263,"name":"Robynn & Kendy","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2313008,"name":"Dear Diary","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1361116800007,"size":10,"copyrightId":7003,"status":3,"picId":2336462209041205,"mark":0},"duration":195788,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":8192},{"id":346573,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34147,"name":"光辉岁月十五年","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":930758400000,"size":13,"copyrightId":5003,"status":1,"picId":29686813951246,"mark":0},"duration":324180,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":376199,"fee":8,"rUrl":null,"mark":0},{"id":28427790,"name":"海阔天空 (Live)","artists":[{"id":2110,"name":"曹格","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2786870,"name":"我是歌手第二季 突围赛","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1395331200007,"size":7,"copyrightId":12009,"status":1,"picId":5864795022750093,"mark":0},"duration":273000,"copyrightId":404023,"status":0,"alias":["原唱：Beyond"],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":0},{"id":25702224,"name":"海阔天空 (Live)","artists":[{"id":3080,"name":"黄贯中","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2282021,"name":"我是歌手第一季 第1期","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1358438400007,"size":7,"copyrightId":12009,"status":40,"picId":1710840092840653,"mark":0},"duration":303320,"copyrightId":404023,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":0},{"id":115093,"name":"海阔天空","artists":[{"id":3706,"name":"林子祥","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":11277,"name":"港乐。林子祥2002","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1041177600000,"size":24,"copyrightId":7002,"status":1,"picId":871912720826751,"mark":0},"duration":330000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":1,"rUrl":null,"mark":270336},{"id":347247,"name":"遥かなる梦に","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34209,"name":"海阔天空","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":746812800000,"size":10,"copyrightId":1003,"status":1,"picId":102254581395219,"mark":0},"duration":322000,"copyrightId":1003,"status":0,"alias":["原曲:海阔天空"],"rtype":0,"ftype":0,"mvid":5436433,"fee":0,"rUrl":null,"mark":262144},{"id":347033,"name":"海阔天空(live 1996)","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34181,"name":"Beyond的精彩 Live & Basic","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":830880000000,"size":25,"copyrightId":684010,"status":3,"picId":41781441867527,"mark":0},"duration":497159,"copyrightId":684010,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5436067,"fee":8,"rUrl":null,"mark":1125899906851328},{"id":400162138,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34430029,"name":"华纳23周年纪念精选系列","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":999273600000,"size":14,"copyrightId":7002,"status":3,"picId":3273246124149810,"mark":0},"duration":323693,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5501497,"fee":1,"rUrl":null,"mark":8192},{"id":28798516,"name":"海阔天空","artists":[{"id":29809639,"name":"余家辉","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2897098,"name":"中国新声代第二季 第七期","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1405267200007,"size":9,"copyrightId":0,"status":0,"picId":5771336534426985,"mark":0},"duration":245000,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":0},{"id":27598595,"name":"海阔天空","artists":[{"id":9881,"name":"汪小敏","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2643274,"name":"一声所爱·大地飞歌（精选集）","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1376841600007,"size":14,"copyrightId":0,"status":1,"picId":3175389581096890,"mark":0},"duration":178677,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":28310909,"name":"海阔天空","artists":[{"id":13594,"name":"左右乐队","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2771348,"name":"末时代","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1348675200000,"size":14,"copyrightId":1416189,"status":0,"picId":18675205000257810,"mark":0},"duration":394000,"copyrightId":1416189,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":28875146,"name":"海阔天空 (Live)","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2921018,"name":"The Story Live 2005","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1112284800007,"size":22,"copyrightId":0,"status":0,"picId":109951163972046200,"mark":0},"duration":369000,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5347246,"fee":8,"rUrl":null,"mark":8192},{"id":416890532,"name":"【海阔天空】国语版-石寅","artists":[{"id":12021013,"name":"石寅.stone","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34584173,"name":"【赤血男儿】石寅.stone","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1458672312582,"size":26,"copyrightId":0,"status":1,"picId":18029791672343268,"mark":0},"duration":324816,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":64153,"name":"海阔天空(Live)","artists":[{"id":2117,"name":"侧田","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":6340,"name":"Justin \"Around The World\" Tour 2011","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1406822400000,"size":28,"copyrightId":7002,"status":1,"picId":109951164084493070,"mark":0},"duration":310000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":1,"rUrl":null,"mark":270336},{"id":27562765,"name":"海阔天空","artists":[{"id":0,"name":"邓小坤","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2639090,"name":"中国梦之声第一季 第十四期","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1375804800007,"size":10,"copyrightId":0,"status":1,"picId":7973658326398208,"mark":0},"duration":162000,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":347319,"name":"海阔天空(live 93)","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34218,"name":"Words & Music(华纳纸盒版)","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":741456000000,"size":8,"copyrightId":5003,"status":3,"picId":122045790696257,"alia":["Words & Music: Final Live With 家驹"],"mark":0},"duration":375000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":1,"rUrl":null,"mark":8192},{"id":1361062159,"name":"Beyond-拍手+海阔天空+上帝是女孩(郑SuPer Mix)（郑顺鹏 / 郑SuPer remix）","artists":[{"id":12037207,"name":"郑顺鹏","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},{"id":0,"name":"郑SuPer","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":78457415,"name":"爆点(郑SuPer Mash Up)","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":563904000000,"size":38,"copyrightId":0,"status":0,"picId":109951164000230340,"mark":0},"duration":227761,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":1413162192,"name":"海阔天空（重编版）（翻自 群星） ","artists":[{"id":8017,"name":"胡芳芳","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":35890039,"name":"Fong cover song","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1502000323691,"size":35,"copyrightId":0,"status":0,"picId":109951162994799090,"mark":0},"duration":99030,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":1335107656,"name":"海阔天空(纯钢琴)","artists":[{"id":30382432,"name":"愚人幽篁里","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":74926720,"name":"梦回","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1545495320028,"size":3,"copyrightId":0,"status":0,"picId":109951163742648960,"mark":0},"duration":166466,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":262144},{"id":473021517,"name":"海阔天空 (2008 Live)","artists":[{"id":3116,"name":"黄家强","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},{"id":6094,"name":"叶世荣","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},{"id":3080,"name":"黄贯中","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":35396892,"name":"艺能25周年","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1262275200007,"size":30,"copyrightId":7003,"status":3,"picId":18437710486428540,"mark":0},"duration":367020,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":270336},{"id":28798780,"name":"海阔天空(Live)","artists":[{"id":4080,"name":"刘心","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},{"id":5518,"name":"王铮亮","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2894108,"name":"热门华语228","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1388534400000,"size":46,"copyrightId":0,"status":2,"picId":109951163926969180,"mark":0},"duration":169000,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":524288},{"id":469391964,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":35336928,"name":"超越BEYOND精选","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1041350400007,"size":19,"copyrightId":7003,"status":3,"picId":17982512672529112,"mark":0},"duration":323787,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":8192}],"songCount":349}
     * code : 200
     */

    private ResultBean result;
    private String code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class ResultBean {
        /**
         * songs : [{"id":347230,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34209,"name":"海阔天空","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":747504000000,"size":10,"copyrightId":1003,"status":1,"picId":102254581395219,"mark":0},"duration":326000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":376199,"fee":1,"rUrl":null,"mark":8192},{"id":346089,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34110,"name":"25周年精选","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1205164800000,"size":48,"copyrightId":5003,"status":1,"picId":82463372084291,"alia":["Beyond 25th Anniversary"],"mark":0},"duration":322000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5501497,"fee":8,"rUrl":null,"mark":0},{"id":27515047,"name":"海阔天空","artists":[{"id":9881,"name":"汪小敏","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2623243,"name":"一声所爱 大地飞歌（第九期）","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1375200000007,"size":12,"copyrightId":0,"status":1,"picId":5630599045913351,"mark":0},"duration":178677,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":255660,"name":"海阔天空 (Live)","artists":[{"id":8336,"name":"林忆莲","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":25533,"name":"Sandy Lam Concert MMXI 演唱会","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1338825600000,"size":24,"copyrightId":7003,"status":3,"picId":109951163062452320,"mark":0},"duration":258000,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":8192},{"id":29017036,"name":"海阔天空","artists":[{"id":6211,"name":"幼稚园杀手","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2965013,"name":"热门华语236","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1388505600004,"size":46,"copyrightId":0,"status":2,"picId":3297435373429925,"mark":0},"duration":224000,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":524288},{"id":387668,"name":"海阔天空","artists":[{"id":13283,"name":"信乐团","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":38400,"name":"挑信","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1101830400000,"size":28,"copyrightId":14025,"status":3,"picId":119846767442613,"mark":0},"duration":279667,"copyrightId":14025,"status":0,"alias":["偶像剧《死了都要爱-信乐团的故事》片头曲"],"rtype":0,"ftype":0,"mvid":5343895,"fee":8,"rUrl":null,"mark":0},{"id":387717,"name":"海阔天空","artists":[{"id":13283,"name":"信乐团","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":38408,"name":"海阔天空","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1075564800000,"size":10,"copyrightId":-1,"status":1,"picId":109951164094157630,"mark":0},"duration":276626,"copyrightId":1416096,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5343895,"fee":1,"rUrl":null,"mark":8192},{"id":31877402,"name":"海阔天空 (Live)","artists":[{"id":7891,"name":"韩红","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":3142050,"name":"我是歌手第三季 第5期","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1422547200007,"size":6,"copyrightId":424010,"status":-1,"picId":2917004349702834,"mark":0},"duration":310000,"copyrightId":404023,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":65536},{"id":25780157,"name":"海阔天空","artists":[{"id":9263,"name":"Robynn & Kendy","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2313008,"name":"Dear Diary","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1361116800007,"size":10,"copyrightId":7003,"status":3,"picId":2336462209041205,"mark":0},"duration":195788,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":8192},{"id":346573,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34147,"name":"光辉岁月十五年","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":930758400000,"size":13,"copyrightId":5003,"status":1,"picId":29686813951246,"mark":0},"duration":324180,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":376199,"fee":8,"rUrl":null,"mark":0},{"id":28427790,"name":"海阔天空 (Live)","artists":[{"id":2110,"name":"曹格","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2786870,"name":"我是歌手第二季 突围赛","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1395331200007,"size":7,"copyrightId":12009,"status":1,"picId":5864795022750093,"mark":0},"duration":273000,"copyrightId":404023,"status":0,"alias":["原唱：Beyond"],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":0},{"id":25702224,"name":"海阔天空 (Live)","artists":[{"id":3080,"name":"黄贯中","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2282021,"name":"我是歌手第一季 第1期","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1358438400007,"size":7,"copyrightId":12009,"status":40,"picId":1710840092840653,"mark":0},"duration":303320,"copyrightId":404023,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":0},{"id":115093,"name":"海阔天空","artists":[{"id":3706,"name":"林子祥","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":11277,"name":"港乐。林子祥2002","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1041177600000,"size":24,"copyrightId":7002,"status":1,"picId":871912720826751,"mark":0},"duration":330000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":1,"rUrl":null,"mark":270336},{"id":347247,"name":"遥かなる梦に","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34209,"name":"海阔天空","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":746812800000,"size":10,"copyrightId":1003,"status":1,"picId":102254581395219,"mark":0},"duration":322000,"copyrightId":1003,"status":0,"alias":["原曲:海阔天空"],"rtype":0,"ftype":0,"mvid":5436433,"fee":0,"rUrl":null,"mark":262144},{"id":347033,"name":"海阔天空(live 1996)","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34181,"name":"Beyond的精彩 Live & Basic","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":830880000000,"size":25,"copyrightId":684010,"status":3,"picId":41781441867527,"mark":0},"duration":497159,"copyrightId":684010,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5436067,"fee":8,"rUrl":null,"mark":1125899906851328},{"id":400162138,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34430029,"name":"华纳23周年纪念精选系列","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":999273600000,"size":14,"copyrightId":7002,"status":3,"picId":3273246124149810,"mark":0},"duration":323693,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5501497,"fee":1,"rUrl":null,"mark":8192},{"id":28798516,"name":"海阔天空","artists":[{"id":29809639,"name":"余家辉","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2897098,"name":"中国新声代第二季 第七期","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1405267200007,"size":9,"copyrightId":0,"status":0,"picId":5771336534426985,"mark":0},"duration":245000,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":0},{"id":27598595,"name":"海阔天空","artists":[{"id":9881,"name":"汪小敏","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2643274,"name":"一声所爱·大地飞歌（精选集）","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1376841600007,"size":14,"copyrightId":0,"status":1,"picId":3175389581096890,"mark":0},"duration":178677,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":28310909,"name":"海阔天空","artists":[{"id":13594,"name":"左右乐队","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2771348,"name":"末时代","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1348675200000,"size":14,"copyrightId":1416189,"status":0,"picId":18675205000257810,"mark":0},"duration":394000,"copyrightId":1416189,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":28875146,"name":"海阔天空 (Live)","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2921018,"name":"The Story Live 2005","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p1.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1112284800007,"size":22,"copyrightId":0,"status":0,"picId":109951163972046200,"mark":0},"duration":369000,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":5347246,"fee":8,"rUrl":null,"mark":8192},{"id":416890532,"name":"【海阔天空】国语版-石寅","artists":[{"id":12021013,"name":"石寅.stone","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34584173,"name":"【赤血男儿】石寅.stone","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1458672312582,"size":26,"copyrightId":0,"status":1,"picId":18029791672343268,"mark":0},"duration":324816,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":64153,"name":"海阔天空(Live)","artists":[{"id":2117,"name":"侧田","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":6340,"name":"Justin \"Around The World\" Tour 2011","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1406822400000,"size":28,"copyrightId":7002,"status":1,"picId":109951164084493070,"mark":0},"duration":310000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":1,"rUrl":null,"mark":270336},{"id":27562765,"name":"海阔天空","artists":[{"id":0,"name":"邓小坤","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2639090,"name":"中国梦之声第一季 第十四期","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1375804800007,"size":10,"copyrightId":0,"status":1,"picId":7973658326398208,"mark":0},"duration":162000,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":347319,"name":"海阔天空(live 93)","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":34218,"name":"Words & Music(华纳纸盒版)","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":741456000000,"size":8,"copyrightId":5003,"status":3,"picId":122045790696257,"alia":["Words & Music: Final Live With 家驹"],"mark":0},"duration":375000,"copyrightId":7002,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":1,"rUrl":null,"mark":8192},{"id":1361062159,"name":"Beyond-拍手+海阔天空+上帝是女孩(郑SuPer Mix)（郑顺鹏 / 郑SuPer remix）","artists":[{"id":12037207,"name":"郑顺鹏","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},{"id":0,"name":"郑SuPer","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":78457415,"name":"爆点(郑SuPer Mash Up)","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":563904000000,"size":38,"copyrightId":0,"status":0,"picId":109951164000230340,"mark":0},"duration":227761,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":1413162192,"name":"海阔天空（重编版）（翻自 群星） ","artists":[{"id":8017,"name":"胡芳芳","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":35890039,"name":"Fong cover song","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1502000323691,"size":35,"copyrightId":0,"status":0,"picId":109951162994799090,"mark":0},"duration":99030,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":0},{"id":1335107656,"name":"海阔天空(纯钢琴)","artists":[{"id":30382432,"name":"愚人幽篁里","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":74926720,"name":"梦回","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1545495320028,"size":3,"copyrightId":0,"status":0,"picId":109951163742648960,"mark":0},"duration":166466,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":262144},{"id":473021517,"name":"海阔天空 (2008 Live)","artists":[{"id":3116,"name":"黄家强","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},{"id":6094,"name":"叶世荣","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},{"id":3080,"name":"黄贯中","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":35396892,"name":"艺能25周年","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1262275200007,"size":30,"copyrightId":7003,"status":3,"picId":18437710486428540,"mark":0},"duration":367020,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":270336},{"id":28798780,"name":"海阔天空(Live)","artists":[{"id":4080,"name":"刘心","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},{"id":5518,"name":"王铮亮","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":2894108,"name":"热门华语228","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1388534400000,"size":46,"copyrightId":0,"status":2,"picId":109951163926969180,"mark":0},"duration":169000,"copyrightId":0,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":0,"rUrl":null,"mark":524288},{"id":469391964,"name":"海阔天空","artists":[{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}],"album":{"id":35336928,"name":"超越BEYOND精选","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":1041350400007,"size":19,"copyrightId":7003,"status":3,"picId":17982512672529112,"mark":0},"duration":323787,"copyrightId":7003,"status":0,"alias":[],"rtype":0,"ftype":0,"mvid":0,"fee":8,"rUrl":null,"mark":8192}]
         * songCount : 349
         */

        private int songCount;
        private List<SongsBean> songs;

        public int getSongCount() {
            return songCount;
        }

        public void setSongCount(int songCount) {
            this.songCount = songCount;
        }

        public List<SongsBean> getSongs() {
            return songs;
        }

        public void setSongs(List<SongsBean> songs) {
            this.songs = songs;
        }

        public static class SongsBean {
            /**
             * id : 347230
             * name : 海阔天空
             * artists : [{"id":11127,"name":"Beyond","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}]
             * album : {"id":34209,"name":"海阔天空","artist":{"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null},"publishTime":747504000000,"size":10,"copyrightId":1003,"status":1,"picId":102254581395219,"mark":0}
             * duration : 326000
             * copyrightId : 7002
             * status : 0
             * alias : []
             * rtype : 0
             * ftype : 0
             * mvid : 376199
             * fee : 1
             * rUrl : null
             * mark : 8192
             */

            private int id;
            private String name;
            private AlbumBean album;
            private String duration;
            private String copyrightId;
            private String status;
            private String rtype;
            private String ftype;
            private String mvid;
            private String fee;
            private Object rUrl;
            private String mark;
            private List<ArtistsBean> artists;
            private List<?> alias;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public AlbumBean getAlbum() {
                return album;
            }

            public void setAlbum(AlbumBean album) {
                this.album = album;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getCopyrightId() {
                return copyrightId;
            }

            public void setCopyrightId(String copyrightId) {
                this.copyrightId = copyrightId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRtype() {
                return rtype;
            }

            public void setRtype(String rtype) {
                this.rtype = rtype;
            }

            public String getFtype() {
                return ftype;
            }

            public void setFtype(String ftype) {
                this.ftype = ftype;
            }

            public String getMvid() {
                return mvid;
            }

            public void setMvid(String mvid) {
                this.mvid = mvid;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public Object getRUrl() {
                return rUrl;
            }

            public void setRUrl(Object rUrl) {
                this.rUrl = rUrl;
            }

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public List<ArtistsBean> getArtists() {
                return artists;
            }

            public void setArtists(List<ArtistsBean> artists) {
                this.artists = artists;
            }

            public List<?> getAlias() {
                return alias;
            }

            public void setAlias(List<?> alias) {
                this.alias = alias;
            }

            public static class AlbumBean {
                /**
                 * id : 34209
                 * name : 海阔天空
                 * artist : {"id":0,"name":"","picUrl":null,"alias":[],"albumSize":0,"picId":0,"img1v1Url":"https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1":0,"trans":null}
                 * publishTime : 747504000000
                 * size : 10
                 * copyrightId : 1003
                 * status : 1
                 * picId : 102254581395219
                 * mark : 0
                 */

                private String id;
                private String name;
                private ArtistBean artist;
                private String publishTime;
                private String size;
                private String copyrightId;
                private String status;
                private String picId;
                private String mark;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public ArtistBean getArtist() {
                    return artist;
                }

                public void setArtist(ArtistBean artist) {
                    this.artist = artist;
                }

                public String getPublishTime() {
                    return publishTime;
                }

                public void setPublishTime(String publishTime) {
                    this.publishTime = publishTime;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getCopyrightId() {
                    return copyrightId;
                }

                public void setCopyrightId(String copyrightId) {
                    this.copyrightId = copyrightId;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getPicId() {
                    return picId;
                }

                public void setPicId(String picId) {
                    this.picId = picId;
                }

                public String getMark() {
                    return mark;
                }

                public void setMark(String mark) {
                    this.mark = mark;
                }

                public static class ArtistBean {
                    /**
                     * id : 0
                     * name :
                     * picUrl : null
                     * alias : []
                     * albumSize : 0
                     * picId : 0
                     * img1v1Url : https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                     * img1v1 : 0
                     * trans : null
                     */

                    private String id;
                    private String name;
                    private Object picUrl;
                    private String albumSize;
                    private String picId;
                    private String img1v1Url;
                    private String img1v1;
                    private Object trans;
                    private List<?> alias;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Object getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(Object picUrl) {
                        this.picUrl = picUrl;
                    }

                    public String getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(String albumSize) {
                        this.albumSize = albumSize;
                    }

                    public String getPicId() {
                        return picId;
                    }

                    public void setPicId(String picId) {
                        this.picId = picId;
                    }

                    public String getImg1v1Url() {
                        return img1v1Url;
                    }

                    public void setImg1v1Url(String img1v1Url) {
                        this.img1v1Url = img1v1Url;
                    }

                    public String getImg1v1() {
                        return img1v1;
                    }

                    public void setImg1v1(String img1v1) {
                        this.img1v1 = img1v1;
                    }

                    public Object getTrans() {
                        return trans;
                    }

                    public void setTrans(Object trans) {
                        this.trans = trans;
                    }

                    public List<?> getAlias() {
                        return alias;
                    }

                    public void setAlias(List<?> alias) {
                        this.alias = alias;
                    }
                }
            }

            public static class ArtistsBean {
                /**
                 * id : 11127
                 * name : Beyond
                 * picUrl : null
                 * alias : []
                 * albumSize : 0
                 * picId : 0
                 * img1v1Url : https://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                 * img1v1 : 0
                 * trans : null
                 */

                private String id;
                private String name;
                private Object picUrl;
                private String albumSize;
                private String picId;
                private String img1v1Url;
                private String img1v1;
                private Object trans;
                private List<?> alias;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Object getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(Object picUrl) {
                    this.picUrl = picUrl;
                }

                public String getAlbumSize() {
                    return albumSize;
                }

                public void setAlbumSize(String albumSize) {
                    this.albumSize = albumSize;
                }

                public String getPicId() {
                    return picId;
                }

                public void setPicId(String picId) {
                    this.picId = picId;
                }

                public String getImg1v1Url() {
                    return img1v1Url;
                }

                public void setImg1v1Url(String img1v1Url) {
                    this.img1v1Url = img1v1Url;
                }

                public String getImg1v1() {
                    return img1v1;
                }

                public void setImg1v1(String img1v1) {
                    this.img1v1 = img1v1;
                }

                public Object getTrans() {
                    return trans;
                }

                public void setTrans(Object trans) {
                    this.trans = trans;
                }

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }
    }
}
