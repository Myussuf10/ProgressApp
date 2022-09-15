import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useEffect } from 'react'
import { useState } from 'react'
import { Avatar, CheckBox } from '@rneui/base';
import { useContext } from 'react';
import { AuthContext } from '../store/AuthContext';
import { fetchStudentsWithSubject, getStudentPerSubject, setAttendance } from './http';
import BouncyCheckbox from 'react-native-bouncy-checkbox';

const Attendance = ({ navigation }) => {
    const [students, setStudents] = useState([]);
    const [attend, setAttend] = useState([])
    const authCtx = useContext(AuthContext)


    useEffect(() => {
        async function getStudents(subjectid) {
            const response = await getStudentPerSubject(subjectid);
            setStudents(response.data)
        }
        getStudents(authCtx.subjectid).catch(err => { console.log(err) })

        console.log(students)

    }, [])

    function attendance(bool, studentid) {

        setAttend((x) => {
            return {
                ...x,
                [studentid]: bool
            }

        })

    }

    function submitAttendance() {
        for(const i in students){
            if(!(students[i].id in attend)){
                console.log("Not inside" + students[i].id)
                setAttend((x)=>{
                    return{
                        ...x,
                        [students[i].id]: false
                    }
                })
            }
        }
        for(const key in attend){
            if(attend[key]){
                console.log(typeof(parseInt(key)))
                const x = {
                    studentid: parseInt(key)
                }
                console.log(x)
                 setAttendance(authCtx.subjectid, x)
            }
        }

    }

    return (
        <><View style={{ alignItems: 'center' }}>
            <Text>Mr {authCtx.userInfo.lastname}</Text>

            <View style={{ flexDirection: 'row', marginLeft: 250 }}>
                <Text style={styles.text}>Present/Absent</Text>

            </View>

            {students.map(student => {
                return (
                    <View key={student.id} style={{ marginRight: 100, flexDirection: 'row' }}>
                        <Text style={styles.title}>{student.firstname + " " + student.lastname} </Text>
                        <View style={{ flexDirection: 'row' }}>
                            <BouncyCheckbox onPress={(bool) => { attendance(bool, student.id) }} fillColor='#608D56' style={{ marginRight: -150, paddingTop: 5 }} />

                        </View>
                    </View>
                )
            })}
            <TouchableOpacity onPress={() => { submitAttendance() }} style={{
                backgroundColor: '#608d56',
                borderRadius: 2, padding: 3, width: 100, margin: 15, marginLeft: 290
            }}>
                <Text style={{ textAlign: 'center', fontSize: 18 }}>
                    Submit
                </Text>
            </TouchableOpacity>
        </View></>



    )
}

export default Attendance

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