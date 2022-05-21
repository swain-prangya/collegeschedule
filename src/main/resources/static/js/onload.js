window.addEventListener('load',loadFunction)
function loadFunction(e) {
    //data retrieve
    async function abc()
    {
        let res=await fetch("/teacherRest/teachers")
        let rs=await res.json()
        for (let i=0;i<rs.length;i++)
        {
            let tr=document.createElement('tr')
            let td1=document.createElement('td')
            let td2=td1.cloneNode();
            let td3=td2.cloneNode();
            td1.innerText=rs[i].teacherName;
            let d="";
            for(let j=0;j<rs[i].courses.length;j++)
            {
                d+=(rs[i].courses[j].subject.subjectName
                    +" { "+ rs[i].courses[j].subject.semester.semesterName
                    +" sem , "+rs[i].courses[j].subject.semester.branch.branchName+" }<br>")
            }
            td2.innerHTML=d
            tr.append(td1,td2)
            tbody.append(tr)
            document.querySelector('figure').classList.add('fo')
        }
    }
    abc()
}