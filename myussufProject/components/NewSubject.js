import { Text } from '@rneui/themed'
import React from 'react'
import { useContext } from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import { KeyboardAvoidingView, StyleSheet, View } from 'react-native'
import { TextInput, TouchableOpacity } from 'react-native-gesture-handler'
import SelectDropdown from 'react-native-select-dropdown'
import AddTeacher from './AddTeacher'
import Dropdown from './Forms/Dropdown'
import Input from './Forms/Input'
import { AuthContext } from './store/AuthContext'
import { addSubject, fetchTeacher } from './util/http'

function NewSubject({ navigation }) {
  const [subject, setSubject] = useState({ subjectname: ""})
  const [teachers, setTeacher] = useState()
  const allteacher = []
  const [teacherid, setTeacherId] = useState()
  const authCtx = useContext(AuthContext)

  useEffect(() => {
    async function getTeachers(token) {
      const x = await fetchTeacher(token).catch((err)=>{console.log(err)});
      setTeacher(x)
    }
    getTeachers(authCtx.token)
    console.log(teachers)
  }, [])

  for (var i in teachers) {
    allteacher.push(teachers[i].name);
  }



  function inputHandler(inputid, value) {
    setSubject((x) => {
      return {
        ...x, [inputid]: value,
      };
    })
  }

  function inputData(data) {
    
    setTeacherId(teachers.find(x => x.name == data).id,)
 
  }

  async function sendData(){
    const response = await addSubject(subject, teacherid, authCtx.token).catch((err)=>{console.log(err)});
    navigation.pop();
  }


  return (
    <KeyboardAvoidingView behavior='padding' style={styles.screen}>
      <Text style={styles.header}>Setting Up Subjects</Text>
      <TextInput label="Subject Name"
        placeholder='Subject Name'
        onChangeText={inputHandler.bind(this, 'subjectname')}

      />
      <View style={styles.inputContainer}>
        <SelectDropdown style={styles.box} data={allteacher} defaultButtonText="Select Teacher" onSelect={(x) => inputData(x)} />
        <TouchableOpacity
          title="AddTeacher"
          onPress={() => {
            navigation.navigate(AddTeacher)

          }}
          style={styles.parentbutton}>
          <Text style={{
            textAlign: 'center',
            fontSize: 20,
            color: "black",
          }}> Add Teacher </Text>
        </TouchableOpacity>
      </View>


      <TouchableOpacity
        title="Submit"
        onPress={() => {
          sendData();

        }}
        style={styles.button}>
        <Text style={{
          textAlign: 'center',
          fontSize: 20,
          color: "white",
        }}> Submit </Text>
      </TouchableOpacity>
    </KeyboardAvoidingView>
  )
}

export default NewSubject

const styles = StyleSheet.create({
  row: {
    alignItems: 'center',
    justifyContent: 'center',
    flexDirection: 'row',
    marginTop: 100

  },
  parentbutton:{
    backgroundColor: '#EFEFEF',
    padding: 12,
    width: 190
  },
  inputContainer: {
    marginHorizontal: 4,
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center'

  },
  box: {
    padding: 60
  },

  background: {
    alignItems: 'center',
    justifyContent: 'center',

  },
  txt: {
    textAlign: 'center',
    fontSize: 20,
    color: "white",
  },
  button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    width: 150,
    height: 60,
    margin: 5
  },
  header: {
    fontSize: 22,
    textAlign: 'center',
    margin: 8
  },
  button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
  },
})