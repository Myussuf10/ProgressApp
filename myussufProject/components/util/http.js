import axios from "axios";

const BACKEND_URL = "http://10.0.2.2:8080/parent/";

export async function fetchParents(){
    const response = await axios.get(BACKEND_URL + "/parents");

    const parents = []
    for (const x in response.data){
        const parentObj = {
            id: response.data[x].id,
            firstname: response.data[x].firstname,
            lastname: response.data[x].lastname,
                   
        };
        parents.push(parentObj);
    }

    console.log(parents)
    return parents;
}