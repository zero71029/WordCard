var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");
var mix = 6000;
var canWidth = canvas.width = window.innerWidth;
var canHeight = canvas.height = window.innerHeight;
var x = [];
var y = [];
var mobileX = [];
var mobileY = []
var diX, diy, dist;
//設定點 位置

for (let i = 0; i < 100; i++) {
    x[i] = Math.random() * canWidth;
    y[i] = Math.random() * canHeight;
    mobileX[i] = Math.random() * 2 - 1;//移動量
    mobileY[i] = Math.random() * 2 - 1;
}
// 讀取滑鼠位置
window.onmousemove = function (e) {
    x[100] = e.clientX;
    y[100] = e.clientY;
}
window.onmouseout = () => {
    x[100] = null;
    y[100] = null;
};
mobileX[100] = 0;
mobileY[100] = 0;
draw()

//畫點
function draw() {
    ctx.clearRect(0, 0, canWidth, canHeight);
    for (let i = 0; i < x.length; i++) {
        x[i] += mobileX[i];//移動量
        y[i] += mobileY[i];
        if (x[i] > canWidth || x[i] < 0) mobileX[i] *= -1;//碰掉牆壁反彈
        if (y[i] > canHeight || y[i] < 0) mobileY[i] *= -1

        ctx.fillStyle = "#3591be";
        ctx.fillRect(x[i], y[i], 5, 5)
        //判斷2點距離
        for (let s = i + 1; s < x.length; s++) {
            diX = x[i] - x[s];
            diY = y[i] - y[s];
            dist = diX * diX + diY * diY;
            if (dist < 10000 && x[s] == x[100] && dist >= mix / 2) {
                x[i] -= 0.03 * diX;
                y[i] -= 0.03 * diY;
            }
            //    console.log(dist < mix && i == 100 && dist >= mix / 2);
            //小於mix畫線
            w = (mix - dist) / mix;
            if (dist < mix) {
                ctx.beginPath();
                ctx.strokeStyle = `rgba(53,150,190,${w + 0.2})`;
                ctx.moveTo(x[i], y[i]);
                ctx.lineTo(x[s], y[s]);
                ctx.stroke();
            }
        }
    }
    requestAnimationFrame(draw);
}