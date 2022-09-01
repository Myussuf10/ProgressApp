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

const TeacherHome = ({ navigation }) => {
  const [state, setState] = useState({});
  const [subjects, setSubjects] = useState([])
  const [isFetchin, setIsFetching] = useState(true);
  const authCtx = useContext(AuthContext)

    
  useEffect(() => {
    setIsFetching(true)
    let ismounted = true;
    async function getTeacher() {
      try{
        const response = await getTeacherByEmail(authCtx.email)
        setState(response);
        ;

      }catch(err){console.log(err)}

    }

    getTeacher() 
    console.log(state)

    return () =>{ismounted = false}

  },[authCtx])

  useEffect(()=>{
    async function getsub(){
    try {
      const subjects = await fetchTeacherSubject(state.id)
      setSubjects(subjects)
    } catch (error) {
      console.log(error)
    }
  }
  getsub()
  setIsFetching(false)

  },[state])
  

  if(isFetchin){
    return (
     <LoadingOverlay/>
    )
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
        <Text style={{ fontSize: 18, padding: 5 }}>{"Mr" + " " +state.firstname }</Text>
      </View>
      <Text style={styles.title}>Subjects Taught</Text>
      <View style={styles.items}>
        {subjects.map(subject => {
          return (
            <TouchableOpacity key={subject} style={styles.button} onPress={() => { navigation.navigate(TeachingPage) }}>
              <Text style={styles.txt}>{subject}</Text>
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