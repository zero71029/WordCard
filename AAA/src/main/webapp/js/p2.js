var $en = $(".wordName");

//顯示新增單字
function newWord() {
    var modal = document.getElementsByClassName("modal")[0].style.visibility = "visible";
}

//關閉新增單字
//關閉新增單字庫
function bClose() {
    document.getElementsByClassName("modal")[0].style.visibility = "hidden";
    document.getElementsByClassName("PaModal")[0].style.visibility = "hidden";
    document.getElementById("prev").style.visibility = "hidden";
    document.getElementById("next").style.visibility = "hidden";
}

//顯示新增單字庫
function CreatePackage() {
    document.getElementsByClassName("modal")[0].style.visibility = "visible";
    document.getElementById("CreatePackage").action = "/AAA/AddPa";
    var mInput = document.getElementsByClassName("mInput");
    mInput[0].placeholder = "請輸名稱";
    mInput[0].name = "paName";
    mInput[1].value = "確認";
}

//輪播圖
function banner(page) {
    var $list = $(".list");//圖片列表
    var $Word = $("#Word");//字符列表
    var $prev = $("#prev");//上一張
    var $next = $("#next");//下一張
    var targeLeft = -400 - page * 400;//初始位置 /*初始輛*/
    var wordName = document.getElementsByClassName("wordName");
    //定義初始位置
    $list.css("left", targeLeft + "px");
    $Word.css("left", targeLeft + "px");


    $(".PaModal").css("visibility", "visible");

    //左右案件顯示判斷
    var currLeft = targeLeft;
    if (currLeft <= -(wordName.length - 2) * 400) {//
        $next.css("visibility", "hidden");
    } else {
        $next.css("visibility", "visible");
    }
    if (currLeft >= -410) {//跳到最後
        $prev.css("visibility", "hidden");
    } else {
        $prev.css("visibility", "visible");
    }

    //字位置
    for (var i = 0; i < wordName.length; i++) {
        wordName[i].style.left = (/*初始量*/  800 * i) + "px";
    }
    //半段上下按鈕
    $next.click(function () {
        nextPage(true);
    });
    $prev.click(function () {
        nextPage(false);
    });

    function nextPage(next) {
        //只顯示英文
        $en.each(function (index, elm) {
            $(elm).html(`<span>${en[index - 1]}</span><button class='play'; onclick='play(${index - 1})''></button>
               <button class='del' onclick='location.href='/AAA/wordDel?del=${en[index - 1]}'>DEL</button>`)

        });

        //移動
         currLeft = targeLeft;
        var offset = next ? -400 : 400;
        let t = 0;
        //圖案移動
        for(let i =0; i<10 ; i++ )
         setTimeout(() => {
            targeLeft += (offset / 10);
            $list.css("left", targeLeft + "px");
            $Word.css("left", targeLeft + "px");
            i++;

            //左右案件顯示判斷
            if (targeLeft <= -(wordName.length - 2) * 390) {//
                $next.css("visibility", "hidden");
            } else {
                $next.css("visibility", "visible");
            }
            if (targeLeft >= -490) {//跳到最後
                $prev.css("visibility", "hidden");
            } else {
                $prev.css("visibility", "visible");
            }
        }, 10*i);
    }

//中英切換
    var $img = $("img");
    let ch = 0;
    $img.on('click', function () {
        console.log(ch);
        if (ch % 2 == 0) {
            $en.each(function (index, elm) {
                $(elm).html(`<span>${en[index - 1]}</span><button class='play' onclick='play(${index - 1})''></button><br><span>${cn[index - 1]}</span>
                <button class='del' onclick='location.href='/AAA/wordDel?del=${en[index - 1]}'>DEL</button>`);
                ch=1;
            });
        } else {
            $en.each(function (index, elm) {
                $(elm).html(`<span>${en[index - 1]}</span><button class='play' onclick='play(${index - 1})''></button>
                <button class='del' onclick='location.href='/AAA/wordDel?del=${en[index - 1]}'>DEL</button>`);
                ch=0;
            });
        }

    });
}

//播放
var audio = document.getElementsByClassName("audio");
function play(index) {
    audio[index].play();
};




