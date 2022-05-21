let duration=document.querySelector('#duration')
let startTime=document.querySelector("#start-time")
let endTime=document.querySelector("#end-time")
let total=document.querySelector(".schedule-body p")
let newSchedule=document.querySelector('.btn.new')
let sb=document.querySelector('.schedule-box')
let cl=document.querySelector(".schedule-box .btn-group1 button[type='button']")
let basicForm=document.getElementById('basic-info')
newSchedule.addEventListener('click',()=>{
    let start=document.querySelector("select[name='sc-branch']")
    async function abc()
    {
        let res=await fetch('/subjectRest/branches')
        let result=await res.json()
        let s="<option value=''>--select--</option>"
        for (let i=0;i<result.length;i++)
        {
            s+=`<option value="${result[i].slNo}">${result[i].branchName}</option>`;
        }
        start.innerHTML=s
        start.addEventListener('change',register)
    }
    abc()
    sb.style.display="block"
})
cl.addEventListener('click',()=>sb.style.display="none")
duration.addEventListener('change',durationFunction)
startTime.addEventListener('change',durationFunction)
endTime.addEventListener('change',durationFunction)
function durationFunction() {
    let v=duration.value;
    if(v!=="")
    {
        let st=startTime.value;
        let et=endTime.value;
        let tt= (((et - st) * 60) / parseInt(v) ).toFixed(0)
        total.innerText=tt
    }
}
basicForm.addEventListener('submit',basicFormFunction);
function basicFormFunction(e){
    e.preventDefault()
    let tot=this.querySelector('p').innerText
    if(tot!=="0")
    {
        let fd=new FormData(this);
        async function create()
        {
            let res=await fetch(`/scheduleRest/schedule/${tot}`,{method:"POST",body:fd})
            let r=await  res.json();
            console.log(r)
            let scHead=document.querySelector(".time-table-content table thead tr");
            let s=`<th>Day</th>`
            for (let i=0;i<r.classes[0].classTimes.length;i++)
            {
                s+=`<th>${r.classes[0].classTimes[i].startTime}
                    - ${r.classes[0].classTimes[i].endTime}</th>`;
            }
            let scbody=document.querySelector(".time-table-content table tbody")

            for(let i=0;i<r.classes.length;i++)
            {
                s1=`<td>${r.classes[i].day}</td>`;
                for(let j=0;j<r.classes[i].classTimes.length;j++)
                {
                    let co=r.classes[i].classTimes[j].course
                    // if(i>0)

                    if(co !== null) {
                        console.log(co.teacher)
                        s1 += `<td>${co.subject.subjectName}<br> /*${co.teacher.teacherName}*/</td>`
                    }else{
                        s1+=`<td>Launch</td>`;
                    }
                }
                let ttr=document.createElement('tr')
                ttr.innerHTML=s1;
                scbody.append(ttr);
            }
            scHead.innerHTML=s

            document.querySelector('.schedule-box').style.animation="animateTop 1s forwards linear"
            document.querySelector('.time-table-box').style.animation= "animateTimeTable 1s forwards linear";
        }
        create()
    }
}
function register(a) {
    let end=document.querySelector("select[name='sc-semester']")
    let s="<option value=''>--select--</option>"
    if(a.target.value!==""){

        async function abc() {
            let r=await fetch(`/subjectRest/semester/${a.target.value}`)
            let result=await r.json()
            for (let i=0;i<result.length;i++)
            {
                s+=`<option value="${result[i].slNo}">${result[i].semesterName}</option>`;
            }
            end.innerHTML=s
        }
        abc()
    }else{
        end.innerHTML=s
    }
}