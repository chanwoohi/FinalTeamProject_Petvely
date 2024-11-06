	

function initValidation() {
	const remoteUrl = '/member/check/value';

	$('#form').validate({
		onkeyup: false,
		rules: {
			me_id: {
				required: true,
				regex: /^\w{3,13}$/,
				remote: {
					url: remoteUrl,
					type: 'post',
					data: {
						type: "id",
						value: function() {
							return $("#me_id").val();
						}
					}
				}
			},
			me_pw: {
				required: true,
				regex: /^[a-zA-Z0-9!@#$]{3,15}$/
			},
			me_pw2: {
				equalTo: me_pw
			},
			me_nickname: {
				required: true,
				regex: /^[\w가-힣]{3,13}$/,
				remote: {
					url: remoteUrl,
					type: 'post',
					data: {
						type: "nickname",
						value: function() {
							return $("#me_nickname").val();
						}
					}
				}
			},
			me_phone: {
				required: true,
				regex: /^01(\d{1})[-. )]*(\d{4})[-. ]*(\d{4})$/,
				remote: {
					url: remoteUrl,
					type: 'post',
					data: {
						type: "phone",
						value: function() {
							return $("#me_phone").val();
						}
					}
				}
			},
			me_email: {
				required: true,
				regex: /([\w\.]+)@([\w\.]+)\.*(\w+)/,
				remote: {
					url: remoteUrl,
					type: 'post',
					data: {
						type: "email",
						value: function() {
							return $("#me_email").val();
						}
					}
				}
			}
		},
		messages: {
			me_id: {
				required: '필수 항목입니다.',
				regex: '아이디는 영어, 숫자만 가능하며, 3~13자이어야 합니다.',
				remote: "이미 사용중인 아이디입니다."
			},
			me_pw: {
				required: '필수 항목입니다.',
				regex: '아이디는 영어, 숫자, 특수문자(!@#$)만 가능하며, 3~15자이어야 합니다.'
			},
			me_pw2: {
				equalTo: '비번과 일치하지 않습니다.'
			},
			me_nickname: {
				required: '필수 항목입니다.',
				regex: '닉네임은 한글, 영어, 숫자만 가능하며, 3~13자이어야 합니다.',
				remote: "이미 사용중인 닉네임입니다."
			},
			me_phone: {
				required: '필수 항목입니다.',
				regex: '010-0000-0000 의 형태만 가늫합니다.',
				remote: "이미 사용중인 휴대전화번호입니다."
			},
			me_email: {
				required: '필수 항목입니다.',
				regex: '이메일 형식이 아닙니다',
				remote: "이미 사용중인 이메일입니다."
			}
		},
		submitHandler: function() {
			alert("가입 성공")
			return true;
		}
	});
	$.validator.addMethod('regex', function(value, element, regex) {
		var re = new RegExp(regex);
		return re.test(value);
	}, "정규표현식을 확인하세요.");

	$(document).keypress(function(e) {
		if (e.keyCode == 13 && !$(e.target).is('textarea')) e.preventDefault();
	});
}