import { Avatar } from '@rneui/base'
import { Button, Header } from '@rneui/themed'
import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { KeyboardAvoidingView, StyleSheet, Text, View } from 'react-native'
import { Agenda, Calendar, CalendarList } from 'react-native-calendars'
import { TouchableOpacity } from 'react-native'
import { Row, Table } from 'react-native-table-component'
import { fetchTeacher, fetchTeacherSubject, getClasInfo, getTeacherByEmail } from './util/http'
import TeachingPage from './util/TeachingPage'
import { Header as Fet } from './Header'
import { useContext } from 'react'
import { AuthContext } from './store/AuthContext'
import { useLayoutEffect } from 'react'
import LoadingOverlay from './util/LoadingSpinner'
import { useMemo } from 'react'
import AsyncStorage from '@react-native-async-storage/async-storage'
import SubjectTeacher from './util/SubjectTeacher'
import Classess from './util/Classess'

const TeacherHome = ({ navigation }) => {
  const [state, setState] = useState({});
  const [subjects, setSubjects] = useState([])
  const [isFetchin, setIsFetching] = useState(true);
  const authCtx = useContext(AuthContext)

if(subjects == null){
  return <LoadingOverlay/>
}
  useEffect(()=>{
    async function getsub(teacherid){
    try {
      const subjects = await fetchTeacherSubject(teacherid)
      setSubjects(subjects)
    } catch (error) {
      console.log(authCtx)
    }
  }
  getsub(authCtx.userInfo.id)
  setIsFetching(false)
  console.log(subjects)

  },[authCtx])

  if(isFetchin){
    return (
     <LoadingOverlay/>
    )
  }

  function NavTeacherPage(subject){
    authCtx.setSubject(subject)
    navigation.navigate("Classess")

  }

  return (


    <KeyboardAvoidingView behavior='height'
      style={{ backgroundColor: '#B4B4B4', flex: 1 }}>
      <View style={{ alignContent: "center", flexDirection: 'row', justifyContent: "center", marginTop: 5, padding: 5 }}>
        <Avatar rounded
          source={{
            uri:
              "https://cdn.pixabay.com/photo/2016/11/21/12/42/beard-1845166_1280.jpg",
          }} />
        <Text style={{ fontSize: 18, padding: 5 }}>{"Mr" + " " + authCtx.userInfo.lastname }</Text>
      </View>
      <Text style={styles.title}>Subjects Taught</Text>
      <View style={styles.items}>
        {subjects.map(subject => {
          return (
            <TouchableOpacity key={subject.id} style={styles.button} onPress={() => { NavTeacherPage(subject.id) }}>
              <Text style={styles.txt}>{subject.subjectname}</Text>
            </TouchableOpacity>
          )
        })}
        </View>
    </KeyboardAvoidingView>

  )
  
}


export default TeacherHome

const styles = StyleSheet.create({
  box: {
    marginLeft: 60
  },
  txt: {
    alignItems: "center",
    justifyContent: "center",
    textAlign: "center"
  }, items: {
    alignContent: "center",
    justifyContent: "center",
    marginLeft: 105,
    marginTop: 70

  },

  background: {
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 5

  },
  title: {
    fontSize: 22,
    textAlign: 'center',
    margin: 8
  }, button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    width: 220
  },
  button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    width: 200,
    margin: 5
  }

});