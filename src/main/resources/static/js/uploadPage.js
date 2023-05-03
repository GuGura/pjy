$(function () {
    video_Description();
    comment_Description();
    comment_List();
    updateBtn();
    $(".MyCommentBtn").click(MyCommentToggle);
    isChecks();
    isVideoLike();
});

function VideoLike(){
    var userid = $('#userID').text();
    console.log(userid);
    if(userid == ""){
        location.href="/login";
    }else{
        let videoLike_lottie = $('#videoLike')[0]['_lottie'];
        console.dir(videoLike_lottie);
        let VideoLike_Checked = $('#videoLike').next("#videoLikeCheck")[0].checked;
        console.log(VideoLike_Checked);
        const formData = new FormData();
        formData.append("checked",VideoLike_Checked);
        $.ajax({
            method:'post',
            data : formData,
            contentType : false,
            processData : false,
            url : "/video/like",
            success : function (result){
                console.dir(result);
                if(result.like == false){
                    $('#videoLikeCnt').text('좋아요: '+result.likeCnt);
                    videoLike_lottie.playSegments([0,25],true);
                    $('#videoLike').next("#videoLikeCheck")[0].checked = true;
                }else{
                    $('#videoLikeCnt').text('좋아요: '+result.likeCnt);
                    videoLike_lottie.playSegments([22,0],true);
                    $('#videoLike').next("#videoLikeCheck")[0].checked = false;
                }
            }
        })
    }
}

function commentLike(event,id) {
    event.stopPropagation();
    var userid = $('#userID').text();
    console.log(userid);
    if(userid == ""){
        location.href="/login";
    }
    else {
        const formData = new FormData();
        let $checkbox = $(event.currentTarget).next('input[id^=checkLike-]');
        let commentCnt = $(event.currentTarget).next('input[id^=checkLike-]').next('[id^=commentCnt-]');
        let checked = $checkbox.prop('checked');
        let lottie = $('#'+id)[0]['_lottie'];
        const middleFrame = 30;
       // console.dir(lottie);
        // 로띠 애니메이션 가져오기
        formData.append("checked",checked);
        formData.append("comment_UIDs",id);
        $.ajax({
            method:'post',
            data: formData,
            contentType: false, // 추가
            processData: false, // 추가
            url : "/comment/like",
            success :function (result){
                console.dir(result);
                if (result.like) {
                    // 애니메이션 프레임 0
                    commentCnt.text(result.likeCnt);
                   // console.log(commentCnt.text());
                    lottie.playSegments([middleFrame, 0],true);
                    $checkbox.prop('checked', false);
                } else {
                    // 애니메이션 재생
                    commentCnt.text(result.likeCnt);
                    //console.log(commentCnt.text());
                    lottie.playSegments([0, middleFrame],true);
                    $checkbox.prop('checked', true);
                }
                //const commentList = $('#videoComment');
                //commentList.html(result);
            }
        });
    }
}

function updateText(a) {
    $("#loading1").css('display', 'flex');
    const Video_comment = $(a).val();
    const formData = new FormData();
    formData.append('Video_comment', Video_comment);
    setTimeout(function () {
        $.ajax({
            method: 'post',
            data: formData,
            contentType: false, // 추가
            processData: false, // 추가
            url: "/updateComment",
            success: function (result) {
                const commentList = $('#videoComment');
                commentList.html(result);
                console.dir(result);
                $("#loading1").hide(); // 로딩 표시 표시

                setTimeout(()=>{
                    isChecks();
                    updateBtn();
                },500);
            },
            error: function (result) {
                alert("실패");
                console.log(result);
                $("#loading1").hide(); // 로딩 표시 표시
            }
        })
    }, 1000)
}
function nextTime(unimplemented){
    var implemented = unimplemented + '구현 예정';
    alert(implemented)
}
function deleteC(a) {
    console.log('comment_UID:' + a);
    if (confirm('삭제하시겠습니까?')) {
        $("#loading1").css('display', 'flex');
        const comment_UID = a;
        const formData = new FormData();
        formData.append("comment_UID", comment_UID);
        setTimeout(function () {
            $.ajax({
                method: 'post',
                data: formData,
                url: "/CommentDelete",
                contentType: false, // 추가
                processData: false, // 추가
                success : function (result){
                    const commentList = $('#videoComment');
                    commentList.html(result);
                    setTimeout(()=>{
                        isChecks();
                        updateBtn();
                    },500);
                },
                error : function (result){
                    console.log(result);
                    $("#loading1").hide(); // 로딩 표시 표시
                }
            });
        }, 1000)
    }
}

function viewDelete(a) {
    console.log('video_UID:' + a);
    if (confirm('삭제하시겠습니까?')) {
        const video_UID = a;
        const formData = new FormData();
        formData.append("video_UID", video_UID);
        $("#loading").show(); // 로딩 표시 표시
        $("#backLoad").show();
        setTimeout(function () {
            $.ajax({
                contentType: false, // 추가
                processData: false, // 추가
                data: formData,
                method: 'post',
                url: '/deleteVideo',
                success: function (result) {
                    console.log(result);
                    $("#loading").hide(); // 로딩 표시 표시
                    $("#backLoad").hide();
                    location.href = "/";
                },
                error: function (result) {
                    console.log(result)
                    $("#loading").hide(); // 로딩 표시 표시
                    $("#backLoad").hide();
                    alert("삭제 실패");

                }
            })
        }, 2000);
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
function MyCommentToggle(event) {
    const MyComment = $(event.currentTarget).find('.MyComment').css('display');
    const MyComments = $(event.currentTarget).find('.MyComment');

    MyComments.click(function (event) {
        event.stopPropagation(); // 하위 엘리먼트 클릭 시 상위 엘리먼트로 이벤트 전파 방지
    });

    if (MyComment == 'none') {
        MyComments.css('display', 'flex');
        $('#background').show();
    }
    $(function () {
        background(MyComments);
    });
}

function background(Window) {
    $('#background').click(function () {
        if (Window.css('display') == 'flex') {
            Window.css('display', 'none');
            $('#background').hide();
            if (Window[0] == $('#myPageBtn')[0]) {
                $('#MyVideoToggle').css('background', '#F2F2F2');
            }
        }
    });

}

function commentBar() {
    var img = $("#commentBar").attr('src');
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
    if (MyVideoToggle == 'none') {
        $('#MyVideoToggle').css('background', '#CECECE');
        $('#myPageBtn').css('display', 'flex');
        $('#background').show();
    }
    $(function () {
        background($('#myPageBtn'));
    });
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
        $textareaList.css('height', '27px');

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

//----------------------------Lottie html 랜더링 후 첫상태------------------------------
//댓글
function isChecks(){
    var lottieId = $('[id^=lottie-like-]');
    var lottieD = $('[id^=lottie-like-]').next('input[id^=checkLike-]');
    for(let i = 0 ; i<lottieId.length ; i++){
        var lottieA = lottieId[i]['_lottie'];
        var ischeck = lottieD[i].checked;
        if(ischeck == true){
            lottieA.playSegments([25,30],true);
        }
    }
}
//비디오
function isVideoLike(){
    let videoLike_lottie = $('#videoLike')[0]['_lottie'];
    console.dir(videoLike_lottie);
    let VideoLike_Checked = $('#videoLike').next("#videoLikeCheck")[0].checked;
    console.log(VideoLike_Checked);
    if (VideoLike_Checked == true){
        videoLike_lottie.playSegments([24,25],true);
    }
}
//----------------------------Lottie html 랜더링 후 첫상태------------------------------