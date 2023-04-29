$(function () {

    $('.menubar').click(function () {
        var id = $(this).text();
        console.log(id);
        var checkPasswd = $('#checkPasswd').text();
        if (checkPasswd == 'true' && id != 'Info') {
            if (confirm("페이지 이동 시 변경사항이 저장됩니다.")) {
                document.form1.action = "/update"
                document.form1.submit();
            } else {
                id = 'Info';
            }
        }
        showContent(id);
    });
    // textarea 요소를 참조합니다.
    const $textarea = $('#description');

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

    //모달창
    $('.textArea1 > label + textarea')
        .on('focus focusout', function () {
            var isFocusIn = event.type === 'focus';
            $('.textArea1').css({
                'color': isFocusIn ? '#065FD4' : '#212121',
                'box-shadow': isFocusIn ? '0 0 4px #065FD4' : '0 0 4px #CCCCCC'
            });
        });
    $('.textArea2 > label + textarea')
        .on('focus focusout', function () {
            var isFocusIn = event.type === 'focus';
            $('.textArea2').css({
                'color': isFocusIn ? '#065FD4' : '#212121',
                'box-shadow': isFocusIn ? '0 0 4px #065FD4' : '0 0 4px #CCCCCC'
            });
        });
    $('#video').change(function () {
        var names = [];
        for (var i = 0; i < $(this)[0].files.length; i++) {
            names.push($(this)[0].files[i].name);
        }
        $('#fileName').text(names.join(', '));
    });


    $("#btnSave").click(function (e) {
        if ($('#videoName').val() == "") {
            alert("제목을 입력하세요");
        } else if ($('#videoContent').val() == "") {
            alert("내용을 입력하세요");
        } else if ($('#video').val() == "") {
            alert("동영상을 업로드하세요");
        } else {
            e.preventDefault();
            insert();
        }
    });

});

//bbs content
function showContent(id) {
    $('#bbs_content1 > div').css('display', 'none');
    $('#' + id + 'Content').css('display', 'flex');
}

function changeInfo() {
    location.href = "/bbsUpdate";
}

//모달창
function openFrom() {
    var Form = $("input[id='upload'] + label + div");
    if (Form.css('display') == 'none') {
        Form.css('display', 'flex');
    }
    $('#video').val("").empty();
    $('#SumNameImg > input[type="file"]').val("").empty();
    $('#fileName').text("파일을 입력해주세요");
    $('.textArea1 > label + textarea').val("");
    $('.textArea2 > label + textarea').val("");
}

function deleteVideo(event) {
    var deleteVideo = $(event.currentTarget).find('#deleteVideo').css('display');
    if (deleteVideo == 'flex') {
        $(event.currentTarget).css('background', 'white');
        $(event.currentTarget).find('#deleteVideo').css('display', 'none');
    } else if (deleteVideo == 'none') {
        $(event.currentTarget).css('background', '#CECECE');
        $(event.currentTarget).find('#deleteVideo').css('display', 'flex');
    }
}

function closeForm() {
    var Form = $("input[id='upload'] + label + div");
    if (Form.css('display') == 'flex' && confirm("창을 닫으면 작업내용을 다시 입력해야합니다.")) {
        Form.css('display', 'none');
    }
}

function deleteR(a) {
    console.log('video_UID:' + a);
    if (confirm('삭제하시겠습니까?')) {
        var video_UID = a;
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
                success: function () {
                    $("#loading").hide(); // 로딩 표시 표시
                    $("#backLoad").hide();
                    window.location.href = "/MyPage";
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

function insert() {
    $("#loading").show(); // 로딩 표시 표시
    $("#backLoad").show();
    var formData = new FormData();
    var videoName = $('#videoName').val();
    var videoContent = $('#videoContent').val();
    var video = $('#video')[0].files[0];
    var SumNImg = $('#SumNImg')[0].files[0];


    formData.append('video', video);
    formData.append('SumNImg', SumNImg);
    formData.append('videoName', videoName);
    formData.append('videoContent', videoContent);

    $.ajax({
        type: "Post",
        url: "/uploadVideo",
        data: formData,
        processData: false,
        contentType: false,
        success: function (result) {
            $("#loading").hide(); // 로딩 표시 표시
            $("#backLoad").hide();
            $('#videoName').val("");
            $('#videoContent').val("");
            $('#video').val("").empty();
            $('#SumNImg').val("").empty();
            $("input[id='upload'] + label + div").css('display', 'none');
            window.location.href = "/videoPage";
        },
        error: function () {
            $("#loading").hide(); // 로딩 표시 표시
            $("#backLoad").hide();
            console.log("insert 실패");
        }
    });

}
