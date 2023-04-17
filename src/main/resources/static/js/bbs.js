$(function(){
    $('.menubar').click(function(){
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
});

//bbs content
function showContent(id){
    $('#bbs_content1 > div').css('display','none');
    $('#' + id + 'Content').css('display','flex');
}

//bbs changeInfo
function changeInfo(){
    location.href="/bbsUpdate";
}
