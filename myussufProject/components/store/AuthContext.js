import { useState } from "react";
import { createContext } from "react";
import React from "react";

export const AuthContext = createContext({
    token: '',
    isAuthenticated: false,
    role: '',
    email: ' ',
    teacher: {},
    authenticate: (token, role) => { },
    logout: () => { },
    setTeacher: ()=>{}
});

function AuthProvider({children}) {
const [authToken, setAuthToken] = useState();
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