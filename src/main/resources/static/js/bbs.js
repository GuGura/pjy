$(function () {
    $('.menubar').click(function () {
        var id = $(this).text();
        console.log(id);
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
                'border': isFocusIn ? '1px solid #065FD4' : '1px solid #CCCCCC'
            });
        });
    $('.textArea2 > label + textarea')
        .on('focus focusout', function () {
            var isFocusIn = event.type === 'focus';
            $('.textArea2').css({
                'color': isFocusIn ? '#065FD4' : '#212121',
                'border': isFocusIn ? '1px solid #065FD4' : '1px solid #CCCCCC'
            });
        });
    $('#video').change(function() {
        var names = [];
        for (var i = 0; i < $(this)[0].files.length; i++) {
            names.push($(this)[0].files[i].name);
        }
        $('#fileName').text(names.join(', '));
    });

    $('input[type="radio"][name="sumName"]').on('change', function() {
        if ($(this).val() === 'choose') {
            $('#SumNameImg > input[type="file"]').val("").empty();
            $('#SumNameImg').css('display', 'flex');
        } else {
            $('#SumNameImg').css('display', 'none');
        }
    });
    $("#btnSave").click(function() {
        if ($('#videoName').val()==""){
            alert("제목을 입력하세요");
        }else if($('#videoContent').val()==""){
            alert("내용을 입력하세요");
        }else if($('#video').val() == ""){
            alert("동영상을 업로드하세요");
        }else{
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
    $('.textArea1 > label + textarea').val("");
    $('.textArea2 > label + textarea').val("");
}

function closeForm() {
    var Form = $("input[id='upload'] + label + div");
    if (Form.css('display') == 'flex' && confirm("창을 닫으면 작업내용을 다시 입력해야합니다.")) {
        Form.css('display', 'none');
    }
}
function insert(){
    var formData = new FormData();
    var videoName = $('#videoName').val();
    var videoContent = $('#videoContent').val();
    var video = $('#video')[0].files[0];
    var SumNImg = $('#SumNImg')[0].files[0];


    formData.append('video',video);
    formData.append('SumNImg',SumNImg);
    formData.append('videoName',videoName);
    formData.append('videoContent',videoContent);
    $.ajax({
        type: "POST",
        url : "/uploadVideo",
        data : formData,
        processData: false,
        contentType: false,
        success : function(){
          //  list(); //회원목록 갱신
            $('#videoName').val("");
            $('#videoContent').val("");
            $('#video').val("").empty();
            $('#SumNImg').val("").empty();
            $("input[id='upload'] + label + div").css('display', 'none');
        },
        error : function (){
            console.log("insert 실패");
        }
    });
}