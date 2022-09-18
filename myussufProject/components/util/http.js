import axios from "axios";
import { decode as atob, encode as btoa } from 'base-64'
import base64 from "react-native-base64";
import { decode, encode } from 'base-64'
import QueryString from "qs";

if (!global.btoa) { global.btoa = encode }

if (!global.atob) { global.atob = decode }

const BACKEND_URL = "http://10.0.2.2:8080/";

export async function fetchParents(token) {
    const response = await axios.get(BACKEND_URL + "management/parents", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    console.log(response)
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

export async function fetchTeacher(token) {
    const response = await axios.get(BACKEND_URL + `/management/teacher/all`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    const teachers = []
    for (const x in response.data) {
        const teacher = {
            id: response.data[x].id,
            name: response.data[x].firstname + " " + response.data[x].lastname

        }; teachers.push(teacher);

    } return teachers;
}

export async function fetchSubjects(token) {
    const response = await axios.get(BACKEND_URL + "management/subjects", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    const subjects = {}
    for (const x in response.data) {
        const subjectid = response.data[x].id;
        const subjectname = response.data[x].subjectname
        subjects[subjectid] = subjectname;
    }

    return subjects;
}

export function signUpTeacher(teacherInfo, token) {
    axios.post(BACKEND_URL + 'management/teacher', teacherInfo, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
}

export function storeParents(parentsInfo, token) {
    axios.post(BACKEND_URL + 'management/parent', parentsInfo, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
}

// export async function getClasInfo(sid, token) {
//     const response = await axios.get(BACKEND_URL + `class/subject/${sid}`, {
//         headers: {
//             "Authorization": `Bearer ${token}`
//         }
//     });

//     return response;

// }
export async function storeStudent(parentid, info, token) {
    const resp = await axios.post(BACKEND_URL + `management/student/${parentid}`, info, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    const studid = resp.data;
    return studid
}
export function setClass(subjectid, info, token) {
    axios.post(BACKEND_URL + `management/class/${subjectid}`, info, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
}


export async function addSubject(subjectinfo, teacherid, token) {
    const response = axios.post(BACKEND_URL + `management/subject/${teacherid}`, subjectinfo, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response;
}

export async function fetchTeacherSubject(teacherid, token) {
    const response = await axios.get(BACKEND_URL + `teacher/${teacherid}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    console.log("TOKEN") 
    console.log(token)  
    console.log(typeof(token))

    return response.data.subjects;
}

export async function getTeacherByEmail(email, token) {
    const response = await axios.get(BACKEND_URL + `teacher/email/${email}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response.data
}

export async function getParentByEmail(email, token) {
    const response = await axios.get(BACKEND_URL + `parent/parent/${email}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response.data
}

export async function getAdminByEmail(email, token) {
    const response = await axios.get(BACKEND_URL + `management/admin/${email}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response.data
}

export async function getStudentPerSubject(subject, token) {
    const response = await axios.get(BACKEND_URL + `teacher/students/${subject}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response;
}

export async function login(email, password) {
    let body = { email: email, password: password }
    let x = QueryString.stringify(body)
    const response = await axios.post(BACKEND_URL + `/login`, x);
    return response.headers;
}

export async function fetchStudentsWithSubject(subject, token) {

    // const x = 'hello123'
    // const y = 'myussuf1988@gmail.com'
    const response = await axios.get(BACKEND_URL + "adminteacher/students", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    const subjects = [];
    for (const x in response.data) {
        const student = {
            id: response.data[x].id,
            name: response.data[x].firstname + " " + response.data[x].lastname,
            school: response.data[x].school
        }
        subjects.push(student)
    }
    console.log(response.data)
    return subjects

}

export async function fetchAllstudents(token) {

    const response = await axios.get(BACKEND_URL + "adminteacher/students", {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });

    return response;
}

export async function fetchStudentsTeacherComment(studentid,token){
    const response = await axios.get(BACKEND_URL + `api/student/${studentid}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    const students = []
    for(const i in response.data)
    

    return response.data;

}

export async function sentComment(studentid, teacherid, message, token) {
    const response = await axios.post(BACKEND_URL + `api/${studentid}/comments/${teacherid}`, message, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response
}

export async function assignSubjectToStudent(subjectid, studentid, token) {
    const response = await axios.patch(BACKEND_URL + `management/student/${subjectid}/${studentid}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response;
}

export async function getClassPerSubject(subjectid, token) {
    const response = await axios.get(BACKEND_URL + `api/subject/${subjectid}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response.data
}

export async function getAttendancePerStudent(studentid, subjectid, token) {
    const response = await axios.get(BACKEND_URL + `parent/attendance/${studentid}/${subjectid}`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    });
    return response.data;
}

export async function setAttendance(classid, data, token) {

    const response = await axios.post(BACKEND_URL + `teacher/attendance/${classid}`, data, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response
}


export async function setProgress(studentid, classid, data, token) {
    const response = await axios.put(BACKEND_URL + `teacher/progress/${studentid}/${classid}`, data, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response;
}


export async function deleteComment(commentid,studentid,token){
    const response = await axios.delete(BACKEND_URL + `api/comments/${commentid}/${studentid}/`, {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    })
    return response;
}




