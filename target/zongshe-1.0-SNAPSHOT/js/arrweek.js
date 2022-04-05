var mon=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
var wkshort=['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
var headdate=document.querySelector('.headcon .date');
var timebase=new Date();
var daynum=document.querySelector('#d');
timebase.setDate(timebase.getDate()-1)
headdate.innerHTML=mon[timebase.getMonth()]+'&nbsp;'+timebase.getFullYear();
var weeklist=document.querySelector('.daycho .day ul');
var weeklistch=null;
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
}
function preD(){
    weeklistch[daynum.innerHTML].className='';
    daynum.innerHTML=Number(daynum.innerHTML)<=0?0:Number(daynum.innerHTML)-1;
    colorwkblk();
}