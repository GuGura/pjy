$(function(){
    var isCheckId = $("#isCheckId").text();
    if(isCheckId == "true"){
        alert("중복된 아이디입니다.");
    }else if(isCheckId == "false"){
        alert("사용가능한 아이디입니다.");
    }else if(isCheckId === "id"){
        alert("아이디패턴을 맞춰주세요");
    }else if(isCheckId === "passwd"){
        alert("비밀번호 패턴을 맞춰주세요");
    }

    var userID = $('input[name=userID]');
    var password = $('input[name=password]');
    userID.on("focus", function (){
        $('#idPattern').css('display','flex');
    });
    userID.on("blur", function (){
        $('#idPattern').css('display','none');
    });
    password.on("focus", function (){
        $('#passwdPattern').css('display','flex');
    });
    password.on("blur", function (){
        $('#passwdPattern').css('display','none');
    });

});

function checkId(){
    var id = $("input[name=userID]");
    if(id.val() == null || id.val().trim().length === 0){
        alert("아이디를 입력해주세요");
    }
    else{
        document.form1.action = "/checkId";
        document.form1.submit();
    }
}

function gb_update() {
    var id = $("input[name=userID]");

    var email = $("input[name=email]");
    var passwd = $("input[name=password]");
    let isCheckId = $("#isCheckId").text();
    if (id.val() == "") {
        alert('아이디를 입력하세요');
        id.focus();
        return;
    }
    if (passwd.val() == "") {
        alert('비밀번호을 입력하세요');
        passwd.focus();
        return;
    }
    if (email.val() == "") {
        alert('이메일을 입력하세요');
        email.focus();
        return;
    }
    if(isCheckId == "false"){
        document.form1.action = "/singUp";
        document.form1.submit();
    }else if(isCheckId === "id"){
        alert("아이디패턴을 맞춰주세요");
    }  else if(isCheckId == "true"){
        alert("중복된 아이디 입니다.");
    }else{
        alert("아이디 체크를 해주세요");
    }
}