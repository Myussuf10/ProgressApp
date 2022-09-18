import RNDateTimePicker from '@react-native-community/datetimepicker'
import { Button, Header } from '@rneui/themed'
import { fonts } from '@rneui/themed/dist/config'
import React, { useEffect } from 'react'
import { useContext } from 'react'
import { useState } from 'react'
import { StyleSheet, View, Text } from 'react-native'
import { KeyboardAvoidingView } from 'react-native'
import DatePicker from 'react-native-date-picker'
import { TextInput, TouchableOpacity } from 'react-native-gesture-handler'
import SelectDropdown from 'react-native-select-dropdown'
import Input from './Forms/Input'
import { AuthContext } from './store/AuthContext'
import { getKeyByValue } from './util/helperFunctions'
import {assignSubjectToStudent, fetchAllstudents, fetchSubjects } from './util/http'

const SubjectStudent = ({ navigation }) => {
    const [subjects, setSubjects] = useState({})
    const [chosenSub, setChosenSub] = useState()
    const [studentId, setStudentId] = useState()
    const [students, setStudents] = useState({})
    const subjectlist = [];
    const studentlist = [];
    const authCtx = useContext(AuthContext)

    useEffect(() => {
        async function getSubjects(token) {
            const subject = await fetchSubjects(token).catch((err) => { console.log(err) });
            const response = await fetchAllstudents(token).catch(err => console.log(err));
            const x = {}
            for (const i in response.data) {
                const studentid = response.data[i].id;
                const studentname = response.data[i].firstname + " " + response.data[i].lastname
                x[studentid] = studentname;
            }
            setStudents(x)
            setSubjects(subject)
        }
        getSubjects(authCtx.token);



    }, [])
    for (const i in subjects) {
        subjectlist.push(subjects[i])
    }
    for (const i in students) {
        studentlist.push(students[i])
    }

    const Confirm = () => {
        const sub = parseInt(chosenSub);
        const stud = parseInt(studentId)
        assignSubjectToStudent(chosenSub,studentId,authCtx.token).catch(err =>console.log(err))
        //navigation.pop()
        console.log(sub)
    }

    return (
        <KeyboardAvoidingView
            keyboardVerticalOffset={Header.HEIGHT} // adjust the value here if you need more padding
            style={{ flex: 1, backgroundColor: '#B4B4B4', alignItems: 'center' }}
            behavior="padding" >


            <Text style={styles.header}> Assign Subject to student</Text>
            <View style={{ marginTop: 40 }}>
                <SelectDropdown style={styles.box} data={subjectlist} defaultButtonText={"Select Subject"} onSelect={(x) => { setChosenSub(getKeyByValue(subjects, x)) }} search={true} />
            </View>
            <View style={{ marginTop: 10 }}>
                <SelectDropdown style={styles.box} data={studentlist} defaultButtonText={"Select Student"} onSelect={(x) => { setStudentId(getKeyByValue(students, x)) }} search={true} />
            </View>

            <TouchableOpacity onPress={() => { Confirm() }} style={styles.confirm}>
                <Text style={styles.txt}> Confirm</Text>
            </TouchableOpacity>


        </KeyboardAvoidingView>
    )
}

export default SubjectStudent

const styles = StyleSheet.create({
    header: {
        fontSize: 30,
        textAlign: 'center',
        margin: 8
    },
    txt: {
        textAlign: 'center',
        fontSize: 20,

    },


    confirm: {
        backgroundColor: '#608d56',
        borderRadius: 5,
        padding: 10,
        marginTop: 10,
        width: 207,
    },

})