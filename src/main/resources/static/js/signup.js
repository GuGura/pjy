$(function(){
    var isCheckId = $("#isCheckId").text();
    if(isCheckId == "true"){
        alert("중복된 아이디입니다.");
    }else if(isCheckId == "false"){
        alert("사용가능한 아이디입니다.");
    }
});

function checkId(){
    var id = $("input[name=userID]");
    if(id.val() == null || id.val().trim().length === 0){
        alert("아이디를 입력해주세요");
        console.log("1234");
    }
    else{
        console.log("123");
        document.form1.action = "/checkId";
        document.form1.submit();
    }
}

function gb_update() {
    var id = $("input[name=userID]");
    var email = $("input[name=email]");
    var passwd = $("input[name=password]");
    var isCheckId = $("#isCheckId").text();
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
    }
    else if(isCheckId == "true"){
        alert("중복된 아이디 입니다.");
    }else{
        alert("아이디 체크를 해주세요");
    }
}