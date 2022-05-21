let btn=document.querySelector('.add')
let mb=document.querySelector('.modal-box')
let close=document.getElementById('close')
let addForm=document.getElementById('add-form')
let tbody=document.getElementById('show-teacher')
btn.addEventListener('click',e=>{
    mb.style.display="block";
    document.querySelector("input[name='name']").value=""
    counter=1;
    selectBoxDivFun();
})
close.addEventListener('click',e=>mb.style.display="none")
addForm.addEventListener('submit',addFormFunction)
function addFormFunction(e) {
    e.preventDefault()
    let fd=new FormData(addForm);
    async function abc()
    {
        let res=await fetch(`/teacherRest/teacher/${counter}`,{method:"POST",body:fd})
        let rs=await res.json();
        let tr=document.createElement('tr')
        let td1=document.createElement('td')
        let td2=td1.cloneNode();
        let td3=td2.cloneNode();
        td1.innerText=rs.teacherName;
        let d="";
        for(let j=0;j<rs.courses.length;j++)
        {
            d+=(rs.courses[j].subject.subjectName
                +" { "+ rs.courses[j].subject.semester.semesterName
                +" sem , "+rs.courses[j].subject.semester.branch.branchName+" }<br>")
        }
        td2.innerHTML=d
        tr.append(td1,td2)
        tbody.append(tr)
        mb.style.display="none";
    }
    abc()
}