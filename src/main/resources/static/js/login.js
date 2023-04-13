$(function(){
    var isLogin = $("#isLogin").text();
    if(isLogin == "false"){
        alert("아이디가 존재하지 않거나 비밀번호가 정확하지않습니다.");
    }
});

function check_login() {
    var id = $("input[name=userID]");
    var passwd = $("input[name=password]");
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
    document.form1.action = "/login";
    document.form1.submit();
}