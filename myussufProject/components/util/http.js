import axios from "axios";
import moment from "moment";

const BACKEND_URL = "http://10.0.2.2:8080/";

export async function fetchParents(){
    const response = await axios.get(BACKEND_URL + "/api/parents");

    const parents = []
    for (const x in response.data){
        const parentsO = {
            id: response.data[x].id,
            name: response.data[x].firstname + " " + response.data[x].lastname
                   
        };
        //const parentsO = [response.data[x].id + " - " + response.data[x].firstname + " " + response.data[x].lastname];
        parents.push(parentsO);
    }

    return parents;
}

export async function fetchSubjects(){
    const response = await axios.get(BACKEND_URL + "/subject/subjects");
    
    const subjects = {}
    for (const x in response.data){
        const subjectid = response.data[x].id;
        const subjectname = response.data[x].subjectname
         subjects[subjectid] = subjectname;
    }

    return subjects;
}

export function signUpTeacher(teacherInfo){
    axios.post(BACKEND_URL + 'teacher/teacher', teacherInfo);
}

export function storeParents(parentsInfo){
    axios.post(BACKEND_URL + 'api/parent', parentsInfo);
}

export async function getClasInfo(sid){
    const response = await axios.get(BACKEND_URL + `class/subject/${sid}`);
    [{"dow": "2022-08-07", "id": 13, "time": "22:47"}]
    const dates = [];
    for(const i in response.data){
        dates.push(response.data[i]["dow"]);
    }
    console.log(dates);
   
    return dates;
    
}
export async function storeStudent(parentid, info){
    const resp = await axios.post(BACKEND_URL + `student/student/${parentid}`, info)
    const studid = resp.data;
    return studid
}
export function setClass(subjectid, info){
    axios.post(BACKEND_URL + `class/class/${subjectid}`, info)
}


export function assigSub(subjectid, studentid){
    axios.put(BACKEND_URL + `student/student/${subjectid}/${studentid}`)

}



