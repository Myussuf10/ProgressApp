import axios from "axios";

const BACKEND_URL = "http://10.0.2.2:8080/";

export function newStudent(){
axios.post(BACKEND_URL )
}

export async function fetchParents(){
    const response = await axios.get(BACKEND_URL + "/parent/parents");

    const parents = []
    for (const x in response.data){
        const parentObj = {
            id: response.data[x].id,
            firstname: response.data[x].firstname,
            lastname: response.data[x].lastname,
                   
        };
        parents.push(parentObj);
    }

    return parents;
}

export async function fetchSubjects(){
    const response = await axios.get(BACKEND_URL + "/subject/subjects");

    const subjects = []
    for (const x in response.data){
        const subjectO = {
            id: response.data[x].id,
            subjectname: response.data[x].subjectname,
                  
        };
        subjects.push(subjectO);
    }

    console.log(subjects)
    return subjects;
}