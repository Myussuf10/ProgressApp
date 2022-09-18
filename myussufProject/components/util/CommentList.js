import { View, Text, TouchableOpacity } from 'react-native'
import React, { useEffect } from 'react'
import { getStudentPerSubject } from './http';
import { useState } from 'react';
import { useContext } from 'react';
import { AuthContext } from '../store/AuthContext';
import { StyleSheet } from 'react-native';
import Comments from '../Forms/Comments';

const CommentList = ({ navigation }) => {
    const [students, setStudents] = useState([])
    const authCtx = useContext(AuthContext);


    useEffect(() => {
        async function getStudents(subjectid, token) {
            const response = await getStudentPerSubject(subjectid, token).catch(err => { console.log(err) });
            setStudents(response.data)
        }
        getStudents(authCtx.subjectid, authCtx.token).catch(err => { console.log(err) })


    }, [])

    function LeaveComment(studentid){
        authCtx.setChild(studentid)
       navigation.navigate(Comments)
    }
    return (

        <View style={{ alignItems: 'center' }}>
            <Text>Mr {authCtx.userInfo.lastname}</Text>

            <View style={{ flexDirection: 'row', }}>
                <Text style={{ fontSize: 22, marginTop: 15 }}>CommentList</Text>
            </View>

            {students.map(student => {
                return (
                    <View key={student.id} style={{ marginTop: 20, marginRight: 100, flexDirection: 'row' }}>
                        <Text style={styles.title}>{student.firstname + " " + student.lastname} </Text>
                        <View style={{ flexDirection: 'row' }}>
                            <TouchableOpacity
                                onPress={()=>LeaveComment(student.id)}
                                style={{ width: 100, backgroundColor: "#608D56", borderRadius: 4, marginRight: -90, paddingTop: 5 }}>
                                <Text style={{ color: "white", textAlign: 'center' }}>Comments</Text>
                            </TouchableOpacity>
                        </View>
                    </View>
                )
            })}
        </View>
    )
}

export default CommentList

const styles = StyleSheet.create({
    title: {
        fontSize: 22,
        flex: 1,
        marginLeft: 10

    },
    items: {

    },
    text: {
        fontSize: 20,
    }
})