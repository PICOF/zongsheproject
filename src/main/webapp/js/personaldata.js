var datat;
var datay;
var nlist=$(".search .list ul")
var wid=localStorage.getItem("wid")
var bid=null
$(document).ready(function(){
    loadP()
    setInterval("loadP()",1000);
    setInterval("getPre()",300000);
});
function getPre(){
    if (bid){
        $.get({
            url: "./pre?bid=" + bid,
            success: function (result) {
                var res=JSON.parse(result)
                datat=JSON.parse(res.tpre)
                datay=JSON.parse(res.ypre)
                preView()
                activeView(JSON.parse(res.active))
            }
        })
    }
}
function loadP(){
    var load=localStorage.getItem("bed")
    if (load){
        var bli=JSON.parse(load)
        nlist.empty()
        createList(bli)
    }
}
function createList(bli){
    for (let i=0;i<bli.length;i++){
        if(bli[i].drid==wid){
            var ch=$("<li></li>").text(bli[i].name)
            ch.click(function(){
                selectf.innerHTML=this.innerHTML;
                plist.style.maxHeight='0';
                arrow.style.transform='rotate(0)';
                bid=bli[i].bid
                getPre()
                getArr()
            })
            nlist.append(ch)
        }
        if (bli[i].name == $("#sel span.text").html()) {
            statusView(bli[i])
            $("#temball").html(bli[i].tem)
            $("#oxyball").html(bli[i].oxygen)
            $(".hb .data .num").html(bli[i].hb)
        }
    }
}
function statusView(b){
    $(".body .choose").css("background-color",b.status)
}
function preView(){
    if ($(".pre .choose .text").html()=="Today"){
        var d=JSON.parse(JSON.stringify(datat))
    }else {
        var d=JSON.parse(JSON.stringify(datay))
    }
    d.unshift(0, 12);
    Array.prototype.splice.apply(data, d);
    pregraph.setOption(option,true)
}
function activeView(b){
    var num=b.length-1
    for (var i=0; i<num; i++){
        actdata[i]=b[i]
    }
    actdata[num].value=b[num]
    actgraph.setOption(option1);
}