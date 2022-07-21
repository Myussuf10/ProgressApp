import { StyleSheet, Text, View, TouchableOpacity, KeyboardAvoidingView} from 'react-native'
import React from 'react'
import Input from './Input'
import Parent from '../Parent'
import { Button } from '@rneui/themed/dist/Button'
import { useState } from 'react'
import Subject from '../Subject'


const SignUp = () => {
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [school, setSchool] = useState("");
  const [dob, setDob] = useState("");

  const [subjects, setSub] = useState({Maths:false, English:false, Science:false});

  function Assign() {
    console.log(firstname);
    console.log(students);

  }
  const handleChange =(name, value)=>{
    setStudents(prevState=>{
      return {...prevState, firstname: value}
    })
  }
  

  return (
    <KeyboardAvoidingView behavior='padding' style={styles.screen}>
      <Text style={styles.header}>Signing Up</Text>
      <Input label="First Name" textInputConfig={{
        placeholder: 'First Name',
        onChangeText: (val) => { setFirstname(val)}
      }} />
      <Input label="Last Name" textInputConfig={{
        placeholder: 'Last Name', 
        onChangeText: (val) => { setStudents(x=>({...x, [students.lastname]: val}))}
      }} />

      <Input label="DOB  " textInputConfig={{
        keyboardType: 'number-pad',
        placeholder: 'DD/MM/YY',
        onChangeText: (val)=>{setStudents({...students, [students.dob]:new Date(val)})}
      }} />
      <Input label="School  " textInputConfig={{
        placeholder: 'School',
        onChangeText: val=>{setStudents({...students, [students.school]:val})}
      }} />
      <View style={styles.dropdown}>
        <Parent />
       
      </View>
      <Text style={styles.header}>Select Subjects</Text>

      <View style={styles.checkboxWrapper}>  
            
      <Subject setSub={setSub}/>
      <Subject setSub={setSub}/>

      </View>
      <TouchableOpacity
        title="Submit"
        onPress={() => {Assign()

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


export default SignUp;


const styles = StyleSheet.create({
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
    checkboxWrapper: {
      alignItems: 'center',
      flexDirection: 'row',
      paddingVertical: 5,
      flexWrap: 'wrap',
      padding: 15,
    },
  dropdown: {
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center'
  },
  screen: { flex: 1, backgroundColor: '#B4B4B4' },
    text:{
    margin: 8,
  }

})