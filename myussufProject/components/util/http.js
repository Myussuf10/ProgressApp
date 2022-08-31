import axios from "axios";
import { decode as atob, encode as btoa } from 'base-64'
import base64 from "react-native-base64";
import { decode, encode } from 'base-64'
import QueryString from "qs";

if (!global.btoa) { global.btoa = encode }

if (!global.atob) { global.atob = decode }



const BACKEND_URL = "http://10.0.2.2:8080/";

export async function fetchParents() {
    const response = await axios.get(BACKEND_URL + "api/parents");

    const parents = []
    for (const x in response.data) {
        const parentsO = {
            id: response.data[x].id,
            name: response.data[x].firstname + " " + response.data[x].lastname
        };
        parents.push(parentsO);
    }

    return parents;
}

export async function fetchTeacher() {
    const response = await axios.get(BACKEND_URL + "teacher/all");
    const teachers = []
    for (const x in response.data) {
        const teacher = {
            id: response.data[x].id,
            name: response.data[x].firstname + " " + response.data[x].lastname

        }; teachers.push(teacher);

    } return teachers;
}

export async function fetchSubjects() {
    const response = await axios.get(BACKEND_URL + "subject/subjects");

    const subjects = {}
    for (const x in response.data) {
        const subjectid = response.data[x].id;
        const subjectname = response.data[x].subjectname
        subjects[subjectid] = subjectname;
    }

    return subjects;
}

export function signUpTeacher(teacherInfo) {
    axios.post(BACKEND_URL + 'teacher/teacher', teacherInfo);
}

export function storeParents(parentsInfo) {
    axios.post(BACKEND_URL + 'api/parent', parentsInfo);
}

export async function getClasInfo(sid) {
    const response = await axios.get(BACKEND_URL + `class/subject/${sid}`);
    [{ "dow": "2022-08-07", "id": 13, "time": "22:47" }]
    const dates = [];
    for (const i in response.data) {
        dates.push(response.data[i]["dow"]);
    }
    console.log(dates);

    return dates;

}
export async function storeStudent(parentid, info) {
    const resp = await axios.post(BACKEND_URL + `student/student/${parentid}`, info)
    const studid = resp.data;
    return studid
}
export function setClass(subjectid, info) {
    axios.post(BACKEND_URL + `class/class/${subjectid}`, info)
}


export function assigSub(subjectid, studentid) {
    axios.put(BACKEND_URL + `student/student/${subjectid}/${studentid}`)

}

export async function addSubject(subjectinfo, teacherid) {
    const response = axios.post(BACKEND_URL + `subject/subject/${teacherid}`, subjectinfo)
    return response;
}

export async function fetchTeacherSubject(teacherid) {
    const response = await axios.get(BACKEND_URL + `teacher/${teacherid}`)
    const subjects = response.data.subjects.map((element) => element.subjectname)
    return subjects
}

export async function getTeacherByEmail(email){
    const response = await axios.get(BACKEND_URL + `teacher/email/${email}`)
    return response.data
}

export async function login(email, password) {
    let body = {email:email, password:password }
    let x = QueryString.stringify(body)
    const response = await axios.post(BACKEND_URL + `/login`, x);
    return response.headers;
}

export async function fetchStudentsWithSubject(subject) {
    
    const x = 'hello123'
    const y = 'myussuf1988@gmail.com'
    const response = await axios.get(BACKEND_URL + "student/student/students", {
        auth: {
            username: y,
            password: x
        }
    });
    const subjects = [];
    for (const x in response.data) {
        for (const y in response.data[x].subjects) {
            const student = {
                id: response.data[x].id,
                name: response.data[x].firstname + " " + response.data[x].lastname,
                school: response.data[x].school
            }
            subjects.push(student)

        }

    }

    return subjects

}




