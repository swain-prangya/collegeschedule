let counter=1;
let lClick=document.querySelector('.left-arrow')
let rClick=document.querySelector('.right-arrow')
let cc=document.querySelector('.cc')
let selectBoxDiv=document.getElementById('select-box')
lClick.addEventListener('click',counterLeftClick)
rClick.addEventListener('click',counterRightClick)
function counterLeftClick(e) {
    if(counter>1)
    {
       counter--;
       selectBoxDivFun();
    }
}
function counterRightClick(e) {
    if( counter<20)
    {
        counter++;
        selectBoxDivFun()
    }
}
function selectBoxDivFun() {
    cc.innerText=counter;
    let sb=``;
    for(let  i=1;i<=counter;i++)
    {
        sb+=`
                 <div class="grid1">
                    <div class="fg">
                        <label>Select Branch</label>
                        <select name="branch-${i}" class="sb branch">
                            <option value="">--select--</option>
                        </select>
                    </div>
                    <div class="fg">
                        <label>Select Semester</label>
                        <select name="semester-${i}" id="semester" class="sb semester">
                            <option value="">--select--</option>
                        </select>
                    </div>
                    <div class="fg">
                        <label>Select Subject</label>
                        <select name="subject-${i}"  class="sb subject">
                            <option value="">--select--</option>
                        </select>
                    </div>
                </div>
            `;
    }
    selectBoxDiv.innerHTML=sb;
    async function abc() {
        let res=await fetch('/subjectRest/branches')
        let result=await res.json()
        let s="<option value=''>--select--</option>"
        for (let i=0;i<result.length;i++)
        {
            s+=`<option value="${result[i].slNo}">${result[i].branchName}</option>`;
        }
        document.querySelectorAll('.modal-box .branch')
            .forEach(k =>{
                k.innerHTML=s;
                k.addEventListener('change',function (){
                   let s="<option value=''>--select--</option>"
                   let sub= this.parentElement.parentElement.querySelector("select[class='sb subject']")
                   let sem=this.parentElement.parentElement.querySelector("select[class='sb semester']")
                   sub.innerHTML=s
                   let val=this.value;
                   async function xyz()
                   {
                       if(val !=="")
                       {
                           let res = await fetch(`/subjectRest/semester/${val}`)
                           let result = await res.json()
                           for (let i = 0; i < result.length; i++)
                           {
                               s += `<option value="${result[i].slNo}">${result[i].semesterName}</option>`;
                           }
                       }
                       sem.innerHTML=s;
                       sem.addEventListener('change',function (e) {
                                let v=e.target.value;
                                async function ab()
                                {
                                    let s="<option value=''>--select--</option>"
                                    if(v!=="")
                                    {
                                        let res = await fetch(`/subjectRest/subject/${v}`)
                                        let result = await res.json()

                                        for (let i = 0; i < result.length; i++)
                                        {
                                            s += `<option value="${result[i].slNo}">${result[i].subjectName}</option>`;
                                        }
                                    }
                                    sub.innerHTML=s
                                }
                                ab()
                            })
                        }
                        xyz()
                })
            })
    }
    abc()
}