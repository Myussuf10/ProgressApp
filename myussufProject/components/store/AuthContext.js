import { useState } from "react";
import { createContext } from "react";
import React from "react";
import { login } from "../util/http";
import { useEffect } from "react";

export const AuthContext = createContext({
    token: '',
    isAuthenticated: false,
    role: '',
    email: ' ',
    teacher: {},
    authenticate: (token, role) => { },
    logout: () => { },
    setTeacher: ()=>{},
});

function AuthProvider({children}) {
const [authToken, setAuthToken] = useState('');
const [role, setRole] = useState('');
const [email, setEmail] = useState('');
const [teacherinfo, setTeacherInfo] = useState({
    teacherid: "",
    email: "",
})

function authenticate(token, role, email){
    setAuthToken(token);
    setRole(role);
    setEmail(email);
    setTeacherInfo((info)=>{
        return{
            ...info,
            email:email
        }
    })
};

function logout(){
    setAuthToken(null)
    setRole(null)
    setEmail(null)
};

function setTeacher(teacherinfo){
    setTeacherInfo(teacherinfo)
}

useEffect(()=>{
    
})


async function AuthenticaTe(email, password){
    const response = await login(email, password)
    setAuthToken(response.accestoken)
    setRole(response.userrole)
    setEmail(email)
    setTeacherInfo((info)=>{
        return{
            ...info,
            email:email
        }
    })
    console.log(response)
}

const value = {
    token: authToken,
    isAuthenticated: !!authToken,
    authenticate: authenticate,
    role: role,
    email: email,
    logout: logout,
    setTeacher: setTeacher
}; 
    
    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export default AuthProvider;