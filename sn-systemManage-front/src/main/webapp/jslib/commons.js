﻿	//1.最小长度限制
	$.extend($.fn.validatebox.defaults.rules, {
		minLength : {
			validator : function(value, param) {
				return value.trim().length >= param[0];
			},
			message : '不能小于{0}个字符'
		}
	});
	//2.最大长度限制	
	$.extend($.fn.validatebox.defaults.rules, {
	    maxLength : {
		    validator : function(value, param) {
			   var rules = $.fn.validatebox.defaults.rules;
			   rules.maxLength.message = "长度不能超过{0}位";
			   return value.length <= param[0];
		},
		message : ''
	   },
	});
	//3.长度区间限制
	$.extend($.fn.validatebox.defaults.rules,
			{
				limitLength : {
					validator : function(value, param) {
						var rules = $.fn.validatebox.defaults.rules;
						rules.limitLength.message = "长度不能小于{0},不能大于{1}";
						if (specialCharacterValidation(value) == false) {
							rules.limitLength.message = "不能输入非法字符";
							return false;
						}
						return (value.length >= param[0])
								&& (value.length <= param[1]);
					},
					message : ''
				}
			});

	//4.非法字符验证（是否包含特殊字符）
	function specialCharacterValidation(characters) {
		var patt = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\] ]/im;
		for (var i = 0; i < characters.length; i++) {
			if (patt.test(characters.substr(i, 1))) {
				return false;
			}
		}
		return true;
	}
	$.extend($.fn.validatebox.defaults.rules, {
		isSpecialCharacter : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isSpecialCharacter.message = "长度不能超过{0}位";
				if (specialCharacterValidation(value) == false) {
					rules.isSpecialCharacter.message = "不能输入非法字符";
					return false;
				}
				return value.trim().length <= param[0];
			},
			message : ''
		}
	});

	//5.英文+数字 
	function englishAndNumberValidation(v) {
		var re = new RegExp("^[0-9a-zA-Z\_]+$");
		if (re.test(v))
			return true;
		return false;
	}
	$.extend($.fn.validatebox.defaults.rules, {
		isEnglishAndNumber : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isEnglishAndNumber.message = "长度不能超过{0}位";
				if (specialCharacterValidation(value) == false) {
					rules.isEnglishAndNumber.message = "不能输入非法字符";
					return false;
				}
				if (englishAndNumberValidation(value) == false) {
					rules.isEnglishAndNumber.message = "输入英文加数字";
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		}
	});
	
	//A-F+数字 
	function uppEnglishAndNumberValidation(v) {
		var re = new RegExp("^[0-9A-F\_]+$");
		if (re.test(v))
			return true;
		return false;
	}
	$.extend($.fn.validatebox.defaults.rules, {
		isUppEnglishAndNumber : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isUppEnglishAndNumber.message = "长度不能超过{0}位";
				if (specialCharacterValidation(value) == false) {
					rules.isUppEnglishAndNumber.message = "不能输入非法字符";
					return false;
				}
				if (uppEnglishAndNumberValidation(value) == false) {
					rules.isUppEnglishAndNumber.message = "输入A-F大写字母或数字";
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		}
	});

	//6.验证手机号码格式
	function phoneValidation(e) {
		if (e == "" || e == null) {
			return true;
		}
		var exp = /^1[3|4|5|7|8]\d{9}$/;
		if (exp.test(e)) {
			return true;
		} else {
			return false;
		}
	}
	$.extend($.fn.validatebox.defaults.rules, {
		isPhone : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isPhone.message = "长度不能超过{0}位";
				if (specialCharacterValidation(value) == false) {
					rules.isPhone.message = "不能输入非法字符";
					return false;
				}
				if (phoneValidation(value) == false) {
					rules.isPhone.message = "手机格式错误";
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		}
	});

	//7.身份证验证
	var aCity = {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江 ",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北 ",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏 ",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外 "
	};
	function payNOValidation(sId) {
		var iSum = 0;
		if (sId == "")
			return true;
		var cardPattern = /^\d{18}$|^\d{17}X$/;
		if (!cardPattern.test(sId))
			return false;
		sId = sId.replace(/x$/i, "a");
		if (aCity[parseInt(sId.substr(0, 2))] == null)
			return false;
		sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-"
				+ Number(sId.substr(12, 2));
		var d = new Date(sBirthday.replace(/-/g, "/"));
		if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d
				.getDate()))
			return false;
		for (var i = 17; i >= 0; i--)
			iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
		if (iSum % 11 != 1)
			return false;

		return true;
	}
	$.extend($.fn.validatebox.defaults.rules, {
		isPayNO : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isPayNO.message = "长度不能超过{0}位";
				if (specialCharacterValidation(value) == false) {
					rules.isPayNO.message = "不能输入非法字符";
					return false;
				}
				if (payNOValidation(value) == false) {
					rules.isPayNO.message = "身份证验证错误";
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		}
	});

	//8.验证金额
	function onMoneyValidation(e) {
		var exp = /^([1-9][\d]{0,8}|0)(\.[\d]{1,2})?$/;
		if (!exp.test(e)) {
			return false;
		} else {
			return true;
		}
	}
	$.extend($.fn.validatebox.defaults.rules, {
		isMoney : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isMoney.message = "长度不能超过{0}位";
     			if (onMoneyValidation(value) == false) {
					rules.isMoney.message = "金额格式错误(最多保留两位小数)";
					return false;
				}
				return value.length <= param[0];
			},
			message : '输入的金额格式错误'
		}
	});

	//9.验证数字
	function numberValidation(e) {
		var exp = /^[0-9]*$/;
		if (!exp.test(e)) {
			return false;
		} else {
			return true;
		}
	}
	$.extend($.fn.validatebox.defaults.rules, {
		isNumber : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isNumber.message = "长度不能超过{0}位";
				if (specialCharacterValidation(value) == false) {
					rules.isNumber.message = "不能输入非法字符";
					return false;
				}
				if (numberValidation(value) == false) {
					rules.isNumber.message = "必须输入数字";
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		}
	});
	
	//10.固定验证数字
	$.extend($.fn.validatebox.defaults.rules, {
		isFixedNumber : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isFixedNumber.message = "长度必须为{0}位";
				if (specialCharacterValidation(value) == false) {
					rules.isFixedNumber.message = "不能输入非法字符";
					return false;
				}
				if (numberValidation(value) == false) {
					rules.isFixedNumber.message = "必须输入数字";
					return false;
				}
				return value.length == param[0];
			},
			message : ''
		}
	});
	
	//11. 密码长度 8 位以上，必须是字母加英文的组合
	// add by @Troy 2014-09-22 15:06:42
	$.extend($.fn.validatebox.defaults.rules, {
		validPWD : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.validPWD.message = "新密码长度为{0}-{1}个字符";
				
				var re=/^(?![0-9]+$)(?![a-zA-Z]+$)([0-9A-Za-z]+)$/;
				var result = re.test(value) ;
				if(!result){
					rules.validPWD.message = "密码必须是字母加数字的组合";
					return false ;
				}
				return value.length >= param[0] && value.length <= param[1] ;
			},
			message : ''
		}
	});
	
	
	
	//4.非法字符验证（是否包含特殊字符）
	function accountCharacterValidation(characters) {
		var patt = /[0-9\-]/im;
		for (var i = 0; i < characters.length; i++) {
			if (!patt.test(characters.substr(i, 1))) {
				return false;
			}
		}
		return true;
	}
	// 只允许数字和下划线，银行账号验证
	// add by @Troy 2015-05-28 16:21:10
	$.extend($.fn.validatebox.defaults.rules, {
		isAccount : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.isAccount.message = "长度不能超过{0}位";
				if (accountCharacterValidation(value) == false) {
					rules.isAccount.message = "不能输入非法字符";
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		}
	});
	
	//2016.6.17 add
	$.extend($.fn.validatebox.defaults.rules, {
		//EMAIL地址
		email : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.email.message = "长度不能超过{0}位";
				if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
						.test(value)) {
					rules.email.message = '请输入正确的邮箱格式';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//电话(xxx/xxxx-xxxxxxx/xxxxxxxx或手机号)
		tel : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.tel.message = "长度不能超过{0}位";
				/*if (!/^(\d{3,4}-)\d{7,8}$/.test(value)
						&& !/^1[3|4|5|7|8][0-9]\d{8}$/.test(value)) {
					rules.tel.message = '请输入正确的电话号码格式';
					return false;
				}*/
				if (!/^([0-9-]+)$/.test(value)) {
					rules.tel.message = '请输入正确的电话号码格式';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//英文
		vendor_enname : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.vendor_enname.message = "长度不能超过{0}位";
				if (/[\u4E00-\u9FA5]/.test(value)) {
					rules.vendor_enname.message = '只允许输入英文字符';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//机构编码（数字+字母）
		inst_no : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.inst_no.message = "长度不能超过{0}位";
				if (!/^[0-9A-Za-z]+$/.test(value)) {
					rules.inst_no.message = '只允许输入英文或数字字符';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//机构名称（中文+字母）
		inst_name : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.inst_name.message = "长度不能超过{0}位";
				if (!/^(?! +)[\w\d. \u4e00-\u9fa5]+?[^ +]$/.test(value)) {
					rules.inst_name.message = '只允许输入中文或英文字符';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//
		ip : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.ip.message = "长度不能超过{0}位";
				if (!/^((1?\d?\d|(2([0-4]\d|5[0-5])))\.){3}(1?\d?\d|(2([0-4]\d|5[0-5])))$/.test(value)) {
					rules.ip.message = '请输入正确的IP地址';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//ip+域名!!! 弃用，换成check_ip_domain
		other_ip : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.other_ip.message = "长度不能超过{0}位";
				if (!/^[0-9a-zA-Z]+[0-9a-zA-Z\.-]*\.[a-zA-Z]{2,4}$/.test(value) && !/^((1?\d?\d|(2([0-4]\d|5[0-5])))\.){3}(1?\d?\d|(2([0-4]\d|5[0-5])))$/.test(value)) {
					rules.other_ip.message = '请输入正确的地址';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
	    //匹配IP和域名， 替换other_ip
		//匹配域名（包含协议头）：http://www.baidu.com/s?f=8&rsv_bp=1&rsv_idx=1&word=oracle%20decode&tn=97124639_hao_pg
		//匹配域名:www.baidu.com/s?f=8&rsv_bp=1&rsv_idx=1&word=oracle%20decode&tn=97124639_hao_pg
		//匹配IP：192.146.1.34
	    check_ip_domain: {
	        validator: function (value, param) {
	        	var ip_reg = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
				var urlRegExp=/^((https|http|ftp|rtsp|mms)?(:\/\/)?)+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;	
				
				var rules = $.fn.validatebox.defaults.rules;
				/*rules.check_ip_domain.message = "长度不能超过{0}位";*/
				//if(!/^(25[0-5\]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(value)  && !/^((https|http|ftp|rtsp|mms)?(:\/\/)?)+[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/.test(value) )
				if( ip_reg.test(value) == true)
				{
				//	return value.length <= param[0];
						return true;
				}
				else if(urlRegExp.test(value) ==true )
				{
					
					return true;
				}
				else{
				/*	rules.check_ip_domain.message = '请输入正确的地址';*/
					return false;
				}
	        },
	        message: '请输入正确的ip或域名'
	    },
	     
	})
	
	
	$.extend($.fn.validatebox.defaults.rules, {
		//A-F（a-f）+数字
		key : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.key.message = "长度不能超过{0}位";
				if (!/^[a-fA-F0-9]+$/.test(value)) {
					rules.key.message = '只允许输入A-F大小写英文或数字字符';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//（数字+字母+'-'+'_'）
		model : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.model.message = "长度不能超过{0}位";
				if (!/^[0-9A-Za-z-_]+$/.test(value)) {
					rules.model.message = '只允许输入英文、-、_或数字字符';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//IP+域名
		domain_ip : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.domain_ip.message = "长度不能超过{0}位";
				if (!/^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$|^([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}$/.test(value)) {
					rules.domain_ip.message = '请输入正确的IP或域名地址';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	
	
	$.extend($.fn.validatebox.defaults.rules, {
		fix_length : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.fix_length.message = "该输入框固定长度{0}位";
				if (value.length!=param[0]) {
					rules.domain_ip.message = "该输入框固定长度{0}位";
					return false;
				}
				return true;
			},
			message : ''
		},
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
	    num_char_:{
	        validator:function(value,param){
				var rules = $.fn.validatebox.defaults.rules;
				rules.fix_length.message = "该输入框最大长度{0}位";
				if (value.length>param[0]) {
					rules.domain_ip.message = "该输入框最大长度{0}位";
					return false;
				}
				 var reg = /^\w+$/;
				 return reg.test(value);
	        },
	        message:  ''
	    }
	});
	

	//验证公里数>0
	$.extend($.fn.validatebox.defaults.rules, {
		check_KM : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.check_KM.message = "长度不能超过{0}位";
				if (!/^(\d|[1-9]\d+)(\.\d+)?$/.test(value)) {
					rules.check_KM.message = '输入的格式有误';
					return false;
				}
				return value.length <= param[0];
			},
			message : '输入的格式有误'
		}
	});	
	
	//正整数
	$.extend($.fn.validatebox.defaults.rules, {
		postive_integer : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.postive_integer.message = "长度不能超过{0}位";
				if (!/^([1-9]|[1-9]\d+)$/.test(value)) {
					rules.postive_integer.message = '必须是大于0的整数';
					return false;
				}
				return value.length <= param[0];
			},
			message : '必须是大于0的整数'
		}
	});	
	
	//固定长度的字母或数字
	$.extend($.fn.validatebox.defaults.rules, {
		fix_length_num : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.fix_length_num.message = "固定长度{0}位";
				if (!/^(\d|[A-Za-z])+$/.test(value)) {
					rules.fix_length_num.message = '只能是字母或数字';
					return false;
				}
				return value.length == param[0];
			},
			message : '只能是字母或数字'
		}
	});	
	
	//端口号验证 ,[1025-65535]
	$.extend($.fn.validatebox.defaults.rules, {
		valid_port : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.valid_port.message = "长度不能超过{0}位";
				if (!/^(102[5-9]|10[3-9]\d|1[1-9]\d{2}|[2-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/.test(value)) {
					rules.valid_port.message = '请输入合法范围[1025-65535]';
					return false;
				}
				return value.length <= param[0];
			},
			message : '请输入合法范围[1025-65535]'
		}
	});	
	
	//英文错误描述不能输入汉字
	$.extend($.fn.validatebox.defaults.rules, {
		english_error_desc : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.english_error_desc.message = "长度不能超过{0}位";
				if (!/^[^\u4e00-\u9fa5]+$/.test(value)) {
					rules.english_error_desc.message = '不允许输入汉字';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		}
	});
	
	$.extend($.fn.validatebox.defaults.rules, {
		//IP+URL地址
		ip_url : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.ip_url.message = "长度不能超过{0}位";
				if (!/^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$|^[a-zA-z0-9\/\/:_&?.=-]*$/.test(value)) {
					rules.ip_url.message = '请输入正确的IP或URL地址';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});
	