$(function () {
    $('#btnMenu').click(function () {
        var wid = $("#navbar").css('width');
        if (wid == "220px") {
            $('.itembox_n1 #banner').css('height', "230px");
            $('#navbar').css('width', "70px");
            $('#content').css('margin-left', "90px");
            $('#navbar').css('padding', "12px 2px 12px 2px");
            $('.navbar-items').attr('class', 'navbar-items d-flex-dc w-full');
            $('.navbar-items > div:nth-of-type(1)').attr('class', 'material-icons w-full div-i-off');
            $('.navbar-items > div:nth-of-type(2)').attr('class', 'w-full div-text-off');
        } else {
            $('.itembox_n1 #banner').css('height', "200px");
            $('#navbar').css('width', "220px");
            $('#content').css('margin-left', "240px");
            $('#navbar').css('padding', "12px");
            $('.navbar-items').attr('class', 'navbar-items d-flex-dr w-full');
            $('.navbar-items > div:nth-of-type(1)').attr('class', 'material-icons w-full div-i-on');
            $('.navbar-items > div:nth-of-type(2)').attr('class', 'w-full div-text-on');
        }
    });

    $('#navbar > .navbar-items:nth-of-type(2)').click(function () {
        var display = $('#box').css('display');
        if (display == 'block')
            $('#box').css('display', 'none');
        else
            $('#box').css('display', 'block');
    });

    $('#btnHome').click(function () {
        location.href = "/";
    });
    $('#btnLogin').click(function () {
        location.href = "/login";
    });
    $('#btnLogout').click(function () {
        location.href = "/logout";
    });
    $('#btnSingup').click(function () {
        location.href = "/singUp";
    });
    $('.itemsss').click(function () {
        location.href = "/bbs";
    });

    category('a');
});

function category(x) {
    var category;
    if (x == 'a') {
        $('#category > div:nth-of-type(1) input').css('background-color', '#060606');
        $('#category > div:nth-of-type(1) input').css('color', 'white');
        $('#category > div:nth-of-type(2) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(2) input').css('color', 'black');
        $('#category > div:nth-of-type(3) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(3) input').css('color', 'black');
        $('#category > div:nth-of-type(4) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(4) input').css('color', 'black');
        category = 'resent';
    }if (x == 'o') {
        $('#category > div:nth-of-type(2) input').css('background-color', '#060606');
        $('#category > div:nth-of-type(2) input').css('color', 'white');
        $('#category > div:nth-of-type(1) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(1) input').css('color', 'black');
        $('#category > div:nth-of-type(3) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(3) input').css('color', 'black');
        $('#category > div:nth-of-type(4) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(4) input').css('color', 'black');
        category = 'old';
    }
    if (x == 'p') {
        $('#category > div:nth-of-type(3) input').css('background-color', '#060606');
        $('#category > div:nth-of-type(3) input').css('color', 'white');
        $('#category > div:nth-of-type(1) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(1) input').css('color', 'black');
        $('#category > div:nth-of-type(2) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(2) input').css('color', 'black');
        $('#category > div:nth-of-type(4) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(4) input').css('color', 'black');
        category = 'popular';
    }
    if (x == 's') {
        $('#category > div:nth-of-type(4) input').css('background-color', '#060606');
        $('#category > div:nth-of-type(4) input').css('color', 'white');
        $('#category > div:nth-of-type(2) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(2) input').css('color', 'black');
        $('#category > div:nth-of-type(1) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(1) input').css('color', 'black');
        $('#category > div:nth-of-type(3) input').css('background-color', '#F2F2F2');
        $('#category > div:nth-of-type(3) input').css('color', 'black');
        category = 'view';
    }
    const formData = new FormData();
    formData.append("category", category);
    $.ajax({
        method: 'post',
        data: formData,
        contentType: false,
        processData: false,
        url: '/listview',
        success: function (result) {
            setTimeout(() => {
                $('#listView').html(result);
            }, 500);
        }
    })
}

function myPage() {
    location.href = "/MyPage";
}

function btnSearch() {
    let search = $('input[id=search]').val();
    if (search != "") {
        const formDate = new FormData();
        formDate.append("search", search);
        $.ajax({
            method: 'post',
            data: formDate,
            contentType: false,
            processData: false,
            url: '/searchPage',
            success : function (result){
                $('#mainD_content').html(result);
            }
        });
    }
}
