$(function(){
    $('.menubar').click(function(){
        var id = $(this).text();
        console.log(id);
        showContent(id);
    });
});

//bbs content
function showContent(id){
    $('#bbs_content1 > div').css('display','none');
    $('#' + id + 'Content').css('display','flex');
}