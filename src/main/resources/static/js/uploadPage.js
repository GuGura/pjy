$(function () {
    video_Description();
    comment_Description();
    comment_List();
    updateBtn();
});

function updateText(a) {
    const Video_comment = $(a).val();
    const formData = new FormData();
    formData.append('Video_comment',Video_comment);
    $.ajax({
        method: 'post',
        data: formData,
        contentType: false, // 추가
        processData: false, // 추가
        url: "/updateComment",
        success: function (result) {
            const commentList = $('#videoComment')
            commentList.html(result);
        },
        error:function (result){
            alert("실패");
            console.log(result);
        }
    })
}

function myPage(a) {
    console.log('video_UID:' + a);
    if (confirm('삭제하시겠습니까?')){
        var video_UID = a;
        const formData = new FormData();
        formData.append("video_UID", video_UID);
        $("#loading").show(); // 로딩 표시 표시
        $("#backLoad").show();
        $.ajax({
            contentType: false, // 추가
            processData: false, // 추가
            data : formData,
            method : 'post',
            url: '/deleteVideo',
            success : function (result){
                console.log(result);
                $("#loading").hide(); // 로딩 표시 표시
                $("#backLoad").hide();
                location.href = "/";
            },
            error :function (result){
                console.log(result)
                $("#loading").hide(); // 로딩 표시 표시
                $("#backLoad").hide();
                alert("삭제 실패");

            }
        })
    }
}

function goLogin() {
    location.href = "/login";
}

/************************댓글창 reset *************************************/
function textReset(a) {
    $(a).css('height', '27px');
    $(a).val("");
    updateBtn();
}

/************************댓글창 reset *************************************/

/*****************************************토글?창**************************************/
function MyCommentToggle() {
    var MyComment = $('#MyComment').css('display');
    if (MyComment == 'flex') {
        $('#MyComment').css('display', 'none');
    } else if (MyComment == 'none') {
        $('#MyComment').css('display', 'flex');
    }
}

function commentBar() {
    var img = $("#commentBar").attr('src');
    console.log("123");
    if (img == '/static/image/commentBarA.png') {
        $("#commentBar").attr('src', '/static/image/commentBarB.png');
        $('#howViewComment').css('display', 'flex');
    } else if (img == '/static/image/commentBarB.png') {
        $("#commentBar").attr('src', '/static/image/commentBarA.png');
        $('#howViewComment').css('display', 'none');
    }
}

function MyVideoToggle() {
    var MyVideoToggle = $('#myPageBtn').css('display');
    if (MyVideoToggle == 'flex') {
        $('#MyVideoToggle').css('background', '#F2F2F2');
        $('#myPageBtn').css('display', 'none');
    } else if (MyVideoToggle == 'none') {
        $('#MyVideoToggle').css('background', '#CECECE');
        $('#myPageBtn').css('display', 'flex');
    }
}

/*****************************************토글?창**************************************/


/**************** textbox 크기조절 ************************************************************/
function updateBtn() {
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

function comment_Description() {
    // textarea 요소를 참조합니다.
    const $comment_Description = $('.comment_Description');

    // textarea 요소의 높이를 자동으로 조정하는 함수입니다.
    function autoResizeTextarea() {
        // 스크롤을 사용하지 않도록 먼저 높이를 설정합니다.
        $comment_Description.css('height', '27px');

        // textarea 요소의 높이를 계산합니다.
        const height = $comment_Description[0].scrollHeight;

        // 계산된 높이를 textarea 요소에 적용합니다.
        $comment_Description.css('height', `${height}px`);
    }

// textarea 요소의 내용이 변경될 때마다 높이를 자동으로 조정합니다.
    $comment_Description.on('input', autoResizeTextarea);

// 페이지 로드시 textarea 요소의 높이를 초기화합니다.
    autoResizeTextarea();
}

function comment_List() {
    // textarea 요소를 참조합니다.
    const $textareaList = $('.comment_List');

    // textarea 요소의 높이를 자동으로 조정하는 함수입니다.
    function autoResizeTextarea() {
        // 스크롤을 사용하지 않도록 먼저 높이를 설정합니다.
        $textareaList.css('height', 'auto');

        // 모든 textarea 요소의 높이를 개별적으로 계산합니다.
        $textareaList.each(function () {
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

function video_Description() {
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

/**************** textbox 크기조절 ************************************************************/
