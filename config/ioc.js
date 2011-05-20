/*
{
	"对象名称" : {
		type : "对象类型",
		singleton : true | false,
		events : {
			fetch : "触发器的类型或者函数名",
			create : "触发器的类型或者函数名",
			depose : "触发器的类型或者函数名"
		},
		args : [
			参数1， 参数2 ...
		],
		fields : {
			"字段名称1" : 字段值1 ,
			"字段名称2" : 字段值2 ,
			...
		}
	}
}
*/
var ioc = {
	/* 默认方式 */
	xiaobai : {
		id : 88,
		name : '小白',
		age : 22
	},
	/* 详细配置 */
	xiaohei : {
		// 指定了类型后在ioc.get()时可以不指定类型
		type : 'nutz.test.pojo.Person',
		// 如果为false，每次会new一个对象
		singleton : true,
		args : ['XiaoHei'],
		fields : {
			id : 202,
			age : 33
			// firend : { refer : 'xiaobai' }  // 可以指向另一个对象
		}
	}
}