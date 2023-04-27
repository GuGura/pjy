$(function (){
    video_Description();
    comment_Description();
    updateBtn();
});
function updateBtn(){
    const updateBtn = $('input[type=button][name=updateBtn]');
    const textarea = $('.comment_Description');

    function autoResizeBtn() {
        updateBtn.prop("disabled", true);

        if (textarea.val().trim() !== '') {
            updateBtn.removeAttr("disabled");
        }
    }

    // textarea 요소의 내용이 변경될 때마다 높이를 자동으로 조정합니다.
    textarea.on('input', autoResizeBtn);

    // 페이지 로드시 textarea 요소의 높이를 초기화합니다.
    autoResizeBtn();
}

function updateText(a){
    var Video_comment = $(a).val();
    $.ajax({
        method : 'post',
        data : Video_comment,
        url : "/updateComment",
        success : function (result){

        }
    })
}
function goLogin(){
    location.href = "/login";
}
function textReset(a){
    $(a).css('height', '27px');
    $(a).val("");
    updateBtn();
}
function comment_Description() {
    // textarea 요소를 참조합니다.
    const $textareaList = $('.comment_Description');

    // textarea 요소의 높이를 자동으로 조정하는 함수입니다.
    function autoResizeTextarea() {
        // 스크롤을 사용하지 않도록 먼저 높이를 설정합니다.
        $textareaList.css('height', '27px');

        // 모든 textarea 요소의 높이를 개별적으로 계산합니다.
        $textareaList.each(function() {
            const $textarea = $(this);
            // textarea 요소의 높이를 계산합니다.
            const height = $textarea[0].scrollHeight;
            // 계산된 높이를 textarea 요소에 적용합니다.
            $textarea.css('height', `${height}px`);
        });
    }

    // textarea 요소의 내용이 변경될 때마다 높이를 자동으로 조정합니다.
    $textareaList.on('input', autoResizeTextarea);

    // 페이지 로드시 textarea 요소의 높이를 초기화합니다.
    autoResizeTextarea();
}

function video_Description(){
    // textarea 요소를 참조합니다.
    const $textarea = $('#video_description');

// textarea 요소의 높이를 자동으로 조정하는 함수입니다.
    function autoResizeTextarea() {
        // 스크롤을 사용하지 않도록 먼저 높이를 설정합니다.
        $textarea.css('height', 'auto');

        // textarea 요소의 높이를 계산합니다.
        const height = $textarea[0].scrollHeight;

        // 계산된 높이를 textarea 요소에 적용합니다.
        $textarea.css('height', `${height}px`);
    }

// textarea 요소의 내용이 변경될 때마다 높이를 자동으로 조정합니다.
    $textarea.on('input', autoResizeTextarea);

// 페이지 로드시 textarea 요소의 높이를 초기화합니다.
    autoResizeTextarea();

}
function commentBar(){
    var img = $("#commentBar").attr('src');
    console.log("123");
    if(img == '/static/image/commentBarA.png'){
        $("#commentBar").attr('src','/static/image/commentBarB.png');
        $('#howViewComment').css('display','flex');
    }else if(img == '/static/image/commentBarB.png'){
        $("#commentBar").attr('src','/static/image/commentBarA.png');
        $('#howViewComment').css('display','none');
    }
}