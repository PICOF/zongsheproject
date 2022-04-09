var bednum=36
var pagenum=0
var pageindex=0
var conpn=$(".con .pn")
var time=document.querySelector('.msg .figure .time');
var now=new Date();
var bgroup=$(".group ul li span")
var pcount=$(".info .des .num")
var arrange=$(".arrange .figure li")
var surgery=$(".oper .figure li")
initNotice()
var page=$(".page ul li")
$(document).ready(function(){
    bedLoadon()
    setInterval("bedLoadon()",1000);
});
function bedLoadon(){
    var load=localStorage.getItem("bed")
    if(load){
        var bli=JSON.parse(load)
        if(pagenum!=Math.ceil(bli.length/6)){
            pagenum=Math.ceil(bli.length/6)
            conpn.empty()
            for (let i=0;i<pagenum;i++){
                var pt=$("<div>·&nbsp;</div>")
                pt.click(function () {
                    pageindex=i
                    bedLoadon()
                })
                conpn.append(pt)
            }
            if(pageindex>=Math.ceil(bli.length/6)){
                pageindex=Math.ceil(bli.length/6)-1
            }
        }
        var inp=0
        var outp=0
        var o30=0
        var o20=0
        var o10=0
        var l10=0
        bgroup.attr("class","")
        page.attr("class","")
        page.css("opacity","0")
        page.css("height","0")
        page.css("margin-bottom","0")
        arrange.find(".data").empty()
        surgery.find(".data").empty()
        refreshChoose(pageindex)
        for (let i=0;i<bli.length;i++){
            bgroup.eq(bli[i].bid-1).attr("class",bli[i].status)
            var delta=Math.abs(new Date-new Date(bli[i].iodate))/(1000 * 3600 * 24)
            if (bli[i].inuse){
                if (delta>=30){
                    o30++
                }else if (delta>=20){
                    o20++
                }else if (delta>=10){
                    o10++
                }else {
                    l10++
                }
                notice(bli[i],i)
            }
            if(bli[i].arr&1){
                arrange.eq(2).find(".data").append($("<span></span>").text(bli[i].bid.toString().padStart(2,'0')))
            }else if (bli[i].arr&2){
                arrange.eq(1).find(".data").append($("<span></span>").text(bli[i].bid.toString().padStart(2,'0')))
            }else if (bli[i].arr&4){
                arrange.eq(0).find(".data").append($("<span></span>").text(bli[i].bid.toString().padStart(2,'0')))
            }
            if (bli[i].surgery==1){
                surgery.eq(0).find(".data").append($("<span></span>").text(bli[i].bid.toString().padStart(2,'0')))
            }else if (bli[i].surgery==2){
                surgery.eq(1).find(".data").append($("<span></span>").text(bli[i].bid.toString().padStart(2,'0')))
            }else if (bli[i].surgery==3){
                surgery.eq(2).find(".data").append($("<span></span>").text(bli[i].bid.toString().padStart(2,'0')))
            }
            if(bli[i].iodate==now.getFullYear() + "-" + ((now.getMonth() + 1).toString().padStart(2,'0')) + "-" + ((now.getDate()).toString().padStart(2,'0'))){
                if(bli[i].inuse){
                    inp++
                }else {
                    outp++
                    bgroup.eq(bli[i].bid-1).attr("class","")
                }
            }
        }
        if(pcount.eq(1).html()!=inp||pcount.eq(2).html()!=outp){
            pcount.eq(0).html(bli.length-outp)
            pcount.eq(1).html(inp)
            pcount.eq(2).html(outp)
            pcount.eq(3).html(bednum-bli.length+outp)
        }
        info[0].value=o30
        info[1].value=o20
        info[2].value=o10
        info[3].value=l10
    }
    myChart.setOption(option)
}
function initNotice() {
    for (var i=0;i<bednum;i++){
        var li=$("<li><div class='dot'></div><div class='context'><span class='num'>00</span><span class='icon'></span><span class='des'>正在加载中，请稍等</span></div></li>")
        $(".page ul").append(li)
    }
}
function notice(b,i){
    var status=b.status
    page.eq(i).attr("class",status)
    page.eq(i).find(".num").html(b.bid.toString().padStart(2,'0'))
    if (status=="blue"){
        page.eq(i).find(".des").html("病人生命体征稳定")
    }else if (status=="yellow"){
        page.eq(i).find(".des").html("病人处于低危状态")
    }else {
        page.eq(i).find(".des").html("病人处于高危状态")
    }
    if(i>=pageindex*6&&i<(pageindex+1)*6){
        page.eq(i).css("height","44px")
        page.eq(i).css("margin-bottom","14px")
        page.eq(i).css("opacity","1")
    }
}
function preP(){
    if(pageindex>0){
        pageindex--
    }
    bedLoadon()
}
function nextP(){
    if(pageindex<pagenum-1){
        pageindex++
    }
    bedLoadon()
}
function refreshChoose(i) {
    $(".con .pn div").attr("class","")
    $(".con .pn div").eq(i).attr("class","choose")
}
function show(){
    time.innerHTML=now.getFullYear()+"."+(now.getMonth()+1)+"."+now.getDate();
    now=new Date();
}
show();
setInterval("show()",60000);