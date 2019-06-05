	//1.最小长度限制
	$.extend($.fn.validatebox.defaults.rules, {
		minLength : {
			validator : function(value, param) {
				return value.trim().length >= param[0];
			},
			message : 'minimum {0} characters'
		}
	});
	//2.最大长度限制
	$.extend($.fn.validatebox.defaults.rules, {
	    maxLength : {
		    validator : function(value, param) {
			   var rules = $.fn.validatebox.defaults.rules;
			   rules.maxLength.message = "maximum {0} characters";
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
						rules.limitLength.message = "longer than {0} and shorter than {1}";
						if (specialCharacterValidation(value) == false) {
							rules.limitLength.message = "illegal characters identified";
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
				rules.isSpecialCharacter.message = "maximum {0} characters";
				if (specialCharacterValidation(value) == false) {
					rules.isSpecialCharacter.message = "illegal characters identified";
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
				rules.isEnglishAndNumber.message = "maximum {0} characters";
				if (specialCharacterValidation(value) == false) {
					rules.isEnglishAndNumber.message = "illegal characters identified";
					return false;
				}
				if (englishAndNumberValidation(value) == false) {
					rules.isEnglishAndNumber.message = "input english characters & digits";
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
				rules.isUppEnglishAndNumber.message = "maximum {0} characters";
				if (specialCharacterValidation(value) == false) {
					rules.isUppEnglishAndNumber.message = "illegal characters identified";
					return false;
				}
				if (uppEnglishAndNumberValidation(value) == false) {
					rules.isUppEnglishAndNumber.message = "input a-f captial letters or digits";
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
				rules.isPhone.message = "maximum {0} characters";
				if (specialCharacterValidation(value) == false) {
					rules.isPhone.message = "illegal characters identified";
					return false;
				}
				if (phoneValidation(value) == false) {
					rules.isPhone.message = "invalid cell phone format";
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
				rules.isPayNO.message = "maximum {0} characters";
				if (specialCharacterValidation(value) == false) {
					rules.isPayNO.message = "illegal characters identified";
					return false;
				}
				if (payNOValidation(value) == false) {
					rules.isPayNO.message = "invalid identity";
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
				rules.isMoney.message = "maximum {0} characters";
     			if (onMoneyValidation(value) == false) {
					rules.isMoney.message = "invalid amount format (2 decimal places)";
					return false;
				}
				return value.length <= param[0];
			},
			message : 'invalid amount'
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
				rules.isNumber.message = "maximum {0} characters";
				if (specialCharacterValidation(value) == false) {
					rules.isNumber.message = "illegal characters identified";
					return false;
				}
				if (numberValidation(value) == false) {
					rules.isNumber.message = "input digits";
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
				rules.isFixedNumber.message = "maximum {0} characters";
				if (specialCharacterValidation(value) == false) {
					rules.isFixedNumber.message = "illegal characters identified";
					return false;
				}
				if (numberValidation(value) == false) {
					rules.isFixedNumber.message = "input digits";
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
				rules.validPWD.message = "length of new password must be {0} - {1}";
				
				var re=/^(?![0-9]+$)(?![a-zA-Z]+$)([0-9A-Za-z]+)$/;
				var result = re.test(value) ;
				if(!result){
					rules.validPWD.message = "password must be combination of alphabet & digit";
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
				rules.isAccount.message = "maximum {0} characters";
				if (accountCharacterValidation(value) == false) {
					rules.isAccount.message = "illegal characters identified";
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		}
	});
	
	//2016.6.17 add
	$.extend($.fn.validatebox.defaults.rules, {
		//EMAIL ADDRESS
		email : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.email.message = "maximum {0} characters";
				if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
						.test(value)) {
					rules.email.message = 'invalid email address';
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
				rules.tel.message = "maximum {0} characters";
				/*if (!/^(\d{3,4}-)\d{7,8}$/.test(value)
						&& !/^1[3|4|5|7|8][0-9]\d{8}$/.test(value)) {
					rules.tel.message = 'please input valid phone number';
					return false;
				}*/
				if (!/^([0-9-]+)$/.test(value)) {
					rules.tel.message = 'please input valid phone number';
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
				rules.vendor_enname.message = "maximum {0} characters";
				if (/[\u4E00-\u9FA5]/.test(value)) {
					rules.vendor_enname.message = 'allow english characters';
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
				rules.inst_no.message = "maximum {0} characters";
				if (!/^[0-9A-Za-z]+$/.test(value)) {
					rules.inst_no.message = 'allow english or digits';
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
				rules.inst_name.message = "maximum {0} characters";
				if (!/^(?! +)[\w\d. \u4e00-\u9fa5]+?[^ +]$/.test(value)) {
					rules.inst_name.message = 'allow chinese word or english';
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
				rules.ip.message = "maximum {0} characters";
				if (!/^((1?\d?\d|(2([0-4]\d|5[0-5])))\.){3}(1?\d?\d|(2([0-4]\d|5[0-5])))$/.test(value)) {
					rules.ip.message = 'please input correct ip address';
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
				rules.other_ip.message = "maximum {0} characters";
				if (!/^[0-9a-zA-Z]+[0-9a-zA-Z\.-]*\.[a-zA-Z]{2,4}$/.test(value) && !/^((1?\d?\d|(2([0-4]\d|5[0-5])))\.){3}(1?\d?\d|(2([0-4]\d|5[0-5])))$/.test(value)) {
					rules.other_ip.message = 'please input correct address';
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
	        message: 'please input corect ip or domain'
	    },
	     
	})
	
	
	$.extend($.fn.validatebox.defaults.rules, {
		//A-F（a-f）+数字
		key : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.key.message = "maximum {0} characters";
				if (!/^[a-fA-F0-9]+$/.test(value)) {
					rules.key.message = 'allow a-f,a-f or digits';
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
				rules.model.message = "maximum {0} characters";
				if (!/^[0-9A-Za-z-_]+$/.test(value)) {
					rules.model.message = 'allow english, "-", "_" or digits';
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
				rules.domain_ip.message = "maximum {0} characters";
				if (!/^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$|^([a-zA-Z0-9]([a-zA-Z0-9\-]{0,61}[a-zA-Z0-9])?\.)+[a-zA-Z]{2,6}$/.test(value)) {
					rules.domain_ip.message = 'please input correct ip or domain address';
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
				rules.fix_length.message = "please input layout fixed length {0} characters";
				if (value.length!=param[0]) {
					rules.domain_ip.message = "please input layout fixed length {0} characters";
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
				rules.fix_length.message = "please input layout fixed length {0} characters";
				if (value.length>param[0]) {
					rules.domain_ip.message = "please input layout fixed length {0} characters";
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
				rules.check_KM.message = "maximum {0} characters";
				if (!/^(\d|[1-9]\d+)(\.\d+)?$/.test(value)) {
					rules.check_KM.message = 'invalid data format';
					return false;
				}
				return value.length <= param[0];
			},
			message : 'invalid data format'
		}
	});	
	
	//正整数
	$.extend($.fn.validatebox.defaults.rules, {
		postive_integer : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.postive_integer.message = "maximum {0} characters";
				if (!/^([1-9]|[1-9]\d+)$/.test(value)) {
					rules.postive_integer.message = 'input positive whole number';
					return false;
				}
				return value.length <= param[0];
			},
			message : 'input positive whole number'
		}
	});	
	
	//固定长度的字母或数字
	$.extend($.fn.validatebox.defaults.rules, {
		fix_length_num : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.fix_length_num.message = "fixed length {0} characters";
				if (!/^(\d|[A-Za-z])+$/.test(value)) {
					rules.fix_length_num.message = 'allow alphabets or digits';
					return false;
				}
				return value.length == param[0];
			},
			message : 'allow alphabets or digits'
		}
	});	
	
	//端口号验证 ,[1025-65535]
	$.extend($.fn.validatebox.defaults.rules, {
		valid_port : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.valid_port.message = "maximum {0} characters";
				if (!/^(102[5-9]|10[3-9]\d|1[1-9]\d{2}|[2-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/.test(value)) {
					rules.valid_port.message = 'valid interval [1025-65535]';
					return false;
				}
				return value.length <= param[0];
			},
			message : 'valid interval [1025-65535]'
		}
	});	
	
	//英文错误描述不能输入汉字
	$.extend($.fn.validatebox.defaults.rules, {
		english_error_desc : {
			validator : function(value, param) {
				var rules = $.fn.validatebox.defaults.rules;
				rules.english_error_desc.message = "length should not exceed {0}";
				if (!/^[^\u4e00-\u9fa5]+$/.test(value)) {
					rules.english_error_desc.message = 'chinese characters are not allowed';
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
				rules.ip_url.message = "length should not exceed {0}";
				if (!/^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$|^[a-zA-z0-9\/\/:_&?.=-]*$/.test(value)) {
					rules.ip_url.message = 'please enter the correct IP or URL address';
					return false;
				}
				return value.length <= param[0];
			},
			message : ''
		},
	});