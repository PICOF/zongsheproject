var mon=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
var wkshort=['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
var headdate=document.querySelector('.headcon .date');
var timebase=new Date();
var daynum=document.querySelector('#d');
var k={}
var arrlist
var tasks=$(".arr .content .ablock .task .text")
var timeindex
timebase.setDate(timebase.getDate()-1)
headdate.innerHTML=mon[timebase.getMonth()]+'&nbsp;'+timebase.getFullYear();
var weeklist=document.querySelector('.daycho .day ul');
var weeklistch=null;
function postArr(){
    if (bid) {
        $.post({
            url: "./arr",
            data: {
                bid: bid,
                date: wkshort[(timebase.getDay() + Number(daynum.innerHTML) + 1) % 7],
                data: JSON.stringify(arrlist)
            },
            cache: false,
            dataType: "json",
        })
    }
}
function getArr(){
    if (bid){
        $.get({
            url: "./arr?bid=" + bid+"&date="+wkshort[(timebase.getDay()+Number(daynum.innerHTML)+1)%7],
            success: function (result) {
                var res=JSON.parse(result)
                arrlist=res
                setArr(res)
            }
        })
    }
}
function setArr(arr){
    for (var i=0;i<3;i++){
        tasks.eq(i).find(".staff").html(arr[i].name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+arr[i].posi)
        tasks.eq(i).find(".posi").html(arr[i].ke+"科"+arr[i].room+"诊室")
    }
}
$(".arr .task .more").click(function () {
    if (bid==null){
        alert("并未选取病人！")
        return
    }
    timeindex=$(".arr .task .more").index(this)
    $(".arr .getinfo").css("height","156px")
})
function arrformOut(){
    $(".arr .getinfo").css("height","0")
    cleanArrform()
}
function saveArr(){
    $(".arr .getinfo").css("height","0")
    k.name=$("#name").val()
    k.posi=$("#position").val()
    k.ke=$("#ke").val()
    k.room=$("#room").val()
    tasks.eq(timeindex).find(".staff").html(k.name+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+k.posi)
    tasks.eq(timeindex).find(".posi").html(k.ke+"科"+k.room+"诊室")
    arrlist[timeindex]=k
    postArr()
    cleanArrform()
}
function cleanArrform(){
    $("#name").val("")
    $("#position").val("医生")
    $("#ke").val("")
    $("#room").val("")
}
function putin(callback){
    for(var i=0;i<7;i++){
        var wkd=document.createElement('li');
        var wksp1=document.createElement('span');
        wksp1.className='week';
        wksp1.innerHTML=wkshort[((new Date).getDay()+i)%7];
        var wksp2=document.createElement('span');
        wksp2.className='num';
        wksp2.innerHTML=timebase.getDate(timebase.setDate(timebase.getDate()+1));
        wkd.appendChild(wksp1);
        wkd.appendChild(wksp2);
        weeklist.appendChild(wkd);
    }
    callback();
}
putin(()=>{
    weeklistch=document.querySelectorAll('.daycho .day ul li');
    for(let i=0;i<weeklistch.length;i++){
        weeklistch[i].onclick=function(){
            weeklistch[daynum.innerHTML].className='';
            this.className='n';
            daynum.innerHTML=i;
            getArr()
        }
    }
    colorwkblk();
})
function colorwkblk(){
    weeklistch[daynum.innerHTML].className='n';
}
function nextD(){
    weeklistch[daynum.innerHTML].className='';
    daynum.innerHTML=Number(daynum.innerHTML)>=6?6:Number(daynum.innerHTML)+1;
    colorwkblk();
    getArr()
}
function preD(){
    weeklistch[daynum.innerHTML].className='';
    daynum.innerHTML=Number(daynum.innerHTML)<=0?0:Number(daynum.innerHTML)-1;
    colorwkblk();
    getArr()
}