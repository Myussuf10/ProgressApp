import axios from "axios";

const BACKEND_URL = "http://10.0.2.2:8080/";

export function newStudent(){
axios.post(BACKEND_URL )
}

export async function fetchParents(){
    const response = await axios.get(BACKEND_URL + "/parent/parents");

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
    
    const subjects = []
    for (const x in response.data){
        const subjectname = response.data[x].subjectname;
        const subject = {};
        subject[subjectname] = false
        subjects.push(subject);
    }

    console.log(subjects)
    return subjects;
}

export function storeParents(parentsInfo){
    axios.post(BACKEND_URL + 'parent/parent', parentsInfo);

}