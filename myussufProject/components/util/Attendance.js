import { StyleSheet, Text, TouchableOpacity, View } from 'react-native'
import React, { useEffect } from 'react'
import { useState } from 'react'
import { Avatar, CheckBox } from '@rneui/base';
import { useContext } from 'react';
import { AuthContext } from '../store/AuthContext';
import { fetchStudentsWithSubject } from './http';
import BouncyCheckbox from 'react-native-bouncy-checkbox';

const Attendance = ({ navigation }) => {
    const [students, setStudents] = useState([]);
    const [selected, setSelected] = useState(false)
    const [attend, setAttend] = useState({})
    const authCtx = useContext(AuthContext)


    useEffect(() => {
        async function getStudents() {
            const response = await fetchStudentsWithSubject();
            setStudents(response)
        }
        getStudents().catch(err => { console.log(err) })
        for(const x in students){
            setAttend(currentinput => {
                return {
                    ...currentinput,
                    [students[x].id]: false
                }
            })
        }
        console.log(attend)
    }, [])

    function submitAttendance(bool, studentid) {
            
        setAttend(currentinput => {
            return {
                ...currentinput,
                [studentid]: bool
            }
        })
        console.log(attend)
    }

    return (
        <><View style={{ alignItems: 'center' }}>
            <Text>Mr {authCtx.userInfo.lastname}</Text>

            <View style={{ flexDirection: 'row', marginLeft: 260 }}>
                <Text style={styles.text}>Present/Absent</Text>
            </View>

            {students.map(student => {
                return (
                    <View key={student.id} style={{ flexDirection: 'row' }}>
                        <Text style={styles.title}>{student.name} </Text>
                        <View style={{ marginLeft: 110, flexDirection: 'row' }}>
                            <BouncyCheckbox  onPress={(c => { submitAttendance(c, student.id) })} fillColor='#608D56' style={{ padding: 4 }} />
                        </View>
                    </View>
                )
            })}
            <TouchableOpacity onPress={() => console.log(attend)} style={{
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
    },
    items: {
        flexDirection: "row"

    },
    text: {
        fontSize: 18,
        padding: 10,
    }
})