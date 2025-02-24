document.addEventListener("DOMContentLoaded", function () {
    const refreshButton = document.getElementById("refresh-secureNo");

    if (refreshButton) {
        refreshButton.addEventListener("click", function () {
            fetch(`${contextPath}/prescription/refreshSecureNo.do`, { method: "POST" })
                .then(response => response.json())
                .then(data => {
                    if (data.reqSecureNoDecoded) {
                        document.getElementById("secureNoImage").src = data.reqSecureNoDecoded;
                        console.log("보안문자가 새로고침되었습니다.");
                    } else {
                        alert("새로운 보안문자를 가져오는 데 실패했습니다.");
                    }
                })
                .catch(error => {
                    console.error("보안문자 새로고침 중 오류 발생:", error);
                    alert("보안문자를 새로고침하는 중 오류가 발생했습니다.");
                });
        });
    } else {
        console.error("refresh-secureNo 버튼을 찾을 수 없습니다."); // 디버깅용 로그
    }
});



document.addEventListener("DOMContentLoaded", function () {
    const step1Inputs = document.querySelectorAll("#step-1 input");
    const step2 = document.getElementById("step-2");
    const step3 = document.getElementById("step-3");
    const step4 = document.getElementById("step-4");
    const phoneNumberInput = document.getElementById("phoneNumber");
    const secureNoImage = document.getElementById("secureNoImage");
    const captchaInput = document.getElementById("captcha");
    const smsAuthNumberInput = document.getElementById("sms-auth-number");

    function checkStep1() {
        const idFront = document.getElementById("id-number-front").value.trim();
        const idBack = document.getElementById("id-number-back").value.trim();
        const name = document.getElementById("name").value.trim();

        if (idFront.length === 6 && idBack.length === 7 && name.length > 1) {
            step2.classList.remove("hidden");
        } else {
            step2.classList.add("hidden");
        }
    }

    step1Inputs.forEach(input => {
        input.addEventListener("input", checkStep1);
    });

	phoneNumberInput.addEventListener("input", () => {
	    if (phoneNumberInput.value.trim().length === 11) {
			
	        console.log("[DEBUG] 전화번호 입력 완료 → 본인인증 요청 시작...");

			document.getElementById('loading').style.display = 'block';
			
	        const formData = new FormData();
	        formData.append("idNumberFront", document.getElementById("id-number-front").value.trim());
	        formData.append("idNumberBack", document.getElementById("id-number-back").value.trim());
	        formData.append("name", document.getElementById("name").value.trim());
	        formData.append("telecom", document.getElementById("telecom").value);
	        formData.append("phoneNumber", phoneNumberInput.value.trim());

	        fetch(`${contextPath}/prescription/processCertification.do`, {
	            method: "POST",
	            body: new URLSearchParams(formData)
	        })
	            .then(response => response.json())
	            .then(data => {
	                if (data.redirectToSecureInput) {
	                    if (data.reqSecureNoDecoded) {
							document.getElementById('loading').style.display = 'none';
	                        secureNoImage.src = data.reqSecureNoDecoded;
	                        step3.classList.remove("hidden"); // 보안문자 입력 단계 표시
	                        console.log("[DEBUG] 보안문자 갱신 완료.");
	                    } else {
	                        alert("보안문자를 불러오는 데 실패했습니다.");
	                    }
	                } else {
	                    alert("추가 인증 없이 다음 단계로 진행합니다.");
	                    window.location.href = `${contextPath}/prescription/additionalCertification.do`;
	                }
	            })
	            .catch(error => {
	                console.error("[ERROR] 본인인증 요청 중 오류 발생:", error);
	                alert("본인인증 요청 중 오류가 발생했습니다.");
	            });
	    } else {
	        step3.classList.add("hidden"); // 전화번호 미입력 시 보안문자 숨김
	    }
	});


	document.getElementById("refresh-secureNo").addEventListener("click", function () {
	    fetch(`${contextPath}/prescription/refreshSecureNo.do`, { // 변경된 contextPath 사용
	        method: "POST",
	    })
	    .then(response => response.json())
	    .then(data => {
	        if (data.reqSecureNoDecoded) {
	            document.getElementById("secureNoImage").src = data.reqSecureNoDecoded;
	            console.log("보안문자가 새로고침되었습니다.");
	        } else {
	            alert("새로운 보안문자를 가져오는 데 실패했습니다.");
	        }
	    })
	    .catch(error => {
	        console.error("보안문자 새로고침 중 오류 발생:", error);
	        alert("보안문자를 새로고침하는 중 오류가 발생했습니다.");
	    });
	});


	$('#secure-submit').on('click', function () {
	    const secureData = {
	        secureNo: $('#captcha').val(),
	        secureNoRefresh: '0',
	        is2Way: true,
	        jobIndex: sessionStorage.getItem('jobIndex') || 0,
	        threadIndex: sessionStorage.getItem('threadIndex') || 0,
	        jti: sessionStorage.getItem('jti') || '',
	        twoWayTimestamp: sessionStorage.getItem('twoWayTimestamp') || Date.now()
	    };

		document.getElementById('loading').style.display = 'block';

	    console.log('[DEBUG] 보안문자 인증 요청 데이터:', secureData);

	    $.ajax({
	        url: contextPath + "/prescription/processSecureInput.do",
	        method: 'POST',
	        headers: { "Content-Type": "application/x-www-form-urlencoded" },
	        data: $.param(secureData),
	        success: function (response) {
	            console.log('[DEBUG] 추가 인증 응답:', response);
				

	            if (response.success) {

					document.getElementById('loading').style.display = 'none';
	                alert('보안문자 인증 성공! 이제 SMS 인증번호를 입력하세요.');

	                // SMS 인증 필드 표시
	                $('#step-4').removeClass('hidden');
	                $('#sms-auth-number').prop('disabled', false);
	                $('#verify-sms').prop('disabled', false);
	            } else {
	                alert('보안문자 인증 실패: ' + response.errorMessage);
	            }
	        },
	        error: function (xhr, status, error) {
	            console.error('[ERROR] 보안문자 인증 중 오류 발생:', xhr.responseText);
	            alert('보안문자 인증 중 오류가 발생했습니다.');
	        }
	    });
	});





	

    document.getElementById("verify-sms").addEventListener("click", function () {
        const smsCode = smsAuthNumberInput.value.trim();

        if (smsCode.length === 6) {
			
			document.getElementById('loading').style.display = 'block';
			
			fetch(`${contextPath}/prescription/verifySmsCode.do`, {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: `smsAuthNo=${smsCode}&is2Way=true`
            })
                .then(response => response.json())
                .then(data => {
                    if (data.verified) {
                        alert("SMS 인증이 완료되었습니다.");
                        step4.classList.add("hidden");
                        window.location.href = `${contextPath}/prescription/prescriptionList.do`;
                    } else {
                        alert("인증번호가 올바르지 않습니다. 다시 입력해주세요.");
                    }
                })
                .catch(error => {
                    console.error("[ERROR] SMS 인증 요청 중 오류 발생:", error);
                });
        } else {
            alert("6자리 인증번호를 입력해주세요.");
        }
    });

	document.addEventListener("DOMContentLoaded", function () {
	    const certificationForm = document.getElementById("certification-form");

	    if (certificationForm) {
	        certificationForm.addEventListener("submit", function (e) {
	            e.preventDefault(); // 기본 제출 방지
	            const formData = new FormData(certificationForm);

	            fetch(`${contextPath}/prescription/processCertification.do`, {
	                method: "POST",
	                body: new URLSearchParams(formData)
	            })
	                .then(response => response.json())
	                .then(data => {
	                    if (data.redirectToSecureInput) {
	                        if (data.reqSecureNoDecoded) {
	                            document.getElementById("secureNoImage").src = data.reqSecureNoDecoded;
	                            document.getElementById("step-3").classList.remove("hidden");
	                            console.log("보안문자 이미지가 렌더링되었습니다.");
	                        } else {
	                            alert("보안문자를 불러오는 데 실패했습니다.");
	                        }
	                    } else {
	                        alert("추가 인증 없이 다음 단계로 진행합니다.");
	                        window.location.href = `${contextPath}/prescription/additionalCertification.do`;
	                    }
	                })
	                .catch(error => {
	                    console.error("AJAX 요청 실패:", error);
	                    alert("요청 중 오류가 발생했습니다.");
	                });
	        });
	    } else {
	        console.error("certification-form을 찾을 수 없습니다."); // 디버깅용 로그
	    }
	});
});
