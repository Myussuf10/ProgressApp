import { useState } from "react";
import { createContext } from "react";
import React from "react";
import { getAdminByEmail, getParentByEmail, getTeacherByEmail, login } from "../util/http";
import { useEffect } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";

export const AuthContext = createContext({
    token: '',
    isAuthenticated: false,
    role: '',
    email: ' ',
    userInfo: {},
    childId: [],
    authenticate: (token, role, email) => { },
    logout: () => { },
    setChild: ()=>{}
});

function AuthProvider({ children }) {
    const [authToken, setAuthToken] = useState('');
    const [role, setRole] = useState('');
    const [email, setEmail] = useState('');
    const [userInfo, setUserInfo] = useState({})
    const [childId, setChildId] = useState([])

    useEffect(() => {
        async function fetchToken() {
            try {
                const storedToken = await AsyncStorage.getItem('token');
                if (storedToken) {
                    setAuthToken(storedToken)
                }
            } catch (error) {

            }

        }

        fetchToken();
    }, [])

    async function getUser(email) {
        const role = await AsyncStorage.getItem('role')
        console.log("inside gGet User +  " + role)
        try {
            if (role == "ROLE_TEACHER") {
                const teacher = await getTeacherByEmail(email)
                setUserInfo(teacher)
            }
            else if (role == "ROLE_ADMIN") {
                const admin = await getAdminByEmail(email)
                setUserInfo(admin)
            } else if (role == "ROLE_PARENT") {
                const parent = await getParentByEmail(email)
                setUserInfo(parent)
            }

        } catch (error) {
            console.log(error)

        }

    }

    async function authenticate(token, role, email) {
        setAuthToken(token);
        const rol = await AsyncStorage.setItem('role', role)
        const tok = await AsyncStorage.setItem('token', token) 
        setRole(role);
        setEmail(email);
        getUser(email)
    };

    function logout() {
        setAuthToken(null)
        setRole(null)
        setEmail(null)
        AsyncStorage.removeItem('token')
        AsyncStorage.removeItem('role')
    };
    
    function setChild(childid,subjectid){
        setChildId([childid,subjectid])
    }

    const value = {
        token: authToken,
        isAuthenticated: !!authToken,
        authenticate: authenticate,
        role: role,
        email: email,
        logout: logout,
        userInfo: userInfo,
        childId: childId,
        setChild: setChild
    };

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export default AuthProvider;